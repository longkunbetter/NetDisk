package com.lk.bean;


public class Discuss{

	private Integer id;
	private ShareItem item;
	private User user;
	
	public Discuss(){
		
	}
	
	public Discuss(ShareItem item, User user) {
		super();
		this.item = item;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}
