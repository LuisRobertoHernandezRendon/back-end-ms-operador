package com.unir.ms_buscador.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unir.ms_buscador.data.model.Movie;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

interface MovieJpaRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {


}
