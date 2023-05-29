import org.omg.CosNaming.NamingContextPackage.*;

import java.util.Scanner;

import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import AddModule.*;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NameComponent;

public class AddServer {
	public static void main(String args[]){
		AddModule addModuleImpl = null;
		try{
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args,null);

			org.omg.CORBA.Object obj = orb.resolve_initial_references("Naming Services");
			NamingContextExt n_ref = NamingContextExtHelper.narrow(obj);


			String name = "Add";
			AddModuleImpl = AddModule.AddModuleHelper.narrow(n_ref.resolve_str(name));

			Scanner sc = new Scanner(System.in);
			String num1 = sc.nextLine();
			String num2 = sc.nextLine();
			String res = num1.concat(num2);
			System.out.println(res);
		}
		catch (Exception e){
			System.out.println("Exception is: " + e );
		}
	}
}