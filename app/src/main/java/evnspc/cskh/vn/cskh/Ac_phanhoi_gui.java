package evnspc.cskh.vn.cskh;

import java.util.Calendar;

import evnspc.cskh.vn.cskh.database.DBAdapter;
import evnspc.cskh.vn.cskh.object.Obj_phanhoi;
import evnspc.cskh.vn.cskh.utils.Utils;
import tht.library.crouton.Style;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.Tht_Network;
import com.thtsoftlib.function.Tht_Screen;

public class Ac_phanhoi_gui extends Activity {
	EditText edt_phanhoi;
	Button btn_gui;
	DBAdapter mdb;
	Obj_phanhoi mPH = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Tht_Screen.hide_title(this);
		setContentView(R.layout.activity_phan_hoi_gui);

		try {
			mdb = new DBAdapter(this);
			mdb.open();
			edt_phanhoi = (EditText) findViewById(R.id.edt_phanhoi_ac_phanhoi_gui);
			btn_gui = (Button) findViewById(R.id.btn_guiphanhoi_ac_phanhoi_gui);
		} catch (Exception e) {
			ThtShow.show_crouton_toast(this,
					getString(R.string.loi_khoi_tao_giao_dien), Style.ALERT);
		}
	}

	public void gui_phan_hoi(View v) {
		if (edt_phanhoi.getText().length() > 0) {
			if (Tht_Network.isNetworkAvailable(this)) {
//				Obj_device mDV = mdb.get_device();
//				if (mDV != null) {
//					mDV.setLENH(Utils.REQ_GUI_PHAN_HOI);
//					tao_phan_hoi();
//					mPH.setMa_khachhang(mDV.getMa_khachhang());
//	            	new Conect_server_sign_async(Ac_phanhoi_gui.this,mDV,mPH).execute();
//				}
			} else {
				ThtShow.show_crouton_toast(this, "mang ko kha dung",
						Style.ALERT);
			}
		} else {
			ThtShow.show_crouton_toast(this, "chua nhap phan hoi", Style.ALERT);
		}
	}
	public void tao_phan_hoi(){
		Calendar c = Calendar.getInstance(); 
		mPH = new Obj_phanhoi();
		mPH.setLoai_phanhoi(1);
		mPH.setNam(c.get(Calendar.YEAR));
		mPH.setThang(c.get(Calendar.MONTH)+1);
		mPH.setNgay(c.get(Calendar.DAY_OF_MONTH));
		mPH.setNoidung_phanhoi(edt_phanhoi.getText().toString());
		mPH.setTt_phanhoi(0);
	}

}
