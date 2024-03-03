package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;

import demo.PrimeNumberChecker;

public class PrimeNumberChekerTestJUNIT5 {
    private static PrimeNumberChecker primeNumberChecker;
    @BeforeAll
    public static void initialize() {
        primeNumberChecker = new PrimeNumberChecker();
    }
    @ParameterizedTest
    @CsvSource({ "2, true", "6, false"})
    public void testPrimeNumberChecker(int inputNumber, Boolean expectedResult) {
        System.out.println("Parameterized Number is : " + inputNumber);
        assertEquals(expectedResult,
                primeNumberChecker.validate(inputNumber));
    }
    static Stream<Arguments> testPrimeNumberCheckerArgs(){
        return  Stream.of(Arguments.of( 2, true),
                Arguments.of(6, false ),
                Arguments.of(19, true),
                Arguments.of(22, false ),
                Arguments.of(23, true ));
    }

}