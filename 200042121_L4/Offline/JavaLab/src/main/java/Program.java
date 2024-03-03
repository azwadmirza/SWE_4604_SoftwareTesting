import Calculator.CalculatorNCR;

public class Program {
        public static void main(String[] args) {
                try{
                        CalculatorNCR calculatorNCR=new CalculatorNCR(4,2);
                        System.out.println(calculatorNCR.calculate());
                }
                catch (Exception e){
                        System.out.println(e.getMessage());
                }
        }
}