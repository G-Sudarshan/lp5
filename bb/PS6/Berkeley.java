package PS6;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class Berkeley {

    public static double avgTime(List<Double> nodeCurrTime, double serverTime){
        double timeDiff = 0;
        for(double i:nodeCurrTime){
            timeDiff+=i-serverTime;
        }
        double avgTime = timeDiff/nodeCurrTime.size();
        serverTime = serverTime+avgTime;
        return serverTime;
    }

    public static List<Double> berkeley(List<Double> nodeCurrTime, double serverTime){
        double avg = avgTime(nodeCurrTime, serverTime);
        List<Double> updateCurrTime =  new ArrayList<>();
        for(int i=0;i<nodeCurrTime.size();i++){
            updateCurrTime.add(avg);
        }
        return updateCurrTime;
    }

    public static void main(String args[]){
        System.out.println("Enter no of processes: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        List<Double> nodeCurrTime = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter time for node " + i + " in hr format: ");
            double timeInput = sc.nextDouble();
            nodeCurrTime.add(timeInput);
        }

        System.out.print("\nEnter Server time: ");
        double serverTime = sc.nextDouble();

        System.out.print("Initial time for nodes: ");
        for (int i = 0; i < n; i++) {
            System.out.println("Time at node " + i + " is " + nodeCurrTime.get(i));
        }

        List<Double> updateCurrTime = berkeley(nodeCurrTime,serverTime);
        System.out.print("Updated time for nodes: ");
        for (int i = 0; i < n; i++) {
            System.out.println("Time at node " + i + " is " + updateCurrTime.get(i));
        }

    }
}
