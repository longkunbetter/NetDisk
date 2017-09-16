package com.lk.service;

import java.util.List;

import com.lk.bean.ShareItem;

public interface ShareService {
	public int addShare(String username, String filepath);
	public ShareItem getShareItem(int shareId);
	public List<ShareItem> getShareItem(String username);
	public boolean removeShare(int share_id);
	public String downloadShare(int share_id);
}
