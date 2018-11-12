package com.natancode.carros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.natancode.carros.domain.Sede;

@Repository
public interface SedeRepository extends JpaRepository<Sede, Integer> {

}
