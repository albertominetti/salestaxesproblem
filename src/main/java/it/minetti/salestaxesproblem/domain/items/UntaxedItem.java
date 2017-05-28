package it.minetti.salestaxesproblem.domain.items;

import it.minetti.salestaxesproblem.entities.Product;
import it.minetti.salestaxesproblem.entities.Product.ProductType;

import java.math.BigDecimal;

public final class UntaxedItem implements Item {

  private String description;
  private boolean isImported;
  private BigDecimal shelfPrice;
  private ProductType type;

  // TODO use in tests
  public UntaxedItem(String description, ProductType type, boolean isImported, BigDecimal shelfPrice) {
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

  public BigDecimal getShelfPrice() {
    return this.shelfPrice;
  }

  public BigDecimal getFinalPrice() {
    return shelfPrice;
  }

  public ProductType getType() {
    return type;
  }

}