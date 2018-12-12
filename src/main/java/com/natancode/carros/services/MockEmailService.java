package com.natancode.carros.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

//É uma subclasse da classe abstrata
public class MockEmailService extends AbstractEmailService {
	//assim não instancia toda vez que chama. Será um log referente a essa classe
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulando envio de email...");
		LOG.info(msg.toString());
		LOG.info("Email enviado");
		
	}
}
