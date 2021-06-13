package com.spring.service;

public class Book_review_comment {
	
	private int commentId;
	private int repostId;
	private String comment;
	private String id;
	private String regdate;
	private String profileImgUrl;
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getRepostId() {
		return repostId;
	}
	public void setRepostId(int repostId) {
		this.repostId = repostId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	

	public String getProfileImgUrl() {
		return profileImgUrl;
	}
	public void setProfileImgUrl(String profileImgUrl) {
		this.profileImgUrl = profileImgUrl;
	}

}
	