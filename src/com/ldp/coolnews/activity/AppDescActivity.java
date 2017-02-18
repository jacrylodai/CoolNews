package com.ldp.coolnews.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ldp.coolnews.R;
import com.ldp.coolnews.ui.GuidePercentView;

public class AppDescActivity extends ActionBarActivity {

	private ViewPager vpAppDesc;
	
	private static int[] sGuideIdArr = new int[]{
		R.drawable.splash_horse_newyear,R.drawable.guide_1
		,R.drawable.splash_horse_newyear,R.drawable.guide_2,R.drawable.guide_3};
	
	private List<ImageView> mIVDescList;
	
	private LinearLayout llPercentContainer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_app_desc);

		vpAppDesc = (ViewPager) findViewById(R.id.vp_app_desc);
		llPercentContainer = (LinearLayout) findViewById(R.id.ll_percent_container);
		
		initAppDesc();
		initPercent();

		AppDescPagerAdapter adapter = new AppDescPagerAdapter();
		vpAppDesc.setAdapter(adapter);
	}
	
	private void initAppDesc() {
		
		mIVDescList = new ArrayList<ImageView>();
		
		for(int i=0;i<sGuideIdArr.length;i++){
			ImageView ivDesc = new ImageView(this);
			ivDesc.setImageResource(sGuideIdArr[i]);
			
			mIVDescList.add(ivDesc);
		}
	}
	
	private void initPercent(){
		
		for(int i=0;i<sGuideIdArr.length;i++){
			
			GuidePercentView percentView = new GuidePercentView(this);
			percentView.setPercentBackground(R.drawable.shape_guide_percent);
			llPercentContainer.addView(percentView);
		}
	}

	private class AppDescPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return sGuideIdArr.length;
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			
			return view == obj;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			
			ImageView ivDesc = mIVDescList.get(position);
			container.addView(ivDesc);
			return ivDesc;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			
			View view = (View) object;
			container.removeView(view);
		}
		
	}
	
}
