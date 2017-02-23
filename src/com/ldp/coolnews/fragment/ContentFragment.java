package com.ldp.coolnews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.ldp.coolnews.R;
import com.ldp.coolnews.ui.TabContentHomeView;
import com.ldp.coolnews.ui.TabContentNewsCenterView;
import com.ldp.coolnews.ui.TabContentSettingView;
import com.ldp.coolnews.ui.TabContentView;

public class ContentFragment extends Fragment {
	
	private static final String TAG = ContentFragment.class.getSimpleName();
	
	private static final int TAB_CONTENT_VIEW_LENGTH = 3; 
	
	private ViewPager vpContentPager;
	
	private RadioGroup rgTabGroup;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_content, container, false);
		
		vpContentPager = (ViewPager) view.findViewById(R.id.vp_content_pager);
		rgTabGroup = (RadioGroup) view.findViewById(R.id.rg_tab_group);
		
		vpContentPager.setAdapter(new ContentPagerAdapter());
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
	
}
