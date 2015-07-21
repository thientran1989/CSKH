package evnspc.cskh.vn.cskh;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.Tht_Screen;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import evnspc.cskh.vn.cskh.database.DBAdapter;
import evnspc.cskh.vn.cskh.object.CallbackResult;
import evnspc.cskh.vn.cskh.object.Obj_khachhang;
import evnspc.cskh.vn.cskh.object.ObjectClient;
import evnspc.cskh.vn.cskh.utils.CONFIG_LINK;
import evnspc.cskh.vn.cskh.utils.DB_COMMAND;
import evnspc.cskh.vn.cskh.utils.Function;
import evnspc.cskh.vn.cskh.utils.M_READ_JSON;
import evnspc.cskh.vn.cskh.utils.Utils;


public class Ac_dangky extends Activity {
	EditText edt_ma_khachhang,edt_matkhau,edt_xacnhan_matkhau;

//    private ProgressDialog pDialog;
    ProgressWheel prov;
    CountDownTimer mcountdown;
    CallbackResult mCB=null;
    public static String MAKH_DK="";
    String my_url="";
    ObjectClient mOC=null;
    String KQSV ="null";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tht_Screen.hide_title(this);
        Tht_Screen.hide_keyboard(this);
        setContentView(R.layout.activity_dangky);
        
        edt_ma_khachhang = (EditText)findViewById(R.id.edt_makhachhang_dangky);
        edt_matkhau = (EditText)findViewById(R.id.edt_matkhau_dangky);
        edt_xacnhan_matkhau = (EditText)findViewById(R.id.edt_xac_nhan_matkhau_dangky);
        prov = (ProgressWheel) findViewById(R.id.progress_wheel);
        
        edt_ma_khachhang.setText("PB11010001148");
        mOC = new ObjectClient();

    }
    public void to_intent_main(View v){
    	String MA_KH=edt_ma_khachhang.getText().toString();
    	String MAT_KHAU = edt_matkhau.getText().toString();
    	String XN_MAT_KHAU = edt_xacnhan_matkhau.getText().toString();
    	
    	if (MA_KH.length()==0 || MAT_KHAU.length()==0 || XN_MAT_KHAU.length()==0){
    		ThtShow.show_toast(Ac_dangky.this, "Chưa nhập đủ thông tin !");
    	}else{
    		if (!MAT_KHAU.equals(XN_MAT_KHAU)){
    			ThtShow.show_toast(Ac_dangky.this, "Xác nhận mật khẩu không đúng !");
    			edt_xacnhan_matkhau.setSelection(edt_xacnhan_matkhau.length());
    			edt_xacnhan_matkhau.requestFocus();
    		}else{
                my_url = CONFIG_LINK.mURL_server;
                new Conect_server_sign_async().execute();
    		}
    	}
    	
    }


    public void to_intent_giathich(View v){
//    	Intent i = new Intent(Ac_dangky.this,Ac_giaithich.class);
//    	startActivity(i);
    }
    // gui du lieu
    class Conect_server_sign_async extends AsyncTask<String, String, String> {
        String KQSV = "Không kết nối được server";
        ObjectClient mOC;

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
                ObjectClient mOC = new ObjectClient();
                mOC.setCommand(DB_COMMAND.LENH_DANGKY);
                Obj_khachhang oKH = new Obj_khachhang();
                oKH.setMA_KHANG(edt_ma_khachhang.getText().toString());
                oKH.setPASSWORD(edt_matkhau.getText().toString());
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
                                MAKH_DK=edt_ma_khachhang.getText().toString();
                                Intent i = new Intent();
                                setResult(RESULT_OK);
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
                        KQSV = "loi doc callback :" + e.toString();
                    }
                } else {
                    KQSV = "ko doc dc JSON";
                }
            } catch (Exception ex) {
                if (my_url.equals(CONFIG_LINK.mURL)) {
                    KQSV = getString(R.string.alert_not_connect_server);
                } else {
                    my_url = CONFIG_LINK.mURL;
                    upload(my_url, mOC);
                }

            }

        }

    }
}
