package evnspc.cskh.vn.cskh.broadcast;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.Thtcovert;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import evnspc.cskh.vn.cskh.R;
import evnspc.cskh.vn.cskh.activity.MainActivity;
import evnspc.cskh.vn.cskh.adapter.Adapter_HDON;
import evnspc.cskh.vn.cskh.adapter.Adapter_SANLUONG;
import evnspc.cskh.vn.cskh.database.DBAdapter;
import evnspc.cskh.vn.cskh.object.CallbackResult;
import evnspc.cskh.vn.cskh.object.Obj_hdonctiet;
import evnspc.cskh.vn.cskh.object.Obj_hoadon;
import evnspc.cskh.vn.cskh.object.Obj_sanluong;
import evnspc.cskh.vn.cskh.object.ObjectClient;
import evnspc.cskh.vn.cskh.utils.CONFIG_LINK;
import evnspc.cskh.vn.cskh.utils.DB_COMMAND;
import evnspc.cskh.vn.cskh.utils.Function;
import evnspc.cskh.vn.cskh.utils.M_READ_JSON;
import evnspc.cskh.vn.cskh.utils.NotificationUtils;

public class Conect_server_sign_async extends AsyncTask<String, String, String> {
    String KQSV = "Không kết nối được server";
    Context mCon=null;
    ObjectClient mOC;
    CountDownTimer mcountdown;
    CallbackResult mCB=null;
    String my_url="";
    View prov =null;
    DBAdapter mdb;
    RecyclerView recList;
    // lay hoa don
    Adapter_HDON mAdapter_hoadon;
    Adapter_SANLUONG mAdapter_sanluong;

    // lay hoa don
    public Conect_server_sign_async(Context mCon,String my_url,ObjectClient mOC,View prov, DBAdapter mdb,RecyclerView recList,Adapter_HDON mAdapter){
        this.mCon = mCon;
        this.my_url = my_url;
        this.mOC =mOC;
        this.prov = prov;
        this.mdb = mdb;
        this.recList = recList;
        this.mAdapter_hoadon = mAdapter;
    }

    public Conect_server_sign_async(Context mCon,String my_url,ObjectClient mOC,View prov, DBAdapter mdb,RecyclerView recList,Adapter_SANLUONG mAdapter){
        this.mCon = mCon;
        this.my_url = my_url;
        this.mOC =mOC;
        this.prov = prov;
        this.mdb = mdb;
        this.recList = recList;
        this.mAdapter_sanluong = mAdapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(prov!=null){
            prov.setVisibility(View.VISIBLE);
        }
        mcountdown = new CountDownTimer(CONFIG_LINK.READ_TIMEOUT,1000) {
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                if(prov!=null){
                    prov.setVisibility(View.GONE);
                }
                Toast.makeText(mCon.getApplicationContext(), "time out", Toast.LENGTH_LONG).show();
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
        mcountdown.cancel();
        ((Activity)mCon).runOnUiThread(new Runnable() {
            public void run() {
                if(mCB!=null){
                    if(mCB.getResultString().equals("OK")){
                        if(prov!=null){
                            prov.setVisibility(View.GONE);
                        }
                        if(mCB.getCommand().equals(DB_COMMAND.LENH_GETHDON)){
                            load_hoadon_ok(mCB);
                        }else  if(mCB.getCommand().equals(DB_COMMAND.LENH_GETSANLUONG)){
                            load_sanluong_ok(mCB);
                        }else  if(mCB.getCommand().equals(DB_COMMAND.LENH_CHECKNEW)){
                            try{
//                                check_new_ok(mCB);
                            }catch (Exception e){

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
//                                    TT =TT+"\n"+mCon.getString(R.string.tongtien)+" "+Thtcovert.int_format_tien(oHD.getTONG_TIEN());
//                                    show_chitiet(mCon.getString(R.string.chitiet)+" "+mCon.getString(R.string.ky)+" "+String.valueOf(oHD.getKY())+" - "+
//                                            mCon.getString(R.string.thang)+" "+String.valueOf(oHD.getTHANG())
//                                            +"/"+String.valueOf(oHD.getNAM()),TT);
                                }
                            }catch(Exception e){
                                Toast.makeText(mCon.getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                            }
                        }
                    }else{
                        Toast.makeText(mCon.getApplicationContext(),mCB.getResultString(),Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(mCon.getApplicationContext(),KQSV,Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void upload(String mURL,ObjectClient mOC) {
        KQSV = mCon.getString(R.string.alert_not_connect_server);
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
            if(my_url.contains(CONFIG_LINK.IP_LOCAL)){
                KQSV = mCon.getString(R.string.alert_not_connect_server);
            }else{
                my_url = my_url.replace(CONFIG_LINK.IP_SERVER,CONFIG_LINK.IP_LOCAL);
                upload(my_url,mOC);
            }
        }

    }
    // load hoa don thanh cong
    public void load_hoadon_ok(CallbackResult mCB){
        if(mCB!=null) {
            if (mCB.getResultString().equals("OK")) {
                List<Obj_hoadon> my_HD = new ArrayList<Obj_hoadon>();
                try {
                    my_HD = mCB.getList_HD();
                    if (my_HD != null) {
                        if (mdb != null) {
                            for (Obj_hoadon OHD : my_HD) {
                                mdb.insert_hoadon(OHD);
                            }
                        } else {
                            Toast.makeText(mCon, "mdb null", Toast.LENGTH_LONG).show();
                        }
                        set_list_hoadon(my_HD);
                    }
                } catch (Exception e) {
                    Toast.makeText(mCon.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }}
    public void set_list_hoadon(List<Obj_hoadon> list){
        mAdapter_hoadon = new Adapter_HDON(mCon,list);
        recList.setAdapter(mAdapter_hoadon);
    }

    // load san luong thanh cong
    public void load_sanluong_ok(CallbackResult mCB){
        if(mCB!=null) {
            if (mCB.getResultString().equals("OK")) {
                List<Obj_sanluong> my_HD = new ArrayList<Obj_sanluong>();
                try {
                    my_HD = mCB.getList_oSL();
                    if (my_HD != null) {
//                        if (mdb != null) {
//                            for (Obj_hoadon OHD : my_HD) {
//                                mdb.insert_hoadon(OHD);
//                            }
//                        } else {
//                            Toast.makeText(mCon, "mdb null", Toast.LENGTH_LONG).show();
//                        }
                        set_list_sanluong(my_HD);
                    }
                } catch (Exception e) {
                    Toast.makeText(mCon.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }}
    public void set_list_sanluong(List<Obj_sanluong> list){
        mAdapter_sanluong = new Adapter_SANLUONG(mCon,list);
        recList.setAdapter(mAdapter_sanluong);
    }


}
