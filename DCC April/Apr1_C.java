//2140. Solving Questions With Brainpower
// Appraoch-C: Bottom-Up DP type(Hybrid Of Bottom UP + Top Down) 
// TC = O(N), SC = O(N) where N is the number of questions
/*
 1. Combines both recursive memoization and bottom-up DP. However, the actual computation uses bottom-up DP, 
 making it more efficient than the recursive approach.
 2. No extra function calls, No recursion overhead.
 3. 
 */
class Apr1_C {
    private long calcMaxPoints(int[][] questions, int index, int n, long[] dp) {
        if (index == n) return 0;
        if (dp[index] != -1) return dp[index];

        long take = questions[index][0];
        int newIndex = index + questions[index][1] + 1;
        if (newIndex < n) take += calcMaxPoints(questions, index + questions[index][1] + 1, n, dp);
        long notTake = calcMaxPoints(questions, index + 1, n, dp);

        return dp[index] = Math.max(take, notTake);
    }
    
    public static long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n];
        // Arrays.fill(dp, -1);
        // return calcMaxPoints(questions, 0, n, dp);
        dp[n - 1] = questions[n - 1][0];
        for (int i = n - 2 ; i >= 0 ; i--) {
            long take = questions[i][0];
            int newIndex = i + questions[i][1] + 1;
            take += (newIndex < n) ? dp[newIndex] : 0;
            long notTake = dp[i + 1];
            dp[i] = Math.max(take, notTake);
        }

        return dp[0];
    }
}