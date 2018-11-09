package com.natancode.carros;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.natancode.carros.domain.Categoria;
import com.natancode.carros.repositories.CategoriaRepository;

@SpringBootApplication
public class CarrosApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CarrosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Compacto", 50.00);
		Categoria cat2 = new Categoria(null, "Luxo", 300.00);
		Categoria cat3 = new Categoria(null, "SUV", 100.00);
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
	}
}
