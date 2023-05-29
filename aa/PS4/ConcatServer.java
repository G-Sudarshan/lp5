import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.*;
import org.omg.PortableServer.*;
import ConcatModule.Concat;
import ConcatModule.ConcatHelper;
public class ConcatServer {
    public static void main(String[] args) {
        try {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args,null);

            // initialize poa
            org.omg.PortableServer.POA rootPOA = org.omg.PortableServer.POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();

            // create concatimpl object
            // ConcatImpl concatImpl = new ConcatImpl();
            ConcatImpl concatImpl = new ConcatImpl();
            org.omg.CORBA.Object ref = rootPOA.servant_to_reference(concatImpl);

            System.out.println("Server is ready!!");

            Concat helperRef = ConcatHelper.narrow(ref);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

            NamingContextExt nceRef = NamingContextExtHelper.narrow(objRef);

            String name = "Concat";

            NameComponent[] path = nceRef.to_name(name);

            nceRef.rebind(path,helperRef);
            System.out.println("Waiting for client");
            orb.run();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
