package com.natancode.carros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.natancode.carros.domain.Modelo;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Integer> {

}
