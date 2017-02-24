package com.ldp.coolnews.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
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
	
	private List<TabContentView> mTabContentViewList;
	
	private ViewPager vpContentPager;
	
	private RadioGroup rgTabGroup;
	
	private OnSlidingMenuShowStateChangeListener onShowStateChangeListener;

	private ContentPagerAdapter mContentPagerAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mTabContentViewList = new ArrayList<TabContentView>();
		
		mTabContentViewList.add(new TabContentHomeView(getActivity()));
		mTabContentViewList.add(new TabContentNewsCenterView(getActivity()));
		mTabContentViewList.add(new TabContentSettingView(getActivity()));
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_content, container, false);
		
		vpContentPager = (ViewPager) view.findViewById(R.id.vp_content_pager);
		rgTabGroup = (RadioGroup) view.findViewById(R.id.rg_tab_group);
		
		mContentPagerAdapter = new ContentPagerAdapter();
		vpContentPager.setAdapter(mContentPagerAdapter);
		
		vpContentPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				
				TabContentView tabContentView = mTabContentViewList.get(position);
				tabContentView.initData();
				
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
		
		vpContentPager.setCurrentItem(0);
		
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
	
	private class ContentPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return mTabContentViewList.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			
			TabContentView tabContentView = mTabContentViewList.get(position);
			
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
