package old;

import java.util.ArrayList;
import java.util.List;

import current.Auction;
import current.Bid;
import current.DisplayCalendar;

/**
 * Represents a user which can bid on items in auctions.
 * 
 * @author Nina Chepovska
 * @version Nov 6, 2015
 */
public class Bidder extends User { // TODO implement the User interface

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
	public Bidder(final String userName, DisplayCalendar calendar) {
		super(userName, calendar);
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
	public void addBid(final Auction theAuction, final Bid theBid) {
		if (theBid == null) {
			throw new NullPointerException();
		}

		myBids.add(theBid);
		theAuction.addBid(theBid);
	}

	/**
	 * Removes the specified bid.
	 * 
	 * @param theBid
	 */
	public void removeBid(final Auction theAuction, final Bid theBid) {
		if (theBid == null) {
			throw new NullPointerException();
		} else if (!myBids.contains(theBid)) {
			throw new IllegalArgumentException();
		}

		myBids.remove(theBid);
		theAuction.removeBid(theBid);
		
	}

	public String getUserType() {
		return userType;
	}
	
	@Override
	public String toString() {
		return userType + ": " + super.getUsername();
	}
	
	public boolean equals(Object o) {
		Bidder other = (Bidder) o;
		return userType.equals(other.getUserType()) 
				&& super.getUsername().equals(other.getUsername());
	}
}