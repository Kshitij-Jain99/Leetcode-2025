//3473. Sum of K Subarrays With Length at Least M
// Approach 3: Bottom Up DP (Space Optimized)
// TC = O(n*k), k=n worst case, SC = O(n + k) 
/*
 1. Dynamic Programming with multiple DP tables
 */
import java.util.*;

class Solution {
    private static final int NEG_INF = -1000000000;

    public int maxSum(int[] nums, int k, int m) {
        int n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int[][] curr = new int[k + 1][2];
        int[][] nextDP = new int[k + 1][2];
        int[][] next2DP = new int[k + 1][2];
        int[][] next3DP = new int[k + 1][2];
        
        for (int r = 0; r <= k; r++) {
            nextDP[r][0] = (r == 0 ? 0 : NEG_INF);
            nextDP[r][1] = (r == 0 ? 0 : NEG_INF);
            next2DP[r][0] = (r == 0 ? 0 : NEG_INF);
            next2DP[r][1] = (r == 0 ? 0 : NEG_INF);
            next3DP[r][0] = (r == 0 ? 0 : NEG_INF);
            next3DP[r][1] = (r == 0 ? 0 : NEG_INF);
        }

        for (int index = n - 1; index >= 0; index--) {
            curr = new int[k + 1][2];
            
            for (int remaining = 0; remaining <= k; remaining++) {
                if (remaining == 0) {
                    curr[0][0] = 0;
                    curr[0][1] = Math.max(nums[index] + nextDP[0][1], curr[0][0]);
                } else {
                    int opt1 = nextDP[remaining][0];
                    int opt2 = NEG_INF;
                    
                    if (index + m <= n) {
                        int rangeSum = prefix[index + m] - prefix[index];
                        if (m == 1) {
                            opt2 = rangeSum + nextDP[remaining - 1][1];
                        } else if (m == 2) {
                            opt2 = rangeSum + next2DP[remaining - 1][1];
                        } else if (m == 3) {
                            opt2 = rangeSum + next3DP[remaining - 1][1];
                        }
                    }
                    curr[remaining][0] = Math.max(opt1, opt2);
                    curr[remaining][1] = Math.max(nums[index] + nextDP[remaining][1], curr[remaining][0]);
                }
            }
            next3DP = next2DP;
            next2DP = nextDP;
            nextDP = curr;
        }
        return nextDP[k][0];
    }
}