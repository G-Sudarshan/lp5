import ConcatModule.ConcatPOA;
public class ConcatImpl extends ConcatPOA {
    public ConcatImpl(){
        super();
    }

    public String concat_string(String s1, String s2){
        
        return s1 + " " + s2;    
    }
 }
