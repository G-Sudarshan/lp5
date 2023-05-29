import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.*;
import org.omg.PortableServer.*;
import CalculatorModule.Calculator;
import CalculatorModule.CalculatorHelper;
class CalculatorServer {
    public static void main(String[] args) {
        try {
            org.omg.CORBA.ORB orb = ORB.init(args,null);


            // initialize the poa 
            org.omg.PortableServer.POA rootPOA = org.omg.PortableServer.POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();

            // create calculator object
            CalculatorImpl calculatorImpl = new CalculatorImpl();

            org.omg.CORBA.Object ref = rootPOA.servant_to_reference(calculatorImpl);

            System.out.print("SERVER READY");

            Calculator helperRef = CalculatorHelper.narrow(ref);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

            NamingContextExt nceRef = NamingContextExtHelper.narrow(objRef);

            String name = "Calculator";

            NameComponent[] path = nceRef.to_name(name);

            nceRef.rebind(path,helperRef);
            
            System.out.print("Server waiting");
            orb.run();

    
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    
}
