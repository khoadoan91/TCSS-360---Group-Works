package alternative;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Bid;
import model.DisplayCalendar;

/**
 * Represents a user which can bid on items in auctions.
 * 
 * @author Nina Chepovska
 * @version Nov 6, 2015
 */
public class Bidder implements User { 

//	final private String userType = "Bidder";

	/** The billing address of the bidder. */
	@SuppressWarnings("unused")
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
	 * Constructs a new bidder object.
	 * 
	 * @param theAddress
	 * @param theCreditCard
	 */
//	public Bidder(DisplayCalendar myCalendar) {
//		myBids = new ArrayList<Bid>();
//	}

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

	/*
	 * public Bid editBid(final Bid theBid) { if (theBid == null) { throw new
	 * NullPointerException(); } else if (!myBids.contains(theBid)) { throw new
	 * IllegalArgumentException(); }
	 * 
	 * 
	 * }
	 */
//	public String getUserType() {
//		return userType;
//	}

	@Override
	public void run(Scanner scanner, DisplayCalendar cal) {
		// TODO Auto-generated method stub
		
	}
}
