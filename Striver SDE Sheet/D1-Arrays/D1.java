// Kadane's Algorithm
// Appraoch-A: Brute Force
// TC = O(N^3), SC = O(1)

public class D1 {
    public static int maxSubarraySum(int[] arr, int n){
        int max = Integer.MIN_VALUE;

        for(int i = 0; i<n; i++){
            for(int j = i; j<n; j++){
                int sum = 0;
                for(int k =i; k<=j; k++){
                    sum += arr[k];
                }
                max = Math.max(max, sum);
            }
        }
        return max;
    }
}
