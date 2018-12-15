package com.natancode.carros.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.natancode.carros.security.JWTUtil;
import com.natancode.carros.security.UserSS;
import com.natancode.carros.services.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
 	@Autowired
	private JWTUtil jwtUtil;
	
	//Dá um refresh no token e o usuário precisa estar logado para acessar esse método
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}
}
