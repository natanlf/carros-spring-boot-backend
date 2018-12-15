package com.natancode.carros.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.natancode.carros.domain.Cliente;
import com.natancode.carros.domain.Locacao;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Integer> {

	@Transactional(readOnly=true)
	Page<Locacao> findByCliente(Cliente cliente, Pageable pageResquest);
}
