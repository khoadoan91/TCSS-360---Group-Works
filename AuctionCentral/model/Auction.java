package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author KyleD
 *
 */
public class Auction {
	
	private String myDate;
	private int myHour;
	private List<Item> myItems;
	private boolean isAvailable;
	
	public Auction(final List<Item> theItems, final String theDate, final int theHour) {
		myDate = theDate;
		myHour = theHour;
		myItems = new ArrayList<>();
		myItems.addAll(theItems);
		isAvailable = false;
	}
	
	public void addItem(final Item theItem) {
		if (!myItems.contains(theItem)) 
			myItems.add(theItem);
	}
	
	public void removeItem(final Item theItem) {
		myItems.remove(theItem);
	}
	
	public Item getItem(final Item theItem) {
		return myItems.get(myItems.indexOf(theItem));
	}
	
	public String getAuctionDay() {
		return myDate;
	}
	
	public List<Item> getAllItems() {
		return myItems;
	}
	
	public void setAuctionDay(final String theDate) {
		myDate = theDate;
	}
	
	public void setAvailable(final boolean theAvailable) {
		isAvailable = theAvailable;
	}
	
	public boolean isAvailable() {
		return isAvailable;
	}
}
