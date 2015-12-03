package refactored;

/**
 * A class that represents a bid at an auction.
 * 
 * @author chepovska_nina
 */
public class Bid {
	
	/** The item that was bid on. */
	private Item myItem;

	/** The dollar amount that was bid. */
	private double myBidAmount;
	
	/** The bidder of this bid. */
	private String myBidder;

	/**
	 * Constructs a new Bid object.
	 * 
	 * @param theItem is the item to bid on.
	 * @param theBidAmount is the dollar amount to bid.
	 * 
	 * @throws NullPointerException when theItem is null
	 * @throws IllegalArgumentException when theBidAmount is less than zero or theBidAmount is 
	 *         less than the item's starting price. 
	 */
	public Bid(final Item theItem, final double theBidAmount, final String theBidder) {
		if (theItem == null) {
			throw new NullPointerException();
		} else if (theBidAmount <= 0.0) {
			throw new IllegalArgumentException();
		} else if (theBidAmount < theItem.getStartingPrice().doubleValue()) {
			throw new IllegalArgumentException();
		}
		
		myItem = theItem;
		myBidAmount = theBidAmount;
		myBidder = theBidder;
	}

	/**
	 * Gets the item that was bid on.
	 * 
	 * @return the item.
	 */
	public Item getItem() {
		return myItem;
	}

	/**
	 * Gets the amount that was bid.
	 * 
	 * @return the dollar amount.
	 */
	public double getBidAmount() {
		return myBidAmount;
	}
	
	/**
	 * Gets the bidder of this bid.
	 * 
	 * @return the bidder as a String.
	 */
	public String getBidder() {
		return myBidder.toString();
	}

	/**
	 * Sets the bid amount.
	 * 
	 * @param theBidAmount is the dollar amount to change the bid to.
	 * 
	 * @throws IllegalArgumentException when theBidAmount is less than zero or theBidAmount is 
	 *         less than the item's starting price.
	 */
	public void setBidAmount(final double theBidAmount) {
		if (theBidAmount <= 0.0) {
			throw new IllegalArgumentException();
		} else if (theBidAmount < myItem.getStartingPrice().doubleValue()) {
			throw new IllegalArgumentException();
		}

		myBidAmount = theBidAmount;
	}
	
	@Override
	public String toString() {
		return myBidder + ": " + myItem.toString() + " " + myBidAmount;
	}
	
	@Override
	public boolean equals(Object o) {
		Bid other = (Bid) o;
		return myItem.equals(other.getItem()) && myBidAmount == other.getBidAmount();
	}
}
