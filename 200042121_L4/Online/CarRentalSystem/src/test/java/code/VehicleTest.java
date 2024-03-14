package code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {

    @Test
    void testRentCar() {
        Vehicle car = new Vehicle("C_123", 2022, "Toyota", "Camry", 0, new VehicleType(4));
        assertTrue(car.rent("customer123", new DateTime(2, 2, 2024), 5));
    }

    @Test
    void testRentVan() {
        Vehicle van = new Vehicle("V_456", 2022, "Ford", "Transit", 0, new VehicleType(8,new DateTime(2,2,2024)));
        assertTrue(van.rent("customer456", new DateTime(2, 2, 2024), 5));
    }

    @Test
    void testRentVanDueMaintenance() {
        Vehicle van = new Vehicle("V_456", 2022, "Ford", "Transit", 0, new VehicleType(8,new DateTime(2,1,2024)));
        assertFalse(van.rent("customer456", new DateTime(2, 2, 2024), 5));
    }

    @Test
    void testRentCarDueMaintenance() {
        Vehicle van = new Vehicle("C_123", 2022, "Toyota", "Camry", 0, new VehicleType(8,new DateTime(2,1,2024)));
        assertFalse(van.rent("customer456", new DateTime(2, 2, 2024), 5));
    }

    @Test
    void testRentInvalidDays() {
        Vehicle car = new Vehicle("C_123", 2022, "Toyota", "Camry", 0, new VehicleType(4));
        assertFalse(car.rent("customer123", new DateTime(2, 2, 2024), 0));
        assertFalse(car.rent("customer123", new DateTime(2, 2, 2024), 1));
        assertFalse(car.rent("customer123", new DateTime(2, 2, 2024), 15));
    }

    @Test
    void testRentUnavailable() {
        Vehicle car = new Vehicle("C_123", 2022, "Toyota", "Camry", 1, new VehicleType(4));
        assertFalse(car.rent("customer123", new DateTime(2, 2, 2024), 5));
    }

    @Test
    void testPerformMaintenance() {
        Vehicle vehicle = new Vehicle("C_123", 2022, "Toyota", "Camry", 0, new VehicleType(4));
        assertTrue(vehicle.performMaintenance());
    }

    @Test
    void testPerformMaintenanceInvalidStatus() {
        Vehicle vehicle = new Vehicle("C_123", 2022, "Toyota", "Camry", 1, new VehicleType(4));
        assertFalse(vehicle.performMaintenance());
    }

    @Test
    void testToString() {
        Vehicle vehicle = new Vehicle("C_123", 2022, "Toyota", "Camry", 0, new VehicleType(4));
        assertEquals(vehicle.toString(), "C_123:2022:Toyota:Camry:4:Available");
    }

    @Test
    void testGetDetails() {
        Vehicle vehicle = new Vehicle("C_123", 2022, "Toyota", "Camry", 0, new VehicleType(4));
        assertEquals(vehicle.getDetails(), "Vehicle ID:\tC_123\n Year:\t 2022\n Make:\tToyota\n Model:\tCamry\n Number of Seats:\t4\n Status:\tAvailable");
    }

    @Test
    void testGetLastElementIndex() {
        Vehicle vehicle = new Vehicle("C_123", 2022, "Toyota", "Camry", 0, new VehicleType(4));
        assertEquals(vehicle.getLastElementIndex(), -1);
    }
}
