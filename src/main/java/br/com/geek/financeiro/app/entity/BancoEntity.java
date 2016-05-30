package br.com.geek.financeiro.app.entity;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import br.com.geek.financeiro.componente.entity.BaseEntity;

@Entity
public class BancoEntity extends BaseEntity<String> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 216801724766728157L;

	@Id
	private String nome;
	private String cor;
	private Double saldo;

	public BancoEntity() {
	}

	public BancoEntity(String nome, String cor) {
		super();
		this.nome = nome;
		this.cor = cor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	@Override
	public String getId() {
		return nome;
	}

	@Override
	public void setId(String id) {
		setNome(id);
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
}
