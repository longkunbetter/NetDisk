package com.lk.service;


import com.lk.bean.User;

public interface UserService {
	public User verifyUser(User user);
	public boolean regesterUser(User user);
}
