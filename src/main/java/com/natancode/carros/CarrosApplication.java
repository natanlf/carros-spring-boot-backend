package com.natancode.carros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.natancode.carros.services.S3Service;

@SpringBootApplication
public class CarrosApplication implements CommandLineRunner {

	@Autowired
	private S3Service s3service;
	
	public static void main(String[] args) {
		SpringApplication.run(CarrosApplication.class, args);
 	}
	
	@Override
	public void run(String... args) throws Exception { //roda automaticamente
		s3service.uploadFile("C:\\Users\\lf\\Documents\\Curso Udemy WEB SERVICE REST JAVA\\Imagens S3\\ana.jpg");
	}
	
}
