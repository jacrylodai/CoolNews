package com.ldp.coolnews.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ldp.coolnews.R;

public class GuidePercentView extends RelativeLayout {
	
	private ImageView ivPercent;

	public GuidePercentView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(attrs);
	}

	public GuidePercentView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(attrs);
	}

	public GuidePercentView(Context context) {
		super(context);
		initView(null);
	}

	private void initView(AttributeSet attrs){
		
		View view = LayoutInflater.from(getContext()).inflate(
				R.layout.view_guide_percent, this);
		ivPercent = (ImageView) view.findViewById(R.id.iv_percent);
	}
	
	public void setPercentBackground(int resId){
		ivPercent.setBackgroundResource(resId);
	}

}
