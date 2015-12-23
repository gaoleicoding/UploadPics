package com.example.administrator.uploadphoto;

public class UserInfoUtil {
	public static UserInfoUtil userInfoUtil;
	private String authKey =null;

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public String getClassKey() {
		return classKey;
	}

	public void setClassKey(String classKey) {
		this.classKey = classKey;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getRecentLook() {
		return recentLook;
	}

	public void setRecentLook(String recentLook) {
		this.recentLook = recentLook;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getFansNum() {
		return fansNum;
	}

	public void setFansNum(int fansNum) {
		this.fansNum = fansNum;
	}

	public int getFavoriteNum() {
		return favoriteNum;
	}

	public void setFavoriteNum(int favoriteNum) {
		this.favoriteNum = favoriteNum;
	}

	public int getFollowNum() {
		return followNum;
	}

	public void setFollowNum(int followNum) {
		this.followNum = followNum;
	}

	public int getGoldNum() {
		return goldNum;
	}

	public void setGoldNum(int goldNum) {
		this.goldNum = goldNum;
	}

	public int getIsGameUpdate() {
		return isGameUpdate;
	}

	public void setIsGameUpdate(int isGameUpdate) {
		this.isGameUpdate = isGameUpdate;
	}

	public int getIsMessageReceive() {
		return isMessageReceive;
	}

	public void setIsMessageReceive(int isMessageReceive) {
		this.isMessageReceive = isMessageReceive;
	}

	public int getLoginDays() {
		return loginDays;
	}

	public void setLoginDays(int loginDays) {
		this.loginDays = loginDays;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getVideoNum() {
		return videoNum;
	}

	public void setVideoNum(int videoNum) {
		this.videoNum = videoNum;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}



	public long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public long getRegistDate() {
		return registDate;
	}

	public void setRegistDate(long registDate) {
		this.registDate = registDate;
	}

	private String classKey, englishName, id, nickName, photoUrl, recentLook,
			signature, userId="";
	private int age, fansNum, themeNum, favoriteNum, followNum, goldNum,isFollow,
			isGameUpdate, isMessageReceive, loginDays, sex, videoNum;

	public int getIsFollow() {
		return isFollow;
	}

	public void setIsFollow(int isFollow) {
		this.isFollow = isFollow;
	}

	public int getThemeNum() {
		return themeNum;
	}

	public void setThemeNum(int themeNum) {
		this.themeNum = themeNum;
	}

	private Float latitude, longitude;
	private long lastLoginTime, registDate;

	public static UserInfoUtil getInstance() {
		if (userInfoUtil == null) {
			userInfoUtil = new UserInfoUtil();
		}
		return userInfoUtil;
	}

}
