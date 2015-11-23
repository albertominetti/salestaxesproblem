package it.minetti.salestaxesproblem;

public class Product {
	public enum ProductType {
		BOOK, FOOD, MEDICAL, OTHER
	};

	private String description;
	private ProductType type = ProductType.OTHER;
	public String getDescription() {
		return description;
	}

	public ProductType getType() {
		return type;
	}

	public boolean isImported() {
		return isImported;
	}

	public double getShielfPrice() {
		return shielfPrice;
	}

	private boolean isImported = false;
	private double shielfPrice;

	public static Product create(String description, ProductType type, boolean isImported, double shielfPrice) {
		return new Product(description, type, isImported, shielfPrice);
	}

	private Product(String description, ProductType type, boolean isImported, double shielfPrice) {
		this.description = description;
		this.type = type;
		this.isImported = isImported;
		this.shielfPrice = shielfPrice;
	}
}
