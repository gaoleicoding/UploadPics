package com.example.administrator.uploadphoto;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class ImageAddGridViewAdapter extends BaseAdapter{

	private List<Drawable>list;
	private LayoutInflater inflater;
	public ImageAddGridViewAdapter(Context context,List<Drawable>list){
		inflater=LayoutInflater.from(context);
		this.list=list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	public void changeList(List<Drawable>list){
		this.list=list;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(convertView==null){
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.image_add_gridview_items, null);
			holder.imageView=(ImageView)convertView.findViewById(R.id.theme_picture);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		holder.imageView.setImageDrawable(list.get(position));
		return convertView;
	}

	public class ViewHolder{
		ImageView imageView;
	}
}
