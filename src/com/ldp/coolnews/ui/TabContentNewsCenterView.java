package com.ldp.coolnews.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ldp.coolnews.R;
import com.ldp.coolnews.activity.MainActivity;
import com.ldp.coolnews.domain.NewsCenterResult;
import com.ldp.coolnews.fragment.LeftMenuFragment;
import com.ldp.coolnews.global.GlobalConstants;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class TabContentNewsCenterView extends TabContentView {
	
	private static final String TAG = TabContentNewsCenterView.class.getSimpleName();

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

	@Override
	public void initData() {

		HttpUtils httpUtils = new HttpUtils();
		
		httpUtils.send(HttpMethod.GET, GlobalConstants.NEWS_CENTER_CATEGORY_DATA, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {

				Log.i(TAG, "onSuccess");
				
				String result = responseInfo.result;				
				Log.i(TAG, "result:"+result);
				
				parseJsonData(result);
			}

			@Override
			public void onFailure(HttpException error, String msg) {

				Toast.makeText(getContext(), "连接失败:"+msg, Toast.LENGTH_SHORT).show();
				Log.i(TAG, msg, error);
			}
		});
	}

	private void parseJsonData(String result) {

		Gson gson = new Gson();
		
		NewsCenterResult newsCenterResult = 
				gson.fromJson(result, NewsCenterResult.class);
		Log.i(TAG, "parseResult:"+newsCenterResult.toString());
		
		MainActivity mainActivity = (MainActivity) getContext();
		LeftMenuFragment leftMenuFragment = mainActivity.getLeftMenuFragment();
		leftMenuFragment.updateMenuItemList(newsCenterResult.getData());
	}

}
