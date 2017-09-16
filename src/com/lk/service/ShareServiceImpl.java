package com.lk.service;

import java.util.ArrayList;
import java.util.List;

import com.lk.bean.ShareItem;
import com.lk.bean.User;
import com.lk.dao.FileDao;
import com.lk.dao.ShareItemDao;
import com.lk.dao.ShareItemDaoImpl;
import com.lk.dao.UserDao;

public class ShareServiceImpl implements ShareService{
	private FileDao fileDao = null;
	private ShareItemDao shareItemDao = null;
	private UserDao userDao = null;
	
	public void setShareItemDao(ShareItemDaoImpl shareItemDao) {
		this.shareItemDao = shareItemDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	@Override
	public int addShare(String username, String filepath) {
		ShareItem shareItem = new ShareItem();
		User user = userDao.getUserByName(username);
		if (user == null)
			return -1;
		
		String[] temp = filepath.split("/");
		String filename = temp[temp.length-1];
		
		shareItem.setUser(user);
		shareItem.setFilepath(filepath);
		shareItem.setFilename(filename);
		
		if (this.shareItemDao.exist(shareItem)==false && this.shareItemDao.save(shareItem) == true){
			return shareItem.getId();
		}
		return -1;
	}
	
	@Override
	public ShareItem getShareItem(int shareId){
		return shareItemDao.getById(shareId);
	}
	
	@Override
	public List<ShareItem> getShareItem(String username){
		User user = userDao.getUserByName(username);
		List<ShareItem> list = null;
		if (user != null){
			list = new ArrayList<>(user.getShare_items());
		}
		return list;
	}
	
	@Override
	public String downloadShare(int share_id){
		ShareItem shareItem = this.getShareItem(share_id);
		String username = shareItem.getUser().getUsername();
		String path = shareItem.getFilepath();
		return this.fileDao.fileDownload(username, path);
	}

	@Override
	public boolean removeShare(int share_id) {
		return shareItemDao.delete(share_id);
	}
	
	private boolean isExist(ShareItem shareItem){
		return shareItemDao.exist(shareItem);
	}

}
