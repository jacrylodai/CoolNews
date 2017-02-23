package com.ldp.coolnews.ui;

import com.ldp.coolnews.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

public class TabContentNewsCenterView extends TabContentView {

	public TabContentNewsCenterView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public TabContentNewsCenterView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TabContentNewsCenterView(Context context) {
		super(context);
	}

	protected void initChildCustomView(AttributeSet attrs){

		tvTitle.setText("新闻");
		LayoutInflater.from(getContext()).inflate(
				R.layout.view_detail_content_news_center, flDetailContent);
	}

}
