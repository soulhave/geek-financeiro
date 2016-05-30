package br.com.geek.financeiro.componente.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

import br.com.geek.financeiro.componente.dao.BaseDAO;
import br.com.geek.financeiro.componente.entity.BaseEntity;

/**
 * Base DAO, responsável por gerenciar metodos básicos comuns ao projetos
 * 
 * @author ramon
 *
 * @param <T>
 * @param <I>
 */
public abstract class BaseDAOImpl<T extends BaseEntity<I>, I> implements BaseDAO<T, I> {

	protected Class<T> clazz;

	@SuppressWarnings("unchecked")
	public BaseDAOImpl() {
		clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.swapbudget.persistence.dao.GenericDAO#add(java.lang.Object)
	 */
	@Override
	public Key<T> add(T entity) {
		if (entity == null) {
			return null;
		}
		Objectify objectify = ObjectifyService.ofy();
		Key<T> key = objectify.save().entity(entity).now();

		return key;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.swapbudget.persistence.dao.GenericDAO#findAll()
	 */
	@Override
	public List<T> findAll() {
		Objectify objectify = ObjectifyService.ofy();
		List<T> entities = objectify.load().type(clazz).list();
		if (entities == null || entities.size() <= 0) {
			return new ArrayList<T>();
		}
		return entities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.swapbudget.persistence.dao.GenericDAO#findById(java.io.
	 * Serializable)
	 */
	@Override
	public T findById(I id) {
		Objectify objectify = ObjectifyService.ofy();
		T entity = null;

		if (id instanceof Long) {
			entity = objectify.load().type(clazz).id((Long) id).now();
		} else if (id instanceof String) {
			entity = objectify.load().type(clazz).id((String) id).now();
		}
		return entity;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public boolean delete(I id) {
		return delete(findById(id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.swapbudget.persistence.dao.GenericDAO#delete(java.lang.Object)
	 */
	@Override
	public boolean delete(T entity) {
		Objectify objectify = ObjectifyService.ofy();
		objectify.delete().entity(entity).now();

		// if group ID = null, it was deleted
		if (entity.getId() == null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean deleteAll() {
		Objectify objectify = ObjectifyService.ofy();
		List<Key<T>> keys = objectify.load().type(clazz).keys().list();
		objectify.delete().keys(keys).now();
		return true;
	}
}
