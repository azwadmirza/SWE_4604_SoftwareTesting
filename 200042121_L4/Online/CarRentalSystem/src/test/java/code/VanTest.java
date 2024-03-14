package code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VanTest {

    @Test
    void testNoLateFee() {
        Van van = new Van("V_123", 2022, "Ford", "Transit", 1, new VehicleType(7));
        assertEquals(van.getLateFee(new DateTime(2, 2, 2024), new DateTime(2, 2, 2024)), 0.0);
    }

    @Test
    void testValidLateFee() {
        Van van = new Van("V_123", 2022, "Ford", "Transit", 1, new VehicleType(7));
        DateTime start = new DateTime(2, 2, 2024);
        DateTime end = new DateTime(4, 2, 2024);
        assertEquals(van.getLateFee(end, start), DateTime.diffDays(end, start) * 299);
    }

    @Test
    void testInvalidLateFee() {
        Van van = new Van("V_123", 2022, "Ford", "Transit", 1, new VehicleType(7));
        DateTime start = new DateTime(2, 2, 2024);
        DateTime end = new DateTime(4, 2, 2024);
        assertEquals(van.getLateFee(start, end), 0.0);
        assertThrows(Exception.class, () -> {
            van.getLateFee(start, end);
        });
    }

    @Test
    void testCompleteMaintenance() {
        Van van = new Van("V_123", 2022, "Ford", "Transit", 2, new VehicleType(7,new DateTime(2,3,2024)));
        assertTrue(van.completeMaintenance(new DateTime(2, 3, 2024)));
    }

    @Test
    void testCompletedMaintenance() {
        Van van = new Van("V_123", 2022, "Ford", "Transit", 0, new VehicleType(7,new DateTime(2,3,2024)));
        assertFalse(van.completeMaintenance(new DateTime(2, 3, 2024)));
    }

    @Test
    void testedRentedMaintenance() {
        Van van = new Van("V_123", 2022, "Ford", "Transit", 1, new VehicleType(7,new DateTime(2,3,2024)));
        assertFalse(van.completeMaintenance(new DateTime(2, 3, 2024)));
    }

    @Test
    void testCompleteMaintenanceLastMaintenanceAbove12() {
        Van van = new Van("V_123", 2022, "Ford", "Transit", 2, new VehicleType(7,new DateTime(2,2,2024)));
        assertTrue(van.completeMaintenance(new DateTime(2, 3, 2024)));
    }

    @Test
    void testCompletedMaintenanceLastMaintenanceAbove12() {
        Van van = new Van("V_123", 2022, "Ford", "Transit", 0, new VehicleType(7,new DateTime(2,2,2024)));
        assertTrue(van.completeMaintenance(new DateTime(2, 3, 2024)));
    }

    @Test
    void testedRentedMaintenanceLastMaintenanceAbove12() {
        Van van = new Van("V_123", 2022, "Ford", "Transit", 1, new VehicleType(7,new DateTime(2,2,2024)));
        assertTrue(van.completeMaintenance(new DateTime(2, 3, 2024)));
    }

    @Test
    void testInvalidStatus() {
        Van van = new Van("V_123", 2022, "Ford", "Transit", 3, new VehicleType(7,new DateTime(2,3,2024)));
        assertThrows(Exception.class, () -> {
            van.completeMaintenance(new DateTime(2, 3, 2024));
        });
    }

    @Test
    void testGetDetailsNotRentedVan() {
        Van van = new Van("V_123", 2022, "Ford", "Transit", 2, new VehicleType(7));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            van.returnVehicle(new DateTime(2, 3, 2024));
        });
    }
}
