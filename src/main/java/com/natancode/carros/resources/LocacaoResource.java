package com.natancode.carros.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.natancode.carros.domain.Locacao;
import com.natancode.carros.services.LocacaoService;

@RestController
@RequestMapping(value="/locacoes")
public class LocacaoResource {
	
	@Autowired
	private LocacaoService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<Locacao> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Locacao obj){ // http de resposta é 201 para inserção, RequestBody faz o json ser convertido para objeto java	
		obj =service.insert(obj); //a operação save retorna um objeto	
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri(); //assim temos a url de requisição
		return ResponseEntity.created(uri).build();
	}
}
