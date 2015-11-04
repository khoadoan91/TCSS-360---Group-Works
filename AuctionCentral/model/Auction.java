package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KyleD
 *
 */
public class Auction {
	
	private List<Item> myItem;
	private boolean isAvailable;
	
	public Auction() {
		myItem = new ArrayList<>();
		isAvailable = false;
	}
	
	public void addItem(final Item theItem) {
		myItem.add(theItem);
	}
	
	public void removeItem(final Item theItem) {
		
	}
	
	public boolean isAvailable() {
		return isAvailable;
	}
}
