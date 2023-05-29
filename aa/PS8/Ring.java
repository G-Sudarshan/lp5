import java.util.Scanner;

public class Ring {
    int n, inactive_count;
    int coordinator;
    boolean[] procesState;

    public Ring(int n) {
        this.n = n;
        this.inactive_count = 0;
        this.coordinator = n-1;
        procesState = new boolean[n];
        for(int i = 0; i < n; i++) {
            procesState[i] = true;
        }
        System.out.println("The Coordinator currently is " + n);
    }
    
    public void deactivate_process(int id) {
        if(id > n || id < 0) {
            System.out.println("INVALID ID");
            return;    
        }
        else if(procesState[id - 1] == false) {
            System.out.println("Process " + id + " already inactive");
        }
        else {
            procesState[id-1] = false;
            System.out.println("Process " + id + " deactivated");
            inactive_count+=1;
        } 

    }

    public void viewRing() {
        if(this.inactive_count == n) {
            System.out.println("All Members are INACTIVE!!");
            return;
        }
        else {
            System.out.println("Active Members are ");
            for(int i = 0; i < n; i++) {
               if(procesState[i]) System.out.println((i + 1) + " ");
            }
        }
    }

    public void election(int id) {
        id = id - 1;
        if(this.inactive_count == this.n) {
            System.out.println("All members are inactive ABORTING ELECTION");
            this.coordinator = -1;
        }

        int curr_coord = id;

        int token = (id + 1) % this.n;
        System.out.println("Election Initiator" + (id + 1));
        while(token!=id) {
            System.out.println("Token at process" + (token + 1));
            if(this.procesState[token]) {
                if(token > curr_coord) {
                    curr_coord = token;
                }
            }
            token = (token + 1) % this.n;
        }

        System.out.println("ELECTED COORDINATOR " + (curr_coord + 1));
        this.coordinator = curr_coord;
    }

    public void pingCoord(int id) {
        if(this.procesState[id - 1] == false) {
            System.out.println("INACTIVE PROCESS, ABORTING!");
            return;
        }

        else if(id == this.coordinator ) {
            if(this.procesState[id-1]){
                System.out.println("COORDINATOR ACTIVE");
            }
            else {
                System.out.println("Coordinator inactive \n initiate election process");
            }
            return;
        }
        System.out.println("Sending Message from " + id + " to Coordinator " + (this.coordinator + 1) );

        if(this.procesState[this.coordinator] == false) {
            System.out.println("Coordinator Process not responding \n Conduct Election");
            this.election(id);

        }
        else {
            System.out.println("Coordinator Alive!!");
        }
    }
    public void setCoordinator(int c) {
        this.coordinator =c;
    }
    public static void main(String[] args) {


        int choice = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("ENTER NO OF PROCESS STATES : ");
        int n = sc.nextInt();

        Ring ring = new Ring(n);

        do {
            System.out.println("***********Menu***********");
			System.out.println("1. Deactivate a process");
			System.out.println("2. Ping coordinator");
			System.out.println("3. View Ring");
			System.out.println("4. Election");
			System.out.println("5. Exit");
			System.out.println("**************************");
			System.out.println("Enter Choice : ");
			choice = sc.nextInt();

            switch (choice) {
                case 1:
                    int Did;
                    System.out.println("Enter Process State id to deactivate");
                    Did = sc.nextInt();
                    ring.deactivate_process(Did); 
                    break;
                case 2:
                    int Pid;
                    System.out.println("Enter Process State id to deactivate");
                    Pid = sc.nextInt();
                    ring.pingCoord(Pid); 
                    break;
                case 3:
                    ring.viewRing(); 
                    break;
                case 4:
                    System.out.println("Enter ID to be set as coordinator ");
                    int Coord_id = sc.nextInt();
                    ring.election(Coord_id);
                    break;

                default:
                    break;
            }
        } while (choice!=5);
    }
}
