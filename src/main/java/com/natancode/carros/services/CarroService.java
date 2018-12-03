package com.natancode.carros.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natancode.carros.domain.Carro;
import com.natancode.carros.repositories.CarroRepository;
import com.natancode.carros.services.exceptions.ObjectNotFoundException;

@Service
public class CarroService {

	@Autowired
	private CarroRepository repo;
	
	public Carro find(Integer id) {
		Optional<Carro> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Carro.class.getName(), null)); 
	}
	
	public List<Carro> findAll() {
		return repo.findAll();
	}
	
	public Carro insert(Carro obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Carro update(Carro obj) {
		find(obj.getId()); //se não encontrar o objeto lança um exceção e para a execução
		return repo.save(obj);
	}
}
