package com.unir.ms_buscador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.unir.ms_buscador.controller.model.CreateMovieRequest;
import com.unir.ms_buscador.controller.model.MovieDto;
import com.unir.ms_buscador.data.MovieRepository;
import com.unir.ms_buscador.data.model.Movie;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MoviesServiceImpl implements MoviesService{

  @Autowired
  private MovieRepository repository;

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  public List<Movie> getMovies(String title, String synopsis, String category, Integer release_year, String director, String language, String actor, Boolean visible) {

    if (StringUtils.hasLength(title) || StringUtils.hasLength(synopsis) || StringUtils.hasLength(category) || release_year != null || StringUtils.hasLength(director) || StringUtils.hasLength(language) || StringUtils.hasLength(actor) || visible != null) {
      return repository.search(title, synopsis, category, release_year, director, language, actor, visible);
    }

    List<Movie> movies = repository.getMovies();
    return movies.isEmpty() ? null : movies;
  }
  
  @Override
  public Movie getMovie(Long movieId){
    return repository.getById(movieId);
  }

  @Override
	public Boolean removeMovie(Long movieId) {

		Movie movie = repository.getById(movieId);

		if (movie != null) {
			repository.delete(movie);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
  public Movie createMovie(CreateMovieRequest request) {

    if (request != null && StringUtils.hasLength(request.getTitle()) && StringUtils.hasLength(request.getSynopsis()) && StringUtils.hasLength(request.getCategory()) && request.getRelease_year() != null && StringUtils.hasLength(request.getDirector()) && StringUtils.hasLength(request.getLanguage()) && request.getActors() != null && !request.getActors().isEmpty() && request.getPriceRental() != null && request.getPricePurchase() != null && request.getVisible() != null) {

      Movie movie = Movie.builder()
        .title(request.getTitle().trim())
        .synopsis(request.getSynopsis().trim())
        .category(request.getCategory().trim())
        .release_year(request.getRelease_year())
        .director(request.getDirector().trim())
        .language(request.getLanguage().trim())
        .actors(request.getActors())
        .duration(request.getDuration())
        .trailerUrl(request.getTrailerUrl())
        .image(request.getImage())
        .priceRental(request.getPriceRental())
        .pricePurchase(request.getPricePurchase())
        .visible(request.getVisible())
        .build();

      return repository.save(movie);
    } else {
      return null;
    }
  }

  @Override
	public Movie updateMovie(Long movieId, String request) {

		Movie movie = repository.getById(movieId);
		if (movie != null) {
			try {
				JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(request));
				JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(movie)));
				Movie patched = objectMapper.treeToValue(target, Movie.class);
				repository.save(patched);
				return patched;
			} catch (JsonProcessingException | JsonPatchException e) {
				log.error("Error updating product {}", movieId, e);
        return null;
      }
    } else {
			return null;
		}
	}

  @Override
	public Movie updateMovie(Long movieId, MovieDto updateRequest) {
		Movie movie = repository.getById(movieId);
		if (movie != null) {
			movie.update(updateRequest);
			repository.save(movie);
			return movie;
		} else {
			return null;
		}
	}
}
