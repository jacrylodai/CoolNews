package com.ldp.coolnews.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ldp.coolnews.R;
import com.ldp.coolnews.global.ConfigInfo;
import com.ldp.coolnews.ui.GuidePercentView;

public class AppDescActivity extends ActionBarActivity {
	
	private static final String TAG = AppDescActivity.class.getSimpleName();
	
	private SharedPreferences mPref;

	private ViewPager vpAppDesc;
	
	private static int[] sGuideIdArr = new int[]{
		R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
	
	private List<ImageView> mIVDescList;
	
	private LinearLayout llPercentContainer;
	
	private RelativeLayout rlPercentAll;
	
	private ImageView ivCurrentPercent;
	
	private Button buttonStart;
	
	private int mPercentDistance;
	
	private int mCurrentPercentBaseLeft;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_app_desc);
		
		mPref = getSharedPreferences(ConfigInfo.CONFIG_FILE_NAME, MODE_PRIVATE);

		vpAppDesc = (ViewPager) findViewById(R.id.vp_app_desc);
		llPercentContainer = (LinearLayout) findViewById(R.id.ll_percent_container);
		rlPercentAll = (RelativeLayout) findViewById(R.id.rl_percent_all);
		ivCurrentPercent = (ImageView) findViewById(R.id.iv_current_percent);
		buttonStart = (Button) findViewById(R.id.button_start);
		
		initAppDesc();
		initPercent();

		AppDescPagerAdapter adapter = new AppDescPagerAdapter();
		vpAppDesc.setAdapter(adapter);
		
		buttonStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Log.i(TAG, "You press the start button");
				
				mPref.edit()
					.putBoolean(ConfigInfo.PREF_SHOW_APP_DESC, true)
					.commit();
				
				Intent intent = new Intent(AppDescActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		buttonStart.setVisibility(View.INVISIBLE);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.i(TAG, "onStart");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i(TAG, "onResume");
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
		
		rlPercentAll.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {
			
			@Override
			public void onGlobalLayout() {
				
				Log.i(TAG, "OnGlobalLayoutListener");
				
				if(sGuideIdArr.length == 1){
					mPercentDistance = 0;
				}else{
					//ȡ��2��֮��ļ��
					GuidePercentView gvFirstPercent = 
							(GuidePercentView) llPercentContainer.getChildAt(0);
					GuidePercentView gvSecondPercent = 
							(GuidePercentView) llPercentContainer.getChildAt(1);
					int firstLeft = gvFirstPercent.getLeft();
					int secondLeft = gvSecondPercent.getLeft();
					mPercentDistance = secondLeft - firstLeft;
				}
				Log.i(TAG, "mPercentDistance:"+mPercentDistance);
				//ȡ�õ�ǰ�ڵ���ߵĻ���
				mCurrentPercentBaseLeft = ivCurrentPercent.getLeft();
				Log.i(TAG, "mCurrentPercentBaseLeft:"+mCurrentPercentBaseLeft);
				//����ҳ����ת�ļ�����
				vpAppDesc.setOnPageChangeListener(new AppDescOnPageChangerListener());
				
				rlPercentAll.getViewTreeObserver().removeGlobalOnLayoutListener(this);
			}
		});
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
	
	private class AppDescOnPageChangerListener implements OnPageChangeListener{

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {

			StringBuffer sb = new StringBuffer();
			sb.append("position:"+position);
			sb.append("---");
			sb.append("positionOffset:"+positionOffset);
			sb.append("---");
			sb.append("positionOffsetPixels:"+positionOffsetPixels);
			Log.i(TAG, sb.toString());
			//���㵱ǰ��Ը���ͼ��ߵļ��
			int currentLeft = (int) (mCurrentPercentBaseLeft 
					+ (position+positionOffset)*mPercentDistance);
			Log.i(TAG, "currentLeft:"+currentLeft);
			//��������
			RelativeLayout.LayoutParams currPercParams = 
					(RelativeLayout.LayoutParams) ivCurrentPercent.getLayoutParams();
			currPercParams.leftMargin = currentLeft;
			
			ivCurrentPercent.setLayoutParams(currPercParams);
		}

		@Override
		public void onPageSelected(int position) {
			
			int lastPosition = sGuideIdArr.length-1;
			if(position == lastPosition){
				buttonStart.setVisibility(View.VISIBLE);
			}else{
				buttonStart.setVisibility(View.INVISIBLE);
			}
		}

		@Override
		public void onPageScrollStateChanged(int state) {
			
		}
		
	}
	
}
