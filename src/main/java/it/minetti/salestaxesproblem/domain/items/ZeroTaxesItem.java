package it.minetti.salestaxesproblem.domain.items;

import java.math.BigDecimal;

public class ZeroTaxesItem extends TaxedItem {
  @Override
  public BigDecimal getRate() {
    return BigDecimal.ZERO;
  }

  public ZeroTaxesItem(Item item) {
    super(item);
  }
}
