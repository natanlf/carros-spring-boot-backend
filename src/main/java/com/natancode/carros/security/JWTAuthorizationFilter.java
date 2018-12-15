package com.natancode.carros.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

//Esse filtro vai ter que analisar o Token para ver se o mesmo é válido
//Para analisar o token vamos ter que extrair o usuário do token e ve se exite mesmo, por isso precisamos do UserDetailsService
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private UserDetailsService userDetailsService; //busca o usuário por email
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain chain)
			throws IOException, ServletException {
		
		//No objeto request, tenho como obter o cabeçalho de minha requisição
		//Recebe como argumento o nome do cabeçalho
		String header = request.getHeader("Authorization");
		
		if(header != null && header.startsWith("Bearer ")) {
			//retorna o token caso seja válido
			UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));
			if(auth!=null) { //se for diferente de nulo é porque está tudo certo com o token
				//Liberar autorização do usuário que está tentando acessar o EndPoint
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		chain.doFilter(request, response); //continua a fazer a requisição normalmente
	}
	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if(jwtUtil.tokenValido(token)) { //se o token for válido
			String username = jwtUtil.getUsername(token); //pega o username do token
			UserDetails user = userDetailsService.loadUserByUsername(username); //procura o usuário no banco
			//como não uso credenciais passo null. Authorities são os perfis que criei 
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}
		return null;
	}
}