package code;

import code.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DateTimeTest {

    @Test
    void testDateTimeConstructorAndGetters() {
        DateTime dateTime = new DateTime(10, 3, 2024);
        assertEquals("10/03/2024", dateTime.getFormattedDate());
        assertEquals("10032024", dateTime.getEightDigitDate());
    }

    @Test
    void testDiffDays() {
        DateTime startDate = new DateTime(1, 3, 2024);
        DateTime endDate = new DateTime(10, 3, 2024);

        assertEquals(9, DateTime.diffDays(endDate, startDate));
    }

    @Test
    void testGetNameOfDay() {
        DateTime dateTime = new DateTime(8, 3, 2024);
        assertEquals("Friday", dateTime.getNameOfDay());
    }

    @Test
    void testGetEightDigitDate() {
        DateTime dateTime = new DateTime(1, 3, 2024);
        assertEquals(dateTime.getEightDigitDate(),"01032024");
    }

    @Test
    void testGetFormattedDate() {
        DateTime dateTime = new DateTime(1, 3, 2024);
        assertEquals(dateTime.getFormattedDate(),"01/03/2024");
    }

    @Test
    void getTime(){
        assertEquals(System.currentTimeMillis(),new DateTime().getTime());
    }

    @Test
    void testAdvanceTimeConstructor() {
        int clockForwardDays=3;
        long expectedTime = System.currentTimeMillis()+((clockForwardDays * 24L) * 60L) * 60000L;
        assertEquals(expectedTime, new DateTime(clockForwardDays).getTime());
    }

    @Test
    void testToString(){
        DateTime dateTime = new DateTime(1, 3, 2024);
        assertEquals(dateTime.toString(),"01/03/2024");
    }
    //test fails as set Advance doesn't actually advance the date as outlined, it only sets the advance value, but it does not modify the time
    @Test
    void testSetAdvance(){
        DateTime dateTime = new DateTime(1, 3, 2024);
        dateTime.setAdvance(3,24,24*60);
        assertEquals(dateTime.getFormattedDate(),"07/03/2024");
    }

    @Test
    void testAbnormalDateFormattingForDayPositive() {
        assertThrows(Exception.class, () -> {
            DateTime dateTime = new DateTime(32, 3, 2024);
        });
    }

    @Test
    void testAbnormalDateFormattingForDayNegative() {
        assertThrows(Exception.class, () -> {
            DateTime dateTime = new DateTime(-1, 3, 2024);
        });
    }

    @Test
    void testAbnormalDateFormattingForDayZero() {
        assertThrows(Exception.class, () -> {
            DateTime dateTime = new DateTime(0, 3, 2024);
        });
    }

    @Test
    void testAbnormalDateFormattingForMonthPositive() {
        assertThrows(Exception.class, () -> {
            DateTime dateTime = new DateTime(1, 13, 2024);
        });
    }

    @Test
    void testAbnormalDateFormattingForMonthNegative() {
        assertThrows(Exception.class, () -> {
            DateTime dateTime = new DateTime(1, -1, 2024);
        });
    }

    @Test
    void testAbnormalDateFormattingForMonthZero() {
        assertThrows(Exception.class, () -> {
            DateTime dateTime = new DateTime(1, 0, 2024);
        });
    }

    @Test
    void testAbnormalDateFormattingForYearPositive() {
        assertThrows(Exception.class, () -> {
            DateTime dateTime = new DateTime(32, 3, 10000);
        });
    }

    @Test
    void testAbnormalDateFormattingForYearNegative() {
        assertThrows(Exception.class, () -> {
            DateTime dateTime = new DateTime(-1, 3, -1);
        });
    }

    @Test
    void testAbnormalDateFormattingForYearZero() {
        assertThrows(Exception.class, () -> {
            DateTime dateTime = new DateTime(0, 3, 0);
        });
    }

}
