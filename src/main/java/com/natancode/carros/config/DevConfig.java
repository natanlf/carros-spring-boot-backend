package com.natancode.carros.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.natancode.carros.services.DBService;
import com.natancode.carros.services.EmailService;
import com.natancode.carros.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService; 
	
	//pego o valor da propeties par saber se crio o banco novamente, se atualizo, faço nada...
		@Value("${spring.jpa.hibernate.ddl-auto}")
		private String strategy; 
		
		@Bean
		public boolean instantiateDatabase() throws ParseException {
			
			//se não for para criar o bd retorta false e não faz nada
			if(!"create".equals(strategy))
				return false;
			
			dbService.instantiateTestDatabase(); //instancio a classe que popula o banco
			return true;
		}
		
		@Bean
		public EmailService emailService() { //Para poder enviar o email
			return new SmtpEmailService();
		}
		
}
