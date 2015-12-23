package com.example.administrator.uploadphoto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.uploadphoto.ThemeObject.PictureList;

import java.util.ArrayList;
import java.util.List;

public class ThemeListViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<ThemeObject> list;
    private Context context;
    private List<String> uploadImgUrlList;

    public ThemeListViewAdapter(List<ThemeObject> list,
                                List<String> uploadImgUrlList, Context context) {
        inflater = LayoutInflater.from(context);
        this.list = list;
        this.uploadImgUrlList = uploadImgUrlList;
        this.context = context;

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

    public void changeList(List<ThemeObject> list) {
        this.list = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.theme_listview_items, null);
            holder.user_photo = (ImageView) convertView
                    .findViewById(R.id.user_photo);
            holder.theme_img1 = (ImageView) convertView
                    .findViewById(R.id.theme_img1);
            holder.theme_img2 = (ImageView) convertView
                    .findViewById(R.id.theme_img2);
            holder.theme_img3 = (ImageView) convertView
                    .findViewById(R.id.theme_img3);
            holder.theme_title = (TextView) convertView
                    .findViewById(R.id.theme_title);
            holder.theme_desc = (TextView) convertView
                    .findViewById(R.id.theme_desc);
            holder.user_name = (TextView) convertView
                    .findViewById(R.id.user_name);
            holder.publish_theme_time = (TextView) convertView
                    .findViewById(R.id.publish_theme_time);
            holder.theme_reply_num = (TextView) convertView
                    .findViewById(R.id.theme_reply_num);
            holder.theme_praise_num = (TextView) convertView
                    .findViewById(R.id.theme_praise_num);
            holder.game_playing_prefix = (TextView) convertView
                    .findViewById(R.id.game_playing_prefix);
            holder.game_playing = (TextView) convertView
                    .findViewById(R.id.game_playing);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.user_photo.setTag(position + "");
        holder.theme_img1.setTag(position + "");
        holder.theme_img2.setTag(position + "");
        holder.theme_img3.setTag(position + "");
        holder.game_playing_prefix.setTag(position + "");
        holder.game_playing.setTag(position + "");
        if (list.size() > 0) {
            final ThemeObject object = list.get(position);

            if (holder.user_photo.getTag().equals("" + position)) {
                if (object.getUserPhoto() != null) {
                    new CommonUtils().displayCircleImage(object.getUserPhoto(),
                            holder.user_photo, "photo");
                } else {
                    holder.user_photo.setImageDrawable(context.getResources()
                            .getDrawable(R.drawable.personal_default_photo));
                }
                List<PictureList> themePictureList = object.getPictureList();
                int pictureListSize = themePictureList.size();
                List<String> pictureUrlList = new ArrayList<String>();
                for (int i = 0; i < themePictureList.size(); i++) {
                    pictureUrlList.add(themePictureList.get(i).getUrl());
                }
                Log.d("gaolei", "themePictureList.size()------------" + position + "-------" + themePictureList.size());

                int uploadImgUrlListSize = uploadImgUrlList.size();
                Log.d("gaolei", "uploadImgUrlListSize------------" + position + "-------" + uploadImgUrlListSize);

                if (pictureListSize == 0) {
                    holder.theme_img1.setVisibility(View.GONE);
                    holder.theme_img2.setVisibility(View.GONE);
                    holder.theme_img3.setVisibility(View.GONE);
                }
                if (pictureListSize == 1) {
                    holder.theme_img1.setVisibility(View.VISIBLE);
                    if (holder.theme_img1.getTag().equals("" + position)) {
                        CommonUtils.getUtilInstance().displayLowQualityInImage(pictureUrlList.get(0),
                                holder.theme_img1);
                    }
                }
                if (pictureListSize == 2) {
                    holder.theme_img1.setVisibility(View.VISIBLE);
                    holder.theme_img2.setVisibility(View.VISIBLE);
                    if (holder.theme_img1.getTag().equals("" + position)) {
                        CommonUtils.getUtilInstance().displayLowQualityInImage(pictureUrlList.get(0),
                                holder.theme_img1);
                    }
                    if (holder.theme_img2.getTag().equals("" + position)) {
                        CommonUtils.getUtilInstance().displayLowQualityInImage(pictureUrlList.get(1),
                                holder.theme_img2);
                    }
                }
                if (pictureListSize == 3) {
                    holder.theme_img1.setVisibility(View.VISIBLE);
                    holder.theme_img2.setVisibility(View.VISIBLE);
                    holder.theme_img3.setVisibility(View.VISIBLE);
                    if (holder.theme_img1.getTag().equals("" + position)) {
                        CommonUtils.getUtilInstance().displayLowQualityInImage(pictureUrlList.get(0),
                                holder.theme_img1);
                    }
                    if (holder.theme_img2.getTag().equals("" + position)) {
                        CommonUtils.getUtilInstance().displayLowQualityInImage(pictureUrlList.get(1),
                                holder.theme_img2);
                    }
                    if (holder.theme_img3.getTag().equals("" + position)) {
                        CommonUtils.getUtilInstance().displayLowQualityInImage(pictureUrlList.get(2),
                                holder.theme_img3);
                    }
                }
                if (position == 0) {
                    if (uploadImgUrlListSize == 1) {
                        holder.theme_img1.setVisibility(View.VISIBLE);
                        if (holder.theme_img1.getTag().equals("" + position)) {
                            Bitmap bitmap = UploadPhotoUtil.getInstance().trasformToZoomBitmapAndLessMemory(uploadImgUrlList.get(0));
                            holder.theme_img1.setImageDrawable(new BitmapDrawable(context.getResources(), bitmap));
                        }
                    }
                    if (uploadImgUrlListSize == 2) {
                        holder.theme_img1.setVisibility(View.VISIBLE);
                        holder.theme_img2.setVisibility(View.VISIBLE);
                        if (holder.theme_img1.getTag().equals("" + position)) {
                            Bitmap bitmap = UploadPhotoUtil.getInstance().trasformToZoomBitmapAndLessMemory(uploadImgUrlList.get(0));
                            holder.theme_img1.setImageDrawable(new BitmapDrawable(context.getResources(), bitmap));
                        }
                        if (holder.theme_img2.getTag().equals("" + position)) {
                            Bitmap bitmap = UploadPhotoUtil.getInstance().trasformToZoomBitmapAndLessMemory(uploadImgUrlList.get(1));
                            holder.theme_img2.setImageDrawable(new BitmapDrawable(context.getResources(), bitmap));
                        }
                    }
                    if (uploadImgUrlListSize == 3) {
                        holder.theme_img1.setVisibility(View.VISIBLE);
                        holder.theme_img2.setVisibility(View.VISIBLE);
                        holder.theme_img3.setVisibility(View.VISIBLE);
                        if (holder.theme_img1.getTag().equals("" + position)) {
                            Bitmap bitmap = UploadPhotoUtil.getInstance().trasformToZoomBitmapAndLessMemory(uploadImgUrlList.get(0));
                            holder.theme_img1.setImageDrawable(new BitmapDrawable(context.getResources(), bitmap));
                        }
                        if (holder.theme_img2.getTag().equals("" + position)) {
                            Bitmap bitmap = UploadPhotoUtil.getInstance().trasformToZoomBitmapAndLessMemory(uploadImgUrlList.get(1));
                            holder.theme_img2.setImageDrawable(new BitmapDrawable(context.getResources(), bitmap));
                        }
                        if (holder.theme_img3.getTag().equals("" + position)) {
                            Bitmap bitmap = UploadPhotoUtil.getInstance().trasformToZoomBitmapAndLessMemory(uploadImgUrlList.get(2));
                            holder.theme_img3.setImageDrawable(new BitmapDrawable(context.getResources(), bitmap));
                        }
                    }
                }
                holder.user_name.setText(object.getUserName());
                holder.theme_title.setText(object.getThemeTitle());
                holder.theme_desc.setText(object.getThemeDescr());
                holder.theme_reply_num.setText(object.getReplyNum() + "");
                holder.publish_theme_time.setText(CommonUtils.getUtilInstance()
                        .transformMillisToDate(object.getCreateTime()));
                holder.theme_praise_num.setText(object.getPraiseNum() + "");
            }
            holder.user_photo.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                }
            });
        }
        return convertView;

    }

    class ViewHolder {
        ImageView user_photo, theme_img1, theme_img2, theme_img3;
        TextView user_name, theme_title, theme_desc, publish_theme_time,
                theme_reply_num, theme_praise_num, game_playing,
                game_playing_prefix;
    }


}