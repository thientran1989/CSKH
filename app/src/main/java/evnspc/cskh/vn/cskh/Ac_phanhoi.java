package evnspc.cskh.vn.cskh;

import java.util.List;

import evnspc.cskh.vn.cskh.adapter.Adapter_phanhoi;
import evnspc.cskh.vn.cskh.database.DBAdapter;
import evnspc.cskh.vn.cskh.object.Obj_phanhoi;
import evnspc.cskh.vn.cskh.utils.Utils;
import tht.library.crouton.Style;

import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.Tht_Screen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class Ac_phanhoi extends Activity {
	ListView lv_tieuthu;
	Adapter_phanhoi mAdapter_TT;
	List<Obj_phanhoi> list_TT;
	Button btn_themphanhoi;
	DBAdapter mdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tht_Screen.hide_title(this);
        setContentView(R.layout.activity_phan_hoi);
        Intent it = new Intent();
        setResult(RESULT_OK, it);
        mdb = new DBAdapter(this);
        
        try {
        	TextView tv_title = (TextView)findViewById(R.id.tv_title_tieuthu);
        	lv_tieuthu = (ListView)findViewById(R.id.lv_tieuthu_activity_tieuthu);
        	Bundle b = new Bundle();
        	b = getIntent().getExtras();
        	tv_title.setText(b.getString(Utils.PARAM_title));
		} catch (Exception e) {
			ThtShow.show_crouton_toast(this,
					getString(R.string.loi_khoi_tao_giao_dien), Style.ALERT);
		}
        try {
        	list_TT = mdb.get_list_phanhoi();
        	if (list_TT!=null & list_TT.size()>0){
        		mAdapter_TT = new Adapter_phanhoi(list_TT, this);
    			lv_tieuthu.setAdapter(mAdapter_TT);
        	}
		} catch (Exception e) {
			ThtShow.show_crouton_toast(this,
					getString(R.string.loi_tao_danh_sach_tu_data), Style.ALERT);
		}
    }
    public void to_intent_phanhoi(View v){
    	Intent i = new Intent(Ac_phanhoi.this,Ac_phanhoi_gui.class);
    	startActivity(i);
    }

}
