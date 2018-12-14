package com.natancode.carros.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.natancode.carros.domain.Cliente;
import com.natancode.carros.repositories.ClienteRepository;
import com.natancode.carros.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService { //Implementa um método do Spring Security
	 	@Autowired
		private ClienteRepository repo;
		
		//Vai receber o usuário e vai retornar o UserDetails
		@Override
		public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			Cliente cli = repo.findByEmail(email);
			if(cli==null) {
				throw new UsernameNotFoundException(email);
			}
			
			return new UserSS(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfil());
		}
}
