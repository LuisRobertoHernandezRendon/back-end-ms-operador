package com.unir.ms_buscador.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMovieRequest {

  private String title;
  private String director;
  private Integer release_year;
  private String synopsis;
  private String duration;
  private String trailerUrl;
  private String image;
  private String category;
  private String language;
  private List<String> actors;
  private Double priceRental;
  private Double pricePurchase;
  private Boolean visible;

}
