package com.natancode.carros.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.natancode.carros.domain.Cliente;
import com.natancode.carros.dto.ClienteNewDTO;
import com.natancode.carros.repositories.ClienteRepository;
import com.natancode.carros.resources.exception.FieldMessage;
import com.natancode.carros.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repo;
	
 	@Override
	public void initialize(ClienteInsert ann) {
	}
 	//retorna true para objeto válido e false para inválido
	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
 		List<FieldMessage> list = new ArrayList<>(); // lista de erros
		
		if(!BR.isValidCPF(objDto.getCpf())) {
			list.add(new FieldMessage("cpf", "CPF inválido"));
		}
		
		Cliente cli = repo.findByEmail(objDto.getEmail());
		if(cli!=null) {
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