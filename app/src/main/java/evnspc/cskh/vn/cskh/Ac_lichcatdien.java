package evnspc.cskh.vn.cskh;

import java.util.List;

import evnspc.cskh.vn.cskh.adapter.Adapter_lichcatdien;
import evnspc.cskh.vn.cskh.database.DBAdapter;
import evnspc.cskh.vn.cskh.object.Obj_lichcatdien;
import evnspc.cskh.vn.cskh.utils.Utils;
import tht.library.crouton.Style;

import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.Tht_Screen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;


public class Ac_lichcatdien extends Activity {
	ListView lv_lichcatdien;
	Adapter_lichcatdien mAdapter;
	List<Obj_lichcatdien> list_lichcatdien;
	DBAdapter mdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tht_Screen.hide_title(this);
        setContentView(R.layout.activity_lichcatdien);
        Intent i = new Intent();
        setResult(RESULT_OK, i);
        mdb = new DBAdapter(this);
        mdb.open();
        
        try {
        	TextView tv_title = (TextView)findViewById(R.id.tv_title_lichcatdien);
        	Bundle b = new Bundle();
        	b = getIntent().getExtras();
        	tv_title.setText(b.getString(Utils.PARAM_title));
        	lv_lichcatdien = (ListView)findViewById(R.id.lv_lichcatdien);
		} catch (Exception e) {
			ThtShow.show_crouton_toast(this,
					getString(R.string.loi_khoi_tao_giao_dien), Style.ALERT);
		}
        try {
			mdb = new DBAdapter(this);
			mdb.open();
			list_lichcatdien = mdb.get_list_lichcatdien();
			mAdapter = new Adapter_lichcatdien(list_lichcatdien, this);
			lv_lichcatdien.setAdapter(mAdapter);
		} catch (Exception e) {
			ThtShow.show_crouton_toast(this,"loi mo database", Style.ALERT);
		}
    }
   
}
