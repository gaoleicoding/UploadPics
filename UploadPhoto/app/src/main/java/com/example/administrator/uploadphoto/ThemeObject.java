package com.example.administrator.uploadphoto;

import java.util.List;

public class ThemeObject {


		private String communityImage, gameId, gameName, themeId, id,downloadUrl,
				themeDescr, themeTitle, userId, userName, userPhoto,replyName,descr;
		private boolean hasPraised=false;
		private int sex;
		public int getSex() {
			return sex;
		}

		public void setSex(int sex) {
			this.sex = sex;
		}

		public boolean isHasPraised() {
			return hasPraised;
		}

		public void setHasPraised(boolean hasPraised) {
			this.hasPraised = hasPraised;
		}

		public String getDownloadUrl() {
			return downloadUrl;
		}

		public void setDownloadUrl(String downloadUrl) {
			this.downloadUrl = downloadUrl;
		}
		private int repliesNum, praiseNum, replyNum, state, themeNum,clickNum,hasFavorite;
		public int getHasFavorite() {
			return hasFavorite;
		}

		public void setHasFavorite(int hasFavorite) {
			this.hasFavorite = hasFavorite;
		}
		private long createTime,lastRepliesTime, replyTime;
		private List<PictureList> pictureList;
		private ThemeInfo themeInfo;

		public ThemeInfo getThemeInfo() {
			return themeInfo;
		}

		public void setThemeInfo(ThemeInfo themeInfo) {
			this.themeInfo = themeInfo;
		}

		public List<PictureList> getPictureList() {
			return pictureList;
		}

		public void setPictureList(List<PictureList> pictureList) {
			this.pictureList = pictureList;
		}

		public int getClickNum() {
			return clickNum;
		}

		public String getReplyName() {
			return replyName;
		}

		public void setReplyName(String replyName) {
			this.replyName = replyName;
		}

		public String getDescr() {
			return descr;
		}

		public void setDescr(String descr) {
			this.descr = descr;
		}

		public void setClickNum(int clickNum) {
			this.clickNum = clickNum;
		}

		public long getCreateTime() {
			return createTime;
		}

		public void setCreateTime(long createTime) {
			this.createTime = createTime;
		}

		public String getCommunityImage() {
			return communityImage;
		}

		public void setCommunityImage(String communityImage) {
			this.communityImage = communityImage;
		}

		public String getGameId() {
			return gameId;
		}

		public void setGameId(String gameId) {
			this.gameId = gameId;
		}

		public String getGameName() {
			return gameName;
		}

		public void setGameName(String gameName) {
			this.gameName = gameName;
		}

		public String getThemeId() {
			return themeId;
		}

		public void setThemeId(String themeId) {
			this.themeId = themeId;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getThemeDescr() {
			return themeDescr;
		}

		public void setThemeDescr(String themeDescr) {
			this.themeDescr = themeDescr;
		}

		public String getThemeTitle() {
			return themeTitle;
		}

		public void setThemeTitle(String themeTitle) {
			this.themeTitle = themeTitle;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getUserPhoto() {
			return userPhoto;
		}

		public void setUserPhoto(String userPhoto) {
			this.userPhoto = userPhoto;
		}

		public int getRepliesNum() {
			return repliesNum;
		}

		public void setRepliesNum(int repliesNum) {
			this.repliesNum = repliesNum;
		}

		public int getPraiseNum() {
			return praiseNum;
		}

		public void setPraiseNum(int praiseNum) {
			this.praiseNum = praiseNum;
		}

		public int getReplyNum() {
			return replyNum;
		}

		public void setReplyNum(int replyNum) {
			this.replyNum = replyNum;
		}

		public int getState() {
			return state;
		}

		public void setState(int state) {
			this.state = state;
		}

		public int getThemeNum() {
			return themeNum;
		}

		public void setThemeNum(int themeNum) {
			this.themeNum = themeNum;
		}

		public long getLastRepliesTime() {
			return lastRepliesTime;
		}

		public void setLastRepliesTime(long lastRepliesTime) {
			this.lastRepliesTime = lastRepliesTime;
		}

		public long getReplyTime() {
			return replyTime;
		}

		public void setReplyTime(long replyTime) {
			this.replyTime = replyTime;
		}

		public class PictureList {
			private String id, relateId, url;
			private int state, type;
			private long createTime;

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public String getRelateId() {
				return relateId;
			}

			public void setRelateId(String relateId) {
				this.relateId = relateId;
			}

			public String getUrl() {
				return url;
			}

			public void setUrl(String url) {
				this.url = url;
			}

			public int getState() {
				return state;
			}

			public void setState(int state) {
				this.state = state;
			}

			public int getType() {
				return type;
			}

			public void setType(int type) {
				this.type = type;
			}

			public long getCreateTime() {
				return createTime;
			}

			public void setCreateTime(long createTime) {
				this.createTime = createTime;
			}
		}
		public class ThemeInfo{
			private int clickNum,praiseNum,replyNum,state;
			private long createTime,replyTime;
			private String gameId,id,themeTitle,themeDescr,userId,userName,userPhoto;
			private List<PictureList> pictureList;
			public int getClickNum() {
				return clickNum;
			}
			public void setClickNum(int clickNum) {
				this.clickNum = clickNum;
			}
			public int getPraiseNum() {
				return praiseNum;
			}
			public void setPraiseNum(int praiseNum) {
				this.praiseNum = praiseNum;
			}
			public int getReplyNum() {
				return replyNum;
			}
			public void setReplyNum(int replyNum) {
				this.replyNum = replyNum;
			}
			public int getState() {
				return state;
			}
			public void setState(int state) {
				this.state = state;
			}
			public long getCreateTime() {
				return createTime;
			}
			public void setCreateTime(long createTime) {
				this.createTime = createTime;
			}
			public long getReplyTime() {
				return replyTime;
			}
			public void setReplyTime(long replyTime) {
				this.replyTime = replyTime;
			}
			public String getGameId() {
				return gameId;
			}
			public void setGameId(String gameId) {
				this.gameId = gameId;
			}
			public String getId() {
				return id;
			}
			public void setId(String id) {
				this.id = id;
			}
			public String getThemeTitle() {
				return themeTitle;
			}
			public void setThemeTitle(String themeTitle) {
				this.themeTitle = themeTitle;
			}
			public String getThemeDescr() {
				return themeDescr;
			}
			public void setThemeDescr(String themeDescr) {
				this.themeDescr = themeDescr;
			}
			public String getUserId() {
				return userId;
			}
			public void setUserId(String userId) {
				this.userId = userId;
			}
			public String getUserName() {
				return userName;
			}
			public void setUserName(String userName) {
				this.userName = userName;
			}
			public String getUserPhoto() {
				return userPhoto;
			}
			public void setUserPhoto(String userPhoto) {
				this.userPhoto = userPhoto;
			}
			public List<PictureList> getPictureList() {
				return pictureList;
			}
			public void setPictureList(List<PictureList> pictureList) {
				this.pictureList = pictureList;
			}
		}
	}
