package com.natancode.carros.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED) //mapeando herança. Fazendo uma tabela para cda classe
public class Locacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id; 
	private Date instanteLocacao;
	private Date instanteDevolucao;
	
	public Locacao() {}
	
	public Locacao(Integer id, Date instanteLocacao, Date instanteDevolucao) {
		super();
		this.id = id;
		this.instanteLocacao = instanteLocacao;
		this.instanteDevolucao = instanteDevolucao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstanteLocacao() {
		return instanteLocacao;
	}

	public void setInstanteLocacao(Date instanteLocacao) {
		this.instanteLocacao = instanteLocacao;
	}

	public Date getInstanteDevolucao() {
		return instanteDevolucao;
	}

	public void setInstanteDevolucao(Date instanteDevolucao) {
		this.instanteDevolucao = instanteDevolucao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Locacao other = (Locacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
