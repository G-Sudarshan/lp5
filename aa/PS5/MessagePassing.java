import mpi.MPI;
public class MessagePassing {
    public static void main(String[] args){
        MPI.Init(args);
        int size = MPI.COMM_WORLD.Size();
        int rank = MPI.COMM_WORLD.Rank();

        int[] arr = new int[20];
        for(int i = 0; i < 20; i++) arr[i] = i + 1;

        int elementsPerProcess = arr.length / size;
        int remainder = arr.length % size;

        int start = rank * elementsPerProcess;
        int end = start + elementsPerProcess;

        if(rank == size-1){
            end += remainder;
        }

        int sum = 0;
        for(int i = start; i < end; i++) {
            sum += arr[i];
        }

        System.out.println(rank + sum);

        int[] allSums = null;
        if(rank == 0) {
            allSums = new int[size];
        }
        MPI.COMM_WORLD.Gather(new int[]{sum},0,1,MPI.INT, allSums,0,1,MPI.INT,0);

        if(rank == 0){
            int total = 0;
            for(int partial : allSums) {
                total += partial;
            }

            System.out.println(total);
        }
        MPI.Finalize();
    }
}
// compile
// javac -cp "path to mpi.jar" Hello.java -> javac -cp "/usr/local/lib/mpi.jar" Hello.java

// Run
// mpirun java Hello