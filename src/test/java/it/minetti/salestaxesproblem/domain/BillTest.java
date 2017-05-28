package it.minetti.salestaxesproblem.domain;

import it.minetti.salestaxesproblem.domain.items.TaxedItem;
import it.minetti.salestaxesproblem.domain.items.UntaxedItem;
import it.minetti.salestaxesproblem.entities.Product;
import org.junit.Test;

import java.math.BigDecimal;

import static it.minetti.salestaxesproblem.entities.Product.ProductType.*;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.Assert.*;

/**
 * Created by aminetti on 29.05.17.
 */
public class BillTest {

  private final UntaxedItem anExemptItemt = new UntaxedItem(new Product("ONE", BOOK, false, ONE));
  private final UntaxedItem aNotExemptItem = new UntaxedItem(new Product("Three", OTHER, false, ONE));
  private final UntaxedItem anImportedItem= new UntaxedItem(new Product("TWO", FOOD, true, ONE));
  private final UntaxedItem anImportedAndNotExemptItem= new UntaxedItem(new Product("TWO", OTHER, true, ONE));

  @Test
  public void applyTaxesTo() throws Exception {

    Bill b = new Bill(new Receipt(){{
      this.addProduct(new Product("XX", OTHER, true, TEN));
      this.addProduct(new Product("YY", FOOD, true, new BigDecimal("120.09")));
      this.addProduct(new Product("ZZ", MEDICAL, false, new BigDecimal("123.09")));
    }});

    assertThat(b.getItems().size(), is(3));
    assertThat(b.getTotalAmount(), comparesEqualTo(new BigDecimal("260.73")));
    assertThat(b.getTotalTaxes(), comparesEqualTo(new BigDecimal("7.55")));
  }

  @Test
  public void applyTaxesForNotExemptItem() throws Exception {
    TaxedItem taxedItem = Bill.applyTaxesTo(aNotExemptItem);
    assertThat(taxedItem.getFinalPrice(), comparesEqualTo(new BigDecimal("1.10")));
  }
  @Test
  public void applyTaxesForExemptItem() throws Exception {
    TaxedItem taxedItem = Bill.applyTaxesTo(anExemptItemt);
    assertThat(taxedItem.getFinalPrice(), comparesEqualTo(ONE));
  }
  @Test
  public void applyTaxesForImportedItem() throws Exception {
    TaxedItem taxedItem = Bill.applyTaxesTo(anImportedItem);
    assertThat(taxedItem.getFinalPrice(), comparesEqualTo(new BigDecimal("1.05")));
  }


  @Test
  public void applyTaxesForImportedAndNotExemptItem() throws Exception {
    TaxedItem taxedItem = Bill.applyTaxesTo(anImportedAndNotExemptItem);
    assertThat(taxedItem.getFinalPrice(), comparesEqualTo(new BigDecimal("1.15")));
  }

}