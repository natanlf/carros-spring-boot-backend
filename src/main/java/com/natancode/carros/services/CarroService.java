package com.natancode.carros.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natancode.carros.domain.Carro;
import com.natancode.carros.repositories.CarroRepository;

@Service
public class CarroService {

	@Autowired
	private CarroRepository repo;
	
	public Carro findById(Integer id) {
		Optional<Carro> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
	public List<Carro> findAll() {
		return repo.findAll();
	}
}
