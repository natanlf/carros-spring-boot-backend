package com.natancode.carros.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.natancode.carros.domain.Cliente;
import com.natancode.carros.dto.ClienteDTO;
import com.natancode.carros.dto.ClienteNewDTO;
import com.natancode.carros.enums.Perfil;
import com.natancode.carros.repositories.ClienteRepository;
import com.natancode.carros.security.UserSS;
import com.natancode.carros.services.exceptions.AuthorizationException;
import com.natancode.carros.services.exceptions.DataIntegrityException;
import com.natancode.carros.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private S3Service s3service;
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;

	public Cliente find(Integer id) {
		UserSS user = UserService.authenticated(); //pega o usuário logado
		
		//se não possue perfil de ADMIN e se o id que estou buscando não é igual do usuário logado
		if(user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		} 
		
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName(), null));
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cliente = new Cliente(null, objDto.getNome(), objDto.getCpf(), objDto.getEmail(), pe.encode(objDto.getSenha()));
		cliente.getTelefones().add(objDto.getTelefone1());

		if (objDto.getTelefone2() != null) // Tel 2 e 3 não são obrigatórios
			cliente.getTelefones().add(objDto.getTelefone2());
		if (objDto.getTelefone3() != null)
			cliente.getTelefones().add(objDto.getTelefone3());

		return cliente;
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), null, objDto.getEmail(), null);
	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId()); //se não encontrar esse id, já lança uma exceção e não continua
		updateData(newObj, obj);
		return repo.save(newObj); //save or update, quando o id é nulo insere, quando não é atualiza
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public void delete(Integer id) {
		find(id); //se não encontrar já retorna uma exception
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível deletar pois há relações com outras entidades");
		}
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated(); //tenta pegar usuário autenticado
		
		if(user == null) { //se for nulo, o usuário não está logado no sistema
			throw new AuthorizationException("Acesso negado");
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		String fileName = prefix + user.getId() + ".jpg";
		
		return s3service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}
}
