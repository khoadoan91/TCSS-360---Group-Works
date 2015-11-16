package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Calendar;

/**
 * @author KyleD
 *
 */
public class Item implements Comparable<Item> {

	/** The ID that is initialized by ItemID. */
	private final long myID;

	/** The title of the item. */
	private String myTitle;

	/** The quantity of the item. */
	private int myQuantity;

	// private List<Image> myImages;

	/** The description of the item. */
	private String myDesc;
	
	/** Starting price of the item. */
	private BigDecimal myStartingPrice;

	/**
	 * Constructs an item with parameters info.
	 * 
	 * @param theTitle
	 * @param theQuantity
	 * @param theDesc
	 */
	public Item(final String theTitle, final int theQuantity, final BigDecimal price, 
			final String theDesc) {
		if (theTitle.length() == 0 || theQuantity < 1 || theDesc.length() == 0) {
			throw new IllegalArgumentException();
		}
		if (theTitle == null || theDesc == null) {
			throw new NullPointerException();
		}
		// suppose that the difference in time between 2 created items is AT
		// LEAST 1ms
		myID = Calendar.getInstance().getTimeInMillis();
		myTitle = theTitle;
		myQuantity = theQuantity;
		myStartingPrice = price;
		myDesc = theDesc;
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
	public long getItemID() {
		return myID;
	}

	// public List<Image> getImages() {
	// return myImages;
	// }
	
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

	/**
	 * Sets the title of the item.
	 * 
	 * @param theTitle
	 */
	public void setTitle(final String theTitle) {
		if (theTitle == null || theTitle.length() == 0) {
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
		myStartingPrice = price;
	}

	/**
	 * Sets the Description of the item.
	 * 
	 * @param theDesp
	 */
	public void setDescription(final String theDesc) {
		if (theDesc == null || theDesc.length() == 0) {
			throw new IllegalArgumentException();
		}
		myDesc = theDesc;
	}

	// public void addImage(final Image theImage) {
	// if (!myImages.contains(theImage))
	// myImages.add(theImage);
	// }

	// public void addMultiImages(final Image[] theImages) {
	// for (int i = 0; i < theImages.length; i++) {
	// if(!myImages.contains(theImages[i]))
	// myImages.add(theImages[i]);
	// }
	// }

	// public void removeImage(final Image theImage) {
	// myImages.remove(theImage);
	// }
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

	@Override
	public boolean equals(Object theItem) {
		return ((Item) theItem).myTitle.equalsIgnoreCase(this.myTitle);
	}
}
