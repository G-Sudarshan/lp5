import mpi.MPI;

public class arrSum {

  public static void main(String[] args) {
    MPI.Init(args);
    int rank = MPI.COMM_WORLD.Rank();
    int size = MPI.COMM_WORLD.Size();
    int unitsize = 5, root = 0;
    int send_buffer[] = new int[unitsize * size];
    int recieve_buffer[] = new int[unitsize];
    int new_receiver_buffer[] = new int[size];
    if (rank == root) {
      int total = unitsize * size;
      for (int i = 0; i < total; i++) send_buffer[i] = i + 1;
    }
    MPI.COMM_WORLD.Scatter(
      send_buffer,
      0,
      unitsize,
      MPI.INT,
      recieve_buffer,
      0,
      unitsize,
      MPI.INT,
      root
    );
    for (int i = 1; i < unitsize; i++) recieve_buffer[0] += 
recieve_buffer[i];
    System.out.println(
      "Intermediate sum at  " + rank + " is " + recieve_buffer[0]
    );
    MPI.COMM_WORLD.Gather(
      recieve_buffer,
      0,
      1,
      MPI.INT,
      new_receiver_buffer,
      0,
      1,
      MPI.INT,
      root
    );
    if (rank == root) {
      int total = 0;
      for (int i = 0; i < size; i++) total += new_receiver_buffer[i];
      System.out.println("Total sum is " + total);
    }
    MPI.Finalize();
  }
}

