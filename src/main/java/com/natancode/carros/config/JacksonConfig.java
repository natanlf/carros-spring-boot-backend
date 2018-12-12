package com.natancode.carros.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.natancode.carros.domain.LocacaoDiaria;
import com.natancode.carros.domain.LocacaoLongoPeriodo;

@Configuration
public class JacksonConfig {
 	// https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare
	//Serve para validar as subclasses da classe Locacao
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(LocacaoDiaria.class);
				objectMapper.registerSubtypes(LocacaoLongoPeriodo.class);
				super.configure(objectMapper);
			}
		};
		return builder;
	}
}
