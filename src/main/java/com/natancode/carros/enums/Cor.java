package com.natancode.carros.enums;

public enum Cor {
	
	BRANCO(1, "Branco"),
	PRETO(2, "Preto"),
	CINZA(3, "Cinza"),
	VERMELHO(4, "Vermelho"),
	AZUL(5, "Azul"),
	AMARELO(6, "Amarelo"),
	VERDE(7, "Verde");
	
	private int cod;
	private String descricao;
	
	private Cor(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static Cor toEnum(Integer cod) { //Devolvo uma cor a partir de um cod
		if(cod==null) {
			return null;
		}
		
		for(Cor x : Cor.values()) { //percorre todos os valores possíveis das cores
			if(cod.equals(x.getCod())) { //se o que passei for igual ao da lista
				return x;
			}
		}
		
		//Se entrar no for e não achar, quer dizer que deu exception
		throw new IllegalArgumentException("Id inválido: "+cod);
	}
}
