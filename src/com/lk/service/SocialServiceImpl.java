package com.lk.service;

import java.util.Set;

import com.lk.bean.Comment;
import com.lk.bean.Discuss;
import com.lk.bean.ShareItem;
import com.lk.bean.User;
import com.lk.dao.CommentDao;
import com.lk.dao.DiscussDao;
import com.lk.dao.ShareItemDao;
import com.lk.dao.UserDao;

public class SocialServiceImpl implements SocialService {
	DiscussDao discussDao = null;
	UserDao userDao = null;
	ShareItemDao shareItemDao = null;
	CommentDao commentDao = null;

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	public void setDiscussDao(DiscussDao discussDao) {
		this.discussDao = discussDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setShareItemDao(ShareItemDao shareItemDao) {
		this.shareItemDao = shareItemDao;
	}

	@Override
	public boolean addDiscuss(String username, int shareid) {
		User user = userDao.getUserByName(username);
		ShareItem shareItem = shareItemDao.getById(shareid);
		if (discussDao.exist(user, shareItem) != null) {
			return false;
		}
		Discuss discuss = new Discuss(shareItem, user);
		boolean res = discussDao.save(discuss);
		return res;
	}

	@Override
	public boolean removeDiscuss(String username, int shareid) {
		User user = userDao.getUserByName(username);
		ShareItem shareItem = shareItemDao.getById(shareid);
		Discuss discuss = discussDao.exist(user, shareItem);
		if (discuss == null)//如果没有赞过则返回失败
			return false;
		
		return discussDao.delete(discuss);//取消赞，删除对应记录
	}

	@Override
	public boolean addComment(String username, int shareid, String text) {
		Comment comment = new Comment();
		ShareItem shareItem = shareItemDao.getById(shareid);
		User user = userDao.getUserByName(username);
		comment.setItem(shareItem);
		comment.setUser(user);
		comment.setText(text);
		return commentDao.save(comment);
	}

	@Override
	public boolean removeComment(int shareid) {
		return false;
	}

	@Override
	public Set<Comment> getShareComments(int shareid) {
		ShareItem si = shareItemDao.getById(shareid);
		Set<Comment> comments = si.getComments();
		return comments;
	}

}
