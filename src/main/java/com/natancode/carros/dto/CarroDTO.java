package com.natancode.carros.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class CarroDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=7, max=8, message="O tamanho deve ser entre 7 e 8 caracteres")
	private String placa;
	
	@NotNull
	@Min(1)
	@Max(3)
	private Integer cor;
	
	@NotNull
	private Integer ano;
	
	@NotNull
	private Integer sedeId;
	
	public CarroDTO() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Integer getCor() {
		return cor;
	}

	public void setCor(Integer cor) {
		this.cor = cor;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getSedeId() {
		return sedeId;
	}

	public void setSedeId(Integer sedeId) {
		this.sedeId = sedeId;
	}

}
