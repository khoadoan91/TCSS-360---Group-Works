package refactored;

import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * @author KyleD
 * @author CodyM
 */
public class DisplayCalendar {

	/**
	 *
	 */
	public static final int ONE_YEAR = 365;
	public static final int MAX_AUCTION = 25;
	public static final int NINETY_DAY_FROM_NOW = 90;
	public static final int ONE_HOUR = 60;
//	public static final long ONE_DAY = 1000 * 60 * 60 * 24;
//	public static final long ONE_HOUR = 1000 * 60 * 60;

//	private List<Auction> myAuctions;
	private List<Auction> myPastAuctions;
	private List<Auction> myUpcomingAuctions;

	public DisplayCalendar(final List<Auction> theAuction) {
		myPastAuctions = new LinkedList<>();
		myUpcomingAuctions = new LinkedList<>(theAuction);
		Calendar now = Calendar.getInstance();
		for (int i = 0; i < myUpcomingAuctions.size(); i++) {
			if (myUpcomingAuctions.get(i).getDateAuctionStarts().compareTo(now) < 0) {
				myPastAuctions.add(myUpcomingAuctions.get(i));
			}
		}
		myUpcomingAuctions.removeAll(myPastAuctions);
		Collections.sort(myUpcomingAuctions);
		Collections.sort(myPastAuctions);
//		myAuctions.addAll(theAuction);
//		Collections.sort(myAuctions);
		assert myUpcomingAuctions != null;
	}

	/**
	 * First business rule:
	 * No more than 25 auctions may be scheduled into the future.
	 * False is good -> we can add more auctions.
	 * @return true iff calendar already has exceeded Auction;
	 */
	public boolean hasExceededAuction() {
		return myUpcomingAuctions.size() >= MAX_AUCTION;
//		return checkAvailableAuctions() == MAX_AUCTION;
	}

	/**
	 * Second business rule:
	 * An auction may not be scheduled more than 90 days from the current date.
	 * False is good -> we can add the auction into the calendar.
	 * @param theAuc the new Auction
	 * @return
	 */
	public boolean hasAuctionOver90Days(final Auction theAuc) {
		Calendar ninetyFromNow = Calendar.getInstance();
		ninetyFromNow.set(Calendar.DAY_OF_YEAR, 
				ninetyFromNow.get(Calendar.DAY_OF_YEAR) + NINETY_DAY_FROM_NOW);
		if (theAuc.getDateAuctionStarts().compareTo(ninetyFromNow) > 0)
			return true;
		return false;
	}

	/**
	 * Third business rule:
	 * No more than 5 auctions may be scheduled for any rolling 7 day period.
	 * @param theAuc the new Auction
	 * @return
	 */
	public boolean hasMore5AuctionsIn7Days(final Auction theAuc) {
		int expectedDay = theAuc.getDayOfYear();
		List<Auction> neighborAuction = new LinkedList<>();
		for (Auction auc : myUpcomingAuctions) {
			if (auc.getDayOfYear() - expectedDay < 7
					|| expectedDay - auc.getDayOfYear() < 7) {
				neighborAuction.add(auc);
			}
		}
		int count = 0;
		Collections.sort(neighborAuction);
		for (int i = expectedDay - 6; i <= expectedDay + 6; i++) {
			for (int j = i; j <= i + 6; j++) {
				for (Auction auc : neighborAuction) {
					if (auc.getDayOfYear() == j) {
						count++;
					}
				}
			}
			if (count >= 5) return true;
			else count = 0;
		}
		return false;
	}
	
	/**
	 * Fourth business rule: part a
	 * No more than 2 auctions can be scheduled on the same day, and the start time of the second
	 * can be no earlier than 2 hours after the end time of the first.
	 * @param theAuc the new Auction
	 * @return -1 if there are 2 Auctions in same day, -2 if the new Auction is not in
	 * any other Auctions day.
	 */
	public int has2AuctionsInSameDay(final Auction theAuc) {
		int count = 0, indexAuction = -2;
		for (int i = 0; i < myUpcomingAuctions.size(); i++) {
			if (myUpcomingAuctions.get(i).getYear() == theAuc.getYear()
					&& myUpcomingAuctions.get(i).getDayOfYear() == theAuc.getDayOfYear()) {

				count++;
				indexAuction = i;
			}
			if (count == 2) break;  // no need to check the rest.
		}
		if (count == 2) return -1;
		return indexAuction;
	}

	/**
	 * Fourth business rule: part b
	 * No more than 2 auctions can be scheduled on the same day, and the start time of the second
	 * can be no earlier than 2 hours after the end time of the first.
	 * False is good. We allow to add the new Auction.
	 * @param theAuc
	 * @return
	 */
	public boolean has2HoursBetween2Auctions(final Auction theAuc) {
		int indexAuc = has2AuctionsInSameDay(theAuc);
		if (indexAuc == -1) { // there are 2 auctions at the same day.
			return true;

		} else if (indexAuc == -2) return false; 
		Auction oldAuction = myUpcomingAuctions.get(indexAuc);
		// the old auction starts first.
		if (theAuc.getStartHour() > oldAuction.getStartHour()) {		
			if ((theAuc.getStartHour() * ONE_HOUR + theAuc.getStartMin()) - 
				(oldAuction.getDateAuctionEnds().get(Calendar.HOUR_OF_DAY) * ONE_HOUR + oldAuction.getDateAuctionEnds().get(Calendar.MINUTE)) 
				> ONE_HOUR * 2) {
				return false;
			} else return true;
		} else { // the new auction start first.
			if ((oldAuction.getStartHour() * ONE_HOUR + oldAuction.getStartMin()) - 
				(theAuc.getDateAuctionEnds().get(Calendar.HOUR_OF_DAY) * ONE_HOUR + theAuc.getDateAuctionEnds().get(Calendar.MINUTE)) 
				> ONE_HOUR * 2) {
				return false;
			} else return true;
		}
	}

	/**
	 * Fifth business rule: No more than one auction per year per
	 * Non-profit organization can be scheduled.
	 * True is good -> it means that the NPE has one auction per year.
	 * @param theAuction
	 * @return
	 */
	public boolean hasAuctionPerNPperYear(final Auction theAuction) {
		for (int i = 0; i < myPastAuctions.size(); i++) {
			if (myPastAuctions.get(i).getOrganizationName().equals(theAuction.getOrganizationName())) {
				if (theAuction.getDayOfYear() - myPastAuctions.get(i).getDayOfYear() >= ONE_YEAR) {
					return true;
				} else {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * NPE is not allowed to add an auction to the pass. If the auction is in the past, return true.
	 * False is good -> the new auction is not in the past.
	 * @param theAuction
	 * @return
	 */
	private boolean isInPast(final Auction theAuction) {
		Calendar now = Calendar.getInstance();
		if (theAuction.getDayOfYear() <= now.get(Calendar.DAY_OF_YEAR)) return true;
		return false;
	}

	public int addAuction(final Auction theAuction) {
		int bsrule = checkBusinessRules(theAuction);
		if (bsrule == 0){
			myUpcomingAuctions.add(theAuction);
			Collections.sort(myUpcomingAuctions);
		}
		return bsrule;
	}

	public void removeAuction(final Auction theAuction) {
		myUpcomingAuctions.remove(theAuction);
	}
	
	public int checkBusinessRules(final Auction theAuction) {
		if (hasExceededAuction()) return 1;
		if (hasAuctionOver90Days(theAuction)) return 2;
		if (hasMore5AuctionsIn7Days(theAuction)) return 3;
		if (has2HoursBetween2Auctions(theAuction)) return 4;
		if (!hasAuctionPerNPperYear(theAuction)) return 5;
		if (isInPast(theAuction)) return 2;
		return 0;
	}

	public List<Auction> getUpcomingAuctions() {
		return myUpcomingAuctions;
	}
	public List<Auction> getPastAuctions() {
		return myPastAuctions;
	}

	/**
	 * 
	 *
	 * @param theDate
	 * @return
	 */

	@Override
	public String toString() {
		return displayCalendar(Calendar.getInstance());
	}

	public String displayCalendar(final Calendar time) {
		Calendar calShow = (Calendar) time.clone();
		// aucMonth is already sorted.
		List<Integer> aucDates = viewAuctionsInMonth(calShow.get(Calendar.MONTH));
		calShow.set(Calendar.DAY_OF_MONTH, 1);
		String result = new SimpleDateFormat("MMMMMMMMM").format(calShow.getTime()).toUpperCase();
		result += " " + calShow.get(Calendar.YEAR) + "\t\t F = Filled\n";
		result += "  Sun   Mon   Tue   Wed   Thu   Fri   Sat  \n";
		result += "===========================================\n";
		int firstDay = calShow.get(Calendar.DAY_OF_WEEK);
		int i;
		if (firstDay != 1) {
			i = firstDay - (firstDay - 1)*2;
		} else {
			i = 1;
		}
		while (i < calShow.getActualMaximum(Calendar.DAY_OF_MONTH)) {
			for (int j = 0; j < 7; j++) {
				result += "|  ";
				if (i > 0 && calShow.getActualMaximum(Calendar.DAY_OF_MONTH) >= i) {
					if (i < 10) {
						result += " " + i++ + " ";
					} else {
						result += i++ + " ";
					}
				} else {
					result += "   ";
					i++;
				}
			}
			result += "|\n";
			// Use for loop to fill out the Auction in a calendar.
			for (int k = 0; k < 2; k++) {
				for (int j = i - 7; j < i; j++) {
					result += "|   ";
					if (aucDates.contains(j)) {
						aucDates.remove(aucDates.indexOf(j));
						result += "F ";
					} else result += "= ";
				}
				result += "|\n";
			}
			result += "===========================================\n";

		}
		return result;
	}

	private List<Integer> viewAuctionsInMonth(final int month) {
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < myUpcomingAuctions.size(); i++) {
			if (myUpcomingAuctions.get(i).getMonth() == month) {
				result.add(myUpcomingAuctions.get(i).getDateAuctionStarts().get(Calendar.DAY_OF_MONTH));
			}
		}
		return result;
	}

}
