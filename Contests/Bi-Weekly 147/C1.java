//3409. Longest Subsequence With Decreasing Adjacent Difference
// Appraoch A: Most Optimum: Bottom Up DP
// TC = O(N*300), SC = O(300*300)

class C1 {

    int[][] dp = new int[301][301]; //[currNum][Diff], 300 size can be taken also for diff
    int[][] suffixMax = new int[301][301]; // also can [currNum][Diff] = [301][300]

    public int longestSubsequence(int[] nums) {
        int ans = 1;  // 1 element initially

        for (int num : nums) {
            //for any num with no diff
            if (dp[num][0] < 1) {
                dp[num][0] = 1; //starting with 1 len
            }

            for (int prevNum = 1; prevNum <= 300; prevNum++) {
                int dNew = Math.abs(num - prevNum);
                int bestChain = suffixMax[prevNum][dNew];

                if (bestChain > 0) { //continue
                    dp[num][dNew] = Math.max(dp[num][dNew], bestChain + 1);
                } else {
                    if (suffixMax[prevNum][0] > 0) { //fresh but prevNum should exsts
                        dp[num][dNew] = Math.max(dp[num][dNew], 2);
                    }
                }

                ans = Math.max(ans, dp[num][dNew]);
            }

            suffixMax[num][299] = dp[num][299];
            for (int d = 298; d >= 0; d--) {
                suffixMax[num][d] = Math.max(suffixMax[num][d + 1], dp[num][d]);
            }
        }

        return ans;
    }
}
