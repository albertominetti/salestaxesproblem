package it.minetti.salestaxesproblem.domain.items;

import it.minetti.salestaxesproblem.entities.Product.ProductType;

import java.math.BigDecimal;

public interface Item {
  String getDescription();

  BigDecimal getFinalPrice();

  BigDecimal getShelfPrice();

  boolean isImported();

  ProductType getType();
}
