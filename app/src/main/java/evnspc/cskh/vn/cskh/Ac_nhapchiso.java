package evnspc.cskh.vn.cskh;

import java.io.ByteArrayOutputStream;

import evnspc.cskh.vn.cskh.camera.BitmapHelper;
import evnspc.cskh.vn.cskh.camera.CameraActivity;
import evnspc.cskh.vn.cskh.utils.My_function;
import evnspc.cskh.vn.cskh.utils.Utils;
import tht.library.crouton.Style;

import com.thtsoftlib.function.ThtCanvas;
import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.ThtTime;
import com.thtsoftlib.function.Tht_Screen;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class Ac_nhapchiso extends Activity{
	EditText edt_chiso;
	ImageView iv_chupchiso;
	int w,h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tht_Screen.hide_title(this);
        setContentView(R.layout.activity_nhapchiso);
        try {
        	w = Tht_Screen.get_screen_width(this);
        	h = Tht_Screen.get_screen_heigth_percent(this, 50);
        	TextView tv_title = (TextView)findViewById(R.id.tv_title_nhapchiso);
        	edt_chiso = (EditText)findViewById(R.id.edt_chiso_nhapchiso);
        	iv_chupchiso = (ImageView)findViewById(R.id.iv_chupchiso_nhapchiso);
        	Bundle b = new Bundle();
        	b = getIntent().getExtras();
        	tv_title.setText(b.getString(Utils.PARAM_title));
        	Tht_Screen.hide_keyboard(this);
		} catch (Exception e) {
			ThtShow.show_crouton_toast(this,
					getString(R.string.loi_khoi_tao_giao_dien), Style.ALERT);
		}
    }
    
    public void nhap_chi_so(View v){
    	try {
    		String CS = edt_chiso.getText().toString();   		
        	String CS_add = ((Button)v).getText().toString();
        	if (CS.contains(".")){
        		if (!CS_add.equals(".")){
        			CS = CS+CS_add;
                	edt_chiso.setText(CS);
                	edt_chiso.setSelection(edt_chiso.length());
        		}
        	}else{
        		CS = CS+CS_add;
            	edt_chiso.setText(CS);
            	edt_chiso.setSelection(edt_chiso.length());
        	}
        	
		} catch (Exception e) {
			ThtShow.show_crouton_toast(this,
					getString(R.string.loi_nhap_lieu), Style.ALERT);
		}
    	
    }
    public void xoa_chi_so(View v){
    	try {
    		String CS = edt_chiso.getText().toString();
    		if (CS.length()>0){
    			CS = CS.substring(0, CS.length()-1);
    			edt_chiso.setText(CS);
            	edt_chiso.setSelection(edt_chiso.length());
    		}
		} catch (Exception e) {
			ThtShow.show_crouton_toast(this,
					getString(R.string.loi_nhap_lieu), Style.ALERT);
		}
    	
    }
    
    // camera
    
    public void call_camera (View v){
    	if(cameraNotDetected()){
        	String message = "No camera";
        	ThtShow.show_crouton_toast(Ac_nhapchiso.this, message, tht.library.crouton.Style.ALERT);
        }else{
        	Intent intent = new Intent(Ac_nhapchiso.this, CameraActivity.class);
	    	startActivityForResult(intent, Utils.REQ_CAMERA_IMAGE);
        }
    }
    private boolean cameraNotDetected() {
		return !getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
	}
    protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{  
	   
	    if(requestCode == Utils.REQ_CAMERA_IMAGE && resultCode == RESULT_OK){
			String imgPath = data.getStringExtra(CameraActivity.EXTRA_IMAGE_PATH);
			displayImage(imgPath);
		} else
		if(requestCode == Utils.REQ_CAMERA_IMAGE && resultCode == RESULT_CANCELED){
		}
	    
	}
	private void displayImage(String path) {
	  //Get our saved file into a bitmap object:
		try {
			   Bitmap newBitmap = null;
		       Bitmap bitmap = BitmapHelper.decodeSampledBitmap(path, 1000, 700);
//		       bitmap = ResizedBitmap(bitmap, Tht_Screen.get_screen_heigth(Ac_nhapchiso.this));
		                  
		       Config config = bitmap.getConfig();
		       if(config == null){
		        config = Bitmap.Config.ARGB_8888;
		       }
		       if (bitmap!=null){
		    	   Matrix mtx = new Matrix();
		           mtx.postRotate(90);
		           // Rotating Bitmap
		           newBitmap = Bitmap.createBitmap(bitmap, 0, 0,bitmap.getWidth()/2,bitmap.getHeight(), mtx, true);
//		           newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), config);
//			       newBitmap = Bitmap.createBitmap(Tht_Screen.get_screen_width(Ac_nhapchiso.this),Tht_Screen.get_screen_heigth(Ac_nhapchiso.this), config);
			       Canvas newCanvas = new Canvas(newBitmap);
			       int DON_LO=get_canh_chu_thap(newBitmap);
			       Paint paint = new Paint();
		           paint.setColor(Color.RED);
		           paint.setStrokeWidth(get_do_lon_chu_thap(newBitmap)); 
		           paint.setStyle(android.graphics.Paint.Style.FILL);
		           paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
		           
//		           newCanvas.drawBitmap(bitmap,( (Tht_Screen.get_screen_width(Ac_nhapchiso.this)-bitmap.getWidth())/2),0, paint);
//		           ThtCanvas.ve_chu_thap(newCanvas, newBitmap, paint, DON_LO);
		           My_function.ve_khung(newCanvas, newBitmap.getWidth(), newBitmap.getHeight(), paint);
		           paint.setColor(Color.RED);
		           paint.setStrokeWidth(get_do_lon_chu_thap(newBitmap)); 
		           paint.setTextSize(get_do_lon_chu_thap(newBitmap)*5);
		           paint.setStyle(android.graphics.Paint.Style.FILL);
		           paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)); 
		          
		           Paint paint2 = new Paint();
		           paint2.setColor(Color.RED); 
		           paint2.setStrokeWidth(get_do_lon_chu_thap(newBitmap)); 
		           paint2.setTextSize(get_do_lon_chu_thap(newBitmap)*4);
		           paint2.setStyle(android.graphics.Paint.Style.FILL);
		           paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
		           // some more settings...
		           String TEN = "TRẦN HỮU THIỆN";
		           String DC  = "60B, đường 3/2, P. An Khánh, Q. Ninh Kiều, TP. Cần Thơ";
		           // ve them text ten dia chi kh
		           ThtCanvas.ve_text_on_canvas(newCanvas, newBitmap, paint, TEN, 1,1,0);
		           ThtCanvas.ve_text_on_canvas(newCanvas, newBitmap, paint2, DC, 2,0,0);
//		           ThtCanvas.ve_text_on_canvas(newCanvas, newBitmap, paint, "Tên NV : ", 3,0,(float)0.25);
		           ThtCanvas.ve_text_on_canvas(newCanvas, newBitmap, paint2,"Ngày giờ : "+ThtTime.get_curent_full_date_time(), 3,0,(float)0.25);
		           ByteArrayOutputStream stream = new ByteArrayOutputStream();
		           newBitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);	  
				   byte imageInByte[] = stream.toByteArray();

					ThtShow.show_crouton_toast(Ac_nhapchiso.this,  "da luu anh !", tht.library.crouton.Style.INFO);
					iv_chupchiso.setImageBitmap(newBitmap);
		       }
		       
		} catch (Exception e) {
			ThtShow.show_crouton_toast(Ac_nhapchiso.this,  "Khong luu duoc hinh", tht.library.crouton.Style.ALERT);
		}
		   
	}
	public int get_do_lon_chu_thap(Bitmap bit){
		 int dolon =0;
		 if (bit!=null){
			 try {
				dolon = bit.getHeight()/100;	
				} catch (Exception e) {
					// TODO: handle exception
				}
		 }
		 return dolon;
		
	 }
	public int get_canh_chu_thap(Bitmap bit){
		 int dolon =0;
		 if (bit!=null){
			 try {
				dolon = bit.getHeight()/13;	
				} catch (Exception e) {
					// TODO: handle exception
				}
		 }
		 return dolon;
		
	 }
	public Bitmap ResizedBitmap(Bitmap image, int max_h) {
		float width = image.getWidth();
		float height = image.getHeight();
        float bitmapRatio = max_h / height;
        if (bitmapRatio > 0) {
            width =(width*bitmapRatio);
            height = max_h;
        } else {
//            height = maxSize;
//            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, (int)width, (int)height, true);
	}



}
