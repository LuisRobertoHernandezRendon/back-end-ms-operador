package com.unir.ms_operador.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.unir.ms_operador.data.model.Rental;

interface RentalJpaRepository extends JpaRepository<Rental, Long>, JpaSpecificationExecutor<Rental> {

  List<Rental> findByUserId(Long userId);

}