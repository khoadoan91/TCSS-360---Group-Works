package model;

import java.awt.Image;
import java.util.List;

/**
 * @author KyleD
 *
 */
public class Item {
	// TODO Question: How to generate the unique itemID?
	
	private String myTitle;
	private int myQuantity;
	private String myDesp;
	
	public Item(final String theTitle, final int theQuantity, final String theDesp) {
		myTitle = theTitle;
		myQuantity = theQuantity;
		myDesp = theDesp;
	}
	
	public String getTitle() {
		return myTitle;
	}
	
	public int getQuantity() {
		return myQuantity;
	}
	
	public String getDescription() {
		return myDesp;
	}
	
	// these below methods are used for the editing
	public void setTitle(final String theTitle) {
		myTitle = theTitle;
	}
	
	public void setQuantity(final int theQt) {
		myQuantity = theQt;
	}
	
	public void setDescription(final String theDesp) {
		myDesp = theDesp;
	}

}
