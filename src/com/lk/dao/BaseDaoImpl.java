package com.lk.dao;

import java.io.Serializable;

/*
 * 
 * Õ®”√dao
 * 
 * */


import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.lang.reflect.Type;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lk.util.HibernateUtil;

public class BaseDaoImpl<T, I extends Serializable> implements BaseDao<T, I> {
	private Class<?> entiry_type = null;
	private Class<?> id_type = null;

	public BaseDaoImpl() {
		ParameterizedType pType = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] types = pType.getActualTypeArguments();
		entiry_type = (Class<?>) types[0];
		System.out.println(entiry_type.getName());
		id_type = (Class<?>) types[1];
		System.out.println(id_type.getName());
	}

	@Override
	public boolean save(T entiry) {
		boolean res = true;
		Session session = HibernateUtil.getSession();
		Transaction ts = session.beginTransaction();
		session.save(entiry);
		try {
			ts.commit();
		} catch (Exception e) {
			res = false;
		} finally {
			session.close();
		}
		return res;
	}

	@Override
	public boolean saveOrUpdate(T entiry) {
		boolean res = true;
		Session session = HibernateUtil.getSession();
		Transaction ts = session.beginTransaction();

		session.saveOrUpdate(entiry);

		try {
			ts.commit();
		} catch (Exception e) {
			res = false;
		} finally {
			session.close();
		}
		return res;
	}

	@Override
	public boolean delete(T entiry) {
		boolean res = true;
		Session session = HibernateUtil.getSession();
		Transaction ts = session.beginTransaction();

		session.delete(entiry);

		try {
			ts.commit();
		} catch (Exception e) {
			res = false;
		} finally {
			session.close();
		}
		return res;
	}

	@Override
	public boolean deleteAll() {
		String hql = "delete from " + this.entiry_type.getName();
		boolean res = true;

		Session session = HibernateUtil.getSession();
		Transaction ts = session.beginTransaction();
		session.createQuery(hql).executeUpdate();
		try {
			ts.commit();
		} catch (Exception e) {
			res = false;
		} finally {
			session.close();
		}
		return res;
	}

	@Override
	public T getById(I id) {
		Session session = HibernateUtil.getSession();
		return (T) session.get(entiry_type, id);
	}

	@Override
	public boolean update(T entiry) {
		boolean res = true;
		Session session = HibernateUtil.getSession();
		Transaction ts = session.beginTransaction();

		session.update(entiry);

		try {
			ts.commit();
		} catch (Exception e) {
			res = false;
		} finally {
			session.close();
		}
		return res;
	}

	@Override
	public List<T> queryByHQL(String hql) {
		Session session = HibernateUtil.getSession();
		List<T> res = session.createQuery(hql).list();
		session.close();
		return res;
	}

	@Override
	public List<T> queryBySQL(String sql) {
		Session session = HibernateUtil.getSession();
		List<T> res = session.createSQLQuery(sql).list();
		session.close();
		return res;
	}

	@Override
	public List<T> listAll() {
		List<T> res = null;
		String hql = "from " + this.entiry_type.getName();
		Session session = HibernateUtil.getSession();
		res = session.createQuery(hql).list();
		session.close();
		return res;
	}

	@Override
	public List<T> listByPage(int pid, int pagesize) {
		long recordsCount = this.countRecords();
		int start = (pid - 1) * pagesize;
		String hql = "from " + this.entiry_type.getName();
		Session session = HibernateUtil.getSession();
		List<T> list =
			session.createQuery(hql)
			.setFirstResult(start)
			.setMaxResults(pagesize).list();
		session.close();
		return list;
	}
	
	@Override
	public long countRecords() {
		String hql = "select count(*) from " + this.entiry_type;
		Session session = HibernateUtil.getSession();
		return ((Long)session.createQuery(hql).uniqueResult()).longValue();
	}

}
