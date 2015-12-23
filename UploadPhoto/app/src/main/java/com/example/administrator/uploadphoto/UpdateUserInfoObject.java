package com.example.administrator.uploadphoto;

public class UpdateUserInfoObject {
	private String authKey = null;
	private UserInfoObject userInfo;
	private int errorCode;

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public UserInfoObject getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoObject userInfo) {
		this.userInfo = userInfo;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}