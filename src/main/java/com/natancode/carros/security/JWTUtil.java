package com.natancode.carros.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}
	
	public boolean tokenValido(String token) {
		//armazena  as reivindicações do token, no nosso caso é o usuário e o tempo de expiração do token
		//o usuário alega que é o usuário x e que o tempo de expiração do token é x
		Claims claims = getClaims(token); //vamos obter os claims a partir do token
		
		if(claims!=null) {
			String username = claims.getSubject(); //pega o usuário
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			
			//Se tem usuário, data de expiração e a data de expiração é menor que a atual, o token é valido
			if(username!=null && expirationDate!=null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}
	
	//Vamos obter o usuário a partir do token
	public String getUsername(String token) {
		Claims claims = getClaims(token); //vamos obter os claims a partir do token
		
		if(claims!=null) {
			return claims.getSubject(); //pega o usuário
		}
		return null;
	}
 	//Recupera os claims a partir de um token
	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e) { //se o token é inválido ou deu algum problema, retorna nulo
			return null;
		}	
	}
}
