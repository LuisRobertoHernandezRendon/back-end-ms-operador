package com.unir.ms_buscador.data;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.unir.ms_buscador.data.model.Movie;
import com.unir.ms_buscador.data.utils.Consts;
import com.unir.ms_buscador.data.utils.SearchCriteria;
import com.unir.ms_buscador.data.utils.SearchOperation;
import com.unir.ms_buscador.data.utils.SearchStatement;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MovieRepository {

    private final MovieJpaRepository repository;

    public List<Movie> getMovies() {
      return repository.findAll();
    }

    public Movie getById(Long id) {
      return repository.findById(id).orElse(null);
    }

    public Movie save(Movie product) {
      return repository.save(product);
    }

    public void delete(Movie product) {
      repository.delete(product);
    }

    public List<Movie> search(String title, String synopsis, String category, Integer release_year, String director, String language, String actor, Boolean visible) {

      SearchCriteria<Movie> spec = new SearchCriteria<>();

      if (StringUtils.isNotBlank(title)) {
        spec.add(new SearchStatement(Consts.TITLE, title, SearchOperation.MATCH));
      }

      if (StringUtils.isNotBlank(synopsis)) {
        spec.add(new SearchStatement(Consts.SYNOPSIS, synopsis, SearchOperation.MATCH));
      }

      if (StringUtils.isNotBlank(category)) {
        spec.add(new SearchStatement(Consts.CATEGORY, category, SearchOperation.MATCH));
      }

      if (release_year != null) {
        spec.add(new SearchStatement(Consts.RELEASE_YEAR, release_year, SearchOperation.EQUAL));
      }

      if (StringUtils.isNotBlank(director)) {
        spec.add(new SearchStatement(Consts.DIRECTOR, director, SearchOperation.MATCH));
      }

      if (StringUtils.isNotBlank(language)) {
        spec.add(new SearchStatement(Consts.LANGUAGE, language, SearchOperation.MATCH));
      }

      if (StringUtils.isNotBlank(actor)) {
        spec.add(new SearchStatement(Consts.ACTORS, actor, SearchOperation.MATCH));
      }

      return repository.findAll(spec);
    }

}
