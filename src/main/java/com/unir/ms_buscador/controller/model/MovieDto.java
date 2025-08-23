package com.unir.ms_buscador.controller.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MovieDto {

  private String title;
  private String director;
  private Integer year;
  private String synopsis;
  private String duration;
  private String trailerUrl;
  private String image;
  private String category;
  private String language;
  private Double priceRental;
  private Double pricePurchase;
  private Boolean visible;

}