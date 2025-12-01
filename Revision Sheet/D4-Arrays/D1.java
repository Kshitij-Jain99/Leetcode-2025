//Length of the longest subarray with zero Sum
// Approach-1: Brute Force
// TC = O(N^2), SC = O(1)

public class D1 {
    public static int solve(int[] a){
        int max = 0;
        for(int i = 0; i<a.length; i++){
            int sum = 0;
            for(int j = i; j<a.length; ++j){
                sum += a[j];
                if(sum == 0) max = Math.max(max, j-i+1);
            }
        }
        return max;
    }
}
