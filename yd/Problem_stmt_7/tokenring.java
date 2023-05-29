import java.util.Scanner;

public class tokenring {

    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter number of processes");
        int n=sc.nextInt();
        int token=0;
        for(int i=0;i<n;i++){
            System.out.println(i+" ");
        }
        System.out.println("0");

        while(true){
            System.out.println("Enter Sender number");
            int s=sc.nextInt();
            System.out.println("Enter reciever number");
            int r=sc.nextInt();
            System.out.println("Enter Data to send");
            String data=sc.next();
            for(int i=token;i!=s;i=(i+1)%n){
                System.out.println(" "+i+" ->");
            }

            System.out.println(s);
            for(int i=(s+1);i!=r;i=(i+1)%n)
            {
                System.out.println("Data : "+data+" Forwarded by:"+ i%n);
            }
            System.out.println("Receiver:"+r+"received by:"+data);
            token=s;

        }
    }
    
}
