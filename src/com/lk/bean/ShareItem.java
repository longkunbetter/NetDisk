package com.lk.bean;

import java.util.HashSet;
import java.util.Set;

public class ShareItem {
	private Integer id;
	private User user;
	private Set<Discuss> discuss = new HashSet<>();
	private Set<Comment> comments = new HashSet<>();
	private String filename;
	private String filepath;
	private Integer discuss_count;
	
	public ShareItem(){
		this.discuss_count = 0;
	}
	
	public ShareItem(User user, String filename, String filepath){
		this.user = user;
		this.filename = filename;
		this.filepath = filepath;
		this.discuss_count = 0;
	}

	public Set<Discuss> getDiscuss() {
		return discuss;
	}

	public void setDiscuss(Set<Discuss> discuss) {
		this.discuss = discuss;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Integer getDiscuss_count() {
		return discuss_count;
	}

	public void setDiscuss_count(Integer discuss_count) {
		this.discuss_count = discuss_count;
	}

}
