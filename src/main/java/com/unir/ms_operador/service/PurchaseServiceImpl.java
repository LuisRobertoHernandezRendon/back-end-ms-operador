package com.unir.ms_operador.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unir.ms_operador.controller.model.CreatePurchaseRequest;
import com.unir.ms_operador.data.PurchaseRepository;
import com.unir.ms_operador.data.model.Purchase;
import com.unir.ms_operador.facade.MoviesFacade;
import com.unir.ms_operador.facade.model.Movie;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PurchaseServiceImpl implements PurchaseService {
  
  @Autowired
  private PurchaseRepository repository;

  @Autowired
  private MoviesFacade moviesFacade;

  @Override
  public List<Purchase> getPurchasesByUser(Long userId){

    List<Purchase> purchases = repository.getByUserId(userId);
    return purchases.isEmpty() ? null : purchases;

  }

  @Override
  public Purchase createPurchase(CreatePurchaseRequest request){
    if (request != null && request.getUserId() != null && request.getMovieId() != null) {
      // buscamos la pelicula 
      Movie movie = moviesFacade.getMovie(request.getMovieId());

      if (movie == null) {
        log.warn("No se encontró la película con ID {}", request.getMovieId());
        return null; 
      }
      
      LocalDateTime purchaseDate = LocalDateTime.now();

      Purchase purchase = Purchase.builder()
        .userId(request.getUserId())
        .movieId(request.getMovieId())
        .pricePurchase(movie.getPricePurchase())
        .purchaseAt(purchaseDate)
        .build();

      return repository.save(purchase);
      
    }else {
      return null;
    }
  }

}
