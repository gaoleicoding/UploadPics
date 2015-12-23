package com.example.administrator.uploadphoto;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;


public class ImagePagerAdapter extends FragmentStatePagerAdapter{
	private List<String>list;
	 public ImagePagerAdapter(FragmentManager fm,List<String> list) {
		super(fm);
		this.list=list;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		return new ImageDisplayFragment().create(list.get(position),position);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return PagerAdapter.POSITION_NONE;
			}
	public void changeList(List<String>list){
		this.list=list;
	}
	
}
