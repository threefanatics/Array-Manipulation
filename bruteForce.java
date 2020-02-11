import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {


    static long findMax(long arr[]){

        long max = arr[0];
        int length = arr.length;

        for(int i=1;i<length;i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }

        return max;
    }
    // Complete the arrayManipulation function below.
    static long arrayManipulation(int n, int[][] queries) {

        long arr[] = new long[n];
        Arrays.fill(arr, 0);

        int length = queries.length;
        for(int i=0;i<length;i++){
            int start = queries[i][0] - 1 ;
            int end = queries[i][1] - 1 ;
            for(int j=start; j <= end;j++)
                arr[j] += queries[i][2];
        }

        return findMax(arr);
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
