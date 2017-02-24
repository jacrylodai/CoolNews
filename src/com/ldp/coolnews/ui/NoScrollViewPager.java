package com.ldp.coolnews.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 无法滑动的ViewPager
 * @author jacrylodai
 *
 */
public class NoScrollViewPager extends ViewPager {

	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NoScrollViewPager(Context context) {
		super(context);
	}
	
	/**
	 * 禁用滑动事件，什么也不做
	 */
	@Override
	public boolean onTouchEvent(MotionEvent motionEvent) {
		return false;
	}

}
