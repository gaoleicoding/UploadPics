package com.example.administrator.uploadphoto;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.uploadphoto.MyInterface.NetRequestIterface;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UploadPhotoActivity extends AppCompatActivity implements View.OnClickListener, NetRequestIterface {
    private final int UPLOAD_TAKE_PICTURE = 4;
    private final int UPLOAD_LOCAL_PICTURE =5;
    private final int SAVE_PHOTO_IMAGE = 6;
    private final int NONE = 0, TAKE_PICTURE = 1, LOCAL_PICTURE = 2;
    private final int SHOW_UPDATE_PHOTO = 3;
    private RelativeLayout update_photo_layout, edit_photo_fullscreen_layout, edit_photo_outer_layout,
            uploading_photo_progress;
    private Animation get_photo_layout_out_from_up, get_photo_layout_in_from_down;
    private TextView take_picture, select_local_picture;
    private ImageView my_photo_image;
    private String photo_take_file_path = MyApplication.photo_path + "temp.png";
    private String photo_local_file_path, photo_url = null;
    private Intent intent;
    private NetRequest requestFragment;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case SHOW_UPDATE_PHOTO:
                    CommonUtils.getUtilInstance().displayCircleImage(msg.obj + "",
                            my_photo_image, "photo");
                    photo_url = msg.obj + "";
                    UserInfoUtil.getInstance().setPhotoUrl(photo_url);
                    uploading_photo_progress.setVisibility(View.GONE);
                    break;
                case UPLOAD_LOCAL_PICTURE:
                    Log.d("gaolei", " case UPLOAD_LOCAL_PICTURE:------------------");
                    Uri uri = intent.getData();

                    try {
                        String[] pojo = {MediaStore.Images.Media.DATA};
                        // The method managedQuery(Uri, String[], String, String[],
                        // String) from the type Activity is deprecated
                        // android用CursorLoader代替

                        CursorLoader cursorLoader = new CursorLoader(UploadPhotoActivity.this, uri, pojo, null,null, null);
                        Cursor cursor = cursorLoader.loadInBackground();
                        cursor.moveToFirst();
                        String photo_local_file_path = cursor.getString(cursor.getColumnIndex(pojo[0]));
                        uploadUserPhotoNew(photo_local_file_path);

                    Log.d("gaolei", "uri.getPath()------------------" + uri.getPath());

                    } catch (Exception e) {
                    }
                    break;
                case UPLOAD_TAKE_PICTURE:
                    Log.d("gaolei", " case UPLOAD_TAKE_PICTURE:------------------");
                    Log.d("gaolei", "photo_take_file_path------------------" + photo_take_file_path);
                    uploadUserPhotoNew(photo_take_file_path);
                    break;
                case SAVE_PHOTO_IMAGE:
                    Log.d("gaolei", " case SAVE_PHOTO_IMAGE:------------------");
                    Map<String, Object> map = (Map<String, Object>) msg.obj;
                    requestFragment.httpRequest(map, CommonUrl.uploadUserPhotoNew);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_photo_activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();

            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        requestFragment = new NetRequest(this, this);
        update_photo_layout = (RelativeLayout) findViewById(R.id.update_photo_layout);
        edit_photo_fullscreen_layout = (RelativeLayout) findViewById(R.id.edit_photo_fullscreen_layout);
        edit_photo_outer_layout = (RelativeLayout) findViewById(R.id.edit_photo_outer_layout);
        uploading_photo_progress = (RelativeLayout) findViewById(R.id.uploading_photo_progress);
        take_picture = (TextView) findViewById(R.id.take_picture);
        my_photo_image = (ImageView) findViewById(R.id.my_photo_image);
        select_local_picture = (TextView) findViewById(R.id.select_local_picture);
        take_picture.setOnClickListener(this);
        select_local_picture.setOnClickListener(this);
        CommonUtils.getUtilInstance().displayCircleImage(
                UserInfoUtil.getInstance().getPhotoUrl(), my_photo_image,
                "photo");
    }

    public void uploadUserPhotoNew(final String filePath) {
        uploading_photo_progress.setVisibility(View.VISIBLE);
        //为什么另开一个线程呢?因为要把图片字节流转化为字符串上传，比较耗时，阻塞UI线程，会使应用卡卡卡，所以要另开一线程
        new Thread() {
            public void run() {
                String fileType = UploadPhotoUtil.getInstance().getFileType(filePath);
                String fileString = UploadPhotoUtil.getInstance().getUploadPhotoZoomString(
                        filePath);
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("imgType", fileType);
                map.put("imgBody", fileString);
                Message msg = handler.obtainMessage();
                msg.obj = map;
                msg.what = SAVE_PHOTO_IMAGE;
                handler.sendMessage(msg);

            }
        }.start();
    }

    public void showEditPhotoLayout(View view) {
        edit_photo_fullscreen_layout.setVisibility(View.VISIBLE);
        get_photo_layout_in_from_down = AnimationUtils.loadAnimation(
                this, R.anim.search_layout_in_from_down);
        edit_photo_outer_layout.startAnimation(get_photo_layout_in_from_down);
    }

    public void hideEditPhotoLayout(View view) {
        get_photo_layout_out_from_up = AnimationUtils.loadAnimation(
                this, R.anim.search_layout_out_from_up);
        edit_photo_outer_layout.startAnimation(get_photo_layout_out_from_up);
        get_photo_layout_out_from_up
                .setAnimationListener(new AnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation arg0) {
                        // TODO Auto-generated method stub
                        edit_photo_fullscreen_layout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation arg0) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onAnimationStart(Animation arg0) {
                        // TODO Auto-generated method stub
                    }
                });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        //为什么不在这处理图片呢？因为处理图片比较耗时，如果在这里处理图片，从图库或者拍照Activity时会不流畅，很卡卡卡，试试就知道了
        if (resultCode == NONE)
            return;
        if (requestCode == TAKE_PICTURE) {
            handler.sendEmptyMessage(UPLOAD_TAKE_PICTURE);
            return;
        }
        if (resultCode == Activity.RESULT_OK) {
            this.intent = intent;
            handler.sendEmptyMessage(UPLOAD_LOCAL_PICTURE);

        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.take_picture:
                edit_photo_fullscreen_layout.setVisibility(View.GONE);
                File file = new File(photo_take_file_path);
                if (file.exists()) {
                    if (file.exists()) {
                        file.delete();
                    }
                }
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(intent, TAKE_PICTURE);
                break;
            case R.id.select_local_picture:
                edit_photo_fullscreen_layout.setVisibility(View.GONE);
                intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        "image/*");
                startActivityForResult(intent, LOCAL_PICTURE);
                break;
        }
    }

    @Override
    public void changeView(String result, String requestUrl) {

        if (requestUrl.equals(CommonUrl.uploadUserPhotoNew)) {
            Log.d("gaolei", "result-----------uploadUserPhotoNew----------------" + result);

            UpdateUserInfoObject object = new Gson().fromJson(result,
                    UpdateUserInfoObject.class);
            String photoUrl = object.getUserInfo().getPhotoUrl();
            Message msg = handler.obtainMessage();
            msg.obj = photoUrl;
            msg.what = SHOW_UPDATE_PHOTO;
            handler.sendMessage(msg);

        }

    }

    @Override
    public void exception(IOException e, String requestUrl) {
        Log.d("gaolei", "exception--------------------------" + e.getMessage());
    }

}
