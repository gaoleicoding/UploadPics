package com.example.administrator.uploadphoto;

public class CommonUrl {

	private static final String netUrl="http://203.195.182.52:8078/";
	private static final String netImgUrl="http://203.195.182.52:8082/";
	
	public static final String url = netUrl+"tongwan-gosu-service/json/";
	public static final String imageUrl =netImgUrl+"gosu-pic/";
	public static final String loginAccountNew= url + "accountLogin/loginAccountNew";//登录接口，登陆之后才能更换头像、发表主题
	public static final String uploadUserPhotoNew  = url + "account/uploadUserPhotoNew";//上传头像接口
	public static final String getThemeList    = url + "community/getThemeList";//获取发布的主题接口
	public static final String saveThemeInfo    = url + "account/saveThemeInfo";//保存发表主题接口
	public static final String saveThemeImgNew    = url + "account/saveThemeImgNew";//保存发表的图片接口

}
