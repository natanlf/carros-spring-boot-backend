package com.natancode.carros;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.natancode.carros.domain.Carro;
import com.natancode.carros.domain.Categoria;
import com.natancode.carros.enums.Cor;
import com.natancode.carros.repositories.CarroRepository;
import com.natancode.carros.repositories.CategoriaRepository;

@SpringBootApplication
public class CarrosApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CarroRepository carroRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CarrosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Compacto", 50.00);
		Categoria cat2 = new Categoria(null, "Luxo", 300.00);
		Categoria cat3 = new Categoria(null, "SUV", 100.00);
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Carro car1 = new Carro(null, "Onix", "POR-5231", sdf.parse("09/11/2018 00:00"), cat1, Cor.BRANCO);
		Carro car2 = new Carro(null, "Mustang", "XOR-3089", sdf.parse("09/11/2018 00:00"), cat2, Cor.PRETO);
		Carro car3 = new Carro(null, "Hilux", "TOR-3089", sdf.parse("09/11/2018 00:00"), cat3, Cor.CINZA);
		Carro car4 = new Carro(null, "Fiat Uno", "YOR-5589", sdf.parse("10/11/2018 00:00"), cat1, Cor.VERMELHO);
		
		cat1.getCarros().addAll(Arrays.asList(car1, car4));
		cat2.getCarros().addAll(Arrays.asList(car2));
		cat3.getCarros().addAll(Arrays.asList(car3));
		
		carroRepository.saveAll(Arrays.asList(car1, car2, car3, car4));
		
	}
}
