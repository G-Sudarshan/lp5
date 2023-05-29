import java.util.Scanner;

public class Bully{
    static boolean[] states = new boolean[5];
    int coordinator;

    public static void Up(int up) {
        if(states[up - 1] == true) {
            System.out.println("Process " + up + " is already up" );
        }
        else {
            states[up - 1] = true;
            System.out.println("Process "+ up + " helds election");
            for(int i = up; i < 5; i++) {
                System.out.println("Election message sent from " + up + " to " + (i+1));
            }
            for(int i = up; i<=5;i++){
                if(states[i - 1]== true) {
                    System.out.println("alive message send from process "+i+" to process"+up);
                    break;
                }
            }

        }
    }

    public static void Down(int down) {
        if(states[down-1]==true){
            System.out.println("Process "+down + " is down");
            states[down - 1] = false;
        }
        else {
            System.out.println("Process "+down+"is already down");
        }
    }

    public static void Mess(int mess) {
        if(states[mess -1] == true) {
            if(states[4] == true) {
                System.out.println("OK");
            }
            else {
                System.out.println("Process "+mess + " held election");
                for(int i = mess; i < 5; i++) {
                    System.out.println("Election message sent from " + mess + " to " + (i+1));
                }
                for(int i = 5; i>=mess;i--){
                    if(states[i - 1]== true) {
                        System.out.println("Coordinator message send from process "+i+" to all process");
                        break;
                    }
                }
            }
        }
        else
        {
            System.out.println("Prccess"+mess+"is down");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        for(int i = 0; i < 5; i++) {
            states[i] = true; 
        }
        System.out.println("5 active process are:");
        System.out.println("Process up  = p1 p2 p3 p4 p5");
        System.out.println("Process 5 is coordinator");

        do {
            System.out.println(".........");
            System.out.println("1 up a process.");
            System.out.println("2.down a process");
            System.out.println("3 send a message");
            System.out.println("4.Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter Process to get it up");
                    int up = sc.nextInt();
                    if(up==5){
                        System.out.println("Process 5 is coordinator");
                        states[4] = true;
                    }
                    else Up(up);
                    break;
                case 2:
                    System.out.println("Enter Process to get it up");
                    int down = sc.nextInt();
                    Down(down);
                    break;
                case 3:
                    System.out.println("Which process will send the message");
                    int mess = sc.nextInt();
                    Mess(mess);
                    break;
                default:
                    break;
            }
        }while(choice!=4);
    }
}