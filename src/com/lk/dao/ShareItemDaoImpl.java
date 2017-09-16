package com.lk.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.lk.bean.ShareItem;
import com.lk.util.HibernateUtil;

public class ShareItemDaoImpl extends BaseDaoImpl<ShareItem, Integer> implements ShareItemDao{
	
	@Override
	public boolean delete(int share_id) {
		ShareItem si = new ShareItem();
		si.setId(share_id);
		return super.delete(null);
	}

	@Override
	public boolean exist(ShareItem shareItem) {
		String path = shareItem.getFilepath();
		int uid = shareItem.getUser().getId();
		String hql = "from ShareItem where filepath=? and uid=?";
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);
		query.setString(0, path);
		query.setInteger(1, uid);

		boolean res = false;
		if (query.uniqueResult() != null)
			res = true;
		
		session.close();
		
		return res;
	}
}
