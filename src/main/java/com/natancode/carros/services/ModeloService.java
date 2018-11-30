package com.natancode.carros.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natancode.carros.domain.Modelo;
import com.natancode.carros.repositories.ModeloRepository;
import com.natancode.carros.services.exceptions.ObjectNotFoundException;

@Service
public class ModeloService {
	
	@Autowired
	private ModeloRepository repo;
	
	public Modelo findById(Integer id) {
		Optional<Modelo> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Modelo.class.getName(), null)); 
	}
	
	public List<Modelo> findAll() {
		return repo.findAll();
	}
}
