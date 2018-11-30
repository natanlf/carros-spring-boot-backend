package com.natancode.carros.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natancode.carros.domain.Sede;
import com.natancode.carros.repositories.SedeRepository;
import com.natancode.carros.services.exceptions.ObjectNotFoundException;

@Service
public class SedeService {
	
	@Autowired
	private SedeRepository repo;
	
	public Sede findById(Integer id) {
		Optional<Sede> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Sede.class.getName(), null)); 
	}
	
	public List<Sede> findAll() {
		return repo.findAll();
	}
}
