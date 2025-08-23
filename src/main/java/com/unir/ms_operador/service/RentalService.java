package com.unir.ms_operador.service;

import java.util.List;

import com.unir.ms_operador.controller.model.CreateRentalRequest;
import com.unir.ms_operador.data.model.Rental;

public interface RentalService {

  List<Rental> getRentalsByUser(Long userId);

  Rental createRental(CreateRentalRequest request);

  Boolean returnRental(Long rentalId);

}
