package com.natancode.carros.domain;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class LocacaoDiaria extends Locacao {

	private static final long serialVersionUID = 1L;
	
	private Integer diasPrevistos;
	
	public LocacaoDiaria() {}

	public LocacaoDiaria(Integer id, Date instanteLocacao, Date instanteDevolucao, Integer diasPrevistos) {
		super(id, instanteLocacao, instanteDevolucao);
		this.diasPrevistos = diasPrevistos;
	}

	public Integer getDiasPrevistos() {
		return diasPrevistos;
	}

	public void setDiasPrevistos(Integer diasPrevistos) {
		this.diasPrevistos = diasPrevistos;
	}
}
