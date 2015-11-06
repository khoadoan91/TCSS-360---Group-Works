package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author KyleD
 *
 */
public class Auction {
	
	private Date myDate;
	private int myHour;
	private List<Item> myItems;
	private boolean isAvailable;
	
	public Auction() {
		this(null, null, -1);
	}
	
	public Auction(final List<Item> theItems, final Date theDate, final int theHour) {
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
		return "on " + myDate + " at " + myHour;
	}
	
	public List<Item> getAllItems() {
		return myItems;
	}
	
	public void setAuctionDay(final Date theDate, final int theHour) {
		myDate = theDate;
		myHour = theHour;
	}
	
	public void setAvailable(final boolean theAvailable) {
		isAvailable = theAvailable;
	}
	
	public boolean isAvailable() {
		return isAvailable;
	}
	
	public void printAuctionDetail() {
		System.out.println();
	}
}
