package it.minetti.salestaxesproblem;

import it.minetti.salestaxesproblem.domain.Bill;
import it.minetti.salestaxesproblem.domain.Receipt;
import it.minetti.salestaxesproblem.domain.items.Item;
import it.minetti.salestaxesproblem.domain.items.TaxedItem;
import it.minetti.salestaxesproblem.domain.items.UntaxedItem;

import java.text.DecimalFormat;
import java.util.Map;

public class Printer {
  private static String NL = System.getProperty("line.separator");
  private static DecimalFormat df = new DecimalFormat("0.00");

  public StringBuffer print(Receipt receipt) {
    Map<UntaxedItem, Integer> items = receipt.getItems();

    StringBuffer sb = new StringBuffer(items.size() * 30);
    sb.append("Receipt input: " + NL);
    for (Map.Entry<UntaxedItem, Integer> e : items.entrySet()) {
      Item item = e.getKey();
      int quantity = e.getValue();
      sb.append("" + quantity + (item.isImported() ? " imported " : " ")
        + item.getDescription() + " at " + df.format(item.getShelfPrice()) + NL);
    }
    return sb;
  }

  public StringBuffer print(Bill bill) {
    Map<TaxedItem, Integer> items = bill.getItems();

    StringBuffer sb = new StringBuffer(items.size() * 30 + 100);
    sb.append("Receipt output: " + NL);
    for (Map.Entry<TaxedItem, Integer> e : items.entrySet()) {
      Item item = e.getKey();
      int quantity = e.getValue();

      sb.append("" + quantity + " " + item.getDescription() + ": "
        + df.format(item.getFinalPrice()) + NL);
    }

    sb.append("Sales Taxes: " + df.format(bill.getTotalTaxes()) + NL);
    sb.append("Total: " + df.format(bill.getTotalAmount()) + NL);

    return sb;
  }
}
