//3473. Sum of K Subarrays With Length at Least M
// Approach 1: Top Down DP 
// TC = O(n*n*2), k=n worst case, SC = O(n*n*2) ->same as TC
/*
 Least Optimized:
 1. Recursion is time consuming
 2. It has very high runtime.
 */
import java.util.*;

class Solution {
    private static final int NEG_INF = -1000000000;
    private Integer[][][] dp;
    
    private int solve(List<Integer> nums, int[] prefix, int index, int remaining, int canExtend, int m) {
        //Base case
        if (index == nums.size()) return remaining == 0 ? 0 : NEG_INF; //all k should be used
        //memoization case
        if (dp[index][remaining][canExtend] != null) return dp[index][remaining][canExtend];
        
        int best = NEG_INF;
        if (canExtend == 1) {
            best = Math.max(best, nums.get(index) + solve(nums, prefix, index + 1, remaining, 1, m));
            best = Math.max(best, solve(nums, prefix, index, remaining, 0, m));
        } else {
            if (remaining == 0) return 0;
            best = Math.max(best, solve(nums, prefix, index + 1, remaining, 0, m));
            if (nums.size() - index >= m) {
                int currentSum = prefix[index + m] - prefix[index];
                best = Math.max(best, currentSum + solve(nums, prefix, index + m, remaining - 1, 1, m));
            }
        }
        
        return dp[index][remaining][canExtend] = best;
    }
    
    public int maxSum(List<Integer> nums, int k, int m) {
        int n = nums.size();
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) { //PRECOMPUTATION
            prefix[i + 1] = prefix[i] + nums.get(i);
        }
        
        dp = new Integer[n + 1][k + 1][2];
        return solve(nums, prefix, 0, k, 0, m);
    }
}
