package it.minetti.salestaxesproblem;

import it.minetti.salestaxesproblem.domain.BasicTaxedItem;
import it.minetti.salestaxesproblem.domain.ImportTaxedItem;
import it.minetti.salestaxesproblem.domain.Item;
import it.minetti.salestaxesproblem.entity.Product.ProductType;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.Arrays.asList;


public class TaxCalculator {

  private static List<ProductType> exemptionByType = asList(ProductType.BOOK, ProductType.FOOD, ProductType.MEDICAL);

  public static Map<Item, Integer> applyTaxesTo(Map<Item, Integer> items) {
    Map<Item, Integer> taxedItems = new LinkedHashMap<>(items.size());

    for (Entry<Item, Integer> e : items.entrySet()) {
      Item item = e.getKey();
      int quantity = e.getValue();

      taxedItems.put(applyTaxesTo(item), quantity);
    }
    return taxedItems;
  }


  public static Item applyTaxesTo(Item item) {
    Item taxedItem = item;

    if (!exemptionByType.contains(item.getType())) {
      taxedItem = new BasicTaxedItem(item);
    }

    if (item.isImported()) {
      taxedItem = new ImportTaxedItem(item);
    }

    return taxedItem;
  }
}
