package it.minetti.salestaxesproblem.domain.items;

import it.minetti.salestaxesproblem.entities.Product;

import java.math.BigDecimal;

public abstract class TaxedItem implements Item {

  private Item item;

  public abstract BigDecimal getRate();

  public TaxedItem(Item item) {
    this.item = item;
  }

  static BigDecimal roundToNearest5cents(BigDecimal decimal) {
    BigDecimal ROUND_FACTOR = new BigDecimal("0.05");
    return ROUND_FACTOR.multiply(decimal.divide(ROUND_FACTOR, 0, BigDecimal.ROUND_UP));
  }

  public BigDecimal getFinalPrice() {
    BigDecimal salesTax = roundToNearest5cents(item.getShelfPrice().multiply(this.getRate()));
    return item.getFinalPrice().add(salesTax);
  }

  protected Item getItem() {
    return item;
  }


  public Product.ProductType getType() {
    return item.getType();
  }

  public boolean isImported() {
    return item.isImported();
  }

  public String getDescription() {
    return item.getDescription();
  }

  public BigDecimal getShelfPrice() {
    return item.getShelfPrice();
  }


}