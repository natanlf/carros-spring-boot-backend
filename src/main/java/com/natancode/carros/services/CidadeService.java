package com.natancode.carros.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natancode.carros.domain.Cidade;
import com.natancode.carros.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;
 	public List<Cidade> findByEstado(Integer estadoId) {
		return repo.findCidades(estadoId);
	}
} 