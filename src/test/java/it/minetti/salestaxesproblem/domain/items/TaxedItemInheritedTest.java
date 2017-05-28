package it.minetti.salestaxesproblem.domain.items;

import org.junit.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.Assert.*;

/**
 * Created by aminetti on 29.05.17.
 */
public class TaxedItemInheritedTest {
  @Test
  public void getRateFromZeroTaxedItem() throws Exception {
    ZeroTaxesItem item = new ZeroTaxesItem(null);
    assertThat(item.getRate(), comparesEqualTo(ZERO));
  }

  @Test
  public void getRateFromBasicTaxedItem() throws Exception {
    BasicTaxedItem item = new BasicTaxedItem(null);
    assertThat(item.getRate(), comparesEqualTo(new BigDecimal("0.10")));
  }


  @Test
  public void getRateFromImportTaxedItem() throws Exception {
    ImportTaxedItem item = new ImportTaxedItem(null);
    assertThat(item.getRate(), comparesEqualTo(new BigDecimal("0.05")));
  }

}