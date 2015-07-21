package evnspc.cskh.vn.cskh.camera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import evnspc.cskh.vn.cskh.utils.My_function;


public class Direction extends View{
	  
	 public Direction(Context context) {
	  super(context);
	  // TODO Auto-generated constructor stub
	 }
	 
	 public Direction(Context context, AttributeSet attrs) {
	  super(context, attrs);
	  // TODO Auto-generated constructor stub
	 }
	 
	 public Direction(Context context, AttributeSet attrs, int defStyle) {
	  super(context, attrs, defStyle);
	  // TODO Auto-generated constructor stub
	 }
	 
	 @Override
	 protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	  setMeasuredDimension(
	    MeasureSpec.getSize(widthMeasureSpec),
	    MeasureSpec.getSize(heightMeasureSpec));
	 }
	 @SuppressLint("DrawAllocation") @Override
	 protected void onDraw(Canvas canvas) {
	   
	  int w = getMeasuredWidth();
	  int h = getMeasuredHeight();
	  int r;
	  if(w > h){
	   r = h/15;
	  }else{
	   r = w/15;
	  }
	   
	  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	  paint.setStyle(Paint.Style.STROKE);
	  paint.setStrokeWidth(5);
	  paint.setColor(Color.RED); 
//	  canvas.drawCircle(w/2, h/2, r, paint);
	  
//	  canvas.drawLine(w/2-50, h/2, w/2+50, h/2, paint);;
//	  canvas.drawLine(w/2, h/2-50, w/2, h/2+50, paint);
	  
	  //    A---              B----
	  //    |                     |
	  //               X/2 Y/2  
	  //    |                     |
	  //    C---               D---
	 My_function.ve_khung(canvas, w, h, paint);
	 }
}