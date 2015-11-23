package it.minetti.salestaxesproblem;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.text.DecimalFormat;

public class Receipt {
	private boolean taxesApplied = false;
	private Map<Item, Integer> items;

	public Receipt() {
		items = new LinkedHashMap<Item, Integer>();
	}

	public int addProduct(Product p) throws TaxesAlreadyApplied {
		if (taxesApplied)
			throw new TaxesAlreadyApplied();
		Item i = new UntaxedItem(p);
		int quantity = 0;

		if (items.containsKey(i)) {
			quantity = items.get(p);
		}

		items.put(i, quantity + 1);
		return quantity + 1;
	}

	public int addProducts(Product p, int amount) throws TaxesAlreadyApplied {
		if (taxesApplied)
			throw new TaxesAlreadyApplied();
		Item i = new UntaxedItem(p);
		int quantity = 0;

		if (items.containsKey(i)) {
			quantity = items.get(p);
		}

		items.put(i, quantity + amount);
		return quantity + amount;
	}

	private static String NL = System.getProperty("line.separator");
	private DecimalFormat df = new DecimalFormat("0.00");

	public StringBuffer prepareInput() {
		StringBuffer sb = new StringBuffer(items.size() * 30);
		sb.append("Receipt input: " + NL);
		for (Entry<Item, Integer> e : items.entrySet()) {
			Item item = e.getKey();
			int quantity = e.getValue();
			sb.append("" + quantity + (item.isImported() ? " imported " : " ") + item.getDescription() + " at "
					+ df.format(item.getShelfPrice()) + NL);
		}
		return sb;
	}
	
	public void calculateTaxes() {
		items = TaxCalculator.calculateTaxes(items);
		taxesApplied = true;
	}
	
	public StringBuffer prepareOutput() throws TaxesNotYetApplied {
		if (!taxesApplied) throw new TaxesNotYetApplied();
		StringBuffer sb = new StringBuffer(items.size() * 30 + 100);
		double totalTaxes = 0.0, totalAmount = 0.0;
		sb.append("Receipt output: " + NL);
		for (Entry<Item, Integer> e : items.entrySet()) {
			Item item = e.getKey();
			int quantity = e.getValue();
			
			double subTotalTaxes = (item.getFinalPrice() - item.getShelfPrice()) * quantity;
			totalTaxes += subTotalTaxes;
			
			double subTotalAmount = item.getFinalPrice() * quantity;
			totalAmount += subTotalAmount;
			
			sb.append("" + quantity + " " + item.getDescription() + ": "
					+ df.format(item.getFinalPrice()) + NL);
		}

		sb.append("Sales Taxes: " + df.format(totalTaxes) + NL);
		sb.append("Total: " + df.format(totalAmount) + NL);
		
		return sb;
	}

	public class TaxesAlreadyApplied extends Exception {
		private static final long serialVersionUID = -8918186433409537096L;
	}
	
	public class TaxesNotYetApplied extends Exception {
		private static final long serialVersionUID = -8918186433409537096L;
	}

	public Map<Item, Integer> getItems(){
		return new LinkedHashMap<Item, Integer>(items); //the keys and values themselves are not cloned
	}
	
}
