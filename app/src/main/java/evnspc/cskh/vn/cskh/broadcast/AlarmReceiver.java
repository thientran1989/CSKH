package evnspc.cskh.vn.cskh.broadcast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thtsoftlib.function.Tht_Network;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

import evnspc.cskh.vn.cskh.R;
import evnspc.cskh.vn.cskh.activity.MainActivity;
import evnspc.cskh.vn.cskh.database.DBAdapter;
import evnspc.cskh.vn.cskh.object.CallbackResult;
import evnspc.cskh.vn.cskh.object.Obj_khachhang;
import evnspc.cskh.vn.cskh.object.ObjectClient;
import evnspc.cskh.vn.cskh.utils.CONFIG_LINK;
import evnspc.cskh.vn.cskh.utils.DB_COMMAND;
import evnspc.cskh.vn.cskh.utils.Function;
import evnspc.cskh.vn.cskh.utils.M_READ_JSON;
import evnspc.cskh.vn.cskh.utils.NotificationUtils;

public class AlarmReceiver extends BroadcastReceiver {
    Context context;
    String my_url="";
    Obj_khachhang oKH=null;
    DBAdapter mdb;
    CallbackResult mCB=null;

	@Override
	public void onReceive(Context context, Intent intent) {
        this.context = context;
        if (Tht_Network.isNetworkAvailable(context)) {
            try {
                mdb = new DBAdapter(context);
                mdb.open();
                oKH = mdb.get_khachhang();
            } catch (Exception e) {

            }
            my_url = CONFIG_LINK.mURL_server_CHECKNEW;
            try {
                ObjectClient mOC = new ObjectClient();
                mOC.setCommand(DB_COMMAND.LENH_CHECKNEW);
                mOC.setoKH(oKH);
                new Conect_server_sign_async( mOC).execute();
            } catch (Exception e) {

            }
        }
    }
    class Conect_server_sign_async extends AsyncTask<String, String, String> {
        String KQSV = "Không kết nối được server";
        ObjectClient mOC;
        CountDownTimer mcountdown;

        // kiem tra check new
        public Conect_server_sign_async( ObjectClient mOC) {
            this.mOC = mOC;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mcountdown = new CountDownTimer(CONFIG_LINK.READ_TIMEOUT, 1000) {
                public void onTick(long millisUntilFinished) {

                }
                public void onFinish() {
                    Toast.makeText(context.getApplicationContext(), "time out", Toast.LENGTH_LONG).show();
                }
            }.start();
        }

        protected String doInBackground(String... kq) {
            try {
                upload(my_url, mOC);
            } catch (Exception e) {
            }

            return null;

        }

        protected void onPostExecute(String result) {
            mcountdown.cancel();
                    if (mCB != null) {
                        if (mCB.getResultString().equals("OK")) {
                            if (mCB.getCommand().equals(DB_COMMAND.LENH_CHECKNEW)) {
                                try {
                                    check_new_ok(mCB);
                                } catch (Exception e) {

                                }
                            }
                        } else {
                            Toast.makeText(context.getApplicationContext(), mCB.getResultString(), Toast.LENGTH_LONG).show();
                        }
                    }
        }

        public void upload(String mURL, ObjectClient mOC) {
            KQSV = context.getString(R.string.alert_not_connect_server);
            HttpURLConnection connection = null;
            DataOutputStream outputStream = null;
            DataInputStream dis = null;
            try {
                URL url = new URL(mURL);
                URLConnection urlConn = url.openConnection();
                urlConn.setConnectTimeout(CONFIG_LINK.CONNECT_TIMEOUT);
                urlConn.setReadTimeout(CONFIG_LINK.READ_TIMEOUT);
                connection = (HttpURLConnection) urlConn;
                connection.setChunkedStreamingMode(0);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type",
                        "application/octet-stream");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setAllowUserInteraction(true);
                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);

                outputStream = new DataOutputStream(
                        connection.getOutputStream());

                KQSV = Function.alldata2server(mOC, null);
                Function.write_String_to_byte(outputStream, KQSV);
                outputStream.flush();
                dis = new DataInputStream(connection.getInputStream());
                KQSV = "";
                KQSV = Function.byte_to_String(dis);
                Log.i("KQSV", KQSV);
                outputStream.close();
                dis.close();
                JsonParser jp = new JsonParser();
                JsonObject mJO = jp.parse(KQSV).getAsJsonObject();
                mCB = M_READ_JSON.read_callback(mJO);
                if (mCB != null) {
                    try {
                        KQSV = mCB.getResultString();
                    } catch (Exception e) {
                        KQSV = "loi doc callback :" + e.toString();
                    }
                } else {
                    KQSV = "ko doc dc JSON";
                }
            } catch (Exception ex) {
                if (my_url.contains(CONFIG_LINK.IP_LOCAL)) {
                    KQSV = context.getString(R.string.alert_not_connect_server);
                } else {
                    my_url = my_url.replace(CONFIG_LINK.IP_SERVER, CONFIG_LINK.IP_LOCAL);
                    upload(my_url, mOC);
                }
            }

        }

        // check new
        public void check_new_ok(CallbackResult mCB) {
            try {
                NotificationUtils mNO = new NotificationUtils(context);
                Intent intent = new Intent(context, MainActivity.class);
                if (mCB != null) {
                    if (mCB.getResultString().equals("OK")) {
                        try {
                            intent.putExtra("KH", oKH);
                            intent.putExtra("lenh", DB_COMMAND.LENH_CHECKNEW);
                            mNO.showNotificationMessage(context.getString(R.string.app_name), KQSV, intent);
                        } catch (Exception e) {

                        }
                    }
                }
            } catch (Exception e) {

            }

        }
    }
}