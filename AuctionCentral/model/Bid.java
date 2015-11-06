package model;

public class Bid {

	private Item myItem;
	private double myBidAmount;
	
	public Bid(final Item theItem, final double theBidAmount) {
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
		myBidAmount = theBidAmount;
	}
}
