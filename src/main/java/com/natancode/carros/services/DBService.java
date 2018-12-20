package com.natancode.carros.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.natancode.carros.domain.Carro;
import com.natancode.carros.domain.Categoria;
import com.natancode.carros.domain.Cidade;
import com.natancode.carros.domain.Cliente;
import com.natancode.carros.domain.Endereco;
import com.natancode.carros.domain.Estado;
import com.natancode.carros.domain.LocacaoDiaria;
import com.natancode.carros.domain.LocacaoLongoPeriodo;
import com.natancode.carros.domain.Modelo;
import com.natancode.carros.domain.Sede;
import com.natancode.carros.enums.Cor;
import com.natancode.carros.enums.Perfil;
import com.natancode.carros.repositories.CarroRepository;
import com.natancode.carros.repositories.CategoriaRepository;
import com.natancode.carros.repositories.CidadeRepository;
import com.natancode.carros.repositories.ClienteRepository;
import com.natancode.carros.repositories.EnderecoRepository;
import com.natancode.carros.repositories.EstadoRepository;
import com.natancode.carros.repositories.LocacaoRepository;
import com.natancode.carros.repositories.ModeloRepository;
import com.natancode.carros.repositories.SedeRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private CarroRepository carroRepository;

	@Autowired
	private ModeloRepository modeloRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private SedeRepository sedeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private LocacaoRepository locacaoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;

	public void instantiateTestDatabase() throws ParseException {

		Categoria cat1 = new Categoria(null, "Hatch", 100.00);
		Categoria cat2 = new Categoria(null, "Luxo", 500.00);
		Categoria cat3 = new Categoria(null, "SUV", 200.00);
		Categoria cat4 = new Categoria(null, "Sedan", 100.00);
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));

		Modelo mod1 = new Modelo(null, "Chevrolet Onix");
		Modelo mod2 = new Modelo(null, "Ford Mustang");
		Modelo mod3 = new Modelo(null, "Toyota Hilux");
		Modelo mod4 = new Modelo(null, "Fiat Uno");
		modeloRepository.saveAll(Arrays.asList(mod1, mod2, mod3, mod4));

		Estado est1 = new Estado(null, "Rio de Janeiro");
		Estado est2 = new Estado(null, "Minas Gerais");

		Cidade cid1 = new Cidade(null, "Rio de Janeiro", est1);
		Cidade cid2 = new Cidade(null, "Niteroi", est1);
		Cidade cid3 = new Cidade(null, "Belo Horizonte", est2);

		est1.getCidades().addAll(Arrays.asList(cid1, cid2));
		est2.getCidades().addAll(Arrays.asList(cid3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));

		Endereco end1 = new Endereco(null, "Estr. do Mendanha", "555", null, "Campo Grande", "23087283", cid1);
		Endereco end2 = new Endereco(null, "Rua João Pessoa", "211", "Próximo a praia", "Icaraí", "24220330", cid2);
		Endereco end3 = new Endereco(null, "Av. das Américas", "4666", null, "Barra da Tijuca", "22640102", cid1);
		Endereco end4 = new Endereco(null, "Av. Pres. Antônio Carlos", "6627", null, "Pampulha", "31270901", cid3);

		Sede sed1 = new Sede(null, -22.8847251, -43.5576218, end1);
		Sede sed2 = new Sede(null, -22.9034915, -43.1018899, end2);
		Sede sed3 = new Sede(null, -22.9034915, -43.1018899, end3);
		Sede sed4 = new Sede(null, -22.9034915, -43.1018899, end4);

		end1.setSede(sed1);
		end2.setSede(sed2);
		end3.setSede(sed3);
		end4.setSede(sed4);

		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3, end4));
		sedeRepository.saveAll(Arrays.asList(sed1, sed2, sed3, sed4));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Carro car1 = new Carro(null, "Onix 1.4 Advantage", "POR-5231", sdf.parse("09/11/2018 00:00"), cat1, Cor.BRANCO,
				mod1, 2018, sed1);
		Carro car2 = new Carro(null, "Mustang Cobra Jet", "XOR-3089", sdf.parse("09/11/2018 00:00"), cat2, Cor.PRETO,
				mod2, 2018, sed1);
		Carro car3 = new Carro(null, "Hilux Cabine Dupla", "TOR-3089", sdf.parse("09/11/2018 00:00"), cat3, Cor.CINZA,
				mod3, 2016, sed1);
		Carro car4 = new Carro(null, "Uno Sporting 1.4 EVO", "YOR-5589", sdf.parse("10/11/2018 00:00"), cat1,
				Cor.VERMELHO, mod4, 2017, sed3);

		cat1.getCarros().addAll(Arrays.asList(car1, car4));
		cat2.getCarros().addAll(Arrays.asList(car2));
		cat3.getCarros().addAll(Arrays.asList(car3));

		mod1.getCarros().addAll(Arrays.asList(car1));
		mod2.getCarros().addAll(Arrays.asList(car2));
		mod3.getCarros().addAll(Arrays.asList(car3));
		mod4.getCarros().addAll(Arrays.asList(car4));

		carroRepository.saveAll(Arrays.asList(car1, car2, car3, car4));

		Cliente cli1 = new Cliente(null, "Renata Alves", "57137591051", "natanlaraferreira@gmail.com", pe.encode("123"));
		Cliente cli2 = new Cliente(null, "Gustavo Green", "63539631062", "natan.developer@gmail.com", pe.encode("123"));
		Cliente cli3 = new Cliente(null, "Robson Nunes", "62294218019", "robsonnunes@gmail.com", pe.encode("123456"));

		cli1.getTelefones().addAll(Arrays.asList("(27) 3990-0996", "(27) 99582-6457"));
		cli2.getTelefones().addAll(Arrays.asList("(21) 3990-8978", "(21) 97082-6457"));
		cli3.getTelefones().addAll(Arrays.asList("(27) 3550-0016", "(27) 97882-3455"));
		
		cli1.addPerfil(Perfil.ADMIN);
		cli2.addPerfil(Perfil.CLIENTE);
		cli3.addPerfil(Perfil.CLIENTE);

		LocacaoDiaria l1 = new LocacaoDiaria(null, sdf.parse("09/11/2018 09:32"), sdf.parse("09/11/2018 20:25"), cli1,
				car1, sed1, 1);
		LocacaoLongoPeriodo l2 = new LocacaoLongoPeriodo(null, sdf.parse("09/11/2018 09:32"),
				sdf.parse("12/11/2018 20:25"), cli2, car2, sed1, 5.00);
		LocacaoLongoPeriodo l3 = new LocacaoLongoPeriodo(null, sdf.parse("12/11/2018 15:02"),
				sdf.parse("16/11/2018 08:32"), cli1, car3, sed1, 10.00);

		cli1.getLocacoes().addAll(Arrays.asList(l1, l3));
		cli2.getLocacoes().addAll(Arrays.asList(l2));

		car1.getLocacoes().addAll(Arrays.asList(l1));
		car2.getLocacoes().addAll(Arrays.asList(l2));
		car3.getLocacoes().addAll(Arrays.asList(l3));

		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3));
		locacaoRepository.saveAll(Arrays.asList(l1, l2, l3));
	}
}
