package com.natancode.carros.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natancode.carros.domain.Locacao;
import com.natancode.carros.repositories.LocacaoRepository;
import com.natancode.carros.services.exceptions.ObjectNotFoundException;

@Service
public class LocacaoService {

	@Autowired
	private LocacaoRepository repo;
	
	@Autowired
	private EmailService emailService;

	public Locacao find(Integer id) {
		Optional<Locacao> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Locacao.class.getName(), null));
	}

	public List<Locacao> findAll() {
		return repo.findAll();
	}
	
	@Transactional
	public Locacao insert(Locacao obj) {
		obj.setId(null);
		obj.setInstanteLocacao(new Date());
		
		repo.save(obj);
		emailService.senderLocacaoConfirmationEmail(obj);
		return obj;
	}

}
