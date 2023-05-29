package PS1;

import java.rmi.*;

public class AddServer {
	public static void main(String args[]){
		try{
			AddServerImpl addServerImpl = new AddServerImpl();
			Naming.rebind("AddServer.java", addServerImpl);
		}
		catch (Exception e){
			System.out.println("Exception is: " + e );
		}
	}
}