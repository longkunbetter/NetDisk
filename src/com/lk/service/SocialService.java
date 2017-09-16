package com.lk.service;

import java.util.Set;

import com.lk.bean.Comment;

public interface SocialService {
	public boolean addDiscuss(String username, int shareid);
	public boolean removeDiscuss(String username, int shareid);
	public boolean addComment(String username, int shareid, String text);
	public boolean removeComment(int shareid);
	public Set<Comment> getShareComments(int shareid);
}
