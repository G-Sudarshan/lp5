import java.rmi.Naming;

public class Server {
    public static void main(String[] args) {
        try {
            ServerImpl serverImpl = new ServerImpl();
            Naming.rebind("Server", serverImpl);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }    
}
