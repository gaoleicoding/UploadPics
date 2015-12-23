package com.example.administrator.uploadphoto;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class MyViewPager extends ViewPager {

	PointF downPoint = new PointF();
	public static boolean ifIntercept = true;
	OnSingleTouchListener onSingleTouchListener;

	public MyViewPager(Context context) {
		super(context);
	}

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public boolean onTouch(MotionEvent event) {
		Log.d("gaolei", "onTouchEvent-------------");
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downPoint.x = event.getX();
			downPoint.y = event.getY();
			// if (this.getChildCount() > 1) { // 有内容，多于1个时
			// 通知其父控件，现在进行的是本控件的操作，不允许拦截
			getParent().requestDisallowInterceptTouchEvent(true);
			// }
			break;
		case MotionEvent.ACTION_MOVE:
			// if (this.getChildCount() > 1) {
			getParent().requestDisallowInterceptTouchEvent(true);
			// }
			break;
		case MotionEvent.ACTION_UP:
			// 在up时判断是否按下和松手的坐标为一个点
			if (PointF.length(event.getX() - downPoint.x, event.getY()
					- downPoint.y) < (float) 5.0) {
				onSingleTouchListener.onSingleTouch(getCurrentItem());
				Log.d("gaolei", "getCurrentItem()-------------"+getCurrentItem());
			}
			break;
		}
		return super.onTouchEvent(event);
	}
//
//	public boolean onInterceptTouchEvent(MotionEvent event) {
//		Log.d("gaolei", "onInterceptTouchEvent-------------");
//		// 当拦截触摸事件到达此位置的时候，返回true，
//		// 说明将onTouch拦截在此控件，进而执行此控件的onTouchEvent
//		return ifIntercept;
//	}

	// 创建点击事件接口 
	 
	public interface OnSingleTouchListener {
		public void onSingleTouch(int position);
	}

	public void setOnSingleTouchListener(
			OnSingleTouchListener onSingleTouchListener) {
		this.onSingleTouchListener = onSingleTouchListener;
	}

}
