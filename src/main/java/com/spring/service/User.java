package com.spring.service;

public class User {
	private int useridx;
	private String id;
	private String pw;
	private String nickname;
	private String email;
	private String userImgUrl;


	public int getUseridx() {
		return useridx;
	}
	public void setUseridx(int useridx) {
		this.useridx = useridx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getuserImgUrl() {
		return userImgUrl;
	}
	public void setuserImgUrl(String userImgUrl) {
		this.userImgUrl = userImgUrl;
	}
	
}
