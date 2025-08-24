package com.unir.ms_operador.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unir.ms_operador.controller.model.CreateRentalRequest;
import com.unir.ms_operador.data.RentalRepository;
import com.unir.ms_operador.data.model.Rental;
import com.unir.ms_operador.facade.MoviesFacade;
import com.unir.ms_operador.facade.model.Movie;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RentalServiceImpl implements RentalService {
  
  @Autowired
  private RentalRepository repository;

  @Autowired
  private MoviesFacade moviesFacade;

  @Override
  public List<Rental> getRentalsByUser(Long userId){

    List<Rental> rentals = repository.getByUserId(userId);
    return rentals.isEmpty() ? null : rentals;

  }

  @Override
  public Rental createRental(CreateRentalRequest request){
    if (request != null && request.getUserId() != null && request.getMovieId() != null) {

      // buscamos la pelicula 
      Movie movie = moviesFacade.getMovie(request.getMovieId());

      if (movie == null) {
        log.warn("No se encontró la película con ID {}", request.getMovieId());
        return null; 
      }
      
      LocalDateTime rentalDate = LocalDateTime.now();
      LocalDateTime returnDate = rentalDate.plusMonths(1);

      Rental rental = Rental.builder()
        .userId(request.getUserId())
        .movieId(request.getMovieId())
        .priceRental(movie.getPriceRental())
        .rentedAt(rentalDate)
        .returnAt(returnDate)
        .build();

      return repository.save(rental);
      
    }else {
      log.warn("Request inválido: {}", request);
      return null;
    }
  }

  @Override
  public Boolean returnRental(Long rentalId){

    Rental rental = repository.getById(rentalId);

    if (rental != null) {
      repository.delete(rental);
      return Boolean.TRUE;
    } else {
      return Boolean.FALSE;
    }
  }

}
