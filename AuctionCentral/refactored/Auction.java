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

package refactored;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


/**
 * @author KyleD
 */
public class Auction implements Comparable<Auction>, java.io.Serializable, Cloneable {

	/** The ID of an Auction that is combination of orgName and time. */
	private String myAucName;
	
	/** The name of non-profit organization */
	private String myOrgName;

	/** The date that auction is held. */
	private Calendar myDate;

	/** The list of items that are in the auction. */
	private List<Item> myItems;

	/** The hour that represents how long for the Auction happens. */
	private int hourDur;

	/** The minute that represents how long for the Auction happens. */
	private int minDur;

	/**
	 * Constructs a Auction object with the info receives from the parameters.
	 *
	 * @param theOrgName the name of organization.
	 * @param theItems the list of item that is in an auction.
	 * @param theDate is Calendar type, already handled a bug that is month starts at 0
	 * @param timeDuration MUST BE in the format "HH:MM" String
	 */
	public Auction(final String theOrgName, final List<Item> theItems, final Calendar theDate,
			final String timeDuration) {
		if (theItems == null || theDate == null) {
			throw new NullPointerException();
		}
		myDate = (Calendar) theDate.clone();
		if (theDate.get(Calendar.MONTH) == 12) {
			myDate.set(Calendar.YEAR, myDate.get(Calendar.YEAR) - 1);
		}
		myDate.set(Calendar.MONTH, myDate.get(Calendar.MONTH) - 1);
		myOrgName = theOrgName;
		getAuctionName();
		setTimeDuration(timeDuration);
		myItems = new LinkedList<>(theItems);
//		myBids = new ArrayList<Bid>();
	}

	/**
	 * Constructs a Auction object with the info receives from the parameters.
	 * 
	 * @param theAucName The ID of an Auction that is combination of orgName and time.
	 * @param theItems The list of items that are in the auction.
	 * @param startTime MUST BE in the format "HH:MM" String
	 * @param timeDur MUST BE in the format "HH:MM" String
	 * @throws ParseException if the month in the ID of the Auction is not in the format MMM
	 */
	public Auction(final String theAucName, final List<Item> theItems, final String startTime,
			final String timeDur) throws ParseException {
		myAucName = theAucName;
		myItems = new LinkedList<>(theItems);
		myOrgName = theAucName.substring(0, theAucName.length() - 12);
		String[] aucInfo = theAucName.split("-");
		Calendar cal = Calendar.getInstance();
		cal.clear();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM");
		cal.setTime(sdf.parse(aucInfo[aucInfo.length - 3]));
		cal.set(Calendar.YEAR, Integer.parseInt(aucInfo[aucInfo.length - 1]));
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(aucInfo[aucInfo.length - 2]));
		myDate = cal;
		setStartingTime(startTime);
		setTimeDuration(timeDur);
	}
	
	 /**
	  * makes a test object with random org name.
	  * lasts 1 hour and 1 minute is only used for testing
	  * @param theDate time the auction starts 
	  * @returns the Auction that was created
	  */
	@SuppressWarnings("unchecked")
	public static Auction makeTestAuction(final Calendar theDate){
		char[] chars = "bbuilbulaqqweqwergfnnfvae".toCharArray();//found this on stack overflow
		StringBuilder sb = new StringBuilder();         //any better way to make a random string??
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		List<Item> aList = new LinkedList<Item>();
	    aList.add(Item.makeRItem());
	    aList.add(Item.makeRItem());
	    return new Auction(sb.toString(),aList , theDate,"01:01");
	}

	/**
	 * Add an item into the auction.
	 * Pre: Item is not null.
	 * @param theItem the item object.
	 * @return true iff the item is new in the list.
	 */
	public boolean addItem(final Item theItem) {
		if (theItem == null)
			throw new NullPointerException();
		if (!myItems.contains(theItem))
			return myItems.add(theItem);
		return false;
	}

	/**
	 * Remove the item that is in the list.
	 * Pre: Item is not null.
	 * @param theItem the item object.
	 * @return true iff the item is removed from the list. False iff the item is not in
	 * the list.
	 */
	public boolean removeItem(final Item theItem) {
		if (theItem == null)
			throw new NullPointerException();
		if (myItems.contains(theItem)) {
			myItems.remove(theItem);
			return true;
		} else {
			return false;
		}
	}

//	/**
//	 * Removes an item from an Auction.
//	 *
//	 * @param theItemTitle
//	 */
//	public boolean removeItem(final String theItemTitle) {
//		if (theItemTitle == null) {
//			throw new NullPointerException();
//		}
//		if (theItemTitle.length() == 0) {
//			throw new IllegalArgumentException();
//		}
//		for (int i = 0; i < myItems.size(); i++) {
//			if (myItems.get(i).getTitle().equals(theItemTitle)) {
//				myItems.remove(i);
//				return true;
//			}
//		}
//		return false;
//	}

//	/**
//	 * Returns a date and hour.
//	 *
//	 * @return a string about the date and hour
//	 */
//	public String printAuctionDay() {
//		return "on " + myDate.get(Calendar.MONDAY) + ", " + myDate.get(Calendar.DAY_OF_MONTH) + " "
//				+ myDate.get(Calendar.YEAR) + " at " + myDate.get(Calendar.HOUR_OF_DAY);
//	}

	/**
	 * Returns a copy of a list of all items in a auction.
	 *
	 * @return a copy of a list of items
	 */
	public List<Item> getAllItems() {
		return new LinkedList<>(myItems);
	}

	/**
	 * Returns an organization name of the auction.
	 *
	 * @return an organization name of the auction.
	 */
	public String getOrganizationName() {
		return myOrgName;
	}

	/**
	 * Returns an ID of an Auction. 
	 *
	 * @return an ID of an Auction.
	 */
	public String getAuctionName() {
		myAucName = myOrgName + "-" + (new SimpleDateFormat("MMM").format(myDate.getTime()))
				+ "-" + myDate.get(Calendar.DAY_OF_MONTH) + "-" + myDate.get(Calendar.YEAR);
		return myAucName;
	}

	/**
	 * Return a copy of the Calendar type holding the time that auction starts.
	 * 
	 * @return a copy of the Calendar type holding the time that auction starts.
	 */
	public Calendar getDateAuctionStarts() {
		return (Calendar) myDate.clone();
	}

	/**
	 * Return a copy of the Calendar type holding the time that auction ends.
	 * 
	 * @return a copy of the Calendar type holding the time that auction ends.
	 */
	public Calendar getDateAuctionEnds() {
		Calendar result = (Calendar) myDate.clone();
		result.set(Calendar.HOUR_OF_DAY, result.get(Calendar.HOUR_OF_DAY) + hourDur);
		result.set(Calendar.MINUTE, result.get(Calendar.MINUTE) + minDur);
		return result;
	}

	/**
	 * Returns the day of the month that the auction is held.
	 *
	 * @return the day of the month that the auction is held.
	 */
	public int getDayOfYear() {
		return myDate.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * Returns the year that auction is held.
	 *
	 * @return the year that auction is held.
	 */
	public int getYear() {
		return myDate.get(Calendar.YEAR);
	}

	/**
	 * Returns the month that auction is held (Note: the month starts with an index 0).
	 *
	 * @return the month that auction is held (Note: the month starts with an index 0).
	 */
	public int getMonth() {
		return myDate.get(Calendar.MONTH);
	}

	/**
	 * Returns the hour that the auction starts.
	 *
	 * @return the hour that the auction starts.
	 */

	public int getStartHour() {
		return myDate.get(Calendar.HOUR_OF_DAY);

	}

	/**
	 * Returns the minute that the auction starts.
	 *
	 * @return the minute that the auction starts.
	 */
	public int getStartMin() {
		return myDate.get(Calendar.MINUTE);
	}
	
	/**
	 * Returns the string with the starting time in format "HH:MM".
	 * 
	 * @return the string with the starting time in format "HH:MM".
	 */
	public String getStartingTime() {
		return myDate.get(Calendar.HOUR_OF_DAY) + ":" + myDate.get(Calendar.MINUTE);
	}
	
	/**
	 * Returns the string with the time duration in format "HH:MM".
	 * 
	 * @return the string with the time duration in format "HH:MM".
	 */
	public String getTimeDuration() {
		return hourDur + ":" + minDur;
	}
	
	/**
	 * Sets starting time for an auction by passing a string in format "HH:MM".
	 * Pre: time is not null, time must be in format "HH:MM", HH in range [0..24] and
	 * MM in range [0..60]
	 * @param time the string must be in format "HH:MM".
	 */
	public void setStartingTime(final String time) {
		if (time == null) {
			throw new NullPointerException();
		}
		if (!time.contains(":")) throw new IllegalArgumentException();
		String start[] = time.split(":");
		int tempHour = Integer.parseInt(start[0]);
		int tempMin = Integer.parseInt(start[1]);
		if (tempHour > 24 || tempHour < 0 || tempMin > 60 || tempMin < 0 ) {
			throw new IllegalArgumentException();
		}
		myDate.set(Calendar.HOUR_OF_DAY, Integer.parseInt(start[0]));
		myDate.set(Calendar.MINUTE, Integer.parseInt(start[1]));
	}

	/**
	 * Sets time duration for an auction by passing a string in format "HH:MM".
	 * Pre: time is not null, time must be in format "HH:MM", HH in range [0..24],
	 * MM in range [0..60] and both HH & MM must not be zero.
	 * @param timeDur the string must be in format "HH:MM".
	 */
	public void setTimeDuration(final String timeDur) {
		if (timeDur == null) {
			throw new NullPointerException();
		}
		if (!timeDur.contains(":")) throw new IllegalArgumentException();
		String time[] = timeDur.split(":");
		int tempHour = Integer.parseInt(time[0]);
		int tempMin = Integer.parseInt(time[1]);
		if (tempHour < 0 || tempMin < 0 || (tempHour == 0 && tempMin == 0) 
				|| tempMin > 60) {
			throw new IllegalArgumentException();
		}
		hourDur = tempHour;
		minDur = tempMin;
	}
	
	/**
	 * Set a day of month of an auction.
	 * Pre: date must be in range of the specific month.
	 * @param date of month of an auction.
	 */
	public void setDateOfMonth(final int date) {
		if (date <= 0) throw new IllegalArgumentException();
		switch(getMonth()) {
			case 0:
			case 2:
			case 4:
			case 6:
			case 7:
			case 9:
			case 11: if (date > 31) throw new IllegalArgumentException();
			case 3:
			case 5:
			case 8:
			case 10: if (date > 30) throw new IllegalArgumentException();
			case 1: 
				if (getYear() % 4 == 0) {
					if (date > 29) throw new IllegalArgumentException();
				} else {
					if (date > 20) throw new IllegalArgumentException();
				}
			default: break;
		}
		myDate.set(Calendar.DAY_OF_MONTH, date);
	}

	/**
	 * Set a month of an auction.
	 * Pre: month must be in range [1..12]
	 * @param month
	 */
	public void setMonth(final int month) {
		if (month < 1 || month > 12) throw new IllegalArgumentException();
		myDate.set(Calendar.MONTH, month - 1);
	}

	/**
	 * Set a year of an auction.
	 * Pre: year can't be less than 1970. Since the Calendar type starts at 1970.
	 * @param year
	 */
	public void setYear(final int year) {
		// this magic number is only used once. 
		if (year < 1970) throw new IllegalArgumentException(); 
		myDate.set(Calendar.YEAR, year);
	}
	
//	public void setAvailable(final boolean theAvailable) {
//		isAvailable = theAvailable;
//	}

//	public boolean isAvailable() {
//		return isAvailable;
//	}
//	@Override
//	public String toString() {
//		return myOrgName + " " + myDate.getTime() + "\n";
//	}

//	public String displayItemsInAuction() {
//		String result = myOrgName + " " + myDate.getTime() + "\n";
//		char c = 'a';
//		for (int i = 0; i < myItems.size(); i++) {
//			result += c++ + ") " + myItems.get(i) + "\n";
//		}
//		return result;
//	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return (getAuctionName() + ", " + getAllItems().size() + ", " + getStartHour()
				+ ":" + getStartMin() + ", " + hourDur + ":" + minDur);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(Auction theAuction) {
		return this.myDate.compareTo(theAuction.myDate);
	}
	
	/**
	 * @return a copy of the Auction (Note: the List of item is not copied).
	 */
	@Override
	public Auction clone() {
		return new Auction(myOrgName, myItems, 
				(Calendar) myDate.clone(), getTimeDuration());
	}
}
