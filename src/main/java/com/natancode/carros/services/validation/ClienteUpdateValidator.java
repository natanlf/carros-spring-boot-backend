package com.natancode.carros.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.natancode.carros.domain.Cliente;
import com.natancode.carros.dto.ClienteDTO;
import com.natancode.carros.repositories.ClienteRepository;
import com.natancode.carros.resources.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository repo;
	
 	@Override
	public void initialize(ClienteUpdate ann) {
	}
 	//retorna true para objeto válido e false para inválido
	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		//Os atributos da requisição são armazenados dentro de um Map
				@SuppressWarnings("unchecked")
				Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
				Integer uriId = Integer.parseInt(map.get("id")); //Id da url
		
 		List<FieldMessage> list = new ArrayList<>(); // lista de erros
		
		Cliente cli = repo.findByEmail(objDto.getEmail());
		if(cli!=null && !cli.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existe"));
		}
		
		//Pego erros da minha lista e coloca na lista do framework
		//É na classe ResourceExceptionHandler que temos a nossa lista de erros personalizadas
		for (FieldMessage e : list) { //percorre a minha lista e para cada objeto diferente, adiciona o erro na lista do framework
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
} 