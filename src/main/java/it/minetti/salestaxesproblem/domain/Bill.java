package it.minetti.salestaxesproblem.domain;

import it.minetti.salestaxesproblem.domain.items.*;
import it.minetti.salestaxesproblem.entities.Product.ProductType;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.Arrays.asList;


public class Bill {

  private static List<ProductType> exemptProductTypes = asList(ProductType.BOOK, ProductType.FOOD, ProductType.MEDICAL);

  private Map<TaxedItem, Integer> items;

  public Bill(Receipt r) {
    this.items = applyTaxesTo(r.getItems());
  }

  Map<TaxedItem, Integer> applyTaxesTo(Map<UntaxedItem, Integer> items) {
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

    if (!exemptProductTypes.contains(item.getType())) {
      taxedItem = new BasicTaxedItem(item);
    }

    if (item.isImported()) {
      taxedItem = new ImportTaxedItem(item);
    }

    return taxedItem;
  }

  public BigDecimal getTotalTaxes() {
    BigDecimal totalTaxes = BigDecimal.ZERO;
    for (Map.Entry<TaxedItem, Integer> e : items.entrySet()) {
      Item item = e.getKey();
      int quantity = e.getValue();

      BigDecimal singleItemTaxes = item.getFinalPrice().subtract(item.getShelfPrice());
      BigDecimal subTotalTaxes = singleItemTaxes.multiply(new BigDecimal(quantity));
      totalTaxes = totalTaxes.add(subTotalTaxes);
    }

    return totalTaxes;
  }

  public BigDecimal getTotalAmount() {
    BigDecimal totalAmount = BigDecimal.ZERO;
    for (Map.Entry<TaxedItem, Integer> e : items.entrySet()) {
      Item item = e.getKey();
      int quantity = e.getValue();

      BigDecimal subTotalAmount = item.getFinalPrice().multiply(new BigDecimal(quantity));
      totalAmount = totalAmount.add(subTotalAmount);
    }

    return totalAmount;
  }

  public Map<TaxedItem, Integer> getItems() {
    return items;
  }
}
