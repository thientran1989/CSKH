package evnspc.cskh.vn.cskh.broadcast;

import com.thtsoftlib.function.Tht_Network;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import evnspc.cskh.vn.cskh.database.DBAdapter;
import evnspc.cskh.vn.cskh.object.Obj_khachhang;
import evnspc.cskh.vn.cskh.object.ObjectClient;
import evnspc.cskh.vn.cskh.utils.CONFIG_LINK;
import evnspc.cskh.vn.cskh.utils.DB_COMMAND;

public class AlarmReceiver extends BroadcastReceiver {
    Context context;
    String my_url="";
    Obj_khachhang oKH=null;
    DBAdapter mdb;

	@Override
	public void onReceive(Context context, Intent intent) {
        this.context = context;
        if (Tht_Network.isNetworkAvailable(context)) {
            try {
                mdb = new DBAdapter(context);
                mdb.open();
                oKH = mdb.get_khachhang();
            } catch (Exception e) {

            }
            my_url = CONFIG_LINK.mURL_server_CHECKNEW;
            try {
                if(oKH!=null){
                    ObjectClient mOC = new ObjectClient();
                    mOC.setCommand(DB_COMMAND.LENH_CHECKNEW);
                    mOC.setoKH(oKH);
                    new Conect_run(context,mdb,my_url,mOC,oKH).execute();
                }
            } catch (Exception e) {

            }
        }
    }
}