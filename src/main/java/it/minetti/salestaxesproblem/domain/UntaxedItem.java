package it.minetti.salestaxesproblem.domain;

import it.minetti.salestaxesproblem.entity.Product;
import it.minetti.salestaxesproblem.entity.Product.ProductType;

public final class UntaxedItem implements Item {

  private String description;
  private boolean isImported;
  private double shelfPrice;
  private ProductType type;

  public UntaxedItem(String description, ProductType type, boolean isImported, double shelfPrice) {
    this.description = description;
    this.shelfPrice = shelfPrice;
    this.isImported = isImported;
    this.type = type;
  }

  public UntaxedItem(Product p) {
    this.description = p.getDescription();
    this.shelfPrice = p.getShielfPrice();
    this.isImported = p.isImported();
    this.type = p.getType();
  }

  public String getDescription() {
    return description;
  }

  public boolean isImported() {
    return isImported;
  }

  public double getShelfPrice() {
    return this.shelfPrice;
  }

  public double getFinalPrice() {
    return shelfPrice;
  }

  public ProductType getType() {
    return type;
  }

}