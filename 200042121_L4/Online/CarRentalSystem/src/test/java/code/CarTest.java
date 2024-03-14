package code;


import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    @Test
    void testNoLateFee() {
        Car car = new Car("C_123", 2022, "Toyota", "Camry", 1, new VehicleType(4));
        assertEquals(car.getLateFee(new DateTime(2,2,2024),new DateTime(2,2,2024)),0.0);
    }

    @Test
    void testValidLateFee(){
        Car car = new Car("C_123", 2022, "Toyota", "Camry", 1, new VehicleType(4));
        DateTime start=new DateTime(2,2,2024);
        DateTime end=new DateTime(4,2,2024);
        assertEquals(car.getLateFee(end,start),DateTime.diffDays(end,start)*1.25*78);
    }

    @Test
    void testInvalidLateFee() {
        Car car = new Car("C_123", 2022, "Toyota", "Camry", 1, new VehicleType(4));
        DateTime start=new DateTime(2,2,2024);
        DateTime end=new DateTime(4,2,2024);
        assertEquals(car.getLateFee(start,end),0.00);
        assertThrows(Exception.class, () -> {
            car.getLateFee(start,end);
        });
    }

    @Test
    void testCompleteMaintenance() {
        Car car = new Car("C_123", 2022, "Toyota", "Camry", 2, new VehicleType(4));
        assertTrue(car.completeMaintenance());
    }

    @Test
    void testCompletedMaintenance() {
        Car car = new Car("C_123", 2022, "Toyota", "Camry", 0, new VehicleType(4));
        assertFalse(car.completeMaintenance());
    }

    @Test
    void testedRentedMaintenance() {
        Car car = new Car("C_123", 2022, "Toyota", "Camry", 1, new VehicleType(4));
        assertFalse(car.completeMaintenance());
    }

    @Test
    void testInvalidStatus() {
        Car car = new Car("C_123", 2022, "Toyota", "Camry", 3, new VehicleType(4));
        assertThrows(Exception.class, () -> {
            car.completeMaintenance();
        });
    }

    @Test
    void testGetDetailsNotRentedCar() {
        Car car = new Car("C_123", 2022, "Toyota", "Camry", 2, new VehicleType(4));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            car.returnVehicle(new DateTime(2,3,2024));
        });
    }
}
