//3473. Sum of K Subarrays With Length at Least M
// Approach 2: Bottom Up DP 
// TC = O(n*n*2), k=n worst case, SC = O(n*n*2) ->same as TC
/*
For Complex bottom up, Make Top-down and then convert it to bottom Up
 */

class C2 {
    private static final int NEG_INF = -1000000000;
    
    public int maxSum(int[] nums, int k, int m) {
        int n = nums.length;
        int[] prefix = new int[n + 1];
        
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        
        int[][][] dp = new int[n + 1][k + 1][2];
        
        for (int r = 0; r <= k; r++) {
            dp[n][r][0] = (r == 0) ? 0 : NEG_INF;
            dp[n][r][1] = (r == 0) ? 0 : NEG_INF;
        }
        
        for (int index = n - 1; index >= 0; index--) {
            for (int remaining = 0; remaining <= k; remaining++) {
                if (remaining == 0) {
                    dp[index][0][0] = 0;
                    dp[index][0][1] = Math.max(nums[index] + dp[index + 1][remaining][1], dp[index][remaining][0]);
                } else {
                    int opt2 = NEG_INF;
                    if (m <= n - index) {
                        opt2 = (prefix[index + m] - prefix[index]) + dp[index + m][remaining - 1][1];
                    }
                    dp[index][remaining][0] = Math.max(dp[index + 1][remaining][0], opt2);
                    dp[index][remaining][1] = Math.max(nums[index] + dp[index + 1][remaining][1], dp[index][remaining][0]);
                }
            }
        }
        
        return dp[0][k][0];
    }
}
