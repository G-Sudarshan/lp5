import java.rmi.*;
import java.rmi.server.*;

public class ServerImpl extends UnicastRemoteObject implements ServerIntf {
    public ServerImpl() throws RemoteException{
        super();
    }
    
    public double add(double num1, double num2) {
        return num1 + num2;
    }
}
