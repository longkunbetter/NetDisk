package com.lk.dao;

import com.lk.bean.ShareItem;

public interface ShareItemDao extends BaseDao<ShareItem, Integer>{
	public boolean delete(int share_id);
	public boolean exist(ShareItem shareItem);
}
