package com.example.administrator.uploadphoto;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.uploadphoto.MyInterface.NetRequestIterface;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/22.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, NetRequestIterface{
    private TextView title;
    private RelativeLayout upload_photo_layout,upload_pic_layout;
    private NetRequest requestFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();

            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        title=(TextView)findViewById(R.id.title);
        upload_photo_layout=(RelativeLayout)findViewById(R.id.upload_photo_layout);
        upload_pic_layout=(RelativeLayout)findViewById(R.id.upload_pic_layout);
        title.setText(getResources().getString(R.string.choose_upload));
        login();
    }
    //这是以前我公司的项目（高手GoSu,可下载看看），我把更新头像和上传图片摘出来，和大家分享。先登录，然后才能操作
    private void login(){
        requestFragment = new NetRequest(this, this);
        Map map = new HashMap();
        map.put("userName", "15652009705");
        map.put("pwd", "123456");
        map.put("thirdPart", "");
        requestFragment.httpRequest(map, CommonUrl.loginAccountNew);
    }
    @Override
    public void changeView(String result, String requestUrl) {
        if (requestUrl.equals(CommonUrl.loginAccountNew)) {
            Log.d("gaolei", "result-----------loginAccountNew----------------" + result);

            Gson gson = new Gson();
            LoginStatusInfoObject loginStatusInfoObject = gson
                    .fromJson(result,
                            LoginStatusInfoObject.class);
            String errorCode = loginStatusInfoObject
                    .getErrorCode();
            if (errorCode.equals("0")) {

                UserInfoUtil.getInstance().setAuthKey(
                        loginStatusInfoObject.getAuthKey());
                UserInfoUtil.getInstance().setUserId(
                        loginStatusInfoObject.getUser()
                                .getUserId());
                UserInfoUtil.getInstance()
                        .setId(loginStatusInfoObject.getUser()
                                .getId());
                UserInfoUtil.getInstance().setPhotoUrl(
                        loginStatusInfoObject.getUser()
                                .getPhotoUrl());
                UserInfoUtil.getInstance().setNickName(
                        loginStatusInfoObject.getUser()
                                .getNickName());
                UserInfoUtil.getInstance().setSignature(
                        loginStatusInfoObject.getUser()
                                .getSignature());
                UserInfoUtil.getInstance().setSex(
                        loginStatusInfoObject.getUser()
                                .getSex());
                UserInfoUtil.getInstance().setFollowNum(
                        loginStatusInfoObject.getUser()
                                .getFollowNum());
                UserInfoUtil.getInstance().setFansNum(
                        loginStatusInfoObject.getUser()
                                .getFansNum());
                UserInfoUtil.getInstance().setGoldNum(
                        loginStatusInfoObject.getUser()
                                .getGoldNum());
                UserInfoUtil.getInstance().setThemeNum(
                        loginStatusInfoObject.getUser()
                                .getThemeNum());
                UserInfoUtil.getInstance().setIsMessageReceive(
                        loginStatusInfoObject.getUser()
                                .getIsMessageReceive());
                MyApplication.getInstance().setMyName(
                        loginStatusInfoObject.getUser().getNickName());
                MyApplication.getInstance().setMyPhoto(
                        loginStatusInfoObject.getUser().getUserId());

            }}
    }

    @Override
    public void exception(IOException e, String requestUrl) {

    }

    @Override
    public void onClick(View v) {

    }
    public void toUploadPhotoActvity(View view) {
        startActivity(new Intent(this, UploadPhotoActivity.class));
    }
    public void toUploadPicActvity(View view){
        startActivity(new Intent(this,UploadPicActivity.class));
    }
}
