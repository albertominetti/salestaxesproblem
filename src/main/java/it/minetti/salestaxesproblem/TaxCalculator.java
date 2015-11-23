package it.minetti.salestaxesproblem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import it.minetti.salestaxesproblem.Product.ProductType;


public class TaxCalculator {

	public static Map<Item, Integer> calculateTaxes(Map<Item, Integer> items){
		Map<Item, Integer> taxedItems = new LinkedHashMap<Item, Integer>(items.size());
		
		for (Entry<Item, Integer> e : items.entrySet()) {
			Item item = e.getKey();
			int quantity = e.getValue();
			
			Item taxedItem = calculateTaxes(item);
			
			taxedItems.put(taxedItem, quantity);
		}
		return taxedItems;
	}
	
	private static List<ProductType> exemptionByType = new ArrayList<ProductType>() {
		private static final long serialVersionUID = 7482555310601248768L;

		{
			add(ProductType.BOOK);
			add(ProductType.FOOD);
			add(ProductType.MEDICAL);
		}
	};

	
	public static Item calculateTaxes(Item item){
		
		if (!exemptionByType.contains(item.getType())) {
			item = new BasicTaxedItem(item);
		}
		
		if (item.isImported()) {
			item = new ImportTaxedItem(item);
		}
		
		return item;
	}
}
