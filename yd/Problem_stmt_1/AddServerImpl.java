import java.rmi.*;
import java.rmi.server.*;

// implements the remote interface
public class AddServerImpl extends UnicastRemoteObject

implements AddServerIntf {

public AddServerImpl() throws RemoteException { 
}
public double add(double d1, double d2) throws RemoteException {
	return d1+d2;
}
}
