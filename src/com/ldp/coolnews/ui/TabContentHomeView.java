package com.ldp.coolnews.ui;

import com.ldp.coolnews.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

public class TabContentHomeView extends TabContentView {

	public TabContentHomeView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public TabContentHomeView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TabContentHomeView(Context context) {
		super(context);
	}

	protected void initChildCustomView(AttributeSet attrs){

		tvTitle.setText("酷欧新闻");
		ibLeftMenu.setVisibility(View.INVISIBLE);
		LayoutInflater.from(getContext()).inflate(
				R.layout.view_detail_content_home, flDetailContent);
	}

}
