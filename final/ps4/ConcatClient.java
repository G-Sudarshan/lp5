import java.util.Scanner;

import ConcatModule.Concat;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
public class ConcatClient {
    public static void main(String[] args) {
        Concat concatImpl = null;
        try {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args,null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt nceRef = NamingContextExtHelper.narrow(objRef);
            
            String name = "Concat";

            concatImpl = ConcatModule.ConcatHelper.narrow(nceRef.resolve_str(name));
            System.out.println("Enter Two String");

            Scanner scanner = new Scanner(System.in);
            String str1 = scanner.nextLine();
            String str2 = scanner.nextLine();

            String result = concatImpl.concat_string(str1, str2);
            System.out.println("String " + result);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
