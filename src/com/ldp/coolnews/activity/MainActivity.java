package com.ldp.coolnews.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.ldp.coolnews.R;
import com.ldp.coolnews.fragment.ContentFragment;
import com.ldp.coolnews.fragment.ContentFragment.OnSlidingMenuShowStateChangeListener;
import com.ldp.coolnews.fragment.LeftMenuFragment;

public class MainActivity extends SlidingFragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_container);
		setBehindContentView(R.layout.activity_menu_container);
		
		initSlidMenu();
		initContentFragment();
		initLeftMenuFragment();
	}

	private void initSlidMenu() {

		SlidingMenu slidingMenu = getSlidingMenu();
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		slidingMenu.setShadowDrawable(R.drawable.shadow);
		slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
	}

	private void initContentFragment() {

		FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment contentFragment = fragmentManager.findFragmentById(R.id.fl_fragment_container);
		if(contentFragment == null){
			
			contentFragment = new ContentFragment();
			fragmentManager.beginTransaction()
					.add(R.id.fl_fragment_container, contentFragment)
					.commit();
		}
		
		ContentFragment convContentFragment = (ContentFragment) contentFragment;
		convContentFragment.setOnSlidingMenuShowStateChangeListener(new OnSlidingMenuShowStateChangeListener(){
			
			@Override
			public void onSlidingMenuShowStateChange(boolean isShow) {
				super.onSlidingMenuShowStateChange(isShow);
				
				SlidingMenu slidingMenu = getSlidingMenu();
				if(isShow){
					slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
				}else{
					slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
				}
			}
		});
	}

	private void initLeftMenuFragment() {

		FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment leftMenuFragment = fragmentManager.findFragmentById(R.id.fl_fragment_menu_container);
		if(leftMenuFragment == null){
			
			leftMenuFragment = new LeftMenuFragment();
			fragmentManager.beginTransaction()
					.add(R.id.fl_fragment_menu_container, leftMenuFragment)
					.commit();
		}
	}
	
	public LeftMenuFragment getLeftMenuFragment(){

		FragmentManager fragmentManager = getSupportFragmentManager();
		LeftMenuFragment leftMenuFragment = 
				(LeftMenuFragment) fragmentManager.findFragmentById(
						R.id.fl_fragment_menu_container);
		return leftMenuFragment;
	}
	
}
