package PS1;

import java.rmi.*;

public class AddClient {
	public static void main (String args[]){
		try{
			String addServerUrl = "rmi://" + args[0] + "/AddServer.java";
			
			AddServerIntf addServerIntf = (AddServerIntf)Naming.lookup(addServerUrl);
			
			System.out.println("The First number is:" + args[1]);
			Double d1 = Double.valueOf(args[1]).doubleValue();
			
			System.out.println("The First number is:" + args[2]);
			Double d2 = Double.valueOf(args[2]).doubleValue();
			
			System.out.println("The sum is:" + addServerIntf.add(d1,d2));
			
		}
		catch(Exception e){
			System.out.println("Exception is "+ e);
		}
	}
}
