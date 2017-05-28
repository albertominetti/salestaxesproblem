package it.minetti.salestaxesproblem;

import it.minetti.salestaxesproblem.Product.ProductType;

public class BasicTaxedItem extends TaxedItem {

	public BasicTaxedItem(Item item) {
		super(item);
	}

	@Override
	double getRate() {
		return 0.10;
	}

	public boolean isImported() {
		return getItem().isImported();
	}

	public String getDescription() {
		return getItem().getDescription();
	}

	public double getShelfPrice() {
		return getItem().getShelfPrice();
	}

	public ProductType getType() {
		return getItem().getType();
	}

}
