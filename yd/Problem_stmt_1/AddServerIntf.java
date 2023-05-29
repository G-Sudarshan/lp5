import java.rmi.*;

// defines the remote interface that is provided by the server
public interface AddServerIntf extends Remote {
	double add(double d1, double d2) throws RemoteException;
}


