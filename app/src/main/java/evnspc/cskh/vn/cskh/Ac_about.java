package evnspc.cskh.vn.cskh;

import java.util.ArrayList;
import java.util.List;

import evnspc.cskh.vn.cskh.object.Obj_hoadon;
import evnspc.cskh.vn.cskh.utils.Utils;
import tht.library.crouton.Style;

import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.Tht_Screen;
import com.thtsoftlib.function.Thtcovert;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;


public class Ac_about extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tht_Screen.hide_title(this);
        setContentView(R.layout.activity_about);
        
        try {
        	TextView tv_title = (TextView)findViewById(R.id.tv_title_tieuthu);
        	Bundle b = new Bundle();
        	b = getIntent().getExtras();
        	tv_title.setText(b.getString(Utils.PARAM_title));
		} catch (Exception e) {
			ThtShow.show_crouton_toast(this,
					getString(R.string.loi_khoi_tao_giao_dien), Style.ALERT);
		}
        try {

		} catch (Exception e) {
			ThtShow.show_crouton_toast(this,
					getString(R.string.loi_tao_danh_sach_tu_data), Style.ALERT);
		}
    }

}
