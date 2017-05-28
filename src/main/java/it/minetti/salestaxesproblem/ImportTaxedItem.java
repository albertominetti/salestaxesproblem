package it.minetti.salestaxesproblem;

import it.minetti.salestaxesproblem.Product.ProductType;

public class ImportTaxedItem extends TaxedItem {

	public ImportTaxedItem(Item item){
		super(item);
	}
	
	@Override
	double getRate() {
		return 0.05;
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
