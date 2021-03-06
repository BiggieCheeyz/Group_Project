package beans;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author francis okoyo
 * 
 * Represents a shopping cart. Stores collection of all the food items the client has
 * added to the shopping cart. The collection is a hash map. the key is the item's product
 * number / ID. the value is and item bean representing an item with that product number.
 * 
 * the second attribute is a string representing the sum of all the prices of the items in this
 * cart.
 * 
 * 
 * 
 * NOTE:
 * 
 * This bean automatically calculates it's shipping cost, tax and total price. notice that the developer
 * cannot set these attributes as the mutator methods are private. This makes life easier.
 *
 */
public class ShoppingCart
{
	HashMap<String,Item> items;
	
	String subTotal;
	String tax;
	String shipping;
	String total;
	String numItems;
	
	public ShoppingCart() {
		/**/
		this.items = new HashMap<String,Item>();
		this.subTotal = "0.00";
		this.tax = "0.00";
		this.shipping = "0.00";
		this.total = "0.00";
		this.numItems = "0";
	}

	public ShoppingCart(HashMap<String,Item> items, String subTotal)
	{
		super();
		this.items = items;
		this.subTotal = subTotal;
		setNumItems();
	}

	public HashMap<String, Item> getItems()
	{
		return items;
	}
	
	/**
	 * sums the total price of each Item object in the list and calculates the tax,
	 * shipping and total cost automatically.
	 * 
	 * @param items
	 */
	public void setItems(HashMap<String, Item> items)
	{
		this.items = items;
		
		double tot = 0.00;
		for(Item item: items.values()) {
			
			// sum up the total prices of all the items.
			tot += Double.parseDouble(item.getTotalPrice().replace("$", ""));
			
		}
		
		setSubTotal(String.format("%.2f", tot));
		setTax(tot + "");
		setShipping(tot + "");
		setTotal();
		setNumItems();
	}

	public String getSubTotal()
	{
		return subTotal;
	}

	private void setSubTotal(String subTotal)
	{
		this.subTotal = subTotal;
	}
	
	public String getTax()
	{
		return tax;
	}
	
	/**
	 * sets the tax depending on the sub total
	 * 
	 * @param subTotal
	 */
	private void setTax(String subTotal)
	{
		double hst = Double.parseDouble(subTotal) * 0.13;
		this.tax = String.format("%.2f", hst);
	}

	public String getShipping()
	{
		return shipping;
	}
	/**
	 * calculates shipping cost depending on the subtotal
	 * 
	 * @param subTotal
	 */
	private void setShipping(String subTotal)
	{
		double sTot = Double.parseDouble(subTotal);
		
		if(sTot <= 0.00 || sTot >= 100.0) {
			this.shipping = "0.00";
		}
		else {
			this.shipping = "5.00";
		}
		
	}

	public String getTotal()
	{
		return total;
	}

	private void setTotal()
	{
		double a = Double.parseDouble(getSubTotal());
		double b = Double.parseDouble(getShipping());
		double c = Double.parseDouble(getTax());
		
		double d = a + b + c;
		
		
		this.total = String.format("%.2f", d);
	}
	
	public String getNumItems() {
		return numItems;
	}
	
	public void setNumItems() {
		int totalNumItems = 0;
		for (Item i : this.items.values()) {
			totalNumItems += Integer.parseInt(i.getQty());
		}
		this.numItems = String.valueOf(totalNumItems);
	}
	
	/**
	 * returns the list of items in this shopping cart as an instance of and "Items"
	 * object. see "Items" class for more details.
	 * 
	 * @return
	 */
	public Items getItemsObject(){
		
		ArrayList<Item> l = new ArrayList<>();
		
		for(String s : this.items.keySet()) {
			l.add(this.items.get(s));
		}
		
		Items i = new Items();
		i.setItm(l);
		
		return i;
	}
	
	
}
