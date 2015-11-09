package model;

/**
 * @author KyleD
 *
 */
public class Item {
	// TODO Question: How to generate the unique itemID?
	
	private String myTitle;
	private int myQuantity;
	private String myDesc;
	
	public Item(final String theTitle, final int theQuantity, final String theDesc) {
		myTitle = theTitle;
		myQuantity = theQuantity;
		myDesc = theDesc;
	}
	
	public String getTitle() {
		return myTitle;
	}
	
	public int getQuantity() {
		return myQuantity;
	}
	
	public String getDescription() {
		return myDesc;
	}
	
	// these below methods are used for the editing
	public void setTitle(final String theTitle) {
		myTitle = theTitle;
	}
	
	public void setQuantity(final int theQt) {
		myQuantity = theQt;
	}
	
	public void setDescription(final String theDesp) {
		myDesc = theDesp;
	}
	
//	public void addImage(final Image theImage) {
//		if (!myImages.contains(theImage))
//			myImages.add(theImage);
//	}
	
//	public void addMultiImages(final Image[] theImages) {
//		for (int i = 0; i < theImages.length; i++) {
//			if(!myImages.contains(theImages[i]))
//				myImages.add(theImages[i]);
//		}
//	}
	
//	public void removeImage(final Image theImage) {
//		myImages.remove(theImage);
//	}
}
