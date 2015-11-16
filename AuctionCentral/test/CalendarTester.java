package test;


import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Calendar;


public class DisplayCalendarTest {

   DisplayCalendar myDC;
   Calendar day;
   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
      myDC = new DisplayCalendar();
      day = Calendar.getInstance();
   }
   //test max auction
   @Test public void testMaxAuctions() {
      assertFalse(myDC.hasExceededAuction());
      for(int i = 0; i < 25; i++){
         day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 1 + (i % 2));
         myDC.addAuction(Auction.makeTestAuction(day));
      }
      assertTrue(myDC.hasExceededAuction());
   }
   
   @Test public void is90DaysAway() {
      
      day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 1);
      assertFalse(myDC.hasMoreThan90Days(Auction.makeTestAuction(day)));
      day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 90);
      assertTrue(myDC.hasMoreThan90Days(Auction.makeTestAuction(day)));
   }
   
   // hasMore5AuctionsIn7Days(final Auction theAuc)
   @Test public void hasMore5AuctionsIn7DaysTEST(){
      day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 1);
      assertFalse(myDC.hasExceededAuction(Auction.makeTestAuction(day)));
      for(int i = 0; i < 5; i++){
         day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 1 + (i % 2));
         myDC.addAuction(Auction.makeTestAuction(day));
      }
      assertTrue(myDC.hasMore5AuctionsIn7Days(day));
      }
   }
   
   // cant have 3 auctions in the same day
   @Test public void only2auctionsindayTEST(){
   //has2AuctionsInSameDay(final Auction theAuc){
      day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 1);
      day.set(Calendar.HOUR_OF_DAY,0);
      assertFalse(myDC.has2AuctionsInSameDay(Auction.makeTestAuction(day)));
      myDC.addAuction(Auction.makeTestAuction(day));
      day.set(Calendar.HOUR_OF_DAY,day.get(Calendar.HOUR_OF_DAY) + 5);
      assertFalse(myDC.has2AuctionsInSameDay(Auction.makeTestAuction(day)));
      myDC.addAuction(Auction.makeTestAuction(day));
      day.set(Calendar.HOUR_OF_DAY,day.get(Calendar.HOUR_OF_DAY) + 5);
      assertTrue(myDC.has2AuctionsInSameDay(Auction.makeTestAuction(day)));
   }
   
   @Test public void noAuctionEvery2Hours(){
   //has2HoursBetween2Auctions(final Auction theAuc)
      day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 1);
      day.set(HOUR_OF_DAY,9);
      assertFalse(DC.has2HoursBetween2Auctions(Auction.makeTestAuction(day)));
      myDC.addAuction(Auction.makeTestAuction(day));
      day.set(Calendar.HOUR_OF_DAY,day.get(Calendar.HOUR_OF_DAY) + 1);
      assertTrue(myDC.has2HoursBetween2Auctions(Auction.makeTestAuction(day)));
      day.set(Calendar.HOUR_OF_DAY,day.get(Calendar.HOUR_OF_DAY) - 2);
      assertTrue(myDC.has2HoursBetween2Auctions(Auction.makeTestAuction(day)));
      day.set(Calendar.HOUR_OF_DAY,day.get(Calendar.HOUR_OF_DAY) - 4);
      assertFalse(myDC.has2HoursBetween2Auctions(Auction.makeTestAuction(day)));
      day.set(Calendar.HOUR_OF_DAY,day.get(Calendar.HOUR_OF_DAY) + 14);
      assertFalse(myDC.has2HoursBetween2Auctions(Auction.makeTestAuction(day)));
   }
   
   @Test public void noAuctionOnSameYearTEST(){
      //public boolean hasAuctionPerNPperYear(final Auction theAuction) {
      day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 1);
      Auction TESTAuction = new Auction("THEORGOFTESTING", null, day, "03-01");
      assertFalse(hasAuctionPerNPperYear(TESTAuction));
      DC.addAuction(TESTAuction));
      day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 1);
      TESTAuction = new Auction("THEORGOFTESTING", null, day, "03-01");
      assertTrue(hasAuctionPerNPperYear(TESTAuction));
   }

   /** A test that always fails. **/
   @Test public void defaultTest() {//this was auto created
      Assert.assertEquals("Default test added by jGRASP. Delete "
            + "this test once you have added your own.", 0, 1);
   }
}
