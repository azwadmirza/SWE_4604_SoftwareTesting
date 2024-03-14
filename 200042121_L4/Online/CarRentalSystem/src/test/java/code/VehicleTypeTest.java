package code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VehicleTypeTest {

    @Test
    void testGetSeatsCar() {
        VehicleType carType = new VehicleType(4);
        assertEquals(carType.getSeats("car"), 4);
    }

    @Test
    void testGetSeatsVan() {
        VehicleType vanType = new VehicleType(7,new DateTime(2,3,2024));
        assertEquals(vanType.getSeats("van"), 7);
    }

    @Test
    void testSetCarSeats() {
        VehicleType carType = new VehicleType(4);
        carType.setCarSeats(5);
        assertEquals(carType.getCarSeats(), 5);
    }

    @Test
    void testGetLastMaintenance() {
        DateTime lastMaintenance = new DateTime(2023, 1, 1);
        VehicleType vanType = new VehicleType(8, lastMaintenance);
        assertEquals(vanType.getLastMaintenance(), lastMaintenance);
    }

    @Test
    void testSetLastMaintenance() {
        DateTime lastMaintenance = new DateTime(2023, 1, 1);
        VehicleType vanType = new VehicleType(8);
        vanType.setLastMaintenance(lastMaintenance);
        assertEquals(vanType.getLastMaintenance(), lastMaintenance);
    }

    @Test
    void testCanBeRentedForMinimumDaysCarWeekday() {
        VehicleType carType = new VehicleType(4);
        DateTime rentDate = new DateTime(8,11,2024);
        assertEquals(carType.canBeRentedForMinimumDays(rentDate, "car"), 3);
    }

    @Test
    void testCanBeRentedForMinimumDaysCarWeekend() {
        VehicleType carType = new VehicleType(4);
        DateTime rentDate = new DateTime(3, 9, 2024);
        assertEquals(carType.canBeRentedForMinimumDays(rentDate, "car"), 2);
    }

    @Test
    void testCanBeRentedForMinimumDaysVan() {
        VehicleType vanType = new VehicleType(8);
        DateTime rentDate = new DateTime(3,11,2024); // Friday
        assertEquals(vanType.canBeRentedForMinimumDays(rentDate, "van"), 1);
    }

    @Test
    void testCanBeRentedForMinimumDaysCar() {
        VehicleType vanType = new VehicleType(8);
        DateTime rentDate = new DateTime(3,11,2024); // Friday
        assertEquals(vanType.canBeRentedForMinimumDays(rentDate, "car"), 2);
    }

    @Test
    void testIsUnderMaintenanceVan() {
        DateTime lastMaintenance = new DateTime(2023, 1, 1);
        VehicleType vanType = new VehicleType(8, lastMaintenance);
        DateTime rentDate = new DateTime(2024, 1, 1);
        assertFalse(vanType.IsUnderMaintenance(rentDate, "van", 5));
    }

    @Test
    void testIsUnderMaintenanceCar() {
        VehicleType carType = new VehicleType(4,new DateTime(2,3,2024));
        DateTime rentDate = new DateTime(2024, 1, 1);
        assertFalse(carType.IsUnderMaintenance(rentDate, "car", 5));
    }

    @Test
    void testIsUnderMaintenanceVanWithinMaintenancePeriod() {
        DateTime lastMaintenance = new DateTime(3,2,2023);
        VehicleType vehicleType = new VehicleType(7, lastMaintenance);
        DateTime rentDate = new DateTime(4,2,2023);
        boolean result = vehicleType.IsUnderMaintenance(rentDate, "van", 10);
        assertFalse(result);
    }

    @Test
    void testIsUnderMaintenanceVanOutsideMaintenancePeriod() {
        DateTime lastMaintenance = new DateTime(3,2,2023);
        VehicleType vehicleType = new VehicleType(7, lastMaintenance);
        DateTime rentDate = new DateTime(4,10,2023);
        boolean result = vehicleType.IsUnderMaintenance(rentDate, "van", 10);
        assertTrue(result);
    }
}
