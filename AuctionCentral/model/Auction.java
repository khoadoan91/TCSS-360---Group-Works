package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author KyleD
 *
 */
public class Auction {
	
	private Date myDate;
	private List<Item> myItems;
	private boolean isAvailable;
	
	public Auction() {
		myDate = null;
		myItems = new ArrayList<>();
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
	
	public Date getAuctionDay() {
		return myDate;
	}
	
	public List<Item> getAllItems() {
		return myItems;
	}
	
	public void setAuctionDay(final Date theDate) {
		myDate = theDate;
	}
	
	public void setAvailable(final boolean theAvailable) {
		isAvailable = theAvailable;
	}
	
	public boolean isAvailable() {
		return isAvailable;
	}
}
