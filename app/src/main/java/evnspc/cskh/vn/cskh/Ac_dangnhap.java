package evnspc.cskh.vn.cskh;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.Tht_Network;
import com.thtsoftlib.function.Tht_Screen;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;

import evnspc.cskh.vn.cskh.activity.*;
import evnspc.cskh.vn.cskh.broadcast.AlarmReceiver;
import evnspc.cskh.vn.cskh.broadcast.BroadcastManager;
import evnspc.cskh.vn.cskh.database.DBAdapter;
import evnspc.cskh.vn.cskh.object.CallbackResult;
import evnspc.cskh.vn.cskh.object.Obj_khachhang;
import evnspc.cskh.vn.cskh.object.ObjectClient;
import evnspc.cskh.vn.cskh.utils.CONFIG_LINK;
import evnspc.cskh.vn.cskh.utils.DB_COMMAND;
import evnspc.cskh.vn.cskh.utils.Function;
import evnspc.cskh.vn.cskh.utils.M_READ_JSON;
import evnspc.cskh.vn.cskh.utils.REQ_CODE;

public class Ac_dangnhap extends Activity {
	String KQSV = "not sent";
	EditText edt_MA_KH, edt_MATKHAU;
	DBAdapter mdb;
	ProgressWheel prov;
	Button btn_dangnhap;
    CountDownTimer mcountdown;
    CallbackResult mCB=null;
    String my_url="";
    private PendingIntent pendingIntent;
    ObjectClient mOC = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Tht_Screen.hide_title(this);
		Tht_Screen.hide_keyboard(this);
		setContentView(R.layout.activity_dangnhap);

        // service WifiManager
        final WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        on();
        // alarm
		 Intent alarmIntent = new Intent(Ac_dangnhap.this, AlarmReceiver.class);
		 pendingIntent = PendingIntent.getBroadcast(Ac_dangnhap.this, 0, alarmIntent, 0);
		 startAt10();

		mdb = new DBAdapter(this);
		mdb.open();
		edt_MA_KH = (EditText) findViewById(R.id.edt_tendangnhap_dangnhap);
		edt_MATKHAU = (EditText) findViewById(R.id.edt_matkhau_dangnhap);
		edt_MA_KH.setText("PB11010018423");
		prov = (ProgressWheel) findViewById(R.id.progress_wheel);
		btn_dangnhap = (Button) findViewById(R.id.btn_dangnhap);

		if (mdb.get_soluong_khachhang() > 0) {
            try {
                Obj_khachhang oKH = mdb.get_khachhang();
                Intent i = new Intent(Ac_dangnhap.this, evnspc.cskh.vn.cskh.activity.MainActivity.class);
                i.putExtra("KH",oKH);
                i.putExtra("lenh","start");
                startActivity(i);
                finish();
            }catch (Exception e){
                ThtShow.show_toast(this, "Loi lay khach hang \n"+e.toString());
            }

		}
	}

	// mo class
	public void to_intent_main(View v) {
		if (Tht_Network.isNetworkAvailable(this)) {
            my_url = CONFIG_LINK.mURL_server;
            new Conect_server_sign_async().execute();
		} else {
			ThtShow.show_toast(this, "Mạng không khả dụng !");
		}

	}

	public void to_intent_dangky(View v) {
		Intent i = new Intent(Ac_dangnhap.this, Ac_dangky.class);
		startActivityForResult(i, REQ_CODE.REQ_DANGKY);
	}
    // gui du lieu
    class Conect_server_sign_async extends AsyncTask<String, String, String> {
        String KQSV = "Không kết nối được server";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            prov.setVisibility(View.VISIBLE);

            mcountdown = new CountDownTimer(CONFIG_LINK.READ_TIMEOUT, 1000) {
                public void onTick(long millisUntilFinished) {
//                    pDialog.setMessage(" Đang kiểm tra...");
                }
                public void onFinish() {
                    prov.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "time out", Toast.LENGTH_LONG).show();
                }
            }.start();
        }

        protected String doInBackground(String... kq) {
            try {
                mOC = new ObjectClient();
                mOC.setCommand(DB_COMMAND.LENH_DANGNHAP);
                Obj_khachhang oKH = new Obj_khachhang();
                oKH.setMA_KHANG(edt_MA_KH.getText().toString());
                oKH.setPASSWORD(edt_MATKHAU.getText().toString());
                mOC.setoKH(oKH);
                upload(my_url,mOC);
            } catch (Exception e) {
            }

            return null;

        }

        protected void onPostExecute(String result) {
            prov.setVisibility(View.GONE);
            mcountdown.cancel();
            runOnUiThread(new Runnable() {
                public void run() {
                    if(mCB!=null){
                        if(mCB.getResultString().equals("OK")){
                            try {
                                Obj_khachhang oKH = mCB.getoKH();
                                oKH.setPASSWORD(edt_MATKHAU.getText().toString());
                                Toast.makeText(getApplicationContext(),mCB.getDiengiai1(),Toast.LENGTH_LONG).show();
                                DBAdapter.Insert_khachhang(oKH);
                                Intent i = new Intent(Ac_dangnhap.this, evnspc.cskh.vn.cskh.activity.MainActivity.class);
                                i.putExtra("KH",mOC.getoKH());
                                i.putExtra("lenh","login");
                                startActivity(i);
                                finish();
                            }catch(Exception e){
                                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),mCB.getResultString(),Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),KQSV,Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

        public void upload(String mURL,ObjectClient mOC) {
            KQSV = getString(R.string.alert_not_connect_server);
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
//                TAG_KQ="mJO :"+mJO.toString();
                mCB = M_READ_JSON.read_callback(mJO);
                if (mCB != null) {
                    try {
                        KQSV = mCB.getResultString();
                    } catch (Exception e) {
                        KQSV = "loi doc callback :"+e.toString();
                    }
                } else {
                    KQSV = "ko doc dc JSON";
                }
            } catch (Exception ex) {
                if(my_url.equals(CONFIG_LINK.mURL)){
                    KQSV = getString(R.string.alert_not_connect_server);
                }else{
                    my_url = CONFIG_LINK.mURL;
                    upload(my_url,mOC);
                }

            }

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode==REQ_CODE.REQ_DANGKY){
           if(resultCode==RESULT_OK){
                edt_MA_KH.setText(Ac_dangky.MAKH_DK);
                edt_MATKHAU.requestFocus();
                Toast.makeText(getApplicationContext(),"Đăng ký thành công, Vui lòng nhập lại mật khẩu lần nữa để đăng nhập !",Toast.LENGTH_LONG).show();
           }
       }
    }
    public void on (){
        try {
            PackageManager pm = Ac_dangnhap.this.getPackageManager();
            ComponentName componentName = new ComponentName(
                    Ac_dangnhap.this, BroadcastManager.class);
            pm.setComponentEnabledSetting(componentName,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);
        }catch(Exception e){
            Toast.makeText(this, "on "+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    // alarm
    public void start() {
        try {
            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            int interval = 8000;
            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        }catch(Exception e){
            Toast.makeText(this, "start "+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void cancel() {
        try {
            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            manager.cancel(pendingIntent);
            Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(this, "cancel "+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void startAt10() {
        try {
            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            int interval = 1000 * 60 * 1;

	        /* Set the alarm to start at 10:30 AM */
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, 10);
            calendar.set(Calendar.MINUTE, 24);

	        /* Repeating on every 20 minutes interval */
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    1000 * 60 * 1, pendingIntent);
        }catch(Exception e){
            Toast.makeText(this, "error  startAt10 "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}
