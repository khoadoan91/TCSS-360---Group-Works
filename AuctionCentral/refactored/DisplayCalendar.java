package refactored;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author KyleD
 * @author CodyM
 */
public class DisplayCalendar implements Serializable {

	/**
	 *
	 */
	public static final int ONE_YEAR = 365;
	public static final int MAX_AUCTION = 25;
	public static final int NINETY_DAY_FROM_NOW = 90;
	public static final int ONE_HOUR = 60;
	
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

	public void addAuction(final Auction theAuction) throws ExceedAuctionLimit, 
								Exceed90Days, Exceed5AuctionsIn7Days, 
								ExceedAuctionLimitPerDay, ExceedOneAuctionPerYear{
		checkBusinessRules(theAuction);	
		myUpcomingAuctions.add(theAuction);
		Collections.sort(myUpcomingAuctions);
	}

	public void removeAuction(final Auction theAuction) {
		myUpcomingAuctions.remove(theAuction);
	}
	
	/**
	 * @return 0 is good, valid
	 */
	public void checkBusinessRules(final Auction theAuction) throws ExceedAuctionLimit, 
										Exceed90Days, Exceed5AuctionsIn7Days, 
										ExceedAuctionLimitPerDay, ExceedOneAuctionPerYear{
		if (hasExceededAuction()) throw new ExceedAuctionLimit();
		if (hasAuctionOver90Days(theAuction)) throw new Exceed90Days();
		if (hasMore5AuctionsIn7Days(theAuction)) throw new Exceed5AuctionsIn7Days();
		if (has2HoursBetween2Auctions(theAuction)) throw new ExceedAuctionLimitPerDay();
		if (!hasAuctionPerNPperYear(theAuction)) throw new ExceedOneAuctionPerYear();
		if (isInPast(theAuction)) throw new Exceed90Days();
	}

	public List<Auction> getUpcomingAuctions() {
		return new LinkedList<>(myUpcomingAuctions);
	}
	public List<Auction> getPastAuctions() {
		return new LinkedList<>(myPastAuctions);
	}
	
	public class ExceedAuctionLimit extends Exception {
		public ExceedAuctionLimit() {
			super("Exceed 25 auctions in the future.");
		}
	}
	
	public class Exceed90Days extends Exception {
		public Exceed90Days() {
			super("Exceed 90 days from the current date.");
		}
	}
	
	public class Exceed5AuctionsIn7Days extends Exception {
		public Exceed5AuctionsIn7Days() {
			super("Exceed 5 auctions in 7 days");
		}
	}
	
	public class ExceedAuctionLimitPerDay extends Exception {
		public ExceedAuctionLimitPerDay() {
			super("No more than 2 autions can be scheduled on the same day, "
					+ "and the start time of the second can be no earlier "
					+ "than 2 hours after the end time of the first");
		}
	}
	
	public class ExceedOneAuctionPerYear extends Exception {
		public ExceedOneAuctionPerYear() {
			super("No more than one auction per year per Non-profit organization can be"
					+ "scheduled.");
		}
	}
	
}
