package com.natancode.carros.domain;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class LocacaoLongoPeriodo extends Locacao {
	
	private static final long serialVersionUID = 1L;

	private Double desconto;
	
	public LocacaoLongoPeriodo() {}
	
	public LocacaoLongoPeriodo(Integer id, Date instanteLocacao, Date instanteDevolucao, Double desconto) {
		super(id, instanteLocacao, instanteDevolucao);
		this.desconto = desconto;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}
}
