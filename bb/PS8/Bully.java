package PS8;

import java.util.Scanner;

public class Bully {
    public static boolean[] state = new boolean[5];

    public static void up(int up){
        if(state[up-1]==true){
            System.out.println("Process already up");
        }
        else{
            state[up-1] = true;
            for(int i=up;i<5;i++){
                System.out.println("Election message sent by : " + up + " to" + (i+1));
            }
            for(int i=up+1;i<5;i++){
                System.out.println("Alive message sent by" + i + "to" + up);
                break;
            }
        }
    }

    public static void down(int down){
        if(state[down-1]==false){
            System.out.println("Process already down");
        }
        else{
            state[down-1] = false;
        }
    }

    public static void mess(int mess){
        if(state[mess-1]==false){
            System.out.println("Mess is down");
        }
        else{
            if(state[4]==true){
                System.out.println("OK");
            }
            else{
                System.out.println("Election held by: " +  mess);
                for(int i=mess;i<5;i++){
                    System.out.println("Message sent by: " +  mess + " to "+ i+1);
                }
                for(int i=5;i>=mess;i--){
                    if(state[i-1]==true){
                        System.out.println("Coordinator message sent by: " +  i + " to all");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("5 processes up: p1,p2,p3,p4,p5");
        System.out.println("Process 5 in coordinator");

        int ch =0;
        for(int i=0;i<5;i++){
            state[i] = true;
        }

        do{

            System.out.println("================");
            System.out.println("Enter choice:");
            System.out.println("1. Up");
            System.out.println("2. Down");
            System.out.println("3. Message");
            System.out.println("4. Exit");
            System.out.println("================");

            ch = sc.nextInt();

            switch(ch){
                case 1:{
                    System.out.println("Enter process:");
                    int up = sc.nextInt();
                    if(up==4){
                        System.out.println("P5 is coordinator");
                        state[4]=true;
                    }
                    else{
                        up(up);
                    }
                }
                break;
                case 2:{
                    System.out.println("Enter process:");
                    int down = sc.nextInt();
                    down(down);
                }
                break;
                case 3:{
                    System.out.println("Enter process:");
                    int mess = sc.nextInt();
                    mess(mess);
                }
                break;
            }

        }while(ch!=4);
    }
}
