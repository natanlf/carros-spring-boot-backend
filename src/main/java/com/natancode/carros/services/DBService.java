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

		Categoria hatch = new Categoria(null, "Hatch", 100.00);
		Categoria luxo = new Categoria(null, "Luxo", 500.00);
		Categoria suv = new Categoria(null, "SUV", 200.00);
		Categoria sedan = new Categoria(null, "Sedan", 100.00);
		categoriaRepository.saveAll(Arrays.asList(hatch, luxo, suv, sedan));

		Modelo mod1 = new Modelo(null, "Chevrolet Onix");
		Modelo mod2 = new Modelo(null, "Ford Mustang");
		Modelo mod3 = new Modelo(null, "Toyota Hilux");
		Modelo mod4 = new Modelo(null, "Fiat Uno");
		Modelo camaroM = new Modelo(null, "Chevrolet Camaro");
		Modelo vectraM = new Modelo(null, "Chevrolet Vectra");
		Modelo prismaM = new Modelo(null, "Chevrolet Prisma");
		Modelo hondaCM = new Modelo(null, "Honda Civic");
		Modelo bmwM = new Modelo(null, "BMW M3");
		Modelo audiAM = new Modelo(null, "Audi Serie A");
		Modelo audiTTM = new Modelo(null, "Audi TT");
		Modelo cruzeM = new Modelo(null, "Chevrolet Cruze");
		Modelo viperM = new Modelo(null, "Dodge Viper");
		Modelo monzaM = new Modelo(null, "Chevrolet Monza");
		Modelo mod15 = new Modelo(null, "Chevrolet Corvette");
		Modelo hondaCity = new Modelo(null, "Honda City");
		Modelo mod17 = new Modelo(null, "Honda Fit");
		Modelo fiatToro = new Modelo(null, "Fiat Toro");
		Modelo polo = new Modelo(null, "Wolksvagem Polo");
		Modelo mod20 = new Modelo(null, "Wolksvagem Golf");
		Modelo mod21 = new Modelo(null, "Dodge Challenger");
		Modelo porsche = new Modelo(null, "Porche Cayman");
		Modelo mod23 = new Modelo(null, "Toyota Corolla");
		Modelo mod24 = new Modelo(null, "Fiat Cronos");
		Modelo mod25 = new Modelo(null, "Audi R8");
		Modelo focus = new Modelo(null, "Ford Focus");
		Modelo mod27 = new Modelo(null, "Porche 911");
		Modelo mod28 = new Modelo(null, "Renault Megane");
		Modelo mod29 = new Modelo(null, "Renault Fluence");
		Modelo fusion = new Modelo(null, "Ford Fusion");
		Modelo toyotaTruck = new Modelo(null, "Toyota Truck");
		Modelo landRover = new Modelo(null, "Land Rover");
		Modelo mod33 = new Modelo(null, "Volvo X");
		Modelo mod34 = new Modelo(null, "Aston Martin DB9");
		Modelo lambor = new Modelo(null, "Lamborghini Aventador");
		Modelo fordRaptor = new Modelo(null, "Ford Raptor");
		modeloRepository.saveAll(Arrays.asList(mod1, mod2, mod3, mod4, camaroM, vectraM, prismaM,hondaCM,bmwM,audiAM,audiTTM,cruzeM,viperM,monzaM,mod15,hondaCity,mod17,fiatToro,polo,mod20,mod21,porsche,mod23,mod24,mod25,focus,mod27,mod28,mod29,fusion,toyotaTruck,landRover,mod33,mod34,lambor,fordRaptor));

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
		Carro car1 = new Carro(null, "Onix 1.4 Advantage", "POR-5231", sdf.parse("09/11/2018 00:00"), hatch, Cor.BRANCO,
				mod1, 2015, sed1);
		Carro car2 = new Carro(null, "Mustang Cobra Jet", "XOR-3089", sdf.parse("09/11/2018 00:00"), luxo, Cor.VERMELHO,
				mod2, 2018, sed1);
		Carro car3 = new Carro(null, "Hilux Cabine Dupla", "TOR-3089", sdf.parse("09/11/2018 00:00"), suv, Cor.VERMELHO,
				mod3, 2018, sed1);
		Carro car4 = new Carro(null, "Uno Sporting 1.4 EVO", "YOR-5589", sdf.parse("10/11/2018 00:00"), hatch,
				Cor.BRANCO, mod4, 2017, sed1);
		
		Carro CamaroZl = new Carro(null, "Camaro ZL", "YOR-1239", sdf.parse("10/11/2018 00:00"), luxo,
				Cor.VERMELHO, camaroM, 2017, sed1);
		Carro camaroRs = new Carro(null, "Camaro RS", "TYR-1239", sdf.parse("10/11/2018 00:00"), luxo,
				Cor.VERMELHO, camaroM, 2018, sed1);
		
		Carro vectra = new Carro(null, "Vectra GT", "YOR-8547", sdf.parse("10/11/2018 00:00"), hatch,
				Cor.AZUL, vectraM, 2009, sed1);
		
		Carro prisma = new Carro(null, "Prisma Advantage", "YOR-8507", sdf.parse("10/11/2018 00:00"), sedan,
				Cor.PRETO, prismaM, 2016, sed1);
		
		Carro hondaCivic = new Carro(null, "Honda Civic", "VNR-8507", sdf.parse("10/11/2018 00:00"), sedan,
				Cor.VERDE, hondaCM, 2016, sed1);
		
		Carro bmw10 = new Carro(null, "BMW M3 320I", "VXR-8587", sdf.parse("10/11/2018 00:00"), luxo,
				Cor.CINZA, bmwM, 2018, sed1);
		Carro bmw11 = new Carro(null, "BMW M3 E92L", "VXR-8556", sdf.parse("10/11/2018 00:00"), luxo,
				Cor.AZUL, bmwM, 2018, sed1);
		Carro bmw12 = new Carro(null, "BMW M3 E90", "VXT-7587", sdf.parse("10/11/2018 00:00"), luxo,
				Cor.PRETO, bmwM, 2018, sed1);
		Carro bmw13 = new Carro(null, "BMW M3 E92", "WXR-8787", sdf.parse("10/11/2018 00:00"), luxo,
				Cor.BRANCO, bmwM, 2018, sed1);
		
		Carro audiTT2 = new Carro(null, "Audi TT", "KLR-5611", sdf.parse("10/11/2018 00:00"), luxo,
				Cor.BRANCO, audiTTM, 2012, sed1);
		
		Carro cruze1 = new Carro(null, "Cruze Turbo Flex", "LPV-4567", sdf.parse("10/11/2018 00:00"), sedan,
				Cor.PRETO, cruzeM, 2017, sed1);
		
		Carro cruze2 = new Carro(null, "Cruze", "LPV-4555", sdf.parse("10/11/2018 00:00"), hatch,
				Cor.CINZA, cruzeM, 2017, sed1);
		
		Carro toro = new Carro(null, "Toro Rescue", "kMV-7815", sdf.parse("30/12/2018 00:00"), suv,
				Cor.VERMELHO, fiatToro, 2018, sed1);
		
		Carro city = new Carro(null, "Honda City", "kMV-9555", sdf.parse("30/12/2018 00:00"), sedan,
				Cor.CINZA, hondaCity, 2019, sed1);
		
		Carro fusionCar = new Carro(null, "Ford Fusion", "kMV-1295", sdf.parse("30/12/2018 00:00"), sedan,
				Cor.BRANCO, fusion, 2018, sed1);
		
		Carro poloCar = new Carro(null, "Polo", "kMV-6008", sdf.parse("30/12/2018 00:00"), hatch,
				Cor.AMARELO, polo, 2018, sed1);
		
		Carro focusCar = new Carro(null, "Focus", "XMY-6308", sdf.parse("30/12/2018 00:00"), hatch,
				Cor.CINZA, focus, 2018, sed1);
		
		Carro truck = new Carro(null, "Toyota Truck", "kMV-2317", sdf.parse("30/12/2018 00:00"), suv,
				Cor.CINZA, toyotaTruck, 2018, sed1);
		
		Carro landCar = new Carro(null, "Land Rover", "RUT-7817", sdf.parse("30/12/2018 00:00"), suv,
				Cor.CINZA, landRover, 2018, sed1);
		
		Carro raptor = new Carro(null, "Ford Raptor", "RUT-4317", sdf.parse("30/12/2018 00:00"), suv,
				Cor.AZUL, fordRaptor, 2019, sed1);
		
		Carro monza = new Carro(null, "Monza", "kMV-3655", sdf.parse("30/12/2018 00:00"), sedan,
				Cor.VERMELHO, monzaM, 2019, sed1);
		
		Carro viper = new Carro(null, "Viper", "KTY-5771", sdf.parse("10/11/2018 00:00"), luxo,
				Cor.PRETO, viperM, 2012, sed1);
		
		Carro porscheCar = new Carro(null, "Porsche Cayman GTS", "KTY-5555", sdf.parse("10/11/2018 00:00"), luxo,
				Cor.BRANCO, porsche, 2015, sed1);
		
		Carro lamborCar = new Carro(null, "Lamborghini Aventador LP700", "KJY-5441", sdf.parse("10/11/2018 00:00"), luxo,
				Cor.BRANCO, lambor, 2014, sed1);

		hatch.getCarros().addAll(Arrays.asList(car1, car4, vectra,poloCar,focusCar,cruze2));
		luxo.getCarros().addAll(Arrays.asList(car2, CamaroZl, camaroRs, bmw10,bmw11,bmw12,bmw13,audiTT2,viper,porscheCar,lamborCar));
		suv.getCarros().addAll(Arrays.asList(car3,toro,truck,landCar,raptor));
		sedan.getCarros().addAll(Arrays.asList(prisma,hondaCivic,cruze1,city,fusionCar,monza));

		mod1.getCarros().addAll(Arrays.asList(car1));
		mod2.getCarros().addAll(Arrays.asList(car2));
		mod3.getCarros().addAll(Arrays.asList(car3));
		mod4.getCarros().addAll(Arrays.asList(car4));
		camaroM.getCarros().addAll(Arrays.asList(camaroRs, CamaroZl));
		vectraM.getCarros().addAll(Arrays.asList(vectra));
		prismaM.getCarros().addAll(Arrays.asList(prisma));
		hondaCM.getCarros().addAll(Arrays.asList(hondaCivic));
		bmwM.getCarros().addAll(Arrays.asList(bmw10,bmw11,bmw12,bmw13));
		audiTTM.getCarros().addAll(Arrays.asList(audiTT2));
		cruzeM.getCarros().addAll(Arrays.asList(cruze1,cruze2));
		fiatToro.getCarros().addAll(Arrays.asList(toro));
		hondaCity.getCarros().addAll(Arrays.asList(city));
		fusion.getCarros().addAll(Arrays.asList(fusionCar));
		polo.getCarros().addAll(Arrays.asList(poloCar));
		focus.getCarros().addAll(Arrays.asList(focusCar));
		toyotaTruck.getCarros().addAll(Arrays.asList(truck));
		landRover.getCarros().addAll(Arrays.asList(landCar));
		fordRaptor.getCarros().addAll(Arrays.asList(raptor));
		monzaM.getCarros().addAll(Arrays.asList(monza));
		viperM.getCarros().addAll(Arrays.asList(viper));
		porsche.getCarros().addAll(Arrays.asList(porscheCar));
		lambor.getCarros().addAll(Arrays.asList(lamborCar));

		carroRepository.saveAll(Arrays.asList(car1, car2, car3, car4, camaroRs,CamaroZl,vectra,prisma,hondaCivic,bmw10,bmw11,bmw12,bmw13,audiTT2,cruze1,cruze2,toro,city,fusionCar,poloCar,focusCar,truck,landCar,raptor,monza,viper,porscheCar,lamborCar));

		Cliente cli1 = new Cliente(null, "Natan Lara Ferreira", "57137591051", "natanlaraferreira@gmail.com", pe.encode("123"));
		Cliente cli2 = new Cliente(null, "Natan Developer", "63539631062", "natan.developer@gmail.com", pe.encode("123"));
		Cliente cli3 = new Cliente(null, "Ana Silva", "62294218019", "anasilva12345@gmail.com", pe.encode("123456"));

		cli1.getTelefones().addAll(Arrays.asList("(27) 3990-0996", "(27) 99582-6457"));
		cli2.getTelefones().addAll(Arrays.asList("(21) 3990-8978", "(21) 97082-6457"));
		cli3.getTelefones().addAll(Arrays.asList("(27) 3550-0016", "(27) 97882-3455"));
		
		cli1.addPerfil(Perfil.ADMIN);
		cli2.addPerfil(Perfil.CLIENTE);
		cli3.addPerfil(Perfil.CLIENTE);

		LocacaoDiaria l1 = new LocacaoDiaria(null, sdf.parse("09/01/2019 09:32"), sdf.parse("09/01/2019 20:25"), cli1,
				car1, sed1, 1);
		LocacaoLongoPeriodo l2 = new LocacaoLongoPeriodo(null, sdf.parse("09/01/2019 09:32"),
				sdf.parse("12/01/2019 20:25"), cli2, car2, sed1, 5.00);
		LocacaoLongoPeriodo l3 = new LocacaoLongoPeriodo(null, sdf.parse("12/01/2019 15:02"),
				sdf.parse("16/01/2019 08:32"), cli1, car3, sed1, 10.00);

		cli1.getLocacoes().addAll(Arrays.asList(l1, l3));
		cli2.getLocacoes().addAll(Arrays.asList(l2));

		car1.getLocacoes().addAll(Arrays.asList(l1));
		car2.getLocacoes().addAll(Arrays.asList(l2));
		car3.getLocacoes().addAll(Arrays.asList(l3));

		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3));
		locacaoRepository.saveAll(Arrays.asList(l1, l2, l3));
	}
}
