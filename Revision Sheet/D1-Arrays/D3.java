// Kadane's Algorithm
// Appraoch-3: Best
// TC = O(N), SC = O(1)

public class D3 {
     public static long maxSubarraySum(int[] arr, int n){
        long max = Long.MIN_VALUE;
        long sum = 0;

        // To keep track of subarray
        int start = 0;
        int ansStart = -1, ansEnd = -1;

        for(int i = 0; i< n ; i++){
            
            if (sum == 0) start = i; // starting index
            
            sum += arr[i];
            if(sum > max) {
                max = sum;

                ansStart = start;
                ansEnd = i;
            }
            
            if(sum <0) sum = 0;
        }
        return max;
     }
}
