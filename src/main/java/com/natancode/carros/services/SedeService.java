package com.natancode.carros.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natancode.carros.domain.Sede;
import com.natancode.carros.dto.SedeDTO;
import com.natancode.carros.repositories.SedeRepository;
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
	
	public Sede fromDTO(SedeDTO objDto) { //instancio a partir de um DTO
		return new Sede(objDto.getId(), objDto.getLat(), objDto.getLog());
	}
	
	private void updateData(Sede newObj, Sede obj) {
		newObj.setLat(obj.getLat());
		newObj.setLog(obj.getLog());
	}
}
