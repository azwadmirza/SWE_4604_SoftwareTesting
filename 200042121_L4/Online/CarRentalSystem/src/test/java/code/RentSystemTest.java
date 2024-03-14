package code;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Collections;
import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;


public class RentSystemTest {
    private RentSystem rentSystem;
    @BeforeEach
    public void setUp() {
        rentSystem = new RentSystem();
    }
    @Order(1)
    @Test
    public void testExit() {
        InputStream stdin = System.in;
        String input = "7\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Start.main(new String[]{});
        System.setIn(stdin);
        System.setOut(System.out);
        String output = outputStream.toString().trim().replace("\r","");
        String expectedOutput =
                "**** RENT SYSTEM MENU ****\n" +
                        "\n" +
                        "Add vehicle:            1\n" +
                        "Rent vehicle:           2\n" +
                        "Return vehicle:         3\n" +
                        "Vehicle Maintenance:    4\n" +
                        "Complete Maintenance:   5\n" +
                        "Display All Vehicles:   6\n" +
                        "Exit Program:           7\n" +
                        "Enter your choice:";
        assertEquals(expectedOutput, output);
    }
    @Order(2)
    @Test
    public void testAddVan() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        List<InputStream> inputs = List.of(
                new ByteArrayInputStream("1\n".getBytes()),
                new ByteArrayInputStream("Van\n2020\nMahindra\nBolero\n0001\n12/12/2024\n".getBytes()),
                new ByteArrayInputStream("7\n".getBytes())
        );
        InputStream in = new SequenceInputStream(Collections.enumeration(inputs));
        System.setIn(in);
        rentSystem.run();
        System.setOut(System.out);
        String expectedOutput = "V_0001:2020:Mahindra:Bolero:15:Available:12/12/2024";
        assertThat(outputStream.toString().trim(), containsString(expectedOutput));
    }
    @Order(3)
    @Test
    public void testDisplayVan() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        List<InputStream> inputs = List.of(
                new ByteArrayInputStream("1\n".getBytes()),
                new ByteArrayInputStream("Van\n2020\nMahindra\nBolero\n0001\n12/12/2024\n".getBytes()),
                new ByteArrayInputStream("6\n".getBytes()),
                new ByteArrayInputStream("7\n".getBytes())
        );
        InputStream in = new SequenceInputStream(Collections.enumeration(inputs));
        System.setIn(in);
        rentSystem.run();
        System.setOut(System.out);
        String expectedOutput = "***********Vans**********\n" +
        "Vehicle ID:\tV_0001\n" +
                " Year:\t 2020\n" +
                " Make:\tMahindra\n" +
                " Model:\tBolero\n" +
                " Number of Seats:\t15\n" +
                " Status:\tAvailable\n" +
                "Last maintenance date:\t12/12/2024";
        System.out.println(outputStream.toString());
        assertThat(outputStream.toString().trim().replace("\r",""),containsString(expectedOutput));
    }

    @Test
    public void testAddCar() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        List<InputStream> inputs = List.of(
                new ByteArrayInputStream("1\n".getBytes()),
                new ByteArrayInputStream("car\n2020\nNissan\nGT\n123\n4\n".getBytes()),
                new ByteArrayInputStream("7\n".getBytes())
        );
        InputStream in = new SequenceInputStream(Collections.enumeration(inputs));
        System.setIn(in);
        rentSystem.run();
        System.setOut(System.out);
        String expectedOutput = "C_123:2020:Nissan:GT:4:Available";
        assertThat(outputStream.toString().trim(), containsString(expectedOutput));
    }


    @Test
    public void testDisplayCars() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        List<InputStream> inputs = List.of(
                new ByteArrayInputStream("1\n".getBytes()),
                new ByteArrayInputStream("car\n2020\nNissan\nGT\n123\n4\n".getBytes()),
                new ByteArrayInputStream("6\n".getBytes()),
                new ByteArrayInputStream("7\n".getBytes())
        );
        InputStream in = new SequenceInputStream(Collections.enumeration(inputs));
        System.setIn(in);
        rentSystem.run();
        System.setOut(System.out);
        String expectedOutput = "***********Cars**********\n" +
                "Vehicle ID:\tC_123\n" +
                " Year:\t 2020\n" +
                " Make:\tNissan\n" +
                " Model:\tGT\n" +
                " Number of Seats:\t4\n" +
                " Status:\tAvailable\n" +
                "RENTAL RECORD:\tempty";
        assertThat(outputStream.toString().trim().replace("\r",""), containsString(expectedOutput));
    }

    @Test
    public void testRentCar() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        List<InputStream> inputs = List.of(
                new ByteArrayInputStream("1\n".getBytes()),
                new ByteArrayInputStream("car\n2020\nNissan\nGT\n123\n4\n".getBytes()),
                new ByteArrayInputStream("2\n".getBytes()),
                new ByteArrayInputStream("C_123\nCustomer123\n10/03/2024\n5\n".getBytes()),
                new ByteArrayInputStream("7\n".getBytes())
        );
        InputStream in = new SequenceInputStream(Collections.enumeration(inputs));
        System.setIn(in);
        rentSystem.run();
        System.setOut(System.out);
        String expectedOutput = "Vehicle C_123 is now rented by customer Customer123";
        assertThat(outputStream.toString().trim(), containsString(expectedOutput));
    }

    @Test
    public void testReturnCar() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        List<InputStream> inputs = List.of(
                new ByteArrayInputStream("1\n".getBytes()),
                new ByteArrayInputStream("car\n2020\nNissan\nGT\n123\n4\n".getBytes()),
                new ByteArrayInputStream("2\n".getBytes()),
                new ByteArrayInputStream("C_123\nCustomer123\n10/03/2024\n5\n".getBytes()),
                new ByteArrayInputStream("3\n".getBytes()),
                new ByteArrayInputStream("C_123\n16/03/2024\n".getBytes()),
                new ByteArrayInputStream("7\n".getBytes())
        );
        InputStream in = new SequenceInputStream(Collections.enumeration(inputs));
        System.setIn(in);
        rentSystem.run();
        System.setOut(System.out);
        String expectedOutput = "VehicleId: Return date( dd/mm/yyyy): Record ID:             C_123_Customer123_10032024\n" +
                "Rent Date:             10/03/2024\n" +
                "Estimated Return Date: 15/03/2024\n" +
                "Actual Return Date:    16/03/2024\n" +
                "Rental Fee:            468.00\n" +
                "Late Fee:              97.50";
        assertThat(outputStream.toString().trim(), containsString(expectedOutput));
    }

    @Test
    public void testEarlyReturnCar() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        List<InputStream> inputs = List.of(
                new ByteArrayInputStream("1\n".getBytes()),
                new ByteArrayInputStream("car\n2020\nNissan\nGT\n123\n4\n".getBytes()),
                new ByteArrayInputStream("2\n".getBytes()),
                new ByteArrayInputStream("C_123\nCustomer123\n10/03/2024\n5\n".getBytes()),
                new ByteArrayInputStream("3\n".getBytes()),
                new ByteArrayInputStream("C_123\n14/03/2024\n".getBytes()),
                new ByteArrayInputStream("7\n".getBytes())
        );
        InputStream in = new SequenceInputStream(Collections.enumeration(inputs));
        System.setIn(in);
        rentSystem.run();
        System.setOut(System.out);
        String expectedOutput = "VehicleId: Return date( dd/mm/yyyy): Record ID:             C_123_Customer123_10032024\n" +
                "Rent Date:             10/03/2024\n" +
                "Estimated Return Date: 15/03/2024\n" +
                "Actual Return Date:    14/03/2024\n" +
                "Rental Fee:            312.00\n" +
                "Late Fee:              0.00";
        assertThat(outputStream.toString().trim(), containsString(expectedOutput));
    }


    @Test
    public void testVanAndCarMaintenance() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        List<InputStream> inputs = List.of(
                new ByteArrayInputStream("1\n".getBytes()),
                new ByteArrayInputStream("van\n2023\nTesla\nCyberTruck\n123\n12/02/2023\n".getBytes()),
                new ByteArrayInputStream("1\n".getBytes()),
                new ByteArrayInputStream("car\n2020\nNissan\nGT\n123\n4\n".getBytes()),
                new ByteArrayInputStream("4\n".getBytes()),
                new ByteArrayInputStream("V_123\n".getBytes()),
                new ByteArrayInputStream("4\n".getBytes()),
                new ByteArrayInputStream("C_123\n".getBytes()),
                new ByteArrayInputStream("7\n".getBytes())
        );
        InputStream in = new SequenceInputStream(Collections.enumeration(inputs));
        System.setIn(in);
        rentSystem.run();
        System.setOut(System.out);
        String expectedOutput = "Vehicle V_123 is now under maintenance";
        String expectedOutput2 = "Vehicle C_123 is now under maintenance";
        assertThat(outputStream.toString().trim(), containsString(expectedOutput));
        assertThat(outputStream.toString().trim(), containsString(expectedOutput2));
    }

    @Test
    public void testCompleteCarAndVanMaintenance() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        List<InputStream> inputs = List.of(
                new ByteArrayInputStream("1\n".getBytes()),
                new ByteArrayInputStream("van\n2023\nTesla\nCyberTruck\n123\n12/02/2023\n".getBytes()),
                new ByteArrayInputStream("1\n".getBytes()),
                new ByteArrayInputStream("car\n2020\nNissan\nGT\n123\n4\n".getBytes()),
                new ByteArrayInputStream("4\n".getBytes()),
                new ByteArrayInputStream("V_123\n".getBytes()),
                new ByteArrayInputStream("4\n".getBytes()),
                new ByteArrayInputStream("C_123\n".getBytes()),
                new ByteArrayInputStream("5\n".getBytes()),
                new ByteArrayInputStream("C_123\n11/03/2024\n".getBytes()),
                new ByteArrayInputStream("5\n".getBytes()),
                new ByteArrayInputStream("V_123\n11/03/2024\n".getBytes()),
                new ByteArrayInputStream("7\n".getBytes())
        );
        InputStream in = new SequenceInputStream(Collections.enumeration(inputs));
        System.setIn(in);
        rentSystem.run();
        System.setOut(System.out);
        String expectedOutput = "Vehicle V_123 has all maintenance completed and ready for rent";
        assertThat(outputStream.toString().trim(), containsString(expectedOutput));
    }



}
