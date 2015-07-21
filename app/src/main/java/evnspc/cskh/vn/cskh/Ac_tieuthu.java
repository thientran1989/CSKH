package evnspc.cskh.vn.cskh;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import evnspc.cskh.vn.cskh.adapter.Adapter_HDON;
import evnspc.cskh.vn.cskh.database.DBAdapter;
import evnspc.cskh.vn.cskh.object.CallbackResult;
import evnspc.cskh.vn.cskh.object.Obj_hdonctiet;
import evnspc.cskh.vn.cskh.object.Obj_hoadon;
import evnspc.cskh.vn.cskh.object.Obj_khachhang;
import evnspc.cskh.vn.cskh.object.ObjectClient;
import evnspc.cskh.vn.cskh.utils.CONFIG_LINK;
import evnspc.cskh.vn.cskh.utils.DB_COMMAND;
import evnspc.cskh.vn.cskh.utils.Function;
import evnspc.cskh.vn.cskh.utils.M_READ_JSON;
import tht.library.crouton.Style;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.Tht_Screen;
import com.thtsoftlib.function.Thtcovert;


public class Ac_tieuthu extends Activity {
	List<Obj_hoadon> list_TT;
	DBAdapter mdb;
    Obj_khachhang oKH =null;
    ProgressWheel prov;

//    private ProgressDialog pDialog;
    CountDownTimer mcountdown;
    CallbackResult mCB=null;
    String my_url="";

    SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mReAdapter;
    Adapter_HDON mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView recList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tht_Screen.hide_title(this);
        setContentView(R.layout.activity_tieuthu);
        Intent i = new Intent();
        setResult(RESULT_OK, i);
        
        try {
            prov = (ProgressWheel) findViewById(R.id.progress_wheel);
        	TextView tv_title = (TextView)findViewById(R.id.tv_title_tieuthu);
//        	Bundle b = new Bundle();
//        	b = getIntent().getExtras();
//        	tv_title.setText(b.getString(Utils.PARAM_title));
		} catch (Exception e) {
			ThtShow.show_crouton_toast(this,
					getString(R.string.loi_khoi_tao_giao_dien), Style.ALERT);
		}
        try{
            mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
            recList = (RecyclerView) findViewById(R.id.cardList);
            recList.setHasFixedSize(true);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recList.setLayoutManager(llm);
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "loi giao dien :\n"+e.toString(), Toast.LENGTH_LONG).show();
        }
        try{
            oKH = (Obj_khachhang)getIntent().getSerializableExtra("KH");
        }catch(Exception e){
            ThtShow.show_toast(this, "Lỗi "+e.toString());
        }
        my_url = CONFIG_LINK.mURL_server;
        ObjectClient mOC = new ObjectClient();
        mOC.setCommand(DB_COMMAND.LENH_GETHDON);
        mOC.setoKH(oKH);
        new Conect_server_sign_async(mOC).execute();
//        try {
//			mdb = new DBAdapter(this);
//			mdb.open();
//			list_TT = mdb.get_list_tieuthu();
//			mAdapter_TT = new Adapter_tieuthu(list_TT, this);
//			lv_tieuthu.setAdapter(mAdapter_TT);
//		} catch (Exception e) {
//			ThtShow.show_crouton_toast(this,"loi mo database", Style.ALERT);
//		}
        
    }
    // gui du lieu
    class Conect_server_sign_async extends AsyncTask<String, String, String> {
        String KQSV = "Không kết nối được server";
        ObjectClient mOC;
        public Conect_server_sign_async(ObjectClient mOC){
            this.mOC =mOC;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            pDialog = new ProgressDialog(Ac_tieuthu.this);
//            pDialog.setIndeterminate(false);
//            pDialog.setCancelable(false);
//            pDialog.show();
            prov.setVisibility(View.VISIBLE);

            mcountdown = new CountDownTimer(CONFIG_LINK.READ_TIMEOUT,1000) {
                public void onTick(long millisUntilFinished) {
                }
                public void onFinish() {
                    prov.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "time out", Toast.LENGTH_LONG).show();
                }
            }.start();
        }

        protected String doInBackground(String... kq) {
            try {
                upload(my_url,mOC);
            } catch (Exception e) {
            }

            return null;

        }
        protected void onPostExecute(String result) {
//            pDialog.dismiss();
            prov.setVisibility(View.GONE);
            mcountdown.cancel();
            runOnUiThread(new Runnable() {
                public void run() {
                    if(mCB!=null){
                        if(mCB.getResultString().equals("OK")){
                            if(mCB.getCommand().equals(DB_COMMAND.LENH_GETHDON)){
                                List<Obj_hoadon> my_HD = new ArrayList<Obj_hoadon>();
                                try {
                                    my_HD =mCB.getList_HD();
                                    if(my_HD!=null){
                                        set_list(my_HD);
                                    }
                                }catch(Exception e){
                                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                                }
                            }else if(mCB.getCommand().equals(DB_COMMAND.LENH_GETHDONCTIET)){
                                List<Obj_hdonctiet> my_HDCT = new ArrayList<Obj_hdonctiet>();
                                try {
                                    my_HDCT =mCB.getList_HDCT();
                                    if (my_HDCT!=null){
                                        Obj_hoadon oHD = mOC.getoHD();
                                        String TT ="";
                                        for(Obj_hdonctiet oHDCT: my_HDCT ){
                                            TT=TT + Thtcovert.int_to_String(oHDCT.getDIEN_TTHU())
                                                    +" x "+Thtcovert.int_format_tien(oHDCT.getDON_GIA())
                                                    +" = "+Thtcovert.int_format_tien(oHDCT.getSO_TIEN())+"\n";
                                        }
                                        TT =TT+"\n"+getString(R.string.tongtien)+" "+Thtcovert.int_format_tien(oHD.getTONG_TIEN());
                                        show_chitiet(getString(R.string.chitiet)+" "+getString(R.string.ky)+" "+String.valueOf(oHD.getKY())+" - "+
                                                getString(R.string.thang)+" "+String.valueOf(oHD.getTHANG())
                                                +"/"+String.valueOf(oHD.getNAM()),TT);
                                    }
                                }catch(Exception e){
                                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                                }
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
    public void set_list(List<Obj_hoadon> list){
        mAdapter = new Adapter_HDON(Ac_tieuthu.this,list);
        recList.setAdapter(mAdapter);
    }
    public void get_chitiet(Obj_hoadon oHD){
        ObjectClient mOC = new ObjectClient();
        mOC.setCommand(DB_COMMAND.LENH_GETHDONCTIET);
        mOC.setoKH(oKH);
        mOC.setoHD(oHD);
        new Conect_server_sign_async(mOC).execute();
    }
    public void show_chitiet(String tilte,String thongtin){
        try {
            new MaterialDialog.Builder(this)
                    .title(tilte)
                    .content(thongtin)
                    .positiveText(R.string.dong)
//                .negativeText(R.string.sua)
                    .show();
        }catch(Exception e){

        }

    }

}
