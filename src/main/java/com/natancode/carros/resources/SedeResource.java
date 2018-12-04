package com.natancode.carros.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.natancode.carros.domain.Sede;
import com.natancode.carros.dto.SedeDTO;
import com.natancode.carros.dto.SedeNewDTO;
import com.natancode.carros.services.SedeService;

@RestController
@RequestMapping(value="/sedes")
public class SedeResource {
	
	@Autowired
	private SedeService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Sede obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<Sede> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/{id}" ,method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody SedeDTO objDto, @PathVariable Integer id){
		Sede obj = service.fromDTO(objDto);
		obj.setId(id); //garantindo que vai atualizar com o id passado
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody SedeNewDTO objDto){ // http de resposta é 201 para inserção, RequestBody faz o json ser convertido para objeto java	
		Sede obj = service.fromDTO(objDto);
		obj =service.insert(obj); //a operação save retorna um objeto	
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri(); //assim temos a url de requisição
		return ResponseEntity.created(uri).build();
	}
}
