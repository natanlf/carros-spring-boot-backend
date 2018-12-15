package com.natancode.carros.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.natancode.carros.security.UserSS;

public class UserService {
 	//Userss tem id, login, senha e authorities que são os perfis
	public static UserSS authenticated() {
		//Retorna o usuário que está logado no sistema
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null; //se não tiver usuário logado retorna null
		}
		
	}
}