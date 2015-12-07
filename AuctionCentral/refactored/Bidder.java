package refactored;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a Bidder that can make bids in an auction. 
 * 
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
	
	/**
	 * Constructs a new Bidder object.
	 * 
	 * @param userName is the user name of the bidder. 
	 * @param theAddr is the address of the bidder.
	 * @param theCredit is the credit card number of the bidder.
	 */
	public Bidder(String userName, String theAddr, String theCredit) {
		this(userName, theAddr, theCredit, new HashMap<>());
	}
	
	/**
	 * Constructs a new Bidder object.
	 * 
	 * @param userName is the user name of the bidder.
	 * @param theAddr is the address of the bidder.
	 * @param theCredit is the credit card number of the bidder.
	 * @param theBids is the bids this bidder has already made.
	 */
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
	 * @param theAuction is the auction the bid is being made in.
	 * @param theItem is the item that is being bid on.
	 * @param theBid is the amount that is being bid.
	 * 
	 * @return true iff the bid was added successfully. 
	 * 
	 * @throws NullPointerException if theBid, theAuction or theItem are null.
	 * @throws IllegalArgumentException if theItem is not contained in theAuction.
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
	 * @param theAuction is the auction where the bid was made.
	 * @param theItem is the item that was bid on.
	 * 
	 * @throws NullPointerException if theAuction or theItem are null.
	 * @throws IllegalArgumentException if a bid was not placed in this auction or
	 *         theItem is not contained in theAuction.
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
	
	/**
	 * Checks if the bidder has made a bid on the specified item in the specified auction.
	 * 
	 * @param theAuction is the auction the bid was made in.
	 * @param theItem is the item that was bid on.
	 * 
	 * @return true iff a bid was made in the specified auction and the auction contains the
	 *         specified item.
	 */
	public boolean containsBid(final Auction theAuction, final Item theItem) {
		return myBids.containsKey(theAuction) && myBids.get(theAuction).containsKey(theItem);
	}
	
	/**
	 * Gets the bid from the specified auction.
	 * 
	 * @param theAuction is the auction the bid was made in.
	 * @param theItem is the item that was bid on.
	 * 
	 * @return the amount that was bid.
	 * 
	 * @throws NullPointerException if theAuction or theItem are null.
	 * @throws IllegalArgumentException if a bid was not placed in this auction or
	 *         theItem is not contained in theAuction.
	 */
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
