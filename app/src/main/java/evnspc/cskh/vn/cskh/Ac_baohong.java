package evnspc.cskh.vn.cskh;
import evnspc.cskh.vn.cskh.utils.Utils;
import tht.library.crouton.Style;

import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.Tht_Screen;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;


public class Ac_baohong extends Activity {
	TextView tv_title ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tht_Screen.hide_title(this);
        setContentView(R.layout.activity_baohong);
        
        try {
			tv_title = (TextView)findViewById(R.id.tv_title_baohong);
			Bundle b = new Bundle();
        	b = getIntent().getExtras();
        	tv_title.setText(b.getString(Utils.PARAM_title));
		} catch (Exception e) {
			ThtShow.show_crouton_toast(this,
					getString(R.string.loi_khoi_tao_giao_dien), Style.ALERT);
		}
    }
    public void make_call(View v){
    	try {
    		if (((TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE)).getPhoneType()
    			    == TelephonyManager.PHONE_TYPE_NONE)
    			{
    			    // no phone
    			ThtShow.show_crouton_toast(this,getString(R.string.loi_khong_ho_tro_goi), Style.ALERT);
    			}else{
    				try {
//                		ThtDevive.prepair_call(Ac_baohong.this, getString(R.string.author_phone)
//                				.replace("(","").replace(")", "").trim());
            		} catch (Exception e) {
            			ThtShow.show_crouton_toast(this,getString(R.string.loi_khong_the_thuc_hien_goi), Style.ALERT);
            		}
    			}
		} catch (Exception e) {
			ThtShow.show_crouton_toast(this,getString(R.string.loi_da_xay_ra_khi_thuc_hien_chuc_nang_nay), Style.ALERT);
		}
    	
    	
    }
    public void to_intent_bando(View v){
//    	Intent i = new Intent(this,Ac_bando.class);
//    	startActivity(i);
    }
}
