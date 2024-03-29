package com.project.mytechblog.entities;

import java.sql.Date;

public class Post {
	private int postId;
	private String postTitle;
	private String postContent;
	private String postCode;
	private Date postDate;
	private int catId;
	private String postPic;
	private int userId;
	
	public Post(int postId, String postTitle, String postContent, String postCode, Date postDate, int catId, String postPic, int userId) {
		super();
		this.postId = postId;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.postCode = postCode;
		this.postDate = postDate;
		this.catId = catId;
		this.postPic = postPic;
		this.userId = userId;
	}

	public Post() {
		super();
	}

	public Post(String postTitle, String postContent, String postCode, String postPic, Date postDate, int catId, int userId) {
		super();
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.postCode = postCode;
		this.postDate = postDate;
		this.catId = catId;
		this.postPic = postPic;
		this.userId = userId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getPostPic() {
		return postPic;
	}

	public void setPostPic(String postPic) {
		this.postPic = postPic;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	

	
}
