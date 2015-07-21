package evnspc.cskh.vn.cskh.broadcast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONObject;

import com.thtsoftlib.function.Thtcovert;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import evnspc.cskh.vn.cskh.R;

public class DeviceBootReceiver extends BroadcastReceiver {
	int count_bn=0;
	int count_t=0;
	int count_n=0;
	int count_m=0;
	int SL =0;
	String KQSV="false";

	@Override
	public void onReceive(Context context, Intent intent) {

		if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            /* Setting the alarm here */

            Intent alarmIntent = new Intent(context, AlarmReceiver.class);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            int interval = 8000;

            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
            Toast.makeText(context, "Alarm Set", Toast.LENGTH_SHORT).show();
        }

	}

//	public void Notification(Context context, String message) {
//		// Set Notification Title
//		String strtitle = context.getString(R.string.notificationtitle);
//		// Open NotificationView Class on Notification Click
//		Intent intent = new Intent(context, NotificationView.class);
//		// Send data to NotificationView Class
//		intent.putExtra("title", strtitle);
//		intent.putExtra("text", message);
//		// Open NotificationView.java Activity
//		PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent,
//				PendingIntent.FLAG_UPDATE_CURRENT);
//
//		// Create Notification using NotificationCompat.Builder
//		NotificationCompat.Builder builder = new NotificationCompat.Builder(
//				context)
//				// Set Icon
//				.setSmallIcon(R.drawable.logosmall)
//				// Set Ticker Message
//				.setTicker(message)
//				// Set Title
//				.setContentTitle(context.getString(R.string.app_name))
//				// Set Text
//				.setContentText(message)
//				// Add an Action Button below Notification
//				.addAction(R.drawable.ic_launcher, "Action Button", pIntent)
//				// Set PendingIntent into Notification
//				.setContentIntent(pIntent)
//				// Dismiss Notification
//				.setAutoCancel(true);
//
//		// Create Notification Manager
//		NotificationManager notificationmanager = (NotificationManager) context
//				.getSystemService(Context.NOTIFICATION_SERVICE);
//		// Build Notification with Notification Manager
//		notificationmanager.notify(0, builder.build());
//
//	}

	// Check for network availability
	private boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		//
//		ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo.State wifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
//        NetworkInfo.State mobileInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
//		if (wifiInfo == NetworkInfo.State.CONNECTED || mobileInfo == NetworkInfo.State.CONNECTED) {
//            ((Activity) mContext).startDownloadService();
//        } else if (wifiInfo == NetworkInfo.State.DISCONNECTED || mobileInfo == NetworkInfo.State.DISCONNECTED) {
//            ((Activity) mContext).stopDownloadService();
//        }
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null;
	}

	// test
//	// gui du lieu den server
//			class sync_data_to_server extends AsyncTask<String, String, String> {
//				Context context;
//				public sync_data_to_server(Context context){
//					this.context = context;
//				}
//				@Override
//				protected void onPreExecute() {
//					super.onPreExecute();
//				}
//				protected String doInBackground(String... kq) {
//					String url = Link.LINK_JSON_ALLDATA;
//					try {
//						upload(url);
//					} catch (Exception e) {
//					}
//
//					return null;
//
//				}
//
//				protected void onPostExecute(String result) {
//					try {
//						Notification(context,KQSV);
//					} catch (Exception e) {
//
//					}
//				}
//
//			}
//
//			public void upload(String urlServer) {
//				HttpURLConnection connection = null;
//				DataOutputStream outputStream = null;
//				count_bn=0;
//				count_t=0;
//				count_n=0;
//				count_m=0;
//
//				try {
//					URL url = new URL(urlServer);
//					connection = (HttpURLConnection) url.openConnection();
//					connection.setDoInput(true);
//					connection.setDoOutput(true);
//					connection.setRequestMethod("POST");
//					outputStream = new DataOutputStream(connection.getOutputStream());
//					outputStream.writeInt(Utils.LENH_KTRA_DATA);
//					outputStream.writeUTF("So luong thuoc : "+String.valueOf(SL));
//					outputStream.flush();
//					outputStream.close();
//					// nhan du lieu tu server
//					DataInputStream dis = new DataInputStream(connection.getInputStream());
//					KQSV = dis.readUTF();
//				} catch (Exception ex) {
//					Log.i("loi", ex.getMessage());
//				}
//
//			}

}