package com.zjpii.zjkfweb.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zjpii.zjkfweb.dao.IBaseDao;
import com.zjpii.zjkfweb.service.BaseService;
import com.zjpii.zjkfweb.util.Pager;



/**
 * 抽象的service实现,专门用于继承
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {
	
	private IBaseDao<T> dao ;
	
	//注入dao
	@Autowired
	public void setDao(IBaseDao<T> dao) {
		this.dao = dao;
	}

	public void saveEntity(T t) {
		dao.saveEntity(t);
	}

	public void updateEntity(T t) {
		dao.updateEntity(t);
	}

	public void saveOrUpdateEntity(T t) {
		dao.saveOrUpdateEntity(t);
	}

	public void deleteEntity(T t) {
		dao.deleteEntity(t);
	}

	public void batchEntityByHQL(String hql, Object... objects) {
		dao.batchEntityByHQL(hql, objects);
	}

	public T getEntity(Serializable id) {
		return dao.getEntity(id);
	}

	public T loadEntity(Serializable id) {
		return dao.loadEntity(id);
	}

	public List<T> findEntityByHQL(String hql, Object... objects) {
		return dao.findEntityByHQL(hql, objects);
	}
	
	public Object uniqueResult(String hql, Object...objects){
		return dao.uniqueResult(hql, objects);
	}

	public Pager pagedQuery(String hql, int pageNo, int pageSize, Object... values){
		return dao.pagedQuery(hql, pageNo, pageSize, values);
	}
}
