package evnspc.cskh.vn.cskh;

import evnspc.cskh.vn.cskh.database.DBAdapter;
import evnspc.cskh.vn.cskh.object.Obj_khachhang;
import evnspc.cskh.vn.cskh.utils.Utils;
import tht.library.crouton.Style;

import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.Tht_Screen;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class Ac_thongtin extends Activity {
	
	TextView tv_makhachhang,tv_tenkhachhang,tv_diachi;
	DBAdapter mdb;
    Obj_khachhang oKH =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tht_Screen.hide_title(this);
        setContentView(R.layout.activity_thongtin);
        
        try {
        	TextView tv_title = (TextView)findViewById(R.id.tv_title_thongtin);
        	tv_makhachhang = (TextView)findViewById(R.id.tv_makhachhang_ac_thongtin);
        	tv_tenkhachhang = (TextView)findViewById(R.id.tv_tenkhachhang_ac_thongtin);
        	tv_diachi = (TextView)findViewById(R.id.tv_diachi_ac_thongtin);
        	
        	Bundle b = new Bundle();
        	b = getIntent().getExtras();
        	tv_title.setText(b.getString(Utils.PARAM_title));
            try{
                oKH = (Obj_khachhang)getIntent().getSerializableExtra("KH");
            }catch(Exception e){
                ThtShow.show_toast(this, "Lỗi "+e.toString());
            }
		} catch (Exception e) {
			ThtShow.show_crouton_toast(this,
					getString(R.string.loi_khoi_tao_giao_dien), Style.ALERT);
		}
        try {
			mdb = new DBAdapter(this);
			mdb.open();
//			Obj_khachhang kH = mdb.get_khachhang();
			if (oKH!=null){
				tv_makhachhang.setText(oKH.getMA_KHANG());
	        	tv_tenkhachhang.setText(oKH.getTEN_KHANG());
	        	tv_diachi.setText(oKH.getDCHI_HDON());
			}
		} catch (Exception e) {
			ThtShow.show_crouton_toast(this,"loi mo database", Style.ALERT);
		}
    }

}
