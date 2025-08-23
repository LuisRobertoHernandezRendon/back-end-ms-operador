package com.unir.ms_buscador.data.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import java.util.List;

import com.unir.ms_buscador.controller.model.MovieDto;
import com.unir.ms_buscador.data.utils.Consts;

@Entity
@Table(name = "movie")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = Consts.TITLE, unique = true)
	private String title;

  @Column(name = Consts.DIRECTOR)
  private String director;

  @Column(name = Consts.RELEASE_YEAR)
  private Integer release_year;

  @Column(name = Consts.SYNOPSIS, columnDefinition = "TEXT")
  private String synopsis;

  @Column(name = Consts.DURATION)
  private String duration;

  @Column(name =Consts.TRAILERURL)
  private String trailerUrl;

  @Column(name = Consts.IMAGE)
  private String image;

  @Column(name = Consts.CATEGORY)
  private String category;

  @Column(name = Consts.LANGUAGE)
  private String language;

  // Por simplicidad, almacenamos actores como texto separado por comas
  @ElementCollection
  @CollectionTable(name = "movie_actors", joinColumns = @JoinColumn(name = "movie_id"))
  @Column(name = Consts.ACTORS)
  private List<String> actors;

  @Column(name = Consts.PRICERENTAL)
  private Double priceRental;

  @Column(name = Consts.PRICEPURCHASE)
  private Double pricePurchase;

  @Column(name = Consts.VISIBLE)
  private Boolean visible;

  public void update(MovieDto movieDto) {
    this.setTitle(movieDto.getTitle());
    this.setDirector(movieDto.getDirector());
    this.setRelease_year(movieDto.getYear());
    this.setSynopsis(movieDto.getSynopsis());
    this.setDuration(movieDto.getDuration());
    this.setTrailerUrl(movieDto.getTrailerUrl());
    this.setImage(movieDto.getImage());
    this.setCategory(movieDto.getCategory());
    this.setLanguage(movieDto.getLanguage());
    this.setPriceRental(movieDto.getPriceRental());
    this.setPricePurchase(movieDto.getPricePurchase());
    this.setVisible(movieDto.getVisible());
  }
    
}
