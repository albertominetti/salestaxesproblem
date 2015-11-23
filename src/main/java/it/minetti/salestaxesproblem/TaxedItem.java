package it.minetti.salestaxesproblem;

public abstract class TaxedItem implements Item{
		     
		protected Item item;
		
		protected double rate;
		
		abstract double getRate();
		
		public TaxedItem(Item item){
			this.item = item;
		}
		
		private static double ROUND_FACTOR = 0.05;

		private static double roundToNearest5cents(double decimal) {
			return ROUND_FACTOR * (Math.ceil(decimal / ROUND_FACTOR));
		}
		
		public double getFinalPrice(){
			double itemFinalPrice = item.getFinalPrice();
			double salesTax = roundToNearest5cents(item.getShelfPrice() * this.getRate());	
			return salesTax + itemFinalPrice;
		}
		
	}