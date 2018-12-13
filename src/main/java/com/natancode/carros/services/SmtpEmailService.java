package com.natancode.carros.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;


public class SmtpEmailService extends AbstractEmailService {

	//Tem as configurações do SMTP que está no arquivo propeties
		@Autowired
		private MailSender mailSender;
		
		private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	 	@Override
		public void sendEmail(SimpleMailMessage msg) {
			LOG.info("Enviando de email...");
			mailSender.send(msg); //Envia o email
			LOG.info("Email enviado");
		}
}