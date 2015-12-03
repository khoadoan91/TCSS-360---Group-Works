package refactored;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import refactored.Bid;

public class Bidder extends User {

	//	final private String userType = "Bidder";

	/** The billing address of the bidder. */
	//@SuppressWarnings("unused")
	private String myAddress;

	/** The credit card number of the bidder. */
	@SuppressWarnings("unused")
	private String myCreditCard;

	/** The bids this bidder has made. */
	private List<Bid> myBids;
	
	public Bidder(String theAddr, String theCredit) {
		this(theAddr, theCredit, new ArrayList<>());
	}
	
	public Bidder(String theAddr, String theCredit, List<Bid> theBids) {
		myAddress = theAddr;
		myCreditCard = theCredit;
		myBids = theBids;
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

//	@Override
//    public String toString() {
//    	return "Bidder: " + myAddress;
//    }
}
