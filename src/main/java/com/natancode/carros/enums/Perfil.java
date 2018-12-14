package com.natancode.carros.enums;

public enum Perfil {
 	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private int cod;
	private String descricao;
	
	private Perfil(int cod, String descricao) { //construtor de enum é private
		this.cod = cod;
		this.descricao = descricao;
	} 
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(Integer cod) { //Pega o número inteiro e converte para perfil
		if(cod==null) {
			return null;
		}
		
		for(Perfil x : Perfil.values()) { //percorre todos os valores possíveis do meu tipo cliente
			if(cod.equals(x.getCod())) { //se o que passei for igual ao da lista
				return x;
			}
		}
		
		//Se entrar no for e não achar, quer dizer que deu exception
		throw new IllegalArgumentException("Id inválido: "+cod);
	}
}