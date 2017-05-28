package it.minetti.salestaxesproblem.domain.items;

import org.junit.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static org.hamcrest.CoreMatchers.is;
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
    assertThat(TaxedItem.roundToNearest5cents(new BigDecimal("0.14999")), comparesEqualTo(fifteenCents));
  }

  @Test
  public void finalPrice() throws Exception {
    StubTaxedItem item = new StubTaxedItem(new StubItem());
    assertThat(item.getShelfPrice(), comparesEqualTo(new BigDecimal("12345.88")));
    assertThat(item.getFinalPrice(), comparesEqualTo(new BigDecimal("18642.28")));
    assertThat(item.getDescription(), is("descr"));
  }

  private class StubTaxedItem extends TaxedItem {

    public StubTaxedItem(Item item) {
      super(item);
    }

    @Override
    public BigDecimal getRate() {
      return new BigDecimal("0.51");
    }
  }

  private class StubItem implements Item {
    @Override
    public String getDescription() {
      return "descr";
    }

    @Override
    public BigDecimal getFinalPrice() {
      return getShelfPrice();
    }

    @Override
    public BigDecimal getShelfPrice() {
      return new BigDecimal("12345.88");
    }
  }
}