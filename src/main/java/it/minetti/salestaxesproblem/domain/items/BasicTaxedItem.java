package it.minetti.salestaxesproblem.domain.items;

import java.math.BigDecimal;

public class BasicTaxedItem extends TaxedItem {

  public BasicTaxedItem(Item item) {
    super(item);
  }

  @Override
  public BigDecimal getRate() {
    return new BigDecimal("0.10");
  }

}
