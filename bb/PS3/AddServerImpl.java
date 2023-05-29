import AddModule.AddModulePOA;

class AddServerImpl extends AddModulePOA{
	
	AddServerImpl(){
		super();
	}
	public default double add(double d1, double d2) {
		return d1+d2;
	};
	
}

