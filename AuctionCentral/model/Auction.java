package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author KyleD
 * @author CodyM
 */
public class Auction implements Comparable<Auction>{
	
	/** The ID that is initialized by AuctionID. */
	private final long myID;
	
	/** The date that auction is held. */
	private Calendar myDate;
	
	/** The list of items that are in the auction. */
	private List<Item> myItems;
	
	/** The boolean for checking if the auction is available. */
	private boolean isAvailable;
	
	/**
	 * Constructs a Auction object with the info receives from the parameters. 
	 * @param theItems the list of items in an auction
	 * @param theDate the day that auction is held
	 */
	public Auction(final List<Item> theItems, final Calendar theDate) {
		if (theItems.size() < 1) {
			throw new IllegalArgumentException();
		}
		if (theItems == null || theDate == null) {
			throw new NullPointerException();
		}
		// suppose that the difference in time between 2 created auction is AT LEAST 1ms
		myID = Calendar.getInstance().getTimeInMillis();
		myDate = theDate;
		myItems = new ArrayList<>();
		myItems.addAll(theItems);
		isAvailable = false;
	}
	
	/**
	 * Adds an item to an Auction if the item is not in the Auction.
	 * @param theItem
	 */
	public boolean addItem(final Item theItem) {
		if (theItem == null) {
			throw new NullPointerException();
		}
		if (!myItems.contains(theItem)) 
			return myItems.add(theItem);
		return false;
	}
	
	/**
	 * Removes an item from an Auction.
	 * @param theItem
	 */
	public void removeItem(final Item theItem) {
		if (theItem == null) {
			throw new NullPointerException();
		}
		myItems.remove(theItem);
	}
	
	/**
	 * Returns an item. 
	 * @param theItem
	 * @return the item.
	 */
	public Item getItem(final Item theItem) {
		if (theItem == null) {
			throw new NullPointerException();
		}
		return myItems.get(myItems.indexOf(theItem));
	}
	
	/**
	 * Returns a date and hour.
	 * @return a string about the date and hour
	 */
	public String printAuctionDay() {
		return "on " + myDate.get(Calendar.MONDAY)
				+ ", " + myDate.get(Calendar.DAY_OF_MONTH) 
				+ " " + myDate.get(Calendar.YEAR)
				+ " at " + myDate.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * Returns a list of all items in a auction.
	 * @return a list of items
	 */
	public List<Item> getAllItems() {
		return myItems;
	}
	
	/**
	 * Returns an ID of an Auction.
	 * @return
	 */
	public long getAuctionID() {
		return myID;
	}
	
	public Calendar getDateAuction() {
		return (Calendar) myDate.clone();
	}
	
	/**
	 * Returns the day of the month that the auction is held.
	 * @return
	 */
	public int getDayOfMonth() {
		return myDate.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * Returns the year that auction is held.
	 * @return
	 */
	public int getYear() {
		return myDate.get(Calendar.YEAR);
	}
	
	/**
	 * Returns the month that auction is held.
	 * @return
	 */
	public int getMonth() {
		return myDate.get(Calendar.MONTH);
	}
	
	/**
	 * Returns the time that the auction starts.
	 * @return
	 */
	public int getHour() {
		return myDate.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * Returns the time that the auction starts.
	 * @return
	 */
	public int getMin() {
		return myDate.get(Calendar.MINUTE);
	}
	
	/**
	 * Sets a date of a calendar include year, month, day, hour, min.
	 * @param theDate
	 */
	public void setAuctionDay(final Calendar theDate) {
		if (theDate == null) {
			throw new NullPointerException();
		}
		myDate = theDate;
	}
	
	/**
	 * Sets an auction available.
	 * @param theAvailable
	 */
	public void setAvailable(final boolean theAvailable) {
		isAvailable = theAvailable;
	}
	
	/**
	 * Checks if the auction is available.
	 * @return
	 */
	public boolean isAvailable() {
		return isAvailable;
	}

	@Override
	public int compareTo(Auction theAuction) {
		return this.myDate.compareTo(theAuction.myDate);
	}
	
	@Override
	public boolean equals(Object theAuction) {
		return ((Auction) theAuction).myDate.equals(this.myDate);
	}
}
