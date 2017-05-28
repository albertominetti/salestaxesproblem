package it.minetti.salestaxesproblem.domain;

import it.minetti.salestaxesproblem.domain.items.UntaxedItem;
import it.minetti.salestaxesproblem.entities.Product;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static java.math.BigDecimal.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;

/**
 * Created by aminetti on 29.05.17.
 */
public class ReceiptTest {

  private final Product aProduct = new Product("ONE", Product.ProductType.BOOK, true, TEN);
  private final Product anotherProduct = new Product("TWO", Product.ProductType.FOOD, false, ONE);
  private final UntaxedItem anUntaxedItem = new UntaxedItem(aProduct);
  private final Receipt r = new Receipt();

  @Test
  public void addProduct() throws Exception {
    r.addProduct(aProduct);

    assertThat(r.getItems().size(), is(1));
    assertThat(r.getItems().get(anUntaxedItem), is(1));
  }

  @Test
  public void addProductTwice() throws Exception {
    r.addProduct(aProduct);
    r.addProduct(aProduct);

    assertThat(r.getItems().size(), is(1));
    assertThat(r.getItems().get(anUntaxedItem), is(2));
  }


  @Test
  public void addProductTwiceAndAnother() throws Exception {
    r.addProduct(aProduct);
    r.addProduct(aProduct);
    r.addProduct(anotherProduct);

    assertThat(r.getItems().size(), is(2));
    assertThat(r.getItems().get(anUntaxedItem), is(2));
  }


  @Test
  public void addProducts() throws Exception {
    r.addProducts(aProduct, 3);

    assertThat(r.getItems().size(), is(1));
    assertThat(r.getItems().get(anUntaxedItem), is(3));
  }

  @Test
  public void getQuantity() throws Exception {
    r.addProducts(aProduct, 3);

    assertThat(r.getQuantity(anUntaxedItem), is(3));
  }

  @Test
  public void getZeroQuantity() throws Exception {
    assertThat(r.getQuantity(anUntaxedItem), is(0));
  }

  @Test
  public void addProductsDoesnotAffectThePreviousItems() {
    r.addProduct(aProduct);
    Map<UntaxedItem, Integer> items = r.getItems();
    r.addProduct(aProduct);
    r.addProduct(anotherProduct);

    assertThat(items.size(), is(1));
    assertThat(r.getItems().size(), is(2));

    assertThat(items.get(anUntaxedItem), is(1));
    assertThat(r.getItems().get(anUntaxedItem), is(2));
  }

}