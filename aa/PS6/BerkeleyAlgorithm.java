import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BerkeleyAlgorithm {

    public static double averageTime(List<Double> nodeCurrTime, double serverTime) {
        double timeDiff = 0;
        for (double time : nodeCurrTime) {
            timeDiff += time - serverTime;
        }

        double avgTime = timeDiff / nodeCurrTime.size();
        serverTime = avgTime + serverTime;
        System.out.println("\nUpdated Server time: " + serverTime);

        return serverTime;
    }

    public static List<Double> berkeleyAlgorithm(List<Double> nodeCurrTime, double serverTime) {
        double avgTime = averageTime(nodeCurrTime, serverTime);

        List<Double> updatedTime = new ArrayList<>();
        for (int i = 0; i < nodeCurrTime.size(); i++) {
            updatedTime.add(avgTime);
        }

        return updatedTime;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int nodeCount = scanner.nextInt();

        List<Double> nodeCurrTime = new ArrayList<>();
        for (int i = 0; i < nodeCount; i++) {
            System.out.print("Enter time for node " + i + " in hr format: ");
            double timeInput = scanner.nextDouble();
            nodeCurrTime.add(timeInput);
        }

        System.out.print("\nEnter Server time: ");
        double serverTime = scanner.nextDouble();

        System.out.println("\nInitial time of nodes");
        for (int i = 0; i < nodeCount; i++) {
            System.out.println("Time at node " + i + " is " + nodeCurrTime.get(i));
        }

        List<Double> updatedTime = berkeleyAlgorithm(nodeCurrTime, serverTime);
        System.out.println("\nUpdated time of nodes");
        for (int i = 0; i < nodeCount; i++) {
            System.out.println("Time at node " + i + " is " + updatedTime.get(i));
        }
    }
}