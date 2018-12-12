package com.natancode.carros.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.natancode.carros.domain.Locacao;

public abstract class AbstractEmailService implements EmailService{
	
	@Value("${default.sender}")
	private String sender;
 	//Estou sobrescrevendo o método de minha interface EmailService
	@Override
	public void senderLocacaoConfirmationEmail(Locacao obj) {
		SimpleMailMessage sm =  prepareSimpleMailMessageFromPedido(obj); //preparo o email
		sendEmail(sm); //método da minha interface EmailService
	}
 	//protected para poder ser acessado por minhas subclasses
	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Locacao obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Código: "+obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis())); //currentTime pega a data do servidor
		sm.setText(obj.toString());	
		return sm;
	}
}