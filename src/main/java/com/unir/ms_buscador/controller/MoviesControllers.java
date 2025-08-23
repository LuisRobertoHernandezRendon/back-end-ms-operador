package com.unir.ms_buscador.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.unir.ms_buscador.controller.model.CreateMovieRequest;
import com.unir.ms_buscador.controller.model.MovieDto;
import com.unir.ms_buscador.data.model.Movie;
import com.unir.ms_buscador.service.MoviesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j 
@Tag(name = "Movies Controller", description = "Microservicio que expone las opreaciones CRUD para las peliculas guardadas en BD")
public class MoviesControllers {

  private final MoviesService service;

  @GetMapping("/movies")
  @Operation(
    operationId = "Obtener peliculas",
    description = "Operacion de lectura",
    summary = "Se regresa una lista de todas las peliculas guardadas en la base de datos"
  )
  @ApiResponse(
    responseCode = "200",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class ))
  )
  public ResponseEntity<List<Movie>> getMovies(
    @RequestHeader Map<String, String> headers,
    @Parameter(name = "title", description = "Título de la película. No tiene por qué ser exacto", example = "Matrix", required = false)
    @RequestParam(required = false) String title,
    @Parameter(name = "synopsis", description = "Sinopsis de la película. No tiene por qué ser exacta", example = "Un hacker descubre la verdad", required = false)
    @RequestParam(required = false) String synopsis,
    @Parameter(name = "category", description = "Categoría de la película", example = "Ciencia Ficción", required = false)
    @RequestParam(required = false) String category,
    @Parameter(name = "release_year", description = "Año de estreno de la película", example = "1999", required = false)
    @RequestParam(required = false) Integer release_year,
    @Parameter(name = "director", description = "Director de la película", example = "The Wachowskis", required = false)
    @RequestParam(required = false) String director,
    @Parameter(name = "language", description = "Idioma de la película", example = "Inglés", required = false)
    @RequestParam(required = false) String language,
    @Parameter(name = "actor", description = "Actor de la película", example = "Keanu Reeves", required = false)
    @RequestParam(required = false) String actor,
    @Parameter(name = "visible", description = "Estado visible. true o false", example = "true", required = false)
    @RequestParam(required = false) Boolean visible) {

    log.info("headers: {}", headers);

    List<Movie> movies = service.getMovies(title, synopsis, category, release_year, director, language, actor, visible);

    if (movies != null) {
        return ResponseEntity.ok(movies);
    } else {
        return ResponseEntity.ok(Collections.emptyList());
    }
  }

  @GetMapping("/movies/{movieId}")
  @Operation(
    operationId = "Obtener una pelicula",
    description = "Operacion de lectura",
    summary = "Devuelve una pelicula a partir de su identificador.")
  @ApiResponse(
    responseCode = "200",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class)))
  @ApiResponse(
    responseCode = "404",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
    description = "No se ha encontrado la pelicula con el identificador indicado.")
  public ResponseEntity<Movie> getMovie(@PathVariable Long movieId) {

    log.info("Request received for movie {}", movieId);
    Movie movie = service.getMovie(movieId);

    if (movie != null) {
      return ResponseEntity.ok(movie);
    } else {
      return ResponseEntity.notFound().build();
    }

  }
  
  @DeleteMapping("/movies/{movieId}")
  @Operation(
    operationId = "Eliminar una pelicula",
    description = "Operacion de escritura",
    summary = "Se elimina una pelicula a partir de su identificador.")
  @ApiResponse(
    responseCode = "200",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)))
  @ApiResponse(
    responseCode = "404",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
    description = "No se ha encontrado la pelicula con el identificador indicado.")
  public ResponseEntity<Void> removeMovie(@PathVariable Long movieId) {

    Boolean removed = service.removeMovie(movieId);

    if (Boolean.TRUE.equals(removed)) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }

  }

  @PostMapping("/movies")
  @Operation(
    operationId = "Insertar una Pelicula",
    summary = "Crear una nueva película",
    description = "Crea una película a partir de los datos proporcionados.",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "Datos de la película a crear.",
      required = true,
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateMovieRequest.class))
    )
  )
  @ApiResponse(
    responseCode = "201",
    description = "Película creada correctamente.",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))
  )
  @ApiResponse(
    responseCode = "400",
    description = "Datos incorrectos introducidos.",
    content = @Content(mediaType = "application/json")
  )
  @ApiResponse(
    responseCode = "404",
    description = "No se encontró la película con el identificador indicado.",
    content = @Content(mediaType = "application/json")
  )
  public ResponseEntity<Movie> addMovie(@RequestBody CreateMovieRequest request) {

    Movie createdMovie = service.createMovie(request);

    if (createdMovie != null) {
      return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

  @PatchMapping("/movies/{movieId}")
  @Operation(
    operationId = "Modificar parcialmente una pelicula",
    description = "Operacion de escritura",
    summary = "Se modifica parcialmente una pelicula.",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "Datos de la pelicula.",
      required = true,
      content = @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = String.class))))
  @ApiResponse(
    responseCode = "200",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class)))
  @ApiResponse(
    responseCode = "400",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
    description = "Pelicula inválida o datos incorrectos introducidos.")
  public ResponseEntity<Movie> patchMovie(@PathVariable Long movieId, @RequestBody String patchBody) {

    Movie patched = service.updateMovie(movieId, patchBody);
    if (patched != null) {
      return ResponseEntity.ok(patched);
    } else {
      return ResponseEntity.badRequest().build();
    } 

  }

  @PutMapping("/movies/{movieId}")
  @Operation(
    operationId = "Modificar totalmente una pelicula",
    description = "Operacion de escritura",
    summary = "Se modifica totalmente una pelicula.",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "Datos de la pelicula a actualizar.",
      required = true,
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDto.class))))
  @ApiResponse(
    responseCode = "200",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class)))
  @ApiResponse(
    responseCode = "404",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
    description = "Pelicula no encontrada.")
  public ResponseEntity<Movie> updateMovie(@PathVariable long movieId, @RequestBody MovieDto body) {

    Movie updated = service.updateMovie(movieId, body);
    if (updated != null) {
      return ResponseEntity.ok(updated);
    } else {
      return ResponseEntity.notFound().build();
    }
    
  }

}
