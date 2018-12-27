package com.natancode.carros.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Inheritance(strategy=InheritanceType.JOINED) //mapeando herança. Fazendo uma tabela para cada classe
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public class Locacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id; 
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date instanteLocacao;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date instanteDevolucao;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="carro_id")
	private Carro carro;
	
	@ManyToOne
	@JoinColumn(name="sede_id")
	private Sede sede;
	
	public Locacao() {}
	
	public Locacao(Integer id, Date instanteLocacao, Date instanteDevolucao, Cliente cliente, Carro carro, Sede sede) {
		super();
		this.id = id;
		this.instanteLocacao = instanteLocacao;
		this.instanteDevolucao = instanteDevolucao;
		this.cliente = cliente;
		this.carro = carro;
		this.sede = sede;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}
	
	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
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

	@Override
	public String toString() {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
				StringBuilder builder = new StringBuilder();
				builder.append("Pedido número: ");
				builder.append(getId());
				builder.append(", Data locação: ");
				builder.append(sdf.format(getInstanteLocacao()));
				
				builder.append(", Data de devolução: ");
				builder.append(sdf.format(getInstanteDevolucao()));
				
				builder.append("\nCliente:\n");
				builder.append(getCliente().getNome());
				
				builder.append("\nDetalhes:\n");
				builder.append("\nCarro:\n");
				builder.append(getCarro().getNome());
				
				builder.append("\nSede:\n");
				builder.append("Logradouro: ");
				builder.append(getSede().getEndereco().getLogradouro());
				builder.append(", Complemento: ");
				builder.append(getSede().getEndereco().getComplemento());
				builder.append(", Bairro: ");
				builder.append(getSede().getEndereco().getBairro());
				builder.append(", Cep: ");
				builder.append(getSede().getEndereco().getCep());
				builder.append(", Cidade: ");
				builder.append(getSede().getEndereco().getCidade().getNome());
				builder.append(", Estado: ");
				builder.append(getSede().getEndereco().getCidade().getEstado().getNome());
				return builder.toString();
	}
	
	
}
