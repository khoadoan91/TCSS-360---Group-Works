/*
 * IMPORTANT NOTE:
 * 
 * In this class, there is a uncomfortable bug in the Calendar type. The month starts with an index 0.
 * 		0  -->   Jan
 * 		1  -->	 Feb
 * 		2  -->   Mar
 * 		3  -->	 Apr
 * 		4  -->   May
 * 		5  -->	 Jun
 * 		6  -->   Jul
 * 		7  -->	 Aug
 * 		8  -->   Sep
 * 		9 -->	 Oct
 * 		10 -->   Nov
 * 		11 -->	 Dec
 * 	If the Calendar takes 12 as a value of the month, 
 *  it means that the month is January and the year will increment by 1.
 *  Same thing applies for negative number.
 *  
 *  This class will try to handle this bug by subtract the month by 1. 
 *  (Year will subtract by 1 if the month is set as 12)
 *  Therefore, whenever other classes get or set Calendar data, please remember this note.
 *  
 *  This note will not matter ONLY WHEN IT IS CONSTRUCTED.
 */

package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author KyleD
 */
public class Auction implements Comparable<Auction> {

	/** The ID of an Auction that is combination of orgName and time. */
	private final String myAucName;
	/** The non-profit organization */
	private String myOrgName;

	/** The date that auction is held. */
	private Calendar myDate;

	/** The list of items that are in the auction. */
	private List<Item> myItems;

	/** The boolean for checking if the auction is available. */
	private boolean isAvailable;

	private int hourDur;

	private int minDur;

	/**
	 * Constructs a Auction object with the info receives from the parameters.
	 * 
	 * @param theOrgName
	 * @param theItems
	 * @param theDate fixed a bug month starts at 0
	 * @param timeDuration
	 *            MUST BE in the format "HH:MM"
	 */
	public Auction(final String theOrgName, final List<Item> theItems, final Calendar theDate,
			final String timeDuration) {
		if (theItems.size() < 1) {
			throw new IllegalArgumentException();
		}
		if (theItems == null || theDate == null) {
			throw new NullPointerException();
		}
		myDate = (Calendar) theDate.clone();
		if (theDate.get(Calendar.MONTH) == 12) {
			myDate.set(Calendar.YEAR, myDate.get(Calendar.YEAR) - 1);
		}
		myDate.set(Calendar.MONTH, myDate.get(Calendar.MONTH) - 1);
		myOrgName = theOrgName;
		myAucName = theOrgName + "-" + (new SimpleDateFormat("MMM").format(myDate.getTime())) 
				+ "-" + theDate.get(Calendar.DAY_OF_MONTH) + "-" + theDate.get(Calendar.YEAR);
		setTimeDuration(timeDuration);
		myItems = new ArrayList<>();
		myItems.addAll(theItems);
		isAvailable = false;
	}

	/**
	 * Adds an item to an Auction if the item is not in the Auction.
	 * 
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
	 * 
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
	 * 
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
	 * 
	 * @return a string about the date and hour
	 */
	public String printAuctionDay() {
		return "on " + myDate.get(Calendar.MONDAY) + ", " + myDate.get(Calendar.DAY_OF_MONTH) + " "
				+ myDate.get(Calendar.YEAR) + " at " + myDate.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * Returns a list of all items in a auction.
	 * 
	 * @return a list of items
	 */
	public List<Item> getAllItems() {
		return myItems;
	}

	/**
	 * Returns an organization name of the auction.
	 * 
	 * @return
	 */
	public String getOrganizationNam() {
		return myOrgName;
	}

	/**
	 * Returns an ID of an Auction.
	 * 
	 * @return
	 */
	public String getAuctionName() {
		return myAucName;
	}

	public Calendar getDateAuctionStarts() {
		return (Calendar) myDate.clone();
	}

	public Calendar getDateAuctionEnds() {
		Calendar result = (Calendar) myDate.clone();
		result.set(Calendar.HOUR, result.get(Calendar.HOUR) + hourDur);
		result.set(Calendar.MINUTE, result.get(Calendar.MINUTE) + minDur);
		return result;
	}

	/**
	 * Returns the day of the month that the auction is held.
	 * 
	 * @return
	 */
	public int getDayOfMonth() {
		return myDate.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Returns the year that auction is held.
	 * 
	 * @return
	 */
	public int getYear() {
		return myDate.get(Calendar.YEAR);
	}

	/**
	 * Returns the month that auction is held.
	 * 
	 * @return
	 */
	public int getMonth() {
		return myDate.get(Calendar.MONTH);
	}

	/**
	 * Returns the time that the auction starts.
	 * 
	 * @return
	 */
	public int getHour() {
		return myDate.get(Calendar.HOUR_OF_DAY);	
	}

	/**
	 * Returns the time that the auction starts.
	 * 
	 * @return
	 */
	public int getMin() {
		return myDate.get(Calendar.MINUTE);
	}
	
	public void setStartingTime(final String time) {
		String start[] = time.split(":");
		// TODO Check condition to throw exception.
		myDate.set(Calendar.HOUR, Integer.parseInt(start[0]));
		myDate.set(Calendar.MINUTE, Integer.parseInt(start[1]));
	}

	public void setTimeDuration(final String timeDur) {
		String time[] = timeDur.split(":");
		int tempHour = Integer.parseInt(time[0]);
		int tempMin = Integer.parseInt(time[1]);
		if (tempHour < 0 || tempMin < 0 || (tempHour == 0 && tempMin == 0)) {
			throw new IllegalArgumentException();
		}
		hourDur = tempHour;
		minDur = tempMin;
	}

	/**
	 * Sets a date of a calendar include year, month, day, hour, min.
	 * 
	 * @param theDate
	 */
	public void setAuctionDay(final Calendar theDate) {
		if (theDate == null) {
			throw new NullPointerException();
		}
		myDate = (Calendar) theDate.clone();
	}
	
	public void setDate(final int date) {
		myDate.set(Calendar.DAY_OF_MONTH, date);
	}
	
	public void setMonth(final int month) {
		myDate.set(Calendar.MONTH, month);
	}
	
	public void setYear(final int year) {
		myDate.set(Calendar.YEAR, year);
	}

	/**
	 * Sets an auction available.
	 * 
	 * @param theAvailable
	 */
	public void setAvailable(final boolean theAvailable) {
		isAvailable = theAvailable;
	}

	/**
	 * Checks if the auction is available.
	 * 
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

	public static void main(String[] args) {
		List<Item> list = new ArrayList<>();
		Item item1 = new Item("iPad", 1, "New");
		Item item2 = new Item("Macbook", 1, "Used");
		list.add(item1);
		list.add(item2);
		Calendar cal = Calendar.getInstance();
		cal.set(2015, 8, 25, 8, 30);
		System.out.println(cal.get(Calendar.MONTH));
		Auction auc = new Auction("GoodWill", list, cal, "2:10");
		System.out.println(auc.getAuctionName());
		System.out.println(auc.getYear());
		System.out.println(auc.getMonth());
		System.out.println(auc.getDayOfMonth());
		System.out.println(auc.getHour());
		System.out.println(auc.getMin());
//		System.out.println(Calendar.getInstance().get(Calendar.MONTH));
		cal.set(Calendar.DAY_OF_MONTH, 1);
//		System.out.println(new SimpleDateFormat("MMMMMMMMM").format(cal.get(Calendar.MONTH)).toUpperCase());
//		System.out.println(cal.get(Calendar.DAY_OF_WEEK_IN_MONTH));
//		System.out.println(cal.get(Calendar.DAY_OF_WEEK));
		System.out.println(cal.getTime());
	}
}
