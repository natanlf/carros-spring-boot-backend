package com.natancode.carros.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.natancode.carros.domain.Cliente;
import com.natancode.carros.dto.ClienteDTO;
import com.natancode.carros.dto.ClienteNewDTO;
import com.natancode.carros.repositories.ClienteRepository;
import com.natancode.carros.services.exceptions.DataIntegrityException;
import com.natancode.carros.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName(), null));
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cliente = new Cliente(null, objDto.getNome(), objDto.getCpf(), objDto.getEmail());
		cliente.getTelefones().add(objDto.getTelefone1());

		if (objDto.getTelefone2() != null) // Tel 2 e 3 não são obrigatórios
			cliente.getTelefones().add(objDto.getTelefone2());
		if (objDto.getTelefone3() != null)
			cliente.getTelefones().add(objDto.getTelefone3());

		return cliente;
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getCpf(), objDto.getEmail());
	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Cliente update(Cliente obj) {
		find(obj.getId()); // se não encontrar o objeto lança um exceção e para a execução
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
