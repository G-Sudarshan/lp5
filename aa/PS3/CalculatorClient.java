

import CalculatorModule.Calculator;

import java.util.Scanner;

import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.NamingContextPackage.*;
public class CalculatorClient {
    public static void main(String[] args) {
        Calculator calcImpl = null;
        try {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args,null);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt nceRef = NamingContextExtHelper.narrow(objRef);

            String name = "Calculator";

            calcImpl = CalculatorModule.CalculatorHelper.narrow(nceRef.resolve_str(name));

            System.out.println("Enter Two Number ");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the first float number: ");
            float number1 = scanner.nextFloat();

            System.out.print("Enter the second float number: ");
            float number2 = scanner.nextFloat();

            float result = calcImpl.add(number1,number2);
            System.out.println("add " + result);
            
            result = calcImpl.sub(number1,number2);
            System.out.println("sub " + result);

            result = calcImpl.mult(number1,number2);
            System.out.println("multiplication " + result);

            result = calcImpl.div(number1,number2);
            System.out.println("division " + result);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }    
}
