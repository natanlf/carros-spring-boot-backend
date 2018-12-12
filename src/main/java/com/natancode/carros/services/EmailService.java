package com.natancode.carros.services;

import org.springframework.mail.SimpleMailMessage;

import com.natancode.carros.domain.Locacao;

public interface EmailService {

	void senderLocacaoConfirmationEmail(Locacao obj);
	
	void sendEmail(SimpleMailMessage msg);
}
