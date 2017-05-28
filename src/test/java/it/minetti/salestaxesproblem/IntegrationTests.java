package it.minetti.salestaxesproblem;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import it.minetti.salestaxesproblem.domain.Item;
import it.minetti.salestaxesproblem.domain.Receipt;
import it.minetti.salestaxesproblem.domain.UntaxedItem;
import org.junit.Test;

import it.minetti.salestaxesproblem.entity.Product;
import it.minetti.salestaxesproblem.entity.Product.ProductType;
import it.minetti.salestaxesproblem.domain.Receipt.TaxesAlreadyApplied;
import it.minetti.salestaxesproblem.domain.Receipt.TaxesNotYetApplied;

public class IntegrationTests {

	@Test
	public void input1() throws TaxesAlreadyApplied, TaxesNotYetApplied {
		System.out.println("Test for input 1");
    Product theBook = new Product("book", ProductType.BOOK, false, 12.49);
    Product theCD = new Product("music CD", ProductType.OTHER, false, 14.99);
    Product theChocolate = new Product("chocolate bar", ProductType.FOOD, false, 0.85);
		
		Receipt r = new Receipt();

		r.addProduct(theBook);
		r.addProduct(theCD);
		r.addProduct(theChocolate);
		

		System.out.println(r.prepareInput());
		r.calculateTaxes();
		System.out.println(r.prepareOutput());
		
		Map<Item, Integer> taxedItems = r.getItems();

		Item theBookItem = new UntaxedItem(theBook);
		Item theCDItem = new UntaxedItem(theCD);
		Item theChocolateItem = new UntaxedItem(theChocolate);
		
		for (Item taxedItem : taxedItems.keySet()) {
			if (taxedItem.equals(theBookItem)) {
				assertEquals(taxedItem.getShelfPrice(), theBookItem.getShelfPrice(), 0.0);
				assertEquals(taxedItem.getFinalPrice(), theBookItem.getShelfPrice(), 0.0);
			}

			if (taxedItem.equals(theCDItem)) {
				assertEquals(taxedItem.getShelfPrice(), theCDItem.getShelfPrice(), 0.0);
				assertEquals(taxedItem.getFinalPrice(), 16.49, 0.0);
			}
			
			if (taxedItem.equals(theChocolateItem)) {
				assertEquals(taxedItem.getShelfPrice(), theChocolateItem.getShelfPrice(), 0.0);
				assertEquals(taxedItem.getFinalPrice(), 0.85, 0.0);
			}
		}

	}

	@Test
	public void input2() throws TaxesAlreadyApplied, TaxesNotYetApplied {
		System.out.println("Test for input 2");
    Product theChocolate = new Product("imported box of chocolate", ProductType.FOOD, true, 10.0);
    Product thePerfume = new Product("imported bottle of perfume", ProductType.OTHER, true, 47.50);
		
		Receipt r = new Receipt();

		r.addProduct(theChocolate);
		r.addProduct(thePerfume);
		

		System.out.println(r.prepareInput());
		r.calculateTaxes();
		System.out.println(r.prepareOutput());
		
		
		Map<Item, Integer> taxedItems = r.getItems();

		Item theChocolateItem = new UntaxedItem(theChocolate);
		Item thePerfumeItem = new UntaxedItem(thePerfume);
		
		for (Item taxedItem : taxedItems.keySet()) {

			if (taxedItem.equals(theChocolateItem)) {
				assertEquals(taxedItem.getShelfPrice(), theChocolateItem.getShelfPrice(), 0.0);
				assertEquals(taxedItem.getFinalPrice(), 10.50, 0.0);
			}
			

			if (taxedItem.equals(thePerfumeItem)) {
				assertEquals(taxedItem.getShelfPrice(), thePerfumeItem.getShelfPrice(), 0.0);
				assertEquals(taxedItem.getFinalPrice(), 54.64, 0.0);
			}
			
		}
		
	}
	
	@Test
	public void input3() throws TaxesAlreadyApplied, TaxesNotYetApplied {
		System.out.println("Test for input 3");
    Product theImportedPerfume = new Product("imported bottle of perfume", ProductType.OTHER, true, 27.99);
    Product thePerfume = new Product("bottle of perfume", ProductType.OTHER, false, 18.99);
    Product thePills = new Product("packet of headache pills", ProductType.MEDICAL, false, 9.75);
    Product theChocolate = new Product("box of imported chocolate", ProductType.FOOD, true, 11.25);
		
		Receipt r = new Receipt();

		r.addProduct(theImportedPerfume);
		r.addProduct(thePerfume);
		r.addProduct(thePills);
		r.addProduct(theChocolate);
		

		System.out.println(r.prepareInput());
		r.calculateTaxes();
		System.out.println(r.prepareOutput());
		

		Map<Item, Integer> taxedItems = r.getItems();

		Item theImportedPerfumeItem = new UntaxedItem(theImportedPerfume);
		Item thePerfumeItem = new UntaxedItem(thePerfume);
		Item thePillsItem = new UntaxedItem(thePills);
		Item theChocolateItem = new UntaxedItem(theChocolate);
		
		
		for (Item taxedItem : taxedItems.keySet()) {
			if (taxedItem.equals(theImportedPerfumeItem)) {
				assertEquals(taxedItem.getShelfPrice(), theImportedPerfumeItem.getShelfPrice(), 0.0);
				assertEquals(taxedItem.getFinalPrice(), 32.19, 0.0);
			}

			if (taxedItem.equals(thePerfumeItem)) {
				assertEquals(taxedItem.getShelfPrice(), thePerfumeItem.getShelfPrice(), 0.0);
				assertEquals(taxedItem.getFinalPrice(), 20.89, 0.0);
			}

			if (taxedItem.equals(thePillsItem)) {
				assertEquals(taxedItem.getShelfPrice(), thePillsItem.getShelfPrice(), 0.0);
				assertEquals(taxedItem.getFinalPrice(), 9.75, 0.0);
			}
			
			if (taxedItem.equals(theChocolateItem)) {
				assertEquals(taxedItem.getShelfPrice(), theChocolateItem.getShelfPrice(), 0.0);
				assertEquals(taxedItem.getFinalPrice(), 11.85, 0.0);
			}
		}
		
		
	}

}
