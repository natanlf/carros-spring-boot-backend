package com.natancode.carros.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.natancode.carros.domain.Carro;
import com.natancode.carros.domain.Categoria;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Integer> {

	@Transactional(readOnly=true)
	Page<Carro>findByCategoriaEqualsAndNomeContainingIgnoreCase(@Param("categoria") Categoria categoria, @Param("nome") String nome, Pageable pageRequest);
}
