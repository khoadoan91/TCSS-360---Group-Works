package refactored;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author KyleD
 */
public class CalendarUI {
	
	private DisplayCalendar myCalendar;
	
	public CalendarUI(final DisplayCalendar theCalendar) {
		myCalendar = theCalendar;
	}
	
	public DisplayCalendar getDispCalendar() {
		return myCalendar;
	}
	
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
		result += " " + calShow.get(Calendar.YEAR) + "\t\t X = Filled\n";
		result += "  Sun   Mon   Tue   Wed   Thu   Fri   Sat  \n";
		result += "===========================================\n";
		int firstDay = calShow.get(Calendar.DAY_OF_WEEK);
		int i;
		if (firstDay != 1) {
			i = firstDay - (firstDay - 1)*2;
		} else {
			i = 1;
		}
		while (i <= calShow.getActualMaximum(Calendar.DAY_OF_MONTH)) {
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
						result += "X ";
					} else result += "  ";
				}
				result += "|\n";
			}
			result += "===========================================\n";

		}
		return result;
	}

	private List<Integer> viewAuctionsInMonth(final int month) {
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < myCalendar.getUpcomingAuctions().size(); i++) {
			if (myCalendar.getUpcomingAuctions().get(i).getMonth() == month) {
				result.add(myCalendar.getUpcomingAuctions().get(i).getDateAuctionStarts().get(Calendar.DAY_OF_MONTH));
			}
		}
		return result;
	}
}
