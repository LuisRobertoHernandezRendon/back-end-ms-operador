package com.unir.ms_operador.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.unir.ms_operador.controller.model.CreateRentalRequest;
import com.unir.ms_operador.data.model.Rental;
import com.unir.ms_operador.service.RentalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j 
@Tag(name = "Rentals Controller", description = "Microservicio que expone las opreaciones para realizar la renta y devolucion de peliculas")
public class RentalControllers {
  
  private final RentalService service;

  @GetMapping("rentals/user/{userId}")
  @Operation(
    operationId = "Obtener peliculas rentadas por usuario",
    description = "Operacion de lectura",
    summary = "Se regresa una lista de todas las peliculas rentadas por un usuario"
  )
  @ApiResponse(
    responseCode = "200",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Rental.class ))
  )
  public ResponseEntity<List<Rental>> getRentalsByUser(@PathVariable Long userId){
    
    List<Rental> rentals = service.getRentalsByUser(userId);

    if (rentals != null) {
      return ResponseEntity.ok(rentals);
    } else {
      return ResponseEntity.ok(Collections.emptyList());
    }

  }

  @PostMapping("/rentals")
  @Operation(
    operationId = "Insertar una renta de película",
    summary = "Crear una nueva renta de película",
    description = "Crea una renta de película a partir de los datos proporcionados.",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "Datos de la renta de película a crear.",
      required = true,
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateRentalRequest.class))
    )
  )
  @ApiResponse(
    responseCode = "201",
    description = "Renta de Película creada correctamente.",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Rental.class))
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
  public ResponseEntity<Rental> createRental(@RequestBody CreateRentalRequest request) {

    // FALTA BUSCAR LA PELICULA CON EL MS DE BUSCADOR

    Rental createdRental = service.createRental(request);

    if (createdRental != null) {
      return ResponseEntity.status(HttpStatus.CREATED).body(createdRental);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/rentals/{rentalId}/return")
  @Operation(
    operationId = "Devolver una pelicula rentada",
    description = "Operacion de escritura",
    summary = "Se elimina una pelicula rentada a partir del identificador de la renta.")
  @ApiResponse(
    responseCode = "200",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)))
  @ApiResponse(
    responseCode = "404",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
    description = "No se ha encontrado la renta de pelicula con el identificador indicado.")
  public ResponseEntity<Void> returnRental(@PathVariable Long rentalId) {

    Boolean returned = service.returnRental(rentalId);

    if (Boolean.TRUE.equals(returned)) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }

  }
}
