package com.natancode.carros.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.natancode.carros.dto.EmailDTO;
import com.natancode.carros.security.JWTUtil;
import com.natancode.carros.security.UserSS;
import com.natancode.carros.services.AuthService;
import com.natancode.carros.services.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
	
 	@Autowired
	private JWTUtil jwtUtil;
 	
 	@Autowired
 	private AuthService service;
	
	//Dá um refresh no token e o usuário precisa estar logado para acessar esse método
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
		service.sendNewPassword(objDto.getEmail());
		return ResponseEntity.noContent().build();
	}
}
