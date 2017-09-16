package com.lk.service;

import com.lk.bean.User;
import com.lk.dao.UserDao;

public class UserServiceImpl implements UserService {
	private UserDao userDao = null;

	public void setUserDao(UserDao userdao) {
		this.userDao = userdao;
	}

	@Override
	public User verifyUser(User user) {
		User u = userDao.getUserByName(user.getUsername());
		if (u != null && user.getPassword().equals(u.getPassword()))
			return u;
		return null;
	}

	@Override
	public boolean regesterUser(User user) {
		User u = userDao.getUserByName(user.getUsername());
		if (u != null)
			return false;
		
		return userDao.save(user);
	}

}
