package evnspc.cskh.vn.cskh;
import evnspc.cskh.vn.cskh.database.DBAdapter;
import evnspc.cskh.vn.cskh.object.Obj_khachhang;
import evnspc.cskh.vn.cskh.utils.Utils;
import tht.library.crouton.Style;

import com.afollestad.materialdialogs.MaterialDialog;
import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.Tht_Network;
import com.thtsoftlib.function.Tht_Screen;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {

//    private CharSequence mTitle;
	DBAdapter mdb;
//	ProgressWheel mprogress_wheel;
	boolean mLOCK = false;
    Obj_khachhang oKH =null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tht_Screen.hide_title(this);
        setContentView(R.layout.activity_main_save);
        mdb = new DBAdapter(this);
        try{
            oKH = (Obj_khachhang)getIntent().getSerializableExtra("KH");
        }catch(Exception e){
            ThtShow.show_toast(this, "Lỗi "+e.toString());
        }
//        mprogress_wheel = (ProgressWheel)findViewById(R.id.progress_wheel);
//        mdb = new DBAdapter(this);
//        mdb.open();
//        try {
//			mDV = mdb.get_device();
//		} catch (Exception e) {
//
//		}

//        show_notification(getString(R.string.alert_catdien));
//        show_notification(getString(R.string.alert_tieuthu));

    }

  public void show_tb(){
	  show_notification(getString(R.string.alert_catdien));
  }

    // mo class
    public void to_intent_thongtin(View v){
    	Intent i = new Intent(MainActivity.this,Ac_thongtin.class);
    	Bundle b = new Bundle();
    	b.putString(Utils.PARAM_title, getString(R.string.label_thong_tin));
    	i.putExtras(b);
        i.putExtra("KH",oKH);
    	startActivity(i);
    }
    public void to_intent_phanhoi(View v){
//    	if (Tht_Network.isNetworkAvailable(this)){
//    		if (mDV!=null){
//        		mDV.setLENH(Utils.REQ_GET_PHANHOI);
//        		get_data(mDV);
//        	}
//    	}else{
//    		ThtShow.show_toast(this, "Mạng không khả dụng !");
//    		Intent i = new Intent(this,Ac_phanhoi.class);
//			Bundle b = new Bundle();
//			b.putString(Utils.PARAM_title,getString(R.string.label_phan_hoi));
//			i.putExtras(b);
//			startActivityForResult(i, Utils.REQ_LOADED);
//    	}
    }
    public void to_intent_tieuthu(View v){
    	if (Tht_Network.isNetworkAvailable(this)){
            Intent i = new Intent(MainActivity.this,Ac_tieuthu.class);
            i.putExtra("KH",oKH);
            startActivity(i);
    	}else{
    		ThtShow.show_toast(this, "Mạng không khả dụng !");
//    		Intent i = new Intent(this,Ac_tieuthu.class);
//			Bundle b = new Bundle();
//			b.putString(Utils.PARAM_title,getString(R.string.label_tieu_thu));
//			i.putExtras(b);
//			startActivityForResult(i, Utils.REQ_LOADED);
    	}
    }
    public void to_intent_lichcatdien(View v){
//    	if (Tht_Network.isNetworkAvailable(this)){
//    		if (mDV!=null){
//        		mDV.setLENH(Utils.REQ_GET_LICHCATDIEN);
//        		get_data(mDV);
//        	}
//    	}else{
//    		ThtShow.show_toast(this, "Mạng không khả dụng !");
//    		Intent i = new Intent(this,Ac_lichcatdien.class);
//			Bundle b = new Bundle();
//			b.putString(Utils.PARAM_title,getString(R.string.label_lich_cat_dien));
//			i.putExtras(b);
//			startActivityForResult(i, Utils.REQ_LOADED);
//    	}
    }
    public void to_intent_baohong(View v){
    	Intent i = new Intent(MainActivity.this,Ac_baohong.class);
    	Bundle b = new Bundle();
    	b.putString(Utils.PARAM_title, getString(R.string.label_bao_hong));
    	i.putExtras(b);
    	startActivity(i);
    }
    public void to_intent_about(View v){
    	Intent i = new Intent(MainActivity.this,Ac_about.class);
    	Bundle b = new Bundle();
    	b.putString(Utils.PARAM_title, getString(R.string.label_about));
    	i.putExtras(b);
    	startActivity(i);
    }
    public void to_intent_nhapchiso(View v){
    	Intent i = new Intent(MainActivity.this,Ac_nhapchiso.class);
    	Bundle b = new Bundle();
    	b.putString(Utils.PARAM_title, getString(R.string.label_nhap_chi_so));
    	i.putExtras(b);
    	startActivity(i);
    }
    // notification
    public void show_notification (String tb){
    	int NOTIFICATION_ID = 1;
    	String ns = Context.NOTIFICATION_SERVICE;
    	NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
    	int icon = R.drawable.evn;
    	long when = System.currentTimeMillis();
    	try {
//			Notification notification = new Notification(icon, getString(R.string.app_name), when);
//
//        	RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.custom_notification);
//        	contentView.setImageViewResource(R.id.notification_image, R.drawable.evn);
//        	contentView.setTextViewText(R.id.notification_title,getString(R.string.title_notification));
//        	contentView.setTextViewText(R.id.notification_text,tb);
//        	notification.contentView = contentView;
//
//        	Intent notificationIntent = new Intent(this, Ac_tieuthu.class);
//        	PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
//
//        	notification.contentIntent = contentIntent;
//
////        	notification.flags |= Notification.FLAG_NO_CLEAR; //Do not clear the notification
//        	notification.defaults |= Notification.DEFAULT_LIGHTS; // LED
//        	notification.defaults |= Notification.DEFAULT_VIBRATE; //Vibration
//        	notification.defaults |= Notification.DEFAULT_SOUND; // Sound
//        	notification.flags |= Notification.FLAG_AUTO_CANCEL;
//        	mNotificationManager.notify(NOTIFICATION_ID, notification);

//        	mNotificationManager.cancel(NOTIFICATION_ID);
		} catch (Exception e) {
			ThtShow.show_crouton_toast(MainActivity.this, e.toString(), Style.ALERT);
		}

    }

	@Override
	public void onBackPressed() {
        showCallbacks();
	}
	 protected void onActivityResult(int requestCode, int resultCode, Intent data)
		{

		    if(requestCode == Utils.REQ_LOADED & resultCode == RESULT_OK){
//		    	mprogress_wheel.setVisibility(View.GONE);
		    	mLOCK = false;
			}

		}
    private void showCallbacks() {
        new MaterialDialog.Builder(this)
                .title(R.string.title_xacnhan)
                .content(R.string.alert_thoat)
                .positiveText(R.string.agree)
                .negativeText(R.string.disagree)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        finish();
                    }
                    @Override
                    public void onNegative(MaterialDialog dialog) {

                    }
                })
                .show();
    }
    public void show_taikhoan(View v) {
        new MaterialDialog.Builder(this)
                .title(R.string.title_xacnhan)
                .content(R.string.thoattaikhoan)
                .positiveText(R.string.agree)
                .negativeText(R.string.disagree)
                .callback(new MaterialDialog.ButtonCallback() {
                              @Override
                              public void onPositive(MaterialDialog dialog) {
                                  String sKQ="loi";
                                  try {
                                      mdb.open();
                                     sKQ= mdb.xoa_khachhang();
                                      Intent i = new Intent(MainActivity.this,Ac_dangnhap.class);
                                      startActivity(i);
                                      finish();
                                  } catch (Exception e) {
//                                      ThtShow.show_crouton_toast(MainActivity.this,sKQ, Style.ALERT);
                                  }
                                  ThtShow.show_crouton_toast(MainActivity.this,sKQ, Style.ALERT);
                              }

                              @Override
                              public void onNegative(MaterialDialog dialog) {

                              }
                          }

                )
                            .

                    show();
                }


    }
