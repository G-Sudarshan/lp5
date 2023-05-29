package PS5;

import java.lang.reflect.Array;

import mpi.*;

public class MessagePassing {
    public static void main(String args[]){
        try {
            MPI.Init(args);

            int rank = MPI.COMM_WORLD.Rank();
            int size = MPI.COMM_WORLD.Size();
            int N = 20;
            int n = size;

            int[] localArray = new int[N / n];

            MPI.COMM_WORLD.scatter(new int[N], 0, N/n, MPI.Init, localArray, 0, N/n, MPI.Init, 0);

            int localSum =0;
            for(int i=0;i<=localArray.length;i++){
                localSum+=localArray[i];
            }

            int[] allSum = new int[size];
            MPI.COMM_WORLD.gather(new int[]{localSum}, 0, 1, MPI.Init, allSum, 0, 1, MPI.Init, 0);

            if(rank==0){
                int finalSum=0;
                for(int i=0;i<=size;i++){
                    finalSum+=allSum[i];
                }
                System.out.println(finalSum);
            }

            MPI.Finalize();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

//javac -cp $MPI_HOME/lib/mpj.jar MessagePassing.java
//$MPI_HOME/bin/mpirun.sh -np 4 MessagePassing
