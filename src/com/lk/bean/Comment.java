package com.lk.bean;

public class Comment {
	private Integer id;
	private String text;
	private ShareItem item;
	private User user;
	
	public Comment(){}
	
	public Comment(User user, ShareItem item, String text){
		this.item = item;
		this.user = user;
		this.text = text;
	}

	public Integer getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ShareItem getItem() {
		return item;
	}

	public void setItem(ShareItem item) {
		this.item = item;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}