package PS1;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class AddServerImpl extends UnicastRemoteObject implements AddServerIntf {
	public AddServerImpl() throws RemoteException{
	}
	
	public double add(double d1, double d2) throws RemoteException {
		return d1+d2;
	}
}
