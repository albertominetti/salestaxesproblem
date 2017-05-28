package it.minetti.salestaxesproblem.domain;

import it.minetti.salestaxesproblem.domain.items.Item;
import it.minetti.salestaxesproblem.domain.items.UntaxedItem;
import it.minetti.salestaxesproblem.entities.Product;

import java.util.LinkedHashMap;
import java.util.Map;

public class Receipt {

  private Map<UntaxedItem, Integer> items;

  public Receipt() {
    items = new LinkedHashMap<>();
  }

  public void addProduct(Product p) {
    addProducts(p, 1);
  }

  public void addProducts(Product p, int amount) {
    UntaxedItem i = new UntaxedItem(p);
    int newQuantity = getQuantity(i) + amount;
    items.put(i, newQuantity);
  }

  public int getQuantity(Item i) {
    return items.getOrDefault(i, 0);
  }

  public Map<UntaxedItem, Integer> getItems() {
    return new LinkedHashMap<>(items); //the keys and values themselves are not cloned
  }

}
