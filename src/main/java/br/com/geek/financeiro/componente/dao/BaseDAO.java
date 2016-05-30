package br.com.geek.financeiro.componente.dao;

import java.util.List;

import com.googlecode.objectify.Key;

import br.com.geek.financeiro.componente.entity.BaseEntity;

public interface BaseDAO<T extends BaseEntity<I>,I> {
	public Key<T> add(T entity);

	public List<T> findAll();

	public T findById(I id);

	public boolean delete(I id);

	public boolean delete(T entity);

	public boolean deleteAll();

}
