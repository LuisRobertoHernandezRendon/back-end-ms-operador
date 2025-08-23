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
@Table(name = "purchase")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Purchase {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long purchaseId;  // identificador único de la renta

  @Column(name = "movie_id", nullable = false)
  private Long movieId;  // Id de la película (referencia al MS de movies)

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(name = "purchase_at", nullable = false)
  private LocalDateTime purchaseAt;

  @Column(name = "price_purchase", nullable = false)
  private Double pricePurchase;
}
