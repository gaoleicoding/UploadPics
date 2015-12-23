package com.example.administrator.uploadphoto;

public class LoginStatusInfoObject {
	private String authKey = null;

	private UserInfoObject user;
	private String errorCode;

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}


	public UserInfoObject getUser() {
		return user;
	}

	public void setUser(UserInfoObject user) {
		this.user = user;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}



}