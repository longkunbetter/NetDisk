package com.lk.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.lk.bean.Discuss;
import com.lk.bean.ShareItem;
import com.lk.bean.User;
import com.lk.util.HibernateUtil;

public class DiscussDaoImpl extends BaseDaoImpl<Discuss, Integer> implements DiscussDao {

	@Override
	public Discuss exist(User user, ShareItem shareItem) {
		int uid = user.getId();
		int sid = shareItem.getId();

		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Discuss where sid=? and uid=?");
		query.setInteger(0, sid);
		query.setInteger(1, uid);
		Object obj = query.uniqueResult();
		session.close();
		
		return (Discuss) obj;
	}

}
