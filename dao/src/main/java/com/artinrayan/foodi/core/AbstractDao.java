package com.artinrayan.foodi.core;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * AbstractDao to be extended by other daos
 *
 * @param <PK>
 * @param <T>
 */
public abstract class AbstractDao<PK extends Serializable, T> {
	
	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDao(){
		this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 *
	 * @return
     */
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	/**
	 *
	 * @param key
	 * @return
     */
	@SuppressWarnings("unchecked")
	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	/**
	 *
	 * @param entity
     */
	public void persist(T entity) {
		getSession().saveOrUpdate(entity);
	}

	/**
	 *
	 * @param entity
     */
	public void update(T entity) {
		getSession().update(entity);
	}

	/**
	 *
	 * @param entity
     */
	public void delete(T entity) {
		getSession().delete(entity);
	}

	/**
	 *
	 * @return
     */
	protected Criteria createEntityCriteria(){
		return getSession().createCriteria(persistentClass);
	}

	/**
	 *
	 * @param hql
	 * @return
     */
	protected Query createQuery(String hql){
		return getSession().createQuery(hql);
	}

}
