package com.natancode.carros.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natancode.carros.domain.Carro;
import com.natancode.carros.domain.Categoria;
import com.natancode.carros.domain.Modelo;
import com.natancode.carros.domain.Sede;
import com.natancode.carros.dto.CarroDTO;
import com.natancode.carros.dto.CarroNewDTO;
import com.natancode.carros.enums.Cor;
import com.natancode.carros.repositories.CarroRepository;
import com.natancode.carros.services.exceptions.ObjectNotFoundException;

@Service
public class CarroService {

	@Autowired
	private CarroRepository repo;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired 
	private ModeloService modeloService;
	
	@Autowired
	private SedeService sedeService;
	
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
		Carro newObj = find(obj.getId()); //se não encontrar o objeto lança um exceção e para a execução
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public Carro fromDTO(CarroNewDTO objDto) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Categoria categoria = categoriaService.find(objDto.getCategoriaId());
		Modelo modelo = modeloService.find(objDto.getModeloId());
		Sede sede = sedeService.find(objDto.getSedeId());
		Date date = null;
		try {
			date = sdf.parse(objDto.getDataAquisicao());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return new Carro(null, objDto.getNome(), objDto.getPlaca(), date, categoria, Cor.toEnum(objDto.getCor()), modelo, objDto.getAno(), sede);
	}
	
	public Carro fromDTO(CarroDTO objDto) {

		Sede sede = sedeService.find(objDto.getSedeId());
		
		return new Carro(null, objDto.getNome(), objDto.getPlaca(), null, null, Cor.toEnum(objDto.getCor()), null, objDto.getAno(), sede);
	}
	
	private void updateData(Carro newObj, Carro obj) {
		newObj.setNome(obj.getNome());
		newObj.setPlaca(obj.getPlaca());
		newObj.setCor(obj.getCor().getCod());
		newObj.setAno(obj.getAno());
		newObj.setSede(obj.getSede());
	}
}
