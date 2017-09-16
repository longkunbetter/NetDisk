package com.lk.bean;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.lk.dao.ShareItemDao;
import com.lk.dao.ShareItemDaoImpl;
import com.lk.dao.UserDao;
import com.lk.dao.UserDaoImpl;

public class TestBean {
	@Test
	public void relationTest() {
		ShareItemDao dao = new ShareItemDaoImpl();
		ShareItem shareItem = dao.getById(7);
		System.out.println(shareItem.getFilename());
		System.out.println(shareItem.getUser().getUsername());
	}

	@Test
	public void userTest() {
		String hql = "from User where username='test_user'";
		UserDao  dao = new UserDaoImpl();
		List<User> list = dao.queryByHQL(hql);
		if (list.size() == 0)
			System.out.println("null");
		else
			System.out.println(list.get(0).getUsername());
	}
	
	@Test
	public void relationTest2(){
		ShareItemDao dao = new ShareItemDaoImpl();
		ShareItem si = dao.getById(13);
		Set set = si.getDiscuss();
		Discuss[] discuss = new Discuss[set.size()];
		System.out.println(set.size());
		set.toArray(discuss);
		for (Discuss d : discuss){
			System.out.println(d.getUser().getUsername());
		}
	}
}
