package it.minetti.salestaxesproblem.domain.items;

import it.minetti.salestaxesproblem.domain.items.Item;
import it.minetti.salestaxesproblem.domain.items.TaxedItem;

public class ZeroTaxesItem extends TaxedItem {
  @Override
  public double getRate() {
    return 0.0;
  }

  public ZeroTaxesItem(Item item) {
    super(item);
  }
}
