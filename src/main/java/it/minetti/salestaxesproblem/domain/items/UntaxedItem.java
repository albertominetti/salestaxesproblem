package it.minetti.salestaxesproblem.domain.items;

import it.minetti.salestaxesproblem.entities.Product;
import it.minetti.salestaxesproblem.entities.Product.ProductType;

import java.math.BigDecimal;

public final class UntaxedItem implements Item {

  private String description;
  private boolean isImported;
  private BigDecimal shelfPrice;
  private ProductType type;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UntaxedItem that = (UntaxedItem) o;

    if (isImported != that.isImported) return false;
    if (description != null ? !description.equals(that.description) : that.description != null) return false;
    if (shelfPrice != null ? !shelfPrice.equals(that.shelfPrice) : that.shelfPrice != null) return false;
    return type == that.type;
  }

  @Override
  public int hashCode() {
    int result = description != null ? description.hashCode() : 0;
    result = 31 * result + (isImported ? 1 : 0);
    result = 31 * result + (shelfPrice != null ? shelfPrice.hashCode() : 0);
    result = 31 * result + (type != null ? type.hashCode() : 0);
    return result;
  }
}