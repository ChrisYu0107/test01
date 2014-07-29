package com.zjpii.zjkfweb.service;

import java.io.Serializable;
import java.util.List;

import com.zjpii.zjkfweb.util.Pager;

public interface BaseService<T> {
	//写操作
	public void saveEntity(T t);
	public void updateEntity(T t);
	public void saveOrUpdateEntity(T t);
	public void deleteEntity(T t);
	//按照hql批处理实体
	public void batchEntityByHQL(String hql,Object...objects);
	public T getEntity(Serializable id);
	public T loadEntity(Serializable id);
	public List<T> findEntityByHQL(String hql,Object...objects);
	public Object uniqueResult(String hql, Object...objects);
	public Pager pagedQuery(String hql, int pageNo, int pageSize, Object... values);
}
