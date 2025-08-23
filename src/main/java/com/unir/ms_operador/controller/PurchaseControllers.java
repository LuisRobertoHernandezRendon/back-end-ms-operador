package com.unir.ms_operador.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.unir.ms_operador.controller.model.CreatePurchaseRequest;
import com.unir.ms_operador.data.model.Purchase;
import com.unir.ms_operador.service.PurchaseService;

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
@Tag(name = "Purchase Controller", description = "Microservicio que expone las opreaciones para realizar la compra de peliculas")
public class PurchaseControllers {
  
  private final PurchaseService service;

  @GetMapping("purchases/user/{userId}")
  @Operation(
    operationId = "Obtener peliculas compradas por usuario",
    description = "Operacion de lectura",
    summary = "Se regresa una lista de todas las peliculas compradas por un usuario"
  )
  @ApiResponse(
    responseCode = "200",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Purchase.class ))
  )
  public ResponseEntity<List<Purchase>> getPurchasesByUser(@PathVariable Long userId){
    
    List<Purchase> purchases = service.getPurchasesByUser(userId);

    if (purchases != null) {
      return ResponseEntity.ok(purchases);
    } else {
      return ResponseEntity.ok(Collections.emptyList());
    }

  }

  @PostMapping("/purchases")
  @Operation(
    operationId = "Insertar una compra de película",
    summary = "Crear una nueva compra de película",
    description = "Crea una compra de película a partir de los datos proporcionados.",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "Datos de la compra de película a crear.",
      required = true,
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreatePurchaseRequest.class))
    )
  )
  @ApiResponse(
    responseCode = "201",
    description = "Compra de Película creada correctamente.",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Purchase.class))
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
  public ResponseEntity<Purchase> createPurchase(@RequestBody CreatePurchaseRequest request) {

    // FALTA BUSCAR LA PELICULA CON EL MS DE BUSCADOR

    Purchase createdPurchase = service.createPurchase(request);

    if (createdPurchase != null) {
      return ResponseEntity.status(HttpStatus.CREATED).body(createdPurchase);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

}
