package model;

import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author KyleD
 * @author CodyM
 */
//TODO serialize
public class DisplayCalendar extends GregorianCalendar{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;//this is the default?
	public static final int MAX_AUCTION = 25;
	public static final long ONE_DAY = 1000*60*60*24;
	public static final long ONE_HOUR = 1000*60*60;
	
	
	private List<Auction> myAuctions;
	
	public DisplayCalendar() {
		super(TimeZone.getDefault(),Locale.US);
		myAuctions = new ArrayList<>();
	}
	
	public DisplayCalendar(final List<Auction> theAuction) {
		this();
		myAuctions.addAll(theAuction);
		Collections.sort(myAuctions);
	}
	
	public boolean addAuction(final Auction theAuction) {
		if (!hasMaxAuctions() && checkAvailability(theAuction.getDate())) {
			myAuctions.add(theAuction);	
			Collections.sort(myAuctions);
			return true;
		}
		return false;
	}
	public List<Auction> getAllAuctions(){
		return myAuctions;
	}
	public List<Auction> getAvailableAuctions(){
		List<Auction> temp = new ArrayList<>();
		for (Auction myAuction : myAuctions) {
			if(myAuction.isAvailable())
				temp.add(myAuction);
        }
		return temp;
	}
	/**
	 * TODO check the business rules
	 * @param theDate
	 * @return
	 */
	public boolean checkAvailability(final Date theDate) {
		List<Auction> theAuctions = getAvailableAuctions();
		if(businessRule1(theDate,theAuctions))return false;
		if(businessRule2(theDate,theAuctions))return false;
		if(businessRule3(theDate,theAuctions))return false;
		if(businessRule4(theDate,theAuctions))return false;
		if(businessRule5(theDate,theAuctions))return false;
		return true;
	}
	//No more than 25 auctions may be scheduledinto the future.
	private boolean businessRule1(final Date theDate,final List<Auction> theAuctiosn){
		return hasMaxAuctions();
	}
	//An auction may not be scheduled more than 90 days from the current date
	private boolean businessRule2(final Date theDate,final List<Auction> theAuctions){
		if (theDate.getTime() > (getTime().getTime()+(ONE_DAY * 90))){
			return true;
		}
		return false;
	}
	//No more than 5 auctions may be scheduled for any rolling 7 day period.
	private boolean businessRule3(final Date theDate,final List<Auction> theAuctions){
		int count = 0;
		for (Auction myAuction : theAuctions) {
			if(myAuction.getDate().getTime() >= theDate.getTime() - ONE_DAY * 7 
					|| myAuction.getDate().getTime() >= theDate.getTime() + ONE_DAY * 7 ){
				count++;
			}
        }
		if (count >= 5){
			return true;
		}
		return false;
	}
	//No more than 2 auctions can be scheduled on the same day, and the start time of the second can be 
	//no earlier than 2 hours after the end time of the first.
	private boolean businessRule4(final Date theDate,final List<Auction> theAuctions){
		int count = 0;
		for (Auction myAuction : theAuctions) {
			if(myAuction.getDate().getTime() >= theDate.getTime() - ONE_HOUR * 2 
					|| myAuction.getDate().getTime() >= theDate.getTime() + ONE_HOUR * 2 ){
				return true;
			}
        }
		for (Auction myAuction : theAuctions) {
			if(myAuction.getDate().getTime() / ONE_DAY == theDate.getTime() / ONE_DAY){
				count++;
			}
        }
		if(count>=2){
			return true;
		}
		return false;
	}
	//No more than one auction per year per Non-profit organization can be scheduled
	private boolean businessRule5(final Date theDate,final List<Auction> theAuctions){
		//TODO this whole method
		return false;
	}
	private int checkAvailableAuctions(){
		int i = 0;
		for (Auction myAuction : myAuctions) {
			if(myAuction.isAvailable())
				i++;
        }
		return i;
	}
	public boolean hasMaxAuctions() {
		return checkAvailableAuctions() == MAX_AUCTION;
	}
	//used for simple tests
	public String toString(){
		String temp = "A = Ready For Bids          F = Full\n";
//		temp = temp + "_____________________________________\n";
//		temp = temp + "************************************\n";

		temp = temp + "|SUN |MON |TUE |WED |THUR|FRI |SAT |\n";
		int dow = this.get(DAY_OF_WEEK);
		int dom = this.get(DAY_OF_MONTH);
		int i = (dow-dom) % 7 + 1;//this should find the first day of the week of the current month but I might be wrong
//		int i = gc.get(DAY_OF_WEEK_IN_MONTH);//I think this finds day of week
//		System.err.print(i);
		int tempdom = 1;
		//TODO make this different for each month
		while(tempdom <= this.daysInMonth(this.get(MONTH))){
			int count = 0;
			temp = temp + "|";
			for(int o = 1;o < i; o++){
				temp = temp + "////|";
			}
			for(; i <= 7; i++){
				//Im assuming this for loop the if statement isnt working
				for (Auction myAuction : this.myAuctions) {
					if((myAuction.getDate().getTime() / ONE_DAY) 
							== (this.getTime().getTime() - (ONE_DAY * (dom - tempdom))) / ONE_DAY)
						count++;
		        }
				temp = temp + count;//TODO make this 0-1-2 depending how many auction are in that day;
				count = 0;
				long theDate = this.getTime().getTime() - (ONE_DAY * (dom - tempdom));
				if (this.checkAvailability(new Date(theDate))
						|| this.checkAvailability(new Date(theDate + (4 * ONE_HOUR + 1)))){
					temp = temp + "A";
				}
				else
					temp = temp + "F";
				if(tempdom < 10)
					temp = temp + " ";
				temp = temp + tempdom;
				temp = temp + "|";
				tempdom++;
				if (tempdom > 31){
					i++;
					break;
				}
			}
			for(;i <= 7; i++){
				temp = temp + "////|";
			}
			temp = temp + "\n";
			i = 1;
		}
//		temp = temp + "_____________________________________\n";
//		temp = temp + "************************************\n";
		return temp;
		
	}

	private int daysInMonth(int month){
		switch (month){
			case 2 :return checkLeap();
			case 4 :
			case 6 :
			case 9 :
			case 11:return 30;
			case 1 :
			case 3 :
			case 5 :
			case 7 :
			case 8 :
			case 10:
			case 12:return 31;
			default: return -1;
		}
	}
	private int checkLeap(){
		if (this.isLeapYear(get(YEAR)))
			return 29;
		return 28;
	}
	public static void main(String[]args){
		DisplayCalendar dc= new DisplayCalendar();
		System.out.print(dc.toString());
	}
}
