package com.unir.ms_operador.data;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.unir.ms_operador.data.model.Purchase;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PurchaseRepository {
  
  private final PurchaseJpaRepository repository;

  // Buscar todas las compras de un usuario
  public List<Purchase> getByUserId(Long userId) {
    return repository.findByUserId(userId);
  }
  
  public Purchase save(Purchase purchase) {
    return repository.save(purchase);
  }

  public void delete(Purchase purchase) {
    repository.delete(purchase);
  }

  public Purchase getById(Long id) {
    return repository.findById(id).orElse(null);
  }
}
