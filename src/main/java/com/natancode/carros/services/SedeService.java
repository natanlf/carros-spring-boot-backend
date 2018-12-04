package com.natancode.carros.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.natancode.carros.domain.Cidade;
import com.natancode.carros.domain.Endereco;
import com.natancode.carros.domain.Sede;
import com.natancode.carros.dto.SedeDTO;
import com.natancode.carros.dto.SedeNewDTO;
import com.natancode.carros.repositories.SedeRepository;
import com.natancode.carros.services.exceptions.DataIntegrityException;
import com.natancode.carros.services.exceptions.ObjectNotFoundException;

@Service
public class SedeService {
	
	@Autowired
	private SedeRepository repo;
	
	public Sede find(Integer id) {
		Optional<Sede> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Sede.class.getName(), null)); 
	}
	
	public List<Sede> findAll() {
		return repo.findAll();
	}
	
	public Sede insert(Sede obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Sede update(Sede obj) {
		Sede newObj = find(obj.getId()); //se não encontrar esse id, já lança uma exceção e não continua
		updateData(newObj, obj); //transforma um dto para domain
		return repo.save(newObj);
	}
	
	public Sede fromDTO(SedeDTO objDto) { //instancio a partir de um DTO / Uso PUT
		return new Sede(objDto.getId(), objDto.getLat(), objDto.getLog());
	}
	
	public Sede fromDTO(SedeNewDTO objDto) { //instancio a partir de um DTO / Uso POST
		Cidade cidade = new Cidade(objDto.getCidadeId(), null, null);
		Endereco endereco = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cidade);
		Sede sede = new Sede(null, objDto.getLat(), objDto.getLog(), endereco);
		
		return sede;
	}
	
	private void updateData(Sede newObj, Sede obj) {
		newObj.setLat(obj.getLat());
		newObj.setLog(obj.getLog());
	}
	
	public void delete(Integer id) {
		find(id); //se não encontrar já retorna uma exception
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível deletar pois há relações com outras entidades");
		}
	}
}
