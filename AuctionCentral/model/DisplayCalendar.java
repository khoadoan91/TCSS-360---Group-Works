package model;

import java.util.List;
import java.util.ArrayList;

/**
 * @author KyleD
 *
 */
public class DisplayCalendar {
	
	public static final int MAX_AUCTION = 25;
	
	private List<Auction> myAuctions;
	
	public DisplayCalendar() {
		myAuctions = new ArrayList<>();
	}
	
	public DisplayCalendar(final List<Auction> theAuction) {
		this();
		myAuctions.addAll(theAuction);
	}
	
	public boolean addAuction(final Auction theAuction) {
		if (!hasMaxAuctions()) {
			return myAuctions.add(theAuction);	
		}
		return false;
	}
	
	/**
	 * TODO check the business rules
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
