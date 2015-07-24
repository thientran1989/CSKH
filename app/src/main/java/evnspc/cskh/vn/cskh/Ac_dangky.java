package evnspc.cskh.vn.cskh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.Tht_Screen;


import evnspc.cskh.vn.cskh.broadcast.Conect_server_sign_async;
import evnspc.cskh.vn.cskh.object.CallbackResult;
import evnspc.cskh.vn.cskh.object.Obj_khachhang;
import evnspc.cskh.vn.cskh.object.ObjectClient;
import evnspc.cskh.vn.cskh.utils.CONFIG_LINK;
import evnspc.cskh.vn.cskh.utils.DB_COMMAND;


public class Ac_dangky extends Activity {
	EditText edt_ma_khachhang,edt_matkhau,edt_xacnhan_matkhau;

    ProgressWheel prov;
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
                ObjectClient mOC = new ObjectClient();
                mOC.setCommand(DB_COMMAND.LENH_DANGKY);
                Obj_khachhang oKH = new Obj_khachhang();
                oKH.setMA_KHANG(edt_ma_khachhang.getText().toString());
                oKH.setPASSWORD(edt_matkhau.getText().toString());
                mOC.setoKH(oKH);
                new Conect_server_sign_async(Ac_dangky.this,my_url,mOC,prov).execute();
    		}
    	}
    	
    }


    public void to_intent_giathich(View v){
//    	Intent i = new Intent(Ac_dangky.this,Ac_giaithich.class);
//    	startActivity(i);
    }

    public void dangky_ok(CallbackResult mCB){
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
}
