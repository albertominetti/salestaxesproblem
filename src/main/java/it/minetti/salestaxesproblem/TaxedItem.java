package it.minetti.salestaxesproblem;

import it.minetti.salestaxesproblem.domain.Item;
import it.minetti.salestaxesproblem.entity.Product;

public abstract class TaxedItem implements Item {

	private Item item;

		public abstract double getRate();
		
		public TaxedItem(Item item){
			this.item = item;
		}

		private static double roundToNearest5cents(double decimal) {
			double ROUND_FACTOR = 0.05;
			return ROUND_FACTOR * (Math.ceil(decimal / ROUND_FACTOR));
		}
		
		public double getFinalPrice(){
			double itemFinalPrice = item.getFinalPrice();
			double salesTax = roundToNearest5cents(item.getShelfPrice() * this.getRate());	
			return salesTax + itemFinalPrice;
		}


		protected Item getItem() {
			return item;
		}


	public Product.ProductType getType() {
		return item.getType();
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


}