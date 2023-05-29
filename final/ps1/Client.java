import java.rmi.Naming;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            String serverUrl = "rmi://" + args[0] + "/Server";
            ServerIntf serverIntf = (ServerIntf)Naming.lookup(serverUrl);
            
            System.out.println("1st no: " + args[1]);
            double d1 = Double.valueOf(args[1]);

            System.out.println("2nd no: " + args[2]);
            double d2 = Double.valueOf(args[2]);

            System.out.println("The sum is " + serverIntf.add(d1, d2));

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }    
}
