package refactored;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Nina
 * @author KyleD
 */
public class Bidder extends User {
	
	/** the user name that bidder uses for log in to the server. */
	private String myUserName;

	/** The billing address of the bidder. */
	private String myAddress;

	/** The credit card number of the bidder. */
	@SuppressWarnings("unused")
	private String myCreditCard;

	/** The bids this bidder has made. */
	private Map<Auction, Map<Item, BigDecimal>> myBids;
	
	public Bidder(String userName, String theAddr, String theCredit) {
		this(userName, theAddr, theCredit, new HashMap<>());
	}
	
	public Bidder(String userName, String theAddr, String theCredit, 
						Map<Auction, Map<Item, BigDecimal>> theBids) {
		myUserName = userName;
		myAddress = theAddr;
		myCreditCard = theCredit;
		myBids = theBids;
	}

	/**
	 * Gets all the bids the bidder has made.
	 * 
	 * @return the bids.
	 */
	public Map<Auction, Map<Item, BigDecimal>> viewBids() {
		return new HashMap<>(myBids);
	}

	/**
	 * Adds a new bid.
	 * 
	 * @param theBid
	 */
	public boolean addBid(final Auction theAuction, final Item theItem, final BigDecimal theBid) {
		if (theBid == null || theAuction == null || theItem == null) {
			throw new NullPointerException();
		}
		if (!theAuction.getAllItems().contains(theItem)) {
			throw new IllegalArgumentException("The Auction doesn't contain the item");
		}
		if (!myBids.containsKey(theAuction)) {
			myBids.put(theAuction, new HashMap<>());
		}
		if (theItem.addBid(myUserName, theBid)) {
			myBids.get(theAuction).put(theItem, theBid);
			return true;
		} 
		return false;
	}

	/**
	 * Removes the specified bid.
	 * 
	 * @param theBid
	 */
	public void removeBid(final Auction theAuction, final Item theItem) {
		if (theItem == null || theAuction == null) {
			throw new NullPointerException();
		}
		if (!myBids.containsKey(theAuction) || !myBids.get(theAuction).containsKey(theItem)) {
			throw new IllegalArgumentException();
		}
		myBids.get(theAuction).remove(theItem);
		theItem.removeBid(myUserName);
	}
	
	public boolean containsBid(final Auction theAuction, final Item theItem) {
		return myBids.containsKey(theAuction) && myBids.get(theAuction).containsKey(theItem);
	}
	
	public BigDecimal getBidFrom(final Auction theAuction, final Item theItem) {
		if (theItem == null || theAuction == null) {
			throw new NullPointerException();
		}
		if (!myBids.containsKey(theAuction) || !myBids.get(theAuction).containsKey(theItem)) {
			throw new IllegalArgumentException();
		}
		return myBids.get(theAuction).get(theItem);
	}

	@Override
    public String toString() {
    	return "Bidder: " + myAddress;
    }
}
