package com.lk.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T, I extends Serializable> {
	public boolean save(T entiry);
	public boolean saveOrUpdate(T entiry);
	public T getById(I id);
	public boolean delete(T entiry);
	public boolean deleteAll();
	public boolean update(T entiry);
	public List<T> queryByHQL(String hql);
	public List<T> queryBySQL(String sql);
	public List<T> listAll();
	public List<T> listByPage(int pid, int pagesize);
	public long countRecords();
}
