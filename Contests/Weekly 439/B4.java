//3472. Longest Palindromic Subsequence After at Most K Operations
//Approach 4 : Best Approach, Most Optimized
// TC = O(n*n*k) , SC = O(2*n*k) = O(n*k)
/*
 1. Reduces 3D DP array to 2D using bitwise tricks:
    Uses a rolling 2-row DP table (dp[2][n+2][K+1]) instead of a full O(n² × K) table.
    Since dp[i][j][k] only depends on dp[i+1][j-1][k], we only need the previous row, so we cycle between two rows.
    This is done by replacing dp[i][j][k] with dp[i & 1][j][k], where i & 1 alternates between 0 and 1 (modulo 2).
    This greatly reduces memory usage while still maintaining correct DP transitions.
2. Bitwise Trick for Space Optimization:
    Uses i & 1 instead of i % 2, which is a faster bitwise operation.
 */
class B4 {
    public int longestPalindromicSubsequence(String s, int K) {
        int n = s.length();
        int[][][] dp = new int[2][n + 2][K + 1];

        //Fill DP Table in Reverse Order
        for (int i = n; i >= 1; i--) {  // Smaller SS -> Larger SS
            for (int j = i; j <= n; j++) { //Builds palindromes of increasing length.
                for (int k = 0, ans; k <= K; dp[i & 1][j][k] = ans, k++) { //Stores the longest palindrome length given at most k modifications.
                    if (i == j) ans = 1;
                    else if (s.charAt(i - 1) == s.charAt(j - 1))
                        ans = 2 + dp[(i + 1) & 1][j - 1][k];
                    else {
                        ans = Math.max(dp[(i + 1) & 1][j][k], dp[i & 1][j - 1][k]);
                        int abs = Math.abs(s.charAt(j - 1) - s.charAt(i - 1));
                        int diff = Math.min(abs, 26 - abs);
                        if (k >= diff)
                            ans = Math.max(ans, 2 + dp[(i + 1) & 1][j - 1][k - diff]);
                    }
                }
            }
        }
        return dp[1][n][K];
    }
}
