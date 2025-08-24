package com.unir.ms_operador.data.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "rental")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Rental {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long rentalId;  // identificador único de la renta

  @Column(name = "movie_id", nullable = false)
  private String movieId;  // Id de la película (referencia al MS de movies)

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(name = "rented_at", nullable = false)
  private LocalDateTime rentedAt;

  @Column(name = "return_at")
  private LocalDateTime returnAt;

  @Column(name = "price_rental", nullable = false)
  private Double priceRental;
}
