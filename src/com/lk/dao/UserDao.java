package com.lk.dao;

import com.lk.bean.User;

public interface UserDao extends BaseDao<User, Integer>{
	public User getUserByName(String username);
}
