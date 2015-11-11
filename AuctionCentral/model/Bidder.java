package model;

import java.util.ArrayList;
import java.util.List;
	
/**
 * Represents a user which can bid on items in auctions. 
 * 
 * @author Nina Chepovska
 * @version Nov 6, 2015
 */
public class Bidder extends User {	// TODO implement the User interface
	
	final private String userType = "Bidder";
	
	/** The billing address of the bidder. */
	private String myAddress;
	
	/** The credit card number of the bidder. */
	private String myCreditCard;
	
	/** The bids this bidder has made. */
	private List<Bid> myBids;
	
	/**
	 * Constructs a new bidder object. 
	 * 
	 * @param theAddress
	 * @param theCreditCard
	 */
	public Bidder(final String userName, DisplayCalendar myCalendar) {
		super(userName, myCalendar);
		myBids = new ArrayList<Bid>();
	}
	
	@Deprecated
	public Bidder(final String userName) {
		super(userName);
		myBids = new ArrayList<Bid>();
	}
	
	/**
	 * Gets all the bids the bidder has made.
	 * 
	 * @return the bids.
	 */
	public List<Bid> viewBids() {
		return myBids;
	}
	
	/**
	 * Adds a new bid. 
	 * 
	 * @param theBid
	 */
	public void addBid(final Bid theBid) {
		if (theBid == null) {
			throw new NullPointerException();
		}
		
		myBids.add(theBid);
	}
	
	/**
	 * Removes the specified bid.
	 * 
	 * @param theBid
	 */
	public void removeBid(final Bid theBid) {
		if (theBid == null) {
			throw new NullPointerException();
		} else if (!myBids.contains(theBid)) {
			throw new IllegalArgumentException();
		}
		
		myBids.remove(theBid);
	}
	
	/*public Bid editBid(final Bid theBid) {
		if (theBid == null) {
			throw new NullPointerException();
		} else if (!myBids.contains(theBid)) {
			throw new IllegalArgumentException();
		}
		
		
	}*/
	public String getUserType() {
		return userType;
	}
}
