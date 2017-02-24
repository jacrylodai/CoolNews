package com.ldp.coolnews.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ldp.coolnews.R;
import com.ldp.coolnews.domain.NewsCenterMenuCategory;

public class LeftMenuFragment extends Fragment {
	
	private static final String TAG = LeftMenuFragment.class.getSimpleName();
	
	private static final String KEY_CURRENT_POSI = "keyCurrentPosi";
	
	private ListView lvMenuItem;
	
	private MenuItemAdapter mMenuItemAdapter;
	
	private List<NewsCenterMenuCategory> mNewsCenterMenuCategoryList;
	
	private int mCurrentPosi;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if(savedInstanceState != null){
			mCurrentPosi = savedInstanceState.getInt(KEY_CURRENT_POSI, 0);
		}else{
			mCurrentPosi = 0;
		}
		
		mNewsCenterMenuCategoryList = new ArrayList<NewsCenterMenuCategory>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_left_menu, container,false);
		
		lvMenuItem = (ListView) view.findViewById(R.id.lv_menu_item);
		mMenuItemAdapter = new MenuItemAdapter(getActivity()
				, R.layout.list_item_menu_item, mNewsCenterMenuCategoryList);
		lvMenuItem.setAdapter(mMenuItemAdapter);
		lvMenuItem.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Log.i(TAG, "position:"+position);
				mCurrentPosi = position;
				mMenuItemAdapter.notifyDataSetChanged();
			}
		});
		
		return view;
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(KEY_CURRENT_POSI, mCurrentPosi);
	}
	
	public void updateMenuItemList(List<NewsCenterMenuCategory> newsCenterMenuCategoryList){
		
		mNewsCenterMenuCategoryList.clear();
		mNewsCenterMenuCategoryList.addAll(newsCenterMenuCategoryList);
		mMenuItemAdapter.notifyDataSetChanged();		
	}
	
	private class MenuItemAdapter extends ArrayAdapter<NewsCenterMenuCategory>{

		private int resourceId;
		
		public MenuItemAdapter(Context context, int resource,
				List<NewsCenterMenuCategory> objects) {
			super(context, resource, objects);
			resourceId = resource;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			NewsCenterMenuCategory newsCenterMenuCategory = getItem(position);
			
			if(convertView == null){
				convertView = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
			}
			
			ImageView ivArrow = (ImageView) convertView.findViewById(R.id.iv_arrow);
			TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
			
			tvTitle.setText(newsCenterMenuCategory.getTitle());
			
			if(mCurrentPosi == position){
				ivArrow.setEnabled(true);
				tvTitle.setEnabled(true);
			}else{

				ivArrow.setEnabled(false);
				tvTitle.setEnabled(false);
			}
			
			return convertView;
		}
		
	}
	
}
