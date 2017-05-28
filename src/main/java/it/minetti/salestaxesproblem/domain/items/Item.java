package it.minetti.salestaxesproblem.domain.items;

import it.minetti.salestaxesproblem.entities.Product.ProductType;

public interface Item {
  String getDescription();

  double getFinalPrice();

  double getShelfPrice();

  boolean isImported();

  ProductType getType();
}
