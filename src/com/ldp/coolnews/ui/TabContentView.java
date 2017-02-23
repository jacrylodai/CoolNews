package com.ldp.coolnews.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ldp.coolnews.R;

public abstract class TabContentView extends RelativeLayout {
	
	private static final String TAG = TabContentView.class.getSimpleName();

	protected TextView tvTitle;
	
	protected ImageButton ibLeftMenu;
	
	protected FrameLayout flDetailContent;

	public TabContentView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(attrs);
	}

	public TabContentView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(attrs);
	}

	public TabContentView(Context context) {
		super(context);
		initView(null);
	}
	
	private void initView(AttributeSet attrs){
		
		Log.i(TAG, "initView");
		
		View view = LayoutInflater.from(getContext()).inflate(
				R.layout.view_tab_content, this);
		
		tvTitle = (TextView) view.findViewById(R.id.tv_title);
		ibLeftMenu = (ImageButton) view.findViewById(R.id.ib_left_menu);
		flDetailContent = (FrameLayout) view.findViewById(R.id.fl_detail_content);
		
		initChildCustomView(attrs);
	}
	
	protected abstract void initChildCustomView(AttributeSet attrs);

}
