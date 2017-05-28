package it.minetti.salestaxesproblem.domain;

import it.minetti.salestaxesproblem.domain.items.*;
import it.minetti.salestaxesproblem.entities.Product.ProductType;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.Arrays.asList;


public class Bill {

  private static List<ProductType> exemptionByType = asList(ProductType.BOOK, ProductType.FOOD, ProductType.MEDICAL);

  private Map<TaxedItem, Integer> items;

  public Bill(Receipt r) {
    this.items = applyTaxesTo(r.getItems());
  }

  public Map<TaxedItem, Integer> applyTaxesTo(Map<UntaxedItem, Integer> items) {
    Map<TaxedItem, Integer> taxedItems = new LinkedHashMap<>(items.size());

    for (Entry<UntaxedItem, Integer> e : items.entrySet()) {
      UntaxedItem item = e.getKey();
      int quantity = e.getValue();

      taxedItems.put(applyTaxesTo(item), quantity);
    }
    return taxedItems;
  }


  TaxedItem applyTaxesTo(UntaxedItem item) {
    TaxedItem taxedItem = new ZeroTaxesItem(item);

    if (!exemptionByType.contains(item.getType())) {
      taxedItem = new BasicTaxedItem(item);
    }

    if (item.isImported()) {
      taxedItem = new ImportTaxedItem(item);
    }

    return taxedItem;
  }

  public Map<TaxedItem, Integer> getItems() {
    return items;
  }

  public double getTotalTaxes() {
    double totalTaxes = 0.0;
    for (Map.Entry<TaxedItem, Integer> e : items.entrySet()) {
      Item item = e.getKey();
      int quantity = e.getValue();

      double subTotalTaxes = (item.getFinalPrice() - item.getShelfPrice()) * quantity;
      totalTaxes += subTotalTaxes;
    }

    return totalTaxes;
  }


  public double getTotalAmount() {
    double totalAmount = 0.0;
    for (Map.Entry<TaxedItem, Integer> e : items.entrySet()) {
      Item item = e.getKey();
      int quantity = e.getValue();

      double subTotalAmount = item.getFinalPrice() * quantity;
      totalAmount += subTotalAmount;

    }
    return totalAmount;
  }

}
