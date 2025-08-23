package com.unir.ms_operador.data;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.unir.ms_operador.data.model.Rental;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RentalRepository {
  
  private final RentalJpaRepository repository;

  // Buscar todas las rentas de un usuario
  public List<Rental> getByUserId(Long userId) {
    return repository.findByUserId(userId);
  }
  
  public Rental save(Rental rental) {
    return repository.save(rental);
  }

  public void delete(Rental rental) {
    repository.delete(rental);
  }

  public Rental getById(Long id) {
    return repository.findById(id).orElse(null);
  }
}
