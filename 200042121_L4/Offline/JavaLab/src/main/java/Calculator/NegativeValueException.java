package Calculator;

public class NegativeValueException extends Exception{
    public NegativeValueException(){
        super("Negative Value Exception: The input values cannot be negative");
    }
}
