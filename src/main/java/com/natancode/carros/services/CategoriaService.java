package com.natancode.carros.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.natancode.carros.domain.Categoria;
import com.natancode.carros.repositories.CategoriaRepository;
import com.natancode.carros.services.exceptions.DataIntegrityException;
import com.natancode.carros.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName(), null)); 
	}
	
	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId()); //se não encontrar o objeto lança um exceção e para a execução
		return repo.save(obj);
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
