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
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired 
	private CarroService carroService;
	
	@Autowired
	private SedeService sedeService;

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
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.setCarro(carroService.find(obj.getCarro().getId()));
		obj.setSede(sedeService.find(obj.getSede().getId()));
		repo.save(obj);
		emailService.sendLocacaoConfirmationHtmlEmail(obj);
		return obj;
	}

}
