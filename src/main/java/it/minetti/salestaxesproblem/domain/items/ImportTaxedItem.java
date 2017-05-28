package it.minetti.salestaxesproblem.domain.items;

public class ImportTaxedItem extends TaxedItem {

  public ImportTaxedItem(Item item) {
    super(item);
  }

  @Override
  public double getRate() {
    return 0.05;
  }

}
