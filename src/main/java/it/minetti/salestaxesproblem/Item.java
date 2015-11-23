package it.minetti.salestaxesproblem;

import it.minetti.salestaxesproblem.Product.ProductType;

public interface Item {
	String getDescription();

	double getFinalPrice();

	double getShelfPrice();

	boolean isImported();

	ProductType getType();
}
