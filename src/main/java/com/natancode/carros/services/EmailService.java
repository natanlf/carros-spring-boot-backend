package com.natancode.carros.services;

import org.springframework.mail.SimpleMailMessage;
import javax.mail.internet.MimeMessage;

import com.natancode.carros.domain.Locacao;

public interface EmailService {

	void senderLocacaoConfirmationEmail(Locacao obj);

	void sendEmail(SimpleMailMessage msg);

	// Enviar email html
	void sendLocacaoConfirmationHtmlEmail(Locacao obj);

	void sendHtmlEmail(MimeMessage msg);
}
