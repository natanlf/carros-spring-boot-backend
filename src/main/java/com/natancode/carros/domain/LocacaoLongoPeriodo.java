package com.natancode.carros.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("LocacaoLongoPeriodo")
public class LocacaoLongoPeriodo extends Locacao {
	
	private static final long serialVersionUID = 1L;

	private Double desconto;
	
	public LocacaoLongoPeriodo() {}
	
	public LocacaoLongoPeriodo(Integer id, Date instanteLocacao, Date instanteDevolucao, Cliente cliente, Carro carro, Sede sede, Double desconto) {
		super(id, instanteLocacao, instanteDevolucao, cliente, carro, sede);
		this.desconto = desconto;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}
}
