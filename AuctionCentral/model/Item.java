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
	
	public void addImage(final Image theImage) {
		if (!myImages.contains(theImage))
			myImages.add(theImage);
	}
	
	public void addMultiImages(final Image[] theImages) {
		for (int i = 0; i < theImages.length; i++) {
			if(!myImages.contains(theImages[i]))
				myImages.add(theImages[i]);
		}
	}
	
	public void removeImage(final Image theImage) {
		myImages.remove(theImage);
	}
}
