package com.natancode.carros.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.natancode.carros.domain.Cliente;
import com.natancode.carros.domain.Locacao;

public interface EmailService {

	void senderLocacaoConfirmationEmail(Locacao obj);

	void sendEmail(SimpleMailMessage msg);

	// Enviar email html
	void sendLocacaoConfirmationHtmlEmail(Locacao obj);

	void sendHtmlEmail(MimeMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);
}
