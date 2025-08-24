package com.unir.ms_operador.facade.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {
  private String movieId;
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
