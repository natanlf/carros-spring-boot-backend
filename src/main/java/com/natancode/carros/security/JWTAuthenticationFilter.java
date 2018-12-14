package com.natancode.carros.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.natancode.carros.dto.CredenciaisDTO;

//Verifica se usuário e senha estão corretos através desse filtro de autenticação
//Quando uso UsernamePasswordAuthenticationFilter o Spring sabe que tem qe interceptar o login
//Inclusive o /login da chamada de login já é um nome reservado pelo Spring Security
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	
private AuthenticationManager authenticationManager;
  
  private JWTUtil jwtUtil;
   public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
  	//chama a classe abaixo que conserta o problema de erro de email e senha inválidos
  	setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
      this.authenticationManager = authenticationManager;
      this.jwtUtil = jwtUtil;
  }
	
	//Tenta autenticar
	@Override
	 public Authentication attemptAuthentication(HttpServletRequest req,
           HttpServletResponse res) throws AuthenticationException {
		try {
			//Tenta pegar no objeto req(objeto de requisição) o login se nha para tentar autenticar
			CredenciaisDTO creds = new ObjectMapper()
	                .readValue(req.getInputStream(), CredenciaisDTO.class); //pega os dados da requisição e converte para o tipo CrendenciaisDTO
	
	        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getSenha(), new ArrayList<>());
	        
	        //Método que verifica se usuário e senha são válidos
	        //O framework faz isso com base no que impleentei nas classes UserDetails, UserDetailsService
	        Authentication auth = authenticationManager.authenticate(authToken); 
	        return auth;
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Se a autenticação ocorrer com sucesso
	//Se autenticar com sucesso, vai gerar um TOKEN e acrescentar na resposta da requisição
	//Esse método já recebe o auth que foi produzido no método acima que tenta autenticar
	@Override
  protected void successfulAuthentication(HttpServletRequest req,
                                          HttpServletResponse res,
                                          FilterChain chain,
                                          Authentication auth) throws IOException, ServletException {
		
		//getPrincipal() = retorna o usuário do SpringSecurity
		String username = ((UserSS) auth.getPrincipal()).getUsername();
      String token = jwtUtil.generateToken(username); //Gera o token passando email como parametro
      res.addHeader("Authorization", "Bearer " + token); //retorno o token no cabeçalho da resposta
	}
	
	//classe abaixo que conserta o problema de erro de email e senha inválidos
	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {
		 
      @Override
      public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
              throws IOException, ServletException {
          response.setStatus(401);
          response.setContentType("application/json"); 
          response.getWriter().append(json());
      }
      
      private String json() {
          long date = new Date().getTime();
          return "{\"timestamp\": " + date + ", "
              + "\"status\": 401, "
              + "\"error\": \"Não autorizado\", "
              + "\"message\": \"Email ou senha inválidos\", "
              + "\"path\": \"/login\"}";
      }
  }
}