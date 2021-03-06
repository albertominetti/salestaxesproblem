package it.minetti.salestaxesproblem.entities;

import java.math.BigDecimal;

public final class Product {
  public enum ProductType {
    BOOK, FOOD, MEDICAL, OTHER
  }

  private String description;
  private ProductType type = ProductType.OTHER;
  private boolean isImported = false;
  private BigDecimal shielfPrice;

  public String getDescription() {
    return description;
  }

  public ProductType getType() {
    return type;
  }

  public boolean isImported() {
    return isImported;
  }

  public BigDecimal getShielfPrice() {
    return shielfPrice;
  }

  public Product(String description, ProductType type, boolean isImported, BigDecimal shielfPrice) {
    this.description = description;
    this.type = type;
    this.isImported = isImported;
    this.shielfPrice = shielfPrice;
  }
}
