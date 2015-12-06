package refactored;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Random;
import java.util.Map;


/**
 * @author KyleD
 *
 */
public class Item implements Comparable<Item>, java.io.Serializable {

	/** The title of the item. */
	private String myTitle;

	/** The quantity of the item. */
	private int myQuantity;

	/** The description of the item. */
	private String myDesc;

	/** Starting price of the item. */
	private BigDecimal myStartingPrice;

	/** The list of the bidding of the each bidder. */
	private Map<String, BigDecimal> myBidders;

	/**
	 * Constructs an item with parameters info.
	 *
	 * @param theTitle The title of the item. 
	 * @param theQuantity The quantity of the item. 
	 * @param thePrice Starting price of the item.
	 * @param theDesc The description of the item.
	 */
	public Item(final String theTitle, final int theQuantity, final BigDecimal thePrice,
			final String theDesc) {
		setTitle(theTitle);
		setQuantity(theQuantity);
		setStartingPrice(thePrice);
		setDescription(theDesc);
		myBidders = new HashMap<>();
	}
	
	/**
	 * Makes a random item.
	 * used for testing
	 */
	static public Item makeRItem(){
		char[] chars = "bbuilbulaqqweqwergfnnfvae".toCharArray();//found this on stack overflow
	      StringBuilder sb = new StringBuilder();         //any better way to make a random string??
	      Random random = new Random();
	      for (int i = 0; i < 10; i++) {
	         char c = chars[random.nextInt(chars.length)];
	      sb.append(c);
	      }
	      StringBuilder sb2 = new StringBuilder();         //any better way to make a random string??
	      for (int i = 0; i < 10; i++) {
	         char c = chars[random.nextInt(chars.length)];
	      sb2.append(c);
	      }
		return new Item(sb.toString(), 3, new BigDecimal(3),sb2.toString());
	}
	
	/**
	 * Returns the title.
	 *
	 * @return the title.
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
	 * Returns the starting price.
	 * 
	 * @return the starting price.
	 */
	public BigDecimal getStartingPrice() {
		return myStartingPrice;
	}

	/**
	 * Returns the description.
	 *
	 * @return the description.
	 */
	public String getDescription() {
		return myDesc;
	}

	/**
	 * Returns a copy of the Map of the bidderUserName and their bid.
	 * @return a copy of the Map of the bidderUserName and their bid.
	 */
	public Map<String, BigDecimal> getItemBid() {
		return new HashMap<>(myBidders);
	}

	/**
	 * Sets the title of the item.
	 * Pre: theTitle is not null and is not empty string.
	 * @param theTitle the title of the item.
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
	 * Pre: the quantity is not less than 1;
	 * @param theQt the quantity of the item.
	 */
	public void setQuantity(final int theQt) {
		if (theQt < 1) {
			throw new IllegalArgumentException();
		}
		myQuantity = theQt;
	}

	/**
	 * Sets the starting price of the item.
	 * Pre: the price is not null and must be greater than 0.
	 * @param thePrice the starting price of the item.
	 */
	public void setStartingPrice(final BigDecimal thePrice) {
		if (thePrice == null) {
			throw new NullPointerException();
		}
		if (thePrice.doubleValue() <= 0) {
			throw new IllegalArgumentException();
		}
		myStartingPrice = thePrice;
	}

	/**
	 * Sets the Description of the item.
	 * Pre: the desciption is not null and is not empty string.
	 * @param theDesp the Description of the item.
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
	 * Add or make change bid from bidderUserName to the list of bidders.
	 * Pre: bidder and price is not null;
	 * Post: add bid to the myBidders Map.
	 * @param bidder the bidderUserName
	 * @param bidPrice the bid of the item from bidderUserName
	 * @return false iff the price is less than the starting price.
	 * @throws IllegalArgumentException if bidder or price is null.
	 */
	public boolean addBid(final String bidder, final BigDecimal bidPrice) {
		if (bidder == null || bidPrice == null || bidder.length() == 0) 
			throw new NullPointerException();
		if (bidPrice.compareTo(myStartingPrice) < 0) {
			return false;
		} else {
			myBidders.put(bidder, bidPrice);
			return true;
		}
	}

	/**
	 * Remove the bid from bidderUserName.
	 * Pre: bidder is not null.
	 * Post: Bidder Map remove the bid from bidderUserName.
	 * @param bidder the bidderUserName.
	 */
	public void removeBid(final String bidder) {
		if (bidder == null) throw new NullPointerException();
		myBidders.remove(bidder);
	}
	
	/**
	 * Get the bidderUserName which has highest bid of the item.
	 * @return the bidderUserName which has highest bid of the item.
	 */
	public String getBidderHighestBid() {
		String highest = null;
		BigDecimal max = new BigDecimal("0");
		for (String bidder : myBidders.keySet()) {
			if (myBidders.get(bidder).compareTo(max) > 0) {
				max = myBidders.get(bidder);
				highest = bidder;
			}
		}
		return highest;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return myTitle + "\nQuantity: " + myQuantity + "\nStarting Price: "
				+ NumberFormat.getCurrencyInstance().format(myStartingPrice)
				+ "\nDescription: " + myDesc ;
	}

	/**
	 * Using for saving the file.
	 * @return the detail of an item.
	 */
	public String toStringTextFile() {
		return (myTitle + ", " + myQuantity + ", " + myStartingPrice + ", " + myDesc);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(final Item theItem) {
		return this.myTitle.compareToIgnoreCase(theItem.myTitle);
	}
	
	@Override
	public boolean equals(final Object theItem) {
		return myTitle.equals(((Item) theItem).myTitle);
	}
}
