package com.lk.dao;

import java.util.List;

import com.lk.bean.User;

public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao{

	@Override
	public User getUserByName(String username) {
		String hql = "from User where username='" + username + "'";
		List<User> list = super.queryByHQL(hql);
		if (list.size() == 0)
			return null;
		return list.get(0);
	}
	
}
