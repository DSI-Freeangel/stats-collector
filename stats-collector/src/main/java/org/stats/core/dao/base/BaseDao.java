package org.stats.core.dao.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

@Transactional
public abstract class BaseDao<T> {
	@Autowired
	private SessionFactory sessionFactory;
	private Class<T> clazz;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	protected Session getSession() {
		return getSessionFactory().getCurrentSession();
	}
	
	protected BaseDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	@SuppressWarnings("unchecked")
	public T get(Long id) {
		return (T) getSession().get(this.clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> getList(List<Long> ids) {
		if(CollectionUtils.isEmpty(ids)) {
			return new ArrayList<T>();
		}
		Criteria criteria = getSession().createCriteria(this.clazz).add(Restrictions.in("id", ids));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		Criteria criteria = getSession().createCriteria(this.clazz);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public T save(T entity) {
		return (T) getSession().save(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> save(Collection<T> entities) {
		List<T> result = new ArrayList<T>();
		Session session = getSession();
		for(T entity : entities){
			result.add((T) session.save(entity));
		}
		return result;
	}
	
	public void delete(T entity) {
		getSession().delete(entity);
	}
	
	@Transactional
	public void delete(Collection<T> entities) {
		for(T entity : entities){
			getSession().delete(entity);
		}
	}
}
