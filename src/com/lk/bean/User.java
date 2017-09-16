package com.lk.bean;

import java.util.HashSet;
import java.util.Set;

public class User {
	private int id;
	private String username;
	private String password;
	private boolean isManager;
	private Set<ShareItem> discuss = new HashSet<>();
	private Set<ShareItem> share_items = new HashSet<>();
	private Set<Comment> comments = new HashSet<>();

	public User() {

	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.isManager = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	public Set<ShareItem> getDiscuss() {
		return discuss;
	}

	public void setDiscuss(Set<ShareItem> discuss) {
		this.discuss = discuss;
	}

	public Set<ShareItem> getShare_items() {
		return share_items;
	}

	public void setShare_items(Set<ShareItem> share_items) {
		this.share_items = share_items;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

}
