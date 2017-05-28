package it.minetti.salestaxesproblem.domain.items;

import org.junit.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.Assert.assertThat;

public class TaxedItemTest {
  private final BigDecimal fiveCents = new BigDecimal("0.05");
  private final BigDecimal tenCents = new BigDecimal("0.10");
  private final BigDecimal fifteenCents = new BigDecimal("0.15");

  @Test
  public void roundToNearest5cents() throws Exception {
    assertThat(TaxedItem.roundToNearest5cents(new BigDecimal("0")), comparesEqualTo(ZERO));
    assertThat(TaxedItem.roundToNearest5cents(new BigDecimal("0.02")), comparesEqualTo(fiveCents));
    assertThat(TaxedItem.roundToNearest5cents(new BigDecimal("0.03")), comparesEqualTo(fiveCents));
    assertThat(TaxedItem.roundToNearest5cents(new BigDecimal("0.05")), comparesEqualTo(fiveCents));
    assertThat(TaxedItem.roundToNearest5cents(new BigDecimal("0.0500000000001")), comparesEqualTo(tenCents));
    assertThat(TaxedItem.roundToNearest5cents(new BigDecimal("0.06")), comparesEqualTo(tenCents));
    assertThat(TaxedItem.roundToNearest5cents(new BigDecimal("0.075")), comparesEqualTo(tenCents));
  }

  ;
}