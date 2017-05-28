package it.minetti.salestaxesproblem.domain.items;

import java.math.BigDecimal;

public class ImportTaxedItem extends TaxedItem {

  public ImportTaxedItem(Item item) {
    super(item);
  }

  @Override
  public BigDecimal getRate() {
    return new BigDecimal("0.05");
  }

}
