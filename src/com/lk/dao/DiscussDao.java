package com.lk.dao;

import com.lk.bean.Discuss;
import com.lk.bean.ShareItem;
import com.lk.bean.User;

public interface DiscussDao extends BaseDao<Discuss, Integer>{
	public Discuss exist(User user, ShareItem shareItem);
}
