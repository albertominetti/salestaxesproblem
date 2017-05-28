package it.minetti.salestaxesproblem;

import it.minetti.salestaxesproblem.domain.Bill;
import it.minetti.salestaxesproblem.domain.Receipt;
import it.minetti.salestaxesproblem.domain.items.Item;
import it.minetti.salestaxesproblem.domain.items.TaxedItem;
import it.minetti.salestaxesproblem.domain.items.UntaxedItem;
import it.minetti.salestaxesproblem.entities.Product;
import it.minetti.salestaxesproblem.entities.Product.ProductType;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.Assert.assertThat;

public class AcceptanceIT {

  private final Printer printer = new Printer();

  @Test
  public void input1() {
    System.out.println("Test for input 1");
    Product theBook = new Product("book", ProductType.BOOK, false, new BigDecimal("12.49"));
    Product theCD = new Product("music CD", ProductType.OTHER, false, new BigDecimal("14.99"));
    Product theChocolate = new Product("chocolate bar", ProductType.FOOD, false, new BigDecimal("0.85"));

    Receipt r = new Receipt();

    r.addProduct(theBook);
    r.addProduct(theCD);
    r.addProduct(theChocolate);


    System.out.println(printer.print(r));
    Bill bill = new Bill(r);
    System.out.println(printer.print(bill));

    assertThat(bill.getTotalAmount(), comparesEqualTo(new BigDecimal("29.83")));
    assertThat(bill.getTotalTaxes(), comparesEqualTo(new BigDecimal("1.50")));

  }

  @Test
  public void input2() {
    System.out.println("Test for input 2");
    Product theChocolate = new Product("imported box of chocolate", ProductType.FOOD, true, new BigDecimal("10.0"));
    Product thePerfume = new Product("imported bottle of perfume", ProductType.OTHER, true, new BigDecimal("47.50"));

    Receipt r = new Receipt();

    r.addProduct(theChocolate);
    r.addProduct(thePerfume);

    System.out.println(printer.print(r));
    Bill bill = new Bill(r);
    System.out.println(printer.print(bill));

    assertThat(bill.getTotalAmount(), comparesEqualTo(new BigDecimal("65.15")));
    assertThat(bill.getTotalTaxes(), comparesEqualTo(new BigDecimal("7.65")));

  }

  @Test
  public void input3() {
    System.out.println("Test for input 3");
    Product theImportedPerfume = new Product("imported bottle of perfume", ProductType.OTHER, true, new BigDecimal("27.99"));
    Product thePerfume = new Product("bottle of perfume", ProductType.OTHER, false, new BigDecimal("18.99"));
    Product thePills = new Product("packet of headache pills", ProductType.MEDICAL, false, new BigDecimal("9.75"));
    Product theChocolate = new Product("box of imported chocolate", ProductType.FOOD, true, new BigDecimal("11.25"));

    Receipt r = new Receipt();

    r.addProduct(theImportedPerfume);
    r.addProduct(thePerfume);
    r.addProduct(thePills);
    r.addProduct(theChocolate);


    System.out.println(printer.print(r));
    Bill bill = new Bill(r);
    System.out.println(printer.print(bill));

    assertThat(bill.getTotalAmount(), comparesEqualTo(new BigDecimal("74.68")));
    assertThat(bill.getTotalTaxes(), comparesEqualTo(new BigDecimal("6.70")));

  }

}
