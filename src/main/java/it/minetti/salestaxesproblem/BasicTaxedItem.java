package it.minetti.salestaxesproblem;

import it.minetti.salestaxesproblem.Product.ProductType;

public class BasicTaxedItem extends TaxedItem {

	Item innerItem;

	final double rate = 0.10;

	public BasicTaxedItem(Item item) {
		super(item);
		this.innerItem = item;
	}

	@Override
	double getRate() {
		return this.rate;
	}

	public boolean isImported() {
		return innerItem.isImported();
	}

	public String getDescription() {
		return innerItem.getDescription();
	}

	public double getShelfPrice() {
		return innerItem.getShelfPrice();
	}

	public ProductType getType() {
		return innerItem.getType();
	}

}
