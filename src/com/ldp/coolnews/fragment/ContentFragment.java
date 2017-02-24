package com.ldp.coolnews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.ldp.coolnews.R;
import com.ldp.coolnews.ui.TabContentHomeView;
import com.ldp.coolnews.ui.TabContentNewsCenterView;
import com.ldp.coolnews.ui.TabContentSettingView;
import com.ldp.coolnews.ui.TabContentView;

public class ContentFragment extends Fragment {
	
	private static final String TAG = ContentFragment.class.getSimpleName();
	
	/**
	 * 标签页面的数量
	 */
	private static final int TAB_CONTENT_VIEW_LENGTH = 3; 
	
	private ViewPager vpContentPager;
	
	private RadioGroup rgTabGroup;
	
	private OnSlidingMenuShowStateChangeListener onShowStateChangeListener;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_content, container, false);
		
		vpContentPager = (ViewPager) view.findViewById(R.id.vp_content_pager);
		rgTabGroup = (RadioGroup) view.findViewById(R.id.rg_tab_group);
		
		vpContentPager.setAdapter(new ContentPagerAdapter());
		
		vpContentPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				
				//切换SlidingMenu是否显示，首页和设置不显示，新闻中心要显示
				switch (position) {
				case 0:
					onShowStateChangeListener.onSlidingMenuShowStateChange(false);
					break;

				case 1:
					onShowStateChangeListener.onSlidingMenuShowStateChange(true);
					break;

				case 2:
					onShowStateChangeListener.onSlidingMenuShowStateChange(false);
					break;

				default:
					break;
				}
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				
			}
		});
		
		rgTabGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				switch (checkedId) {
				case R.id.rb_tab_home:
					vpContentPager.setCurrentItem(0);
					break;

				case R.id.rb_tab_news_center:
					vpContentPager.setCurrentItem(1);
					break;

				case R.id.rb_tab_setting:
					vpContentPager.setCurrentItem(2);
					break;

				default:
					break;
				}
			}
		});
		rgTabGroup.check(R.id.rb_tab_home);
		
		return view;
	}
	
	private TabContentView getTabContentViewByIndex(int index){
		
		TabContentView tabContentView = null;
		switch (index) {
		case 0:
			tabContentView = new TabContentHomeView(getActivity());
			break;

		case 1:
			tabContentView = new TabContentNewsCenterView(getActivity());
			break;

		case 2:
			tabContentView = new TabContentSettingView(getActivity());
			break;

		default:
			break;
		}
		
		return tabContentView;
	}

	private class ContentPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return TAB_CONTENT_VIEW_LENGTH;
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			
			TabContentView tabContentView = getTabContentViewByIndex(position);
			
			container.addView(tabContentView);
			return tabContentView;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
		
	}
	
	public void setOnSlidingMenuShowStateChangeListener(OnSlidingMenuShowStateChangeListener onShowStateChangeListener){
		this.onShowStateChangeListener = onShowStateChangeListener;
	}
	
	/**
	 * 点击下面标签，切换不同的界面，侧滑菜单状态改变的监听接口
	 * @author jacrylodai
	 *
	 */
	public static class OnSlidingMenuShowStateChangeListener {
		
		/**
		 * 
		 * @param isShow 是否显示侧滑菜单
		 */
		public void onSlidingMenuShowStateChange(boolean isShow){
			
		}
	}
	
}
