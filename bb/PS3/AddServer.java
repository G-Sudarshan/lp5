import org.omg.CosNaming.NamingContextPackage.*; 
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import AddModule.*;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NameComponent;

public class AddServer {
	public static void main(String args[]){
		try{
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args,null);

			POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPOA.the_POAManager().activate();

			AddServerImpl addServerImpl = new AddServerImpl();
			org.omg.CORBA.Object obj = rootPOA.servant_to_reference(addServerImpl);
			AddModule h_ref = AddModule.AddModuleHelper.narrow(obj);

			org.omg.CORBA.Object obj = orb.resolve_initial_references("Naming Services");
			NamingContextExt n_ref = NamingContextExtHelper.narrow(obj);

			String name = "Add";
			NameComponent path[] = n_ref.to_name(name);

			n_ref.rebind(path, h_ref);
			orb.run();

		}
		catch (Exception e){
			System.out.println("Exception is: " + e );
		}
	}
}