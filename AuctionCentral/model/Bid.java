package model;

public class Bid {

	private Item myItem;
	private double myBidAmount;
	
	public Bid(final Item theItem, final double theBidAmount) {
		if (theItem == null) {
			throw new NullPointerException();
		} else if (theBidAmount <= 0.0) {
			throw new IllegalArgumentException(); 
		}
		
		myItem = theItem;
		myBidAmount = theBidAmount; 
	}
	
	public Item getItem() {
		return myItem;
	}
	
	public double getBidAmount() {
		return myBidAmount;
	}
	
	public void setBidAmount(final double theBidAmount) {
		if (theBidAmount <= 0.0) {
			throw new IllegalArgumentException(); 
		}
		
		myBidAmount = theBidAmount;
	}
}
