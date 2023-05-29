import java.util.*;

class Process {
    public int id;
    public boolean active;

    public Process(int id) {
        this.id = id;
        active = true;
    }
}

public class Ringnew {
    int numberOfProcesses;
    Process[] processes;
    Scanner sc;

    public Ringnew() {
        sc = new Scanner(System.in);
    }

    public void initialiseRing() {
        System.out.println("Enter the number of processes ");
        numberOfProcesses = sc.nextInt();
        processes = new Process[numberOfProcesses];
        for (int i = 0; i < processes.length; i++)
            processes[i] = new Process(i);
    }

    public int getMax() {
        int maxID = -99;
        int maxIDIndex = 0;
        for (int i = 0; i < processes.length; i++) {
            if (processes[i].active && processes[i].id > maxID) {
                maxID = processes[i].id;
                maxIDIndex = i;
            }
        }
        return maxIDIndex;
    }

public void performElection()
{
System.out.println("Process no. " + processes[getMax()].id + " fails");
processes[getMax()].active = false;
System.out.println("Election initiated by: ");
int initiatorProcess = sc.nextInt();
int prev = initiatorProcess;
int next = prev + 1;
while(true)
{
if(processes[next].active)
{
System.out.println("Process " + processes[prev].id
+
" pass Election(" + processes[prev].id
+ ") to " + processes[next].id);
prev = next;
}
next = (next + 1) % numberOfProcesses;
if(next == initiatorProcess)
break;
}
System.out.println("Process " + processes[getMax()].id + " becomes coordinator");
int coordinator = processes[getMax()].id;
prev = coordinator;
next = (prev + 1) % numberOfProcesses;
while(true)
{
if(processes[next].active)
{
System.out.println("Process " + processes[prev].id+ " pass Coordinator("+ coordinator + ") message to process "+ processes[next].id);
prev = next;
}
next = (next + 1) % numberOfProcesses;
if(next == coordinator)
{
System.out.println("End of Election");
break;
}
}
}

    public static void main(String[] args) {
        Ringnew r = new Ringnew();
        r.initialiseRing();
        r.performElection();
    }
}