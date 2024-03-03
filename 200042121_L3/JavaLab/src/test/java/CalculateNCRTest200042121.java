import Calculator.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CalculateNCRTest200042121 {
    @Test
    public void testValidRangeN5R2(){
        try{
            CalculatorNCR calculatorNCR=new CalculatorNCR(5,2);
            assertEquals(calculatorNCR.calculate(),10);
        }
        catch (Exception e){
            assertTrue(false);
        }
    }

    @Test
    public void testValidRangeN12R10(){
        try{
            CalculatorNCR calculatorNCR=new CalculatorNCR(12,10);
            assertEquals(calculatorNCR.calculate(),66);
        }
        catch (Exception e){
            assertTrue(false);
        }
    }

    @Test
    public void testValidRangeN6R6(){
        try{
            CalculatorNCR calculatorNCR=new CalculatorNCR(6,6);
            assertEquals(calculatorNCR.calculate(),1);
        }
        catch (Exception e){
            assertTrue(false);
        }
    }

    @Test
    public void testValidRangeN15R2(){
        try{
            CalculatorNCR calculatorNCR=new CalculatorNCR(15,2);
            assertEquals(calculatorNCR.calculate(),105);
        }
        catch (Exception e){
            assertTrue(false);
        }
    }

    @Test
    public void testValidRangeN15R14(){
        try{
            CalculatorNCR calculatorNCR=new CalculatorNCR(15,14);
            assertEquals(calculatorNCR.calculate(),15);
        }
        catch (Exception e){
            assertTrue(false);
        }
    }

    @Test
    public void testValidRangeN3R0(){
        try{
            CalculatorNCR calculatorNCR=new CalculatorNCR(3,0);
            assertEquals(calculatorNCR.calculate(),1);
        }
        catch (Exception e){
            assertTrue(false);
        }
    }

    @Test
    public void testInvalidRange(){
        InvalidRangeException thrown = assertThrows(
                InvalidRangeException.class,
                () -> new CalculatorNCR(2,4).calculate(),
                "Invalid Range Exception: The value for r should be less than or equal to n"
        );

        assertTrue(thrown.getMessage().contains("Invalid Range Exception: The value for r should be less than or equal to n"));
    }

    @Test
    public void testNegativeRangeForN(){
        NegativeValueException thrown = assertThrows(
                NegativeValueException.class,
                () -> new CalculatorNCR(-4,2).calculate(),
                "Negative Value Exception: The input values cannot be negative"
        );

        assertTrue(thrown.getMessage().contains("Negative Value Exception: The input values cannot be negative"));
    }

    @Test
    public void testNegativeRangeForR(){
        NegativeValueException thrown = assertThrows(
                NegativeValueException.class,
                () -> new CalculatorNCR(4,-2).calculate(),
                "Negative Value Exception: The input values cannot be negative"
        );

        assertTrue(thrown.getMessage().contains("Negative Value Exception: The input values cannot be negative"));
    }

    @Test
    public void testLargeException(){
        LargeException thrown = assertThrows(
                LargeException.class,
                () -> new CalculatorNCR(1000,2).calculate(),
                "Large Exception: value too large"
        );

        assertTrue(thrown.getMessage().contains("Large Exception: value too large"));
    }

    @Test
    public void testZeroException(){
        ZeroNException thrown = assertThrows(
                ZeroNException.class,
                () -> new CalculatorNCR(0,0).calculate(),
                "Zero Exception for N occurs"
        );

        assertTrue(thrown.getMessage().contains("Zero Exception for N occurs"));
    }

    @Test
    public void testFloatExceptionN(){
        FloatException thrown = assertThrows(
                FloatException.class,
                () -> new CalculatorNCR(2.5,1).calculate(),
                "Float Exception: The Number Must Be Integers"
        );

        assertTrue(thrown.getMessage().contains("Float Exception: The Number Must Be Integers"));
    }

    @Test
    public void testFloatExceptionR(){
        FloatException thrown = assertThrows(
                FloatException.class,
                () -> new CalculatorNCR(3,2.5).calculate(),
                "Float Exception: The Number Must Be Integers"
        );

        assertTrue(thrown.getMessage().contains("Float Exception: The Number Must Be Integers"));
    }



}
