package code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RentalRecordTest {

    @Test
    void testGetEstimatedReturnDate() {
        DateTime rentDate = new DateTime(3,12,2024);
        DateTime estimatedReturnDate = new DateTime(17,3,2024);
        RentalRecord rentalRecord = new RentalRecord("R123", rentDate, estimatedReturnDate);
        assertEquals(rentalRecord.getEstimatedReturnDate(), estimatedReturnDate);
    }

    @Test
    void testGetRentDate() {
        DateTime rentDate = new DateTime(3,12,2024);
        DateTime estimatedReturnDate = new DateTime(17,3,2024);
        RentalRecord rentalRecord = new RentalRecord("R123", rentDate, estimatedReturnDate);
        assertEquals(rentalRecord.getRentDate(), rentDate);
    }

    @Test
    void testToStringWithoutData() {
        DateTime rentDate = new DateTime(3,12,2024);
        DateTime estimatedReturnDate = new DateTime(17,3,2024);
        RentalRecord rentalRecord = new RentalRecord("R123", rentDate, estimatedReturnDate);
        assertEquals(rentalRecord.toString(), "R123:03/12/2024:17/03/2024:none:none:none");
    }

    @Test
    void testToStringWithData() {
        DateTime rentDate = new DateTime(3,12,2024);
        DateTime estimatedReturnDate = new DateTime(17,3,2024);
        DateTime actualReturnDate = new DateTime(2024, 3, 15);
        double rentalFee = 100.0;
        double lateFee = 20.0;
        RentalRecord rentalRecord = new RentalRecord("R123", rentDate, estimatedReturnDate);
        rentalRecord.setData(actualReturnDate, rentalFee, lateFee);
        assertEquals(rentalRecord.toString(), "R123:03/12/2024:17/03/2024:13/09/0020:100.0:20.0");
    }

    @Test
    void testGetDetailsWithoutData() {
        DateTime rentDate = new DateTime(3,12,2024);
        DateTime estimatedReturnDate = new DateTime(17,3,2024);
        RentalRecord rentalRecord = new RentalRecord("R123", rentDate, estimatedReturnDate);
        assertEquals(rentalRecord.getDetails(), "Record ID:             R123\n" +
                "Rent Date:             03/12/2024\n" +
                "Estimated Return Date: 17/03/2024");
    }

    @Test
    void testGetDetailsWithData() {
        DateTime rentDate = new DateTime(3,12,2024);
        DateTime estimatedReturnDate = new DateTime(17,3,2024);
        DateTime actualReturnDate = new DateTime(2024, 3, 15);
        double rentalFee = 100.0;
        double lateFee = 20.0;
        RentalRecord rentalRecord = new RentalRecord("R123", rentDate, estimatedReturnDate);
        rentalRecord.setData(actualReturnDate, rentalFee, lateFee);
        assertEquals(rentalRecord.getDetails(), "Record ID:             R123\n" +
                "Rent Date:             03/12/2024\n" +
                "Estimated Return Date: 17/03/2024\n" +
                "Actual Return Date:    13/09/0020\n" +
                "Rental Fee:            100.00\n" +
                "Late Fee:              20.00");
    }
}
