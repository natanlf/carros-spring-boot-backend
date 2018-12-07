package com.natancode.carros.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natancode.carros.domain.Modelo;
import com.natancode.carros.dto.ModeloDTO;
import com.natancode.carros.repositories.ModeloRepository;
import com.natancode.carros.services.exceptions.ObjectNotFoundException;

@Service
public class ModeloService {
	
	@Autowired
	private ModeloRepository repo;
	
	public Modelo find(Integer id) {
		Optional<Modelo> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Modelo.class.getName(), null)); 
	}
	
	public List<Modelo> findAll() {
		return repo.findAll();
	}
	
	public Modelo insert(Modelo obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Modelo update(Modelo obj) {
		find(obj.getId()); //se não encontrar o objeto lança um exceção e para a execução
		return repo.save(obj);
	}
	
	public Modelo fromDTO(ModeloDTO objDto) {
		return new Modelo(null, objDto.getNome());
	}
}
