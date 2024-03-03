package Calculator;

public class CalculatorNCR {
    private Integer n;
    private Integer r;

    public CalculatorNCR(double n,double r) throws Exception{
        throw new FloatException();
    }
    public CalculatorNCR(int n,int r) throws Exception{
        if(n<0 || r<0){
            throw new NegativeValueException();
        }
        else if(r>n){
            throw new InvalidRangeException();
        }
        else if(n>=1000){
            throw new LargeException();
        }
        else if(n==0){
            throw new ZeroNException();
        }
        else{
            this.n=n;
            this.r=r;
        }
    }

    public Long calculate(){
        return factorial(n)/(factorial(n-r)*factorial(r));
    }

    public Long factorial(Integer n){
        if(n==0){
            return 1L;
        }
        Long fact=1L;
        for(long i=n;i>=1;i--){
            fact*=i;
        }
        return fact;
    }
}
