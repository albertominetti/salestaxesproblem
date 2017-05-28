package it.minetti.salestaxesproblem;

import it.minetti.salestaxesproblem.domain.Bill;
import it.minetti.salestaxesproblem.domain.Receipt;
import it.minetti.salestaxesproblem.domain.items.UntaxedItem;
import it.minetti.salestaxesproblem.entities.Product;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by aminetti on 29.05.17.
 */
public class PrinterTest {

  private final Product aProduct = new Product("ONE", Product.ProductType.BOOK, true, TEN);
  private final Product anotherProduct = new Product("TWO", Product.ProductType.FOOD, false, ONE);
  private final UntaxedItem anUntaxedItem = new UntaxedItem(aProduct);
  private final UntaxedItem anotherUntaxedItem = new UntaxedItem(anotherProduct);

  private final Receipt r = new Receipt() {
    @Override
    public Map<UntaxedItem, Integer> getItems() {
      Map<UntaxedItem, Integer> map = new LinkedHashMap<>(2);
      map.put(anUntaxedItem, 2);
      map.put(anotherUntaxedItem, 5);
      return map;
    }
  };

  private final Printer printer = new Printer();

  @Test
  public void print() throws Exception {
    StringBuilder output = printer.print(r);

    assertThat(output.toString(), containsString("Receipt input: "));
    assertThat(output.toString(), containsString("2 imported ONE at 10.00"));
    assertThat(output.toString(), containsString("5 TWO at 1.00"));
  }

  @Test
  public void print1() throws Exception {
    StringBuilder output = printer.print(new Bill(r));

    System.out.println(output);

    assertThat(output.toString(), containsString("Receipt output: "));
    assertThat(output.toString(), containsString("2 ONE: 10.50"));
    assertThat(output.toString(), containsString("5 TWO: 1.00"));
    assertThat(output.toString(), containsString("Sales Taxes: 1.00"));
    assertThat(output.toString(), containsString("Total: 26.00"));
  }
}