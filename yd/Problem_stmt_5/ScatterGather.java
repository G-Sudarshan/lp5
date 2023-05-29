import mpi.MPI;

import java.util.Scanner;

import mpi.*;

public class ScatterGather {
    public static void main(String[] args) throws Exception{
        MPI.Init(args);
        //  Get rank of each process & no of processes
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        
        int unitsize = 5; // elements in each process
        int root = 0; // root process
        int send_buffer[] = null; // for scatter operation
        //  1 process is expected to handle 4 elements
        send_buffer = new int [unitsize * size];
        int recieve_buffer[] = new int [unitsize]; // for gather operation
        int new_recieve_buffer[] = new int [size];


        /*
         * If the current process is not the root process, it means that the process 
         * is not responsible for distributing the data but will receive a portion of the data 
         * from the root process.
         * 
         * If we consider the process with rank 1 (not the root process), 
         * it will receive the data [2, 3]. The loop for (int i = 1; i < unitsize; i++)
         * calculates the sum of the received elements, resulting in 2 + 3 = 5. 
         * The process then prints "Intermediate sum at process 1 is 5".
         * 
         */


        //  Set data for distribution to all processes
        if(rank == root) { 
            int total_elements = unitsize * size;
            System.out.println("Enter " + total_elements + " elements");
            for(int i = 0; i < total_elements; i++) {
                System.out.println("Element " + i + "\t = " + i);
                send_buffer[i] = i;
            }
        }



        //  Scatter data to processes
        MPI.COMM_WORLD.Scatter(
            send_buffer, // data to be sent
            0, // starting pos
            unitsize, // ending pos
            MPI.INT, // data type
            recieve_buffer, // reciving array
            0, // starting pos
            unitsize, // ending pos
            MPI.INT, // data type
            root // rank of the root process that will receive the scatter data
        );

        //  Calculate sum at non root processes
        //  Store result in first index of array
        for(int i = 1; i < unitsize; i++) {
            recieve_buffer[0] += recieve_buffer[i];
        }
        System.out.println(
            "Intermediate sum at process " + rank + " is " + recieve_buffer[0]
        );
        

        //  Gather data from processes into new recieve buffer 
        MPI.COMM_WORLD.Gather(
            recieve_buffer, 
            0,
            1,
            MPI.INT,
            new_recieve_buffer,
            0,
            1,
            MPI.INT,
            root
        );

        //  Adding output from all non root processes and printing final sum
        if(rank == root) {
            int total_sum = 0;
            for(int i = 0; i < size; i++) {
                total_sum += new_recieve_buffer[i];
            }
            System.out.println("Final sum : " + total_sum);
        }

        MPI.Finalize();
    }
}


/*
 * commands to run 
 *  
 * export mpj_home= D:/7 sem -engg/lp5 assignments/swalehacode/LP5-main/Problem_stmt_5/mpj-v0_44
 * export path=mpj_home/bin:$path
 * javac -cp D:/7 sem -engg/lp5 assignments/swalehacode/LP5-main/Problem_stmt_5/mpj-v0_44/lib/mdj.jar ScatterGather.java
 * $mpj_home/bin/mpjrun.sh -np 4 ScatterGather
 * 
 * 
 * cp: classpath
 * np: number of processors
 *
 */