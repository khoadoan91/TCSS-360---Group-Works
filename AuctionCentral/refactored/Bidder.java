package refactored;

import java.util.ArrayList;
import java.util.List;

import old.Bid;

/**
 * A class that represents a bidder at an auction.
 * 
 * @author chepovska_nina
 */
public class Bidder extends User {

	/** The billing address of the bidder. */
	private String myAddress;

	/** The credit card number of the bidder. */
	@SuppressWarnings("unused")
	private String myCreditCard;

	/** The bids this bidder has made. */
	private List<Bid> myBids;
	
	/**
	 * Constructs a new Bidder object.
	 * 
	 * @param theAddr is the address of the bidder.
	 * @param theCredit is the credit card number of the bidder.
	 */
	public Bidder(String theAddr, String theCredit) {
		this(theAddr, theCredit, new ArrayList<>());
	}
	
	/**
	 * Constructs a new Bidder object.
	 * 
	 * @param theAddr is the address of the bidder.
	 * @param theCredit is the credit card number of the bidder.
	 * @param theBids is the bids this bidder has already made.
	 * 
	 * @throws NullPointerException when theAddr, theCredit, or theBids is null.
	 * @throws IllegalArgumentException when theAddr or theCredit are empty strings.
	 */
	public Bidder(String theAddr, String theCredit, List<Bid> theBids) {
		if (theAddr == null) {
			throw new NullPointerException();
		} else if (theAddr.isEmpty()) { 
			throw new IllegalArgumentException();
		} else if (theCredit == null) {
			throw new NullPointerException();
		} else if (theCredit.isEmpty()) {
			throw new IllegalArgumentException();
		} else if (theBids == null) {
			throw new NullPointerException();
		}
		
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
	 * @param theBid is the new bid the bidder has made.
	 * 
	 * @throws NullPointerException when theBid is null.
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
	 * @param theBid is the bid to remove. 
	 * 
	 * @throws NullPointerException when theBid is null.
	 * @throws IllegalArgumentException when the bidder does not have theBid to remove.
	 */
	public void removeBid(final Bid theBid) {
		if (theBid == null) {
			throw new NullPointerException();
		} else if (!myBids.contains(theBid)) {
			throw new IllegalArgumentException();
		}

		myBids.remove(theBid);
	}

	@Override
    public String toString() {
    	return "Bidder: " + myAddress;
    }
}
