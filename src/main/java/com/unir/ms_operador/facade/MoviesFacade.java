package com.unir.ms_operador.facade;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.unir.ms_operador.facade.model.Movie;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class MoviesFacade {
  
  @Value("${getMovie.url}")
  private String getMovieUrl;

  private final RestTemplate restTemplate;

  public Movie getMovie(String movieId) {
    try {
      return restTemplate.getForObject(String.format(getMovieUrl, movieId), Movie.class);
    } catch (HttpClientErrorException e) {
      log.error("Client Error: {}, Movie with ID {}", e.getStatusCode(), movieId);
      return null;
    }
  }

}
