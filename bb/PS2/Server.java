import java.net.*;
import java.io.*;

public class Server {
    public static void main(String args[]) throws Exception{
        ServerSocket ss = new ServerSocket(5555);
        System.out.println("Server Initiated, Waiting for Client to Connect...");

        Socket s = ss.accept();
        System.out.println("Client Connected");
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        OutputStream os = s.getOutputStream();
        PrintWriter pw = new PrintWriter(os, true);
        InputStream is = s.getInputStream();
        BufferedReader receive = new BufferedReader(new InputStreamReader(is));

        String serverMessage = "";
        String clientMessage = "";
        
        while(true){
            clientMessage = receive.readLine();
            System.out.println("Client: " + clientMessage);

            if(clientMessage.equals("bye")){
                break;
            }

            System.out.println("Server: ");
            serverMessage = br.readLine();
            pw.println(serverMessage);
            
            if(serverMessage.equals("bye")){
                break;
            }
        }
        
        s.close();
        ss.close();
        is.close();
        os.close();

        System.out.println("Connection Terminated");
    }
}