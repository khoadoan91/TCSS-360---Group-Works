package model;

/**
 * A class which represents an item bid in an auction. 
 * 
 * @author Nina Chepovska
 * @version Nov 6, 2015
 */
public class Bid {

	/** The item that was bid on. */
	private Item myItem;
	
	/** The dollar amount that was bid. */
	private double myBidAmount;
	
	/**
	 * Constructs a new Bid object.  
	 *  
	 * @param theItem
	 * @param theBidAmount
	 */
	public Bid(final Item theItem, final double theBidAmount) {
		if (theItem == null) {
			throw new NullPointerException();
		} else if (theBidAmount <= 0.0) {
			throw new IllegalArgumentException(); 
		}
		
		myItem = theItem;
		myBidAmount = theBidAmount; 
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
	 * Sets the bid amount.
	 * 
	 * @param theBidAmount
	 */
	public void setBidAmount(final double theBidAmount) {
		if (theBidAmount <= 0.0) {
			throw new IllegalArgumentException(); 
		}
		
		myBidAmount = theBidAmount;
	}
}
