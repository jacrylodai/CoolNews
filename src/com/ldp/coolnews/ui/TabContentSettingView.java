package com.ldp.coolnews.ui;

import com.ldp.coolnews.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

public class TabContentSettingView extends TabContentView {

	public TabContentSettingView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public TabContentSettingView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TabContentSettingView(Context context) {
		super(context);
	}

	protected void initChildCustomView(AttributeSet attrs){

		tvTitle.setText("设置");
		ibLeftMenu.setVisibility(View.INVISIBLE);
		LayoutInflater.from(getContext()).inflate(
				R.layout.view_detail_content_setting, flDetailContent);
	}

}
