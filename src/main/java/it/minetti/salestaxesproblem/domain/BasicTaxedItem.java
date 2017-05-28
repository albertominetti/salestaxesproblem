package it.minetti.salestaxesproblem.domain;

import it.minetti.salestaxesproblem.TaxedItem;

public class BasicTaxedItem extends TaxedItem {

	public BasicTaxedItem(Item item) {
		super(item);
	}

	@Override
	public double getRate() {
		return 0.10;
	}

}
