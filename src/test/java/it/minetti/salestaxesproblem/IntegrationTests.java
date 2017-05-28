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

public class IntegrationTests {

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

    Map<TaxedItem, Integer> taxedItems = bill.getItems();

    Item theBookItem = new UntaxedItem(theBook);
    Item theCDItem = new UntaxedItem(theCD);
    Item theChocolateItem = new UntaxedItem(theChocolate);

//    for (Item taxedItem : taxedItems.keySet()) {
//      if (taxedItem.equals(theBookItem)) {
//        assertEquals(taxedItem.getShelfPrice(), theBookItem.getShelfPrice(), 0.0);
//        assertEquals(taxedItem.getFinalPrice(), theBookItem.getShelfPrice(), 0.0);
//      }
//
//      if (taxedItem.equals(theCDItem)) {
//        assertEquals(taxedItem.getShelfPrice(), theCDItem.getShelfPrice(), 0.0);
//        assertEquals(taxedItem.getFinalPrice(), 16.49, 0.0);
//      }
//
//      if (taxedItem.equals(theChocolateItem)) {
//        assertEquals(taxedItem.getShelfPrice(), theChocolateItem.getShelfPrice(), 0.0);
//        assertEquals(taxedItem.getFinalPrice(), 0.85, 0.0);
//      }
//    }

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


    Map<TaxedItem, Integer> taxedItems = bill.getItems();

    Item theChocolateItem = new UntaxedItem(theChocolate);
    Item thePerfumeItem = new UntaxedItem(thePerfume);

//    for (Item taxedItem : taxedItems.keySet()) {
//
//      if (taxedItem.equals(theChocolateItem)) {
//        assertEquals(taxedItem.getShelfPrice(), theChocolateItem.getShelfPrice(), 0.0);
//        assertEquals(taxedItem.getFinalPrice(), 10.50, 0.0);
//      }
//
//
//      if (taxedItem.equals(thePerfumeItem)) {
//        assertEquals(taxedItem.getShelfPrice(), thePerfumeItem.getShelfPrice(), 0.0);
//        assertEquals(taxedItem.getFinalPrice(), 54.64, 0.0);
//      }
//
//    }

    assertThat(bill.getTotalAmount(), comparesEqualTo(new BigDecimal("60.40")));
    assertThat(bill.getTotalTaxes(), comparesEqualTo(new BigDecimal("2.90")));

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


    Map<TaxedItem, Integer> taxedItems = bill.getItems();

    Item theImportedPerfumeItem = new UntaxedItem(theImportedPerfume);
    Item thePerfumeItem = new UntaxedItem(thePerfume);
    Item thePillsItem = new UntaxedItem(thePills);
    Item theChocolateItem = new UntaxedItem(theChocolate);


//    for (Item taxedItem : taxedItems.keySet()) {
//      if (taxedItem.equals(theImportedPerfumeItem)) {
//        assertEquals(taxedItem.getShelfPrice(), theImportedPerfumeItem.getShelfPrice(), 0.0);
//        assertEquals(taxedItem.getFinalPrice(), 32.19, 0.0);
//      }
//
//      if (taxedItem.equals(thePerfumeItem)) {
//        assertEquals(taxedItem.getShelfPrice(), thePerfumeItem.getShelfPrice(), 0.0);
//        assertEquals(taxedItem.getFinalPrice(), 20.89, 0.0);
//      }
//
//      if (taxedItem.equals(thePillsItem)) {
//        assertEquals(taxedItem.getShelfPrice(), thePillsItem.getShelfPrice(), 0.0);
//        assertEquals(taxedItem.getFinalPrice(), 9.75, 0.0);
//      }
//
//      if (taxedItem.equals(theChocolateItem)) {
//        assertEquals(taxedItem.getShelfPrice(), theChocolateItem.getShelfPrice(), 0.0);
//        assertEquals(taxedItem.getFinalPrice(), 11.85, 0.0);
//      }
//    }

    assertThat(bill.getTotalAmount(), comparesEqualTo(new BigDecimal("71.88")));
    assertThat(bill.getTotalTaxes(), comparesEqualTo(new BigDecimal("3.90")));

  }

}
