import CalculatorModule.CalculatorPOA;
import java.lang.*;
public class CalculatorImpl extends CalculatorPOA {
    
    CalculatorImpl() {
        super();
    }
    
    public float add(float num1, float num2){
        return num1 + num2;
    }

    public float sub(float num1, float num2){
        return num1 - num2;
    }

    public float mult(float num1, float num2){
        return num1 * num2;
    }

    public float div(float num1, float num2){
        return num1 / num2;
    }

}
