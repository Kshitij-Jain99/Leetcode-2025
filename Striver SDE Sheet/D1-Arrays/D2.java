// Kadane's Algorithm
// Appraoch-B: Better Brute Force
// TC = O(N^2), SC = O(1)
/*
 1. Dont need to calculate sum of each next subarray from start, we can just add sum of previous subarray to current one.
 */
public class D2 {
     public static int maxSubarraySum(int[] arr, int n) {
        int max = Integer.MIN_VALUE;
         for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++){
                sum += arr[j];
                max = Math.max(max, sum);
            }
        }
        return max;
        
     }
}
