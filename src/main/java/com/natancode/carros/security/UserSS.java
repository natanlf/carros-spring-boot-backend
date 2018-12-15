package com.natancode.carros.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.natancode.carros.enums.Perfil;

public class UserSS implements UserDetails {
 	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String email;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserSS() {
		
	}
	
	//depois vamos converter o Perfil para a lista de authorities que o Spring Security entede
	public UserSS(Integer id, String email, String senha, Set<Perfil> perfis) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}
 	public Integer getId() {
		return id;
	}
 	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
 	@Override
	public String getPassword() {
		return senha;
	}
 	@Override
	public String getUsername() {
		return email;
	}
 	@Override
	public boolean isAccountNonExpired() { //conta expirada
		return true;
	}
 	@Override
	public boolean isAccountNonLocked() { //conta bloqueada
		return true;
	}
 	@Override
	public boolean isCredentialsNonExpired() { //credencial expirada
		return true;
	}
 	@Override
	public boolean isEnabled() { //usuário está ativo
		return true;
	}
 	
 	public boolean hasRole(Perfil perfil) { //varifica o perfil do usuário
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
	}
 }