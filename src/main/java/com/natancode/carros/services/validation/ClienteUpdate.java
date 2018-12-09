package com.natancode.carros.services.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ClienteUpdateValidator.class) //Preciso da classe validator
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)

public @interface ClienteUpdate { //Nome da anotação que será usada na classe DTO
	String message() default "Erro de validação";
 	Class<?>[] groups() default {};
 	Class<? extends Payload>[] payload() default {};
}
