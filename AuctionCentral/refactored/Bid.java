package refactored;


public class Bid {
	/** The item that was bid on. */
	private Item myItem;

	/** The dollar amount that was bid. */
	private double myBidAmount;
	
	private String myBidder;

	/**
	 * Constructs a new Bid object.
	 * 
	 * @param theItem
	 * @param theBidAmount
	 */
	public Bid(final Item theItem, final double theBidAmount, final String theBidder) {
		if (theItem == null) {
			throw new NullPointerException();
		} else if (theBidAmount <= 0.0) {
			throw new IllegalArgumentException();
		} else if (theBidAmount < theItem.getStartingPrice()) {
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
		return myBidder;
	}

	/**
	 * Sets the bid amount.
	 * 
	 * @param theBidAmount
	 */
	public void setBidAmount(final double theBidAmount) {
		if (theBidAmount <= 0.0) {
			throw new IllegalArgumentException();
		} else if (theBidAmount < myItem.getStartingPrice()) {
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
