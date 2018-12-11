package com.natancode.carros.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.natancode.carros.domain.Carro;
import com.natancode.carros.dto.CarroDTO;
import com.natancode.carros.dto.CarroNewDTO;
import com.natancode.carros.resources.utils.URL;
import com.natancode.carros.services.CarroService;

@RestController
@RequestMapping(value = "/carros")
public class CarroResource {

	@Autowired
	private CarroService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Carro obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<Carro> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CarroNewDTO objDto){
		Carro obj = service.fromDTO(objDto);
        obj =service.insert(obj); 	
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri(); 
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}" ,method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CarroDTO objDto, @PathVariable Integer id){
		Carro obj = service.fromDTO(objDto);
		obj.setId(id); //garantindo que vai atualizar a categoria com o id passado
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/page" ,method=RequestMethod.GET) 
	public ResponseEntity<Page<Carro>> findPage( //parametro opcionais
			@RequestParam(value="categoria", defaultValue="1") Integer categoria,
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) { 
		
		String nomeDecoded =  URL.decodeParam(nome);
		
		Page<Carro> list = service.findPage(categoria, nomeDecoded, page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list); 
	}
}
