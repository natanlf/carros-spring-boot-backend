package com.natancode.carros.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


public class SmtpEmailService extends AbstractEmailService {

	//Tem as configurações do SMTP que está no arquivo propeties
		@Autowired
		private MailSender mailSender;
		
		@Autowired
		private JavaMailSender javaMailSender;
		
		private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	 	@Override
		public void sendEmail(SimpleMailMessage msg) {
			LOG.info("Enviando de email...");
			mailSender.send(msg); //Envia o email
			LOG.info("Email enviado");
		}
	 	
	 	@Override
		public void sendHtmlEmail(MimeMessage msg) {
			LOG.info("Enviando de email...");
			javaMailSender.send(msg); //javaMailSender é capaz de enviar um MimeMessage
			LOG.info("Email enviado");
		}
}