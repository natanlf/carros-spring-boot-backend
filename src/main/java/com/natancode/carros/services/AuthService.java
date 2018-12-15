package com.natancode.carros.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.natancode.carros.domain.Cliente;
import com.natancode.carros.repositories.ClienteRepository;
import com.natancode.carros.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {
	
 	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random(); //gera valores aleatórios para nós
	
	public void sendNewPassword(String email) {
		//vou verificar se esse cliente existe
		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente==null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		String newPass = newPassword();
		cliente.setSenha(pe.encode(newPass)); //seto a nova senha criptografada
		
		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass);
	}
	
 	private String newPassword() {
		//gera senha de 10 caracteres que podem ser dígitos ou letras
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar(); //método que gera caracteres aleatoriamente
		}
		return new String(vet);
	}
 	
 	private char randomChar() {
		int opt = rand.nextInt(3); // gera um número: 0, 1 ou 2
		if(opt==0) { //gera um dígito
			return (char) (rand.nextInt(10) + 48);//tabela unicode vê-la, gera um número de 0 até 9
		}else if(opt == 1) {//gera a letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		}else { //gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
		
	}
}