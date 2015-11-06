/**
 * 
 */
package model;

import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

/**
 * @author KyleD
 *
 */
public class DisplayCalendar {
	
	public static final int MAX_AUCTION = 25;
	
	private Calendar myCalendar;
	private List<String> myDates;
	private List<Auction> myAuctions;
	
	public DisplayCalendar() {
		myAuctions = new ArrayList<>();
	}
	
	public DisplayCalendar(List<Auction> theAuction) {
		
	}
	
	public void addAuction(final Auction theAuction) {
		if (!hasMaxAuctions()) {
			myAuctions.add(theAuction);	
		}
	}
	
	/**
	 * TODO check the business rulesc
	 * @param theDate
	 * @return
	 */
	public boolean checkAvailability(final String theDate) {
		return false;
	}
	
	public boolean hasMaxAuctions() {
		return myAuctions.size() == MAX_AUCTION;
	}
}
