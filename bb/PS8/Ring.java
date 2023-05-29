package PS8;
import java.util.*;

public class Ring {
	int n,inactive_count, coodinator;
	boolean[] state;

	public Ring(int n){
		this.n = n;
		this.inactive_count =0;
		this.state = new boolean[n];

		for(int i=0;i<n;i++){
			this.state[i] = true;
		}
		this.coodinator = n-1;
	}

	public void view(){
		if(this.inactive_count==n){
			System.out.println("All inactive");
		}
		else{
			for(int i=0;i<n;i++){
				if(state[i]==true){
					System.out.println((i+1)+ " ");
				}
			}
		}
	}

	public void deactivate(int id){
		if(id >n || id<0){
			System.out.println("Chutiye sahi se id toh daal bc");
			return;
		}
		if(!state[id-1]){
			System.out.println("Chutiye mari hui process hai");
		}
		else{
			state[id-1]=false;
			inactive_count+=1;
		}
	}


	public void election(int id){
		if(inactive_count==n){
			System.out.println("All inactive");
			System.out.println("Aborting..........");
			this.coodinator=-1;
			return;
		}
		id = id -1;
		int curr_coordinator = id;
		int token = (id+1)%n;
		System.out.println("Election held by " +  id+1);
		while(token!=id){
			System.out.println("Token at " +  token+1);
			if(state[token]){
				if(token>curr_coordinator){
					curr_coordinator=token;
				}
			}
			token=(token+1)%this.n;
		}
		System.out.println("elected coordinator" +  (curr_coordinator+1));
		this.coodinator=curr_coordinator;
	}

	public void ping(int id){
		if(state[id-1]==false){
			System.out.println("Process inactive");
			System.out.println("Aborting");
			return;
		}
		if((id-1) == coodinator){
			if(state[id-1]){
				System.out.println("Coordinator active");
			}
			else{
				System.out.println("Coordinator inactive\nInitialise election by other process");
			}
		}
		System.out.println("Sent message from:" + id + "to" + (coodinator+1));
		if(!state[coodinator]){
			System.out.println("Coordinator not responding");
			System.out.println("Conducting election");
			this.election(id);
		}
		else{
			System.out.println("Coordinator alive");
		}
	}

	public static void main(String args[]){
		System.out.println("Enter no. of processes:");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int ch = 0;
		Ring ring = new Ring(n);

		while(ch<5){
			System.out.println("***********Menu***********");
			System.out.println("1. Deactivate a process");
			System.out.println("2. Ping coordinator");
			System.out.println("3. View Ring");
			System.out.println("4. Election");
			System.out.println("5. Exit");
			System.out.println("**************************");
			System.out.println("Enter Choice : ");
			ch = sc.nextInt();
			switch(ch)
			{
				case 1:{
					System.out.println("Enter process to deactivate:");
					int d = sc.nextInt();
					ring.deactivate(d);
				}
				break;
				case 2:{
					System.out.println("Enter process ID:");
					int id = sc.nextInt();
					ring.ping(id);
				}
				break;
				case 3:{
					System.out.println("view Ring:");
					ring.view();
				}
				break;
				case 4:{
					System.out.println("Enter process ID for election:");
					int id = sc.nextInt();
					ring.election(id);
				}
				break;
			}
		}
	}
}