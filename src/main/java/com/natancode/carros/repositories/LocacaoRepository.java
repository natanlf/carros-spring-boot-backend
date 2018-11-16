package com.natancode.carros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.natancode.carros.domain.Locacao;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Integer> {

}
