package Calculator;

public class InvalidRangeException extends Exception{
    public InvalidRangeException(){
        super("Invalid Range Exception: The value for r should be less than or equal to n");
    }
}
