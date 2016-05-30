package br.com.geek.financeiro.componente.entity;

import java.io.Serializable;

public abstract class BaseEntity<I> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6554423531203501162L;

	public abstract I getId();
	
	public abstract void setId(I id);

}
