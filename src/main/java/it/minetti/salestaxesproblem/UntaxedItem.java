package it.minetti.salestaxesproblem;

import it.minetti.salestaxesproblem.Product.ProductType;

public class UntaxedItem implements Item {

	public void setDescription(String description) {
		this.description = description;
	}

	public void setImported(boolean isImported) {
		this.isImported = isImported;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	private String description;
	private boolean isImported;
	private double shelfPrice;
	private ProductType type;

	public UntaxedItem(String description, ProductType type, boolean isImported, double shelfPrice) {
		this.description = description;
		this.shelfPrice = shelfPrice;
		this.isImported = isImported;
		this.type = type;
	}
	
	public UntaxedItem(Product p) {
		this.description = p.getDescription();
		this.shelfPrice = p.getShielfPrice();
		this.isImported = p.isImported();
		this.type = p.getType();
	} 

	public String getDescription() {
		return description;
	}

	public boolean isImported() {
		return isImported;
	}

	public Item filter() {
		return this;
	}

	public double getShelfPrice() {
		return this.shelfPrice;
	}

	public double getFinalPrice() {
		return shelfPrice;
	}

	public ProductType getType() {
		return type;
	}
	
}