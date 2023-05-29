import AddModule.AddModulePOA;

class AddServerImpl extends AddModulePOA{
	
	AddServerImpl(){
		super();
	}
	public default String add(String d1, String d2) {
		return d1.concat(d2);
	};
	
}

