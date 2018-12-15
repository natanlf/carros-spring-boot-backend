package com.natancode.carros.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.natancode.carros.domain.Cliente;
import com.natancode.carros.domain.Locacao;
import com.natancode.carros.repositories.LocacaoRepository;
import com.natancode.carros.security.UserSS;
import com.natancode.carros.services.exceptions.AuthorizationException;
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
	
	public Page<Locacao> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		UserSS user = UserService.authenticated();
		if(user==null) { //se estiver nulo então o usuário não está autenticado
			throw new AuthorizationException("Acesso negado");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		//vou retornar somente os pedidos do cliente que está logado
		Cliente cliente = clienteService.find(user.getId());
		return repo.findByCliente(cliente, pageRequest);
	}

}
