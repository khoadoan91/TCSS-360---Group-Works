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
	private List<Image> myImages;
	private String myDesp;
	
	public Item(final String theTitle, final int theQuantity, 
			final List<Image> theImages, final String theDesp) {
		myTitle = theTitle;
		myQuantity = theQuantity;
		myImages = theImages;
		myDesp = theDesp;
	}
	
	public String getTitle() {
		return myTitle;
	}
	
	public int getQuantity() {
		return myQuantity;
	}
	
	public List<Image> getImages() {
		return myImages;
	}
	
	public String getDescription() {
		return myDesp;
	}
}
