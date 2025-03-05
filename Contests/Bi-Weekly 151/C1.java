//3469. Find Minimum Cost to Remove Array Elements
// Approach-1: DP Memoizaation+Recursive DP (Top-Down) with a 2D DP table 
// Time Complexity: O(n^2), Space Complexity: O(n^2)-> 2D-Array DP Table
/*
 1. Use of 2D dp array to store the minimum cost to remove the elements from the array.
 */
import java.util.Arrays;

public class C1 {
        public int minCost(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n+1][n+1];
        for(int[] row: dp) Arrays.fill(row, -1); //for uncomputed values
        return solve(0,1, nums, dp); //start recusrion from 0th index
    }
       // recursive fxn
    private int solve(int prevIdx, int idx, int[] nums, int[][] dp){
        int n = nums.length;
        // Base case
        if(idx == n) return nums[prevIdx]; //1 element left
        if(idx == n-1) return Math.max(nums[prevIdx], nums[idx]); //2 elements left
        if(dp[idx][prevIdx] != -1) return dp[idx][prevIdx]; //subproblem already solved

        // Recursive choices
        int op1 = Math.max(nums[idx], nums[idx +1]) + solve(prevIdx, idx + 2, nums,dp);
        int op2 = Math.max(nums[prevIdx], nums[idx + 1]) + solve(idx, idx + 2, nums, dp); //keep idx as prevIdx
        int op3 = Math.max(nums[prevIdx], nums[idx]) + solve(idx + 1, idx + 2, nums, dp); //  keep idx + 1 as prevIdx

        return dp[idx][prevIdx] = Math.min(op1, Math.min(op2, op3));
    }
}
