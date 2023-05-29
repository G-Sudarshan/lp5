import java.net.*;

import java.rmi.*;

//  update the RMI registry
public class AddServer{

public static void main(String args[]){
try {

	AddServerImpl addServerImpl = new AddServerImpl();

	Naming.rebind("AddServer", addServerImpl);
}

catch(Exception e) { 
	System.out.println("Exception: "+ e);
}
}
}

/*
	Commands to run

	new terminal
	javac *.java
	rmic AddServerImpl
	rmiregistry

	new terminal 
	java AddServer

	new terminal
	java AddClient 127.0.0.1 12.5 13.6 
 * 
 */
