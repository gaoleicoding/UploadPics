package com.example.administrator.uploadphoto;

import java.io.IOException;

/**
 *  项目中用到的回调接口
 */
public class MyInterface {

    /**
     * 网络请求回调接口
     */
    public interface NetRequestIterface {
        void changeView(String result, String requestUrl);
        void exception(IOException e, String requestUrl);
    }
    /**
     * 点击退出查看添加图片大图回调接口
     */
    public interface OnSingleTapDismissBigPhotoListener {
        void onDismissBigPhoto();
    }
}
