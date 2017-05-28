package it.minetti.salestaxesproblem.domain;

import it.minetti.salestaxesproblem.entity.Product.ProductType;

public interface Item {
  String getDescription();

  double getFinalPrice();

  double getShelfPrice();

  boolean isImported();

  ProductType getType();
}
