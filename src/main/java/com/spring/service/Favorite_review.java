package com.spring.service;

public class Favorite_review {

	private int favorite_reviewId;
	private int repostId;
	private String id;
	private int bookID;
	private String title;
	private String book_name;
	private String coverImgUrl;
	private String content;
	private String profileImgUrl;
	private String regdate;
	private String writer;
	

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getCoverImgUrl() {
		return coverImgUrl;
	}

	public void setCoverImgUrl(String coverImgUrl) {
		this.coverImgUrl = coverImgUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getProfileImgUrl() {
		return profileImgUrl;
	}

	public void setProfileImgUrl(String profileImgUrl) {
		this.profileImgUrl = profileImgUrl;
	}

	public int getfavorite_reviewId() {
		return favorite_reviewId;
	}

	public void setfavorite_reviewId(int favorite_reviewId) {
		this.favorite_reviewId = favorite_reviewId;
	}

	public int getrepostId() {
		return repostId;
	}

	public void setrepostId(int repostId) {
		this.repostId = repostId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
