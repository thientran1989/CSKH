package evnspc.cskh.vn.cskh.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.thtsoftlib.function.ThtShow;

public class My_function {
	
	public static void dang_ky (Context c){
		ThtShow.show_toast(c, "da dang ky");
	}
	public static void lay_du_lieu (Context c){
		ThtShow.show_toast(c, "lay du lieu");
	}
	public static void thong_bao_cat_dien (Context c,String tb){
		ThtShow.show_toast(c, "lay du lieu");
	}
	public static void thong_bao_san_luong (Context c,String tb){
		ThtShow.show_toast(c, "lay du lieu");
	}
	public static void ve_khung (Canvas canvas,int w,int h,Paint paint){
		  int do_dai_tinh_w = w/5;
		  int do_dai_tinh_h = h/10;
		  int do_dai_canh_w = w/5;
		  int do_dai_canh_h = w/20;
		  PointF  A = new PointF(do_dai_tinh_w, h/2-do_dai_tinh_h);
		  PointF  B = new PointF(w-do_dai_tinh_w, h/2-do_dai_tinh_h);
		  PointF  C = new PointF(do_dai_tinh_w, h/2+do_dai_tinh_h);
		  PointF  D = new PointF(w-do_dai_tinh_w, h/2+do_dai_tinh_h);
		  
		  canvas.drawLine(A.x,A.y, A.x+do_dai_canh_w,A.y, paint);
		  canvas.drawLine(A.x,A.y, A.x,A.y+do_dai_canh_h, paint);
		  
		  canvas.drawLine(C.x,C.y, C.x,C.y-do_dai_canh_h, paint);
		  canvas.drawLine(C.x,C.y, C.x+do_dai_canh_w,C.y, paint);
		  
		  canvas.drawLine(B.x,B.y, B.x-do_dai_canh_w,B.y, paint);
		  canvas.drawLine(B.x,B.y, B.x,B.y+do_dai_canh_h, paint);
		  
		  canvas.drawLine(D.x,D.y, D.x-do_dai_canh_w,D.y, paint);
		  canvas.drawLine(D.x,D.y, D.x,D.y-do_dai_canh_h, paint);

	}
	
	public static String Get_imei1(Context mContext) {
		TelephonyInfo telephonyInfo = TelephonyInfo.getInstance(mContext);

	    String imeiSIM1 = telephonyInfo.getImeiSIM1();
//	    String imeiSIM2 = telephonyInfo.getImeiSIM2();
//
//	    boolean isSIM1Ready = telephonyInfo.isSIM1Ready();
//	    boolean isSIM2Ready = telephonyInfo.isSIM2Ready();
//
//	    boolean isDualSIM = telephonyInfo.isDualSIM();
//	    Log.i("Dual = "," IME1 : " + imeiSIM1 + "\n" +
//	            " IME2 : " + imeiSIM2 + "\n" +
//	            " IS DUAL SIM : " + isDualSIM + "\n" +
//	            " IS SIM1 READY : " + isSIM1Ready + "\n" +
//	            " IS SIM2 READY : " + isSIM2Ready + "\n");
		return imeiSIM1;
	}
}
