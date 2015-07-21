package evnspc.cskh.vn.cskh;

import com.thtsoftlib.function.Tht_Screen;
import android.app.Activity;
import android.os.Bundle;


public class Ac_giaithich extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tht_Screen.hide_title(this);
        setContentView(R.layout.activity_giaithich);
        
    }

}
