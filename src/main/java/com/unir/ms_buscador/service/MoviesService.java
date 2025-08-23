package com.unir.ms_buscador.service;

import com.unir.ms_buscador.data.model.Movie;
import com.unir.ms_buscador.controller.model.CreateMovieRequest;
import com.unir.ms_buscador.controller.model.MovieDto;

import java.util.List;

public interface MoviesService {

    List<Movie> getMovies(String title, String synopsis, String category, Integer release_year, String director, String language, String actor, Boolean visible);

    Movie getMovie(Long movieId);

    Boolean removeMovie(Long movieId);

    Movie createMovie(CreateMovieRequest request);

    Movie updateMovie(Long movieId, String updateRequest);

    Movie updateMovie(Long movieId, MovieDto updateRequest);

}
