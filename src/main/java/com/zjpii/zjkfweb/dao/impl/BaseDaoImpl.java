package com.zjpii.zjkfweb.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;
import com.zjpii.zjkfweb.dao.IBaseDao;
import com.zjpii.zjkfweb.util.Pager;

/**
 * dao基类实现
 */
@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	@Autowired
	public void setSuperHibernateTemplate(HibernateTemplate hibernateTemplate) {
		super.setHibernateTemplate(hibernateTemplate);
	}

	private Class<T> clazz;

	public BaseDaoImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];

	}

	public void saveEntity(T t) {
		this.getHibernateTemplate().save(t);
	}

	public void updateEntity(T t) {
		this.getHibernateTemplate().update(t);
	}

	public void saveOrUpdateEntity(T t) {
		this.getHibernateTemplate().saveOrUpdate(t);
	}

	public void deleteEntity(T t) {
		this.getHibernateTemplate().delete(t);
	}

	public void batchEntityByHQL(String hql, Object... objects) {
		Query q = this.getSessionFactory().getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		q.executeUpdate();
	}

	// get
	public T getEntity(Serializable id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	// load
	public T loadEntity(Serializable id) {
		return (T) this.getHibernateTemplate().load(clazz, id);
	}

	public List<T> findEntityByHQL(String hql, Object... objects) {
		Query q = this.getSessionFactory().getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		return q.list();
	}

	// 单值检索(查询结果有且仅有一条记录)
	public Object uniqueResult(String hql, Object... objects) {
		Query q = this.getSessionFactory().getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		return q.uniqueResult();
	}


	public Pager pagedQuery(String hql, int pageNo, int pageSize, Object... values) {
		Assert.hasText(hql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count查询
		String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));
		List countlist = getHibernateTemplate().find(countQueryString, values);
		long totalCount = (Long) countlist.get(0);
		if (totalCount < 1)
			return new Pager();
		// 实际查询返回分页对象
		int startIndex = Pager.getStartOfPage(pageNo, pageSize);
		Query query = createQuery(hql, values);
		List list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
		return new Pager(startIndex, totalCount, pageSize, list);
	}
	
	private  String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
	
	private  String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
		return hql.substring(beginPos);
	}

	
	public Query createQuery(String hql, Object... values) {
		Assert.hasText(hql);
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		if(values!=null && values.length>0){
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}
}
