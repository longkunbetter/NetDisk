package com.lk.action;

import java.util.Map;

import com.lk.bean.User;
import com.lk.service.UserService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserService userService = null;
	
	public void setUserService(UserService userService){
		this.userService = userService;
	}

	private User user = null;

	public User getUser() {
		return user;
	}

	@Override
	public User getModel() {
		if (this.user == null)
			user = new User();
		return user;
	}

	public String login() {
		User userInfo = userService.verifyUser(user);
		if (userInfo == null)
			return LOGIN;

		String username = userInfo.getUsername();
		String usertype = userInfo.isManager() ?  "MANAGER" : "NORMAL";
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("username", username);
		session.put("usertype", usertype);
		return SUCCESS;
	}
	
	public String register(){
		boolean res = userService.regesterUser(user);
		if (res == false){
			ActionContext.getContext().put("error_msg", "该用户名已被注册");
			return Action.ERROR;
		}
		
		this.login();
		return Action.SUCCESS;
	}
	
	public String loginout(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove("username");
		session.remove("usertype");
		return LOGIN;
	}
	
}
