package com.unir.ms_operador.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.unir.ms_operador.data.model.Purchase;

interface PurchaseJpaRepository extends JpaRepository<Purchase, Long>, JpaSpecificationExecutor<Purchase> {

  List<Purchase> findByUserId(Long userId);

}