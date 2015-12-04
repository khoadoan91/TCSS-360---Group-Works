package refactored;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author KyleD
 *
 */
public class Item implements Comparable<Item> {

	/** The ID that is initialized by ItemID. */
//	private final long myID;

	/** The title of the item. */
	private String myTitle;

	/** The quantity of the item. */
	private int myQuantity;

	/** The description of the item. */
	private String myDesc;
	
	/** Starting price of the item. */
	private BigDecimal myStartingPrice;

	/** The list of the bidding of the each bidder. */
	private Map<Bidder, BigDecimal> myBidders;
	
	/**
	 * Constructs an item with parameters info.
	 * 
	 * @param theTitle
	 * @param theQuantity
	 * @param theDesc
	 */
	public Item(final String theTitle, final int theQuantity, final BigDecimal thePrice, 
			final String theDesc) {
		// suppose that the difference in time between 2 created items is AT
		// LEAST 1ms
//		myID = Calendar.getInstance().getTimeInMillis();
		setTitle(theTitle);
		setQuantity(theQuantity);
		setStartingPrice(thePrice);
		setDescription(theDesc);
		myBidders = new TreeMap<>();
	}

	/**
	 * Returns the title.
	 * 
	 * @return
	 */
	public String getTitle() {
		return myTitle;
	}

	/**
	 * Returns the quantity.
	 * 
	 * @return
	 */
	public int getQuantity() {
		return myQuantity;
	}

	/**
	 * Returns the ID of the item.
	 * 
	 * @return
	 */
//	public long getItemID() {
//		return myID;
//	}

	public BigDecimal getStartingPrice() {
		return myStartingPrice;
	}

	/**
	 * Returns the description.
	 * 
	 * @return
	 */
	public String getDescription() {
		return myDesc;
	}
	
	public Map<Bidder, BigDecimal> getItemBid() {
		return new TreeMap<>(myBidders);
	}

	/**
	 * Sets the title of the item.
	 * 
	 * @param theTitle
	 */
	public void setTitle(final String theTitle) {
		if (theTitle == null) {
			throw new NullPointerException();
		}
		if (theTitle.length() == 0) {
			throw new IllegalArgumentException();
		} 
		myTitle = theTitle;
	}

	/**
	 * Sets the quantity of the item.
	 * 
	 * @param theQt
	 */
	public void setQuantity(final int theQt) {
		if (theQt < 1) {
			throw new IllegalArgumentException();
		}
		myQuantity = theQt;
	}
	
	public void setStartingPrice(final BigDecimal price) {
		if (price == null) {
			throw new NullPointerException();
		}
		if (price.doubleValue() <= 0) {
			throw new IllegalArgumentException();
		}
		myStartingPrice = price;
	}

	/**
	 * Sets the Description of the item.
	 * 
	 * @param theDesp
	 */
	public void setDescription(final String theDesc) {
		if (theDesc == null) 
			throw new NullPointerException();
		if (theDesc.length() == 0) {
			throw new IllegalArgumentException();
		}
		myDesc = theDesc;
	}
	
	/**
	 * 
	 * @param bidder
	 * @param bidPrice
	 */
	public void addBid(final Bidder bidder, final BigDecimal bidPrice) {
		if (bidPrice.compareTo(myStartingPrice) < 0) {
			throw new IllegalArgumentException();
		}
		myBidders.put(bidder, bidPrice);
	}
	
	public void removeBid(final Bidder bidder) {
		myBidders.remove(bidder);
	}
	
	@Override
	public String toString() {
		return myTitle + "\nQuantity: " + myQuantity + "\nStarting Price: " 
				+ NumberFormat.getCurrencyInstance().format(myStartingPrice)
				+ "\nDescription: " + myDesc ;
	}
	
	public String toStringTextFile() {
		return (myTitle + ", " + myQuantity + ", " + myStartingPrice + ", " + myDesc);
	}

	@Override
	public int compareTo(final Item theItem) {
		return this.myTitle.compareToIgnoreCase(theItem.myTitle);
	}
}
