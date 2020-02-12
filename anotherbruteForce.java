import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int AddInArray(long[][] Array,int[] queries,int size){

        int ind = 0;
        for(int i=0;i<size;i++){
            if(queries[0] >= Array[i][0] && queries[0] <=Array[i][1] )
            {
                ind = i;
                break;
            }
        }

        System.out.print("ind :" + String.valueOf(ind));

        if(Array[ind][0] == queries[0] && Array[ind][1] == queries[1]){
            Array[ind][2] += queries[2];
            System.out.println("If 1");
        }

        else if(Array[ind][0] < queries[0] && Array[ind][1] == queries[1])
        {
            Array[ind][1] = queries[0] - 1;
            size++;
            for(int i=size -1 ;i>ind+1;i--){
                Array[i] = Array[i-1];
            }
            Array[ind+1] = new long[3];
            Array[ind+1][0] = queries[0];
            Array[ind+1][1] = queries[1];
            Array[ind+1][2] = Array[ind][2] + queries[2];
            System.out.println("If 2");
        }

        else if (Array[ind][0] == queries[0] && Array[ind][1] > queries[1]){
            size++;
            for(int i=size -1 ;i>ind+1;i--){
                Array[i] = Array[i-1];
            }
            Array[ind+1] = new long[3];
            Array[ind+1][0] = queries[1] + 1;
            Array[ind+1][1] = Array[ind][1];
            Array[ind+1][2] = Array[ind][2];
            Array[ind][1] = queries[1];
            Array[ind][2] += queries[2];
            System.out.println("If 3");
        }

        else if (Array[ind][0] < queries[0] && Array[ind][1] > queries[1]){
            
            size+=2;
            for(int i=size -1 ;i>ind+2;i--){
                Array[i] = Array[i-2];
            }
            Array[ind+1] = new long[3];
            Array[ind+2] = new long[3];
            Array[ind+2][0] = queries[1]+1;
            Array[ind+2][1] = Array[ind][1];
            Array[ind+2][2] = Array[ind][2];
            Array[ind+1][0] = queries[0];
            Array[ind+1][1] = queries[1];
            Array[ind+1][2] = Array[ind][2] + queries[2];
            Array[ind][1] = queries[0]-1;
            System.out.println("If 4");
        }
        
        else if(Array[ind][0] == queries[0] && Array[ind][1] < queries[1]){
            Array[ind][2] += queries[2];
            int index = ind+1;
            for(int i=ind+1;i<size;i++){
                if(Array[i][1] < queries[1])
                    Array[i][2] += queries[2];
                else{
                    index = i;
                    break;
                }
            }
            
           size++;
            for(int i=size -1 ;i>index+1;i--){
                Array[i] = Array[i-1];
            }
            Array[index+1] = new long[3];
            Array[index+1][0] = queries[1] + 1;
            Array[index+1][1] = Array[index][1];
            Array[index+1][2] = Array[index][2];
            Array[index][1] = queries[1];
            Array[index][2] += queries[2];
            System.out.println("If 5");
        }
        else if(Array[ind][0] < queries[0] && Array[ind][1] < queries[1]){
            size++;
            for(int i=size -1 ;i>ind+1;i--){
                Array[i] = Array[i-1];
            }

            /*for(int i=0;i<size;i++){
                System.out.print(String.valueOf(Array[i][0]) + " ");
                System.out.print(String.valueOf(Array[i][1])+ " ");
                System.out.println(String.valueOf(Array[i][2])+ " ");
            }*/

            Array[ind+1] = new long[3];
            Array[ind+1][0] = queries[0];
            Array[ind+1][1] = Array[ind][1];
            Array[ind+1][2] = Array[ind][2] + queries[2];
            Array[ind][1] = queries[0] - 1;

            for(int i=0;i<size;i++){
                System.out.print(String.valueOf(Array[i][0]) + " ");
                System.out.print(String.valueOf(Array[i][1])+ " ");
                System.out.println(String.valueOf(Array[i][2])+ " ");
            }

            boolean check = false;
            int index = ind+2;
            for(int i=ind+2;i<size;i++){
                if(Array[i][1] <= queries[1])
                    Array[i][2] += queries[2];
                else{
                    check = true;
                    index = i;
                    break;
                }
            }

            if(check){
                size++;
                for(int i=size -1 ;i>index+1;i--){
                    Array[i] = Array[i-1];
                }
                Array[index+1] = new long[3];
                Array[index+1][0] = queries[1] + 1;
                Array[index+1][1] = Array[index][1];
                Array[index+1][2] = Array[index][2];
                Array[index][1] = queries[1];
                Array[index][2] += queries[2];
            }
            System.out.println("If 6");
        }

        return size;
    }

    // Complete the arrayManipulation function below.
    static long arrayManipulation(int n, int[][] queries) {

        int length = queries.length;
        long Array[][] = new long[2*length][];
        int size;

        if(queries[0][0] == 1){
            Array[0] = new long[3];
            Array[1] = new long[3];
            for(int i=0;i<3;i++){
                Array[0][i] = queries[0][i];
            }
            Array[1][0] = Array[0][1] + 1;
            Array[1][1] = n;
            Array[1][2] = 0;
            size =  2;
        }
        else{
            Array[0] = new long[3];
            Array[1] = new long[3];
            Array[2] = new long[3];
            for(int i=0;i<3;i++){
                Array[1][i] = queries[0][i];
            }
            Array[0][0] = 1;
            Array[0][1] = queries[0][0] - 1;
            Array[0][2] = 0;
            Array[2][0] = queries[0][1] + 1;
            Array[2][1] = n;
            Array[2][2] = 0;
            size = 3;
        }

        for(int i=1;i<length;i++){
            size = AddInArray(Array,queries[i],size);
            System.out.println("Iteration : " + String.valueOf(i));
            for(int j=0;j<size;j++){
                System.out.print(Long.toString(Array[j][0]) + ", ");
                System.out.print(Long.toString(Array[j][1]) + ", ");
                System.out.println(Long.toString(Array[j][2]));
            }
        }

        long max = Array[0][2];
        for(int i=1;i<size;i++)
        {
            if(max < Array[i][2])
                max = Array[i][2];
        }
        return max;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] queries = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        long result = arrayManipulation(n, queries);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
