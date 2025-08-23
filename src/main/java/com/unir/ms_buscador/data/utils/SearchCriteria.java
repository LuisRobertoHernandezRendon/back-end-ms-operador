package com.unir.ms_buscador.data.utils;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.LinkedList;
import java.util.List;

public class SearchCriteria<T> implements Specification<T> {

  private final List<SearchStatement> conditions = new LinkedList<>();

  public void add(SearchStatement statement) {
    conditions.add(statement);
  }

  @Override
  public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

    List<Predicate> predicates = new LinkedList<>();

    for (SearchStatement statement : conditions) {
      switch (statement.getOperation()) {
        case GREATER_THAN:
          predicates.add(
            builder.greaterThan(root.get(statement.getKey()), statement.getValue().toString()));
          break;
        case LESS_THAN:
          predicates.add(
            builder.lessThan(root.get(statement.getKey()), statement.getValue().toString()));
          break;
        case GREATER_THAN_EQUAL:
          predicates.add(
            builder.greaterThanOrEqualTo(root.get(statement.getKey()), statement.getValue().toString()));
          break;
        case LESS_THAN_EQUAL:
          predicates.add(
            builder.lessThanOrEqualTo(root.get(statement.getKey()), statement.getValue().toString()));
          break;
        case NOT_EQUAL:
          predicates.add(
            builder.notEqual(root.get(statement.getKey()), statement.getValue()));
          break;
        case EQUAL:
          predicates.add(
            builder.equal(root.get(statement.getKey()), statement.getValue()));
          break;
        case MATCH:
          if ("actors".equals(statement.getKey())) {
            // Hacer JOIN a la colección actors si es búsqueda por actor
            Join<Object, String> actorsJoin = root.join("actors", JoinType.LEFT);
            predicates.add(builder.like(
              builder.lower(actorsJoin),
              "%" + statement.getValue().toString().toLowerCase() + "%"));
          } else {
              predicates.add(builder.like(
                      builder.lower(root.get(statement.getKey())),
                      "%" + statement.getValue().toString().toLowerCase() + "%"));
          }
          break;
        case MATCH_END:
          predicates.add(builder.like(
              builder.lower(root.get(statement.getKey())),
              statement.getValue().toString().toLowerCase() + "%"));
          break;
        default:
            throw new UnsupportedOperationException("Operation not supported: " + statement.getOperation());
      }
    }

    return builder.and(predicates.toArray(new Predicate[0]));
  }
}
