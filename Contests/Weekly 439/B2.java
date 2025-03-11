//3472. Longest Palindromic Subsequence After at Most K Operations
//Approach 2 : Bottom-Up 3D DP (Iterative)
// TC = O(n*n*k), SC = O(n*n*k)
// More optimized than Approach 1: due to direct computation and no recursion stack overhead.

class B2 {
    // Function to calculate the cost of changing character 'c' to 'b'
    private int calcost(char c, char b) {
        return Math.min(Math.abs(c - b), 26 - Math.abs(c - b)); // Considering wrap-around distance
    }

    public int longestPalindromicSubsequence(String s, int k) {
        int n = s.length();

        // 3D DP array: dp[i][j][m] stores the longest palindromic subsequence in s[i..j]
        // using at most m modifications.
        int[][][] dp = new int[n][n][k + 1];

        // Base case: A single character is always a palindrome of length 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][i][j] = 1;
            }
        }

        // Base case: Two consecutive characters
        for (int i = 0; i < n - 1; i++) {
            int cost = calcost(s.charAt(i), s.charAt(i + 1));
            for (int j = 0; j <= k; j++) {
                if (j >= cost) 
                    dp[i][i + 1][j] = 2;  // We can make them equal
                else 
                    dp[i][i + 1][j] = 1;  // Otherwise, the best we can do is 1 character as palindrome
            }
        }

        // DP transition: Expanding for substrings of length 3 to n
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int start = i, end = i + len - 1;
                int cost = calcost(s.charAt(start), s.charAt(end));

                for (int j = 0; j <= k; j++) {
                    // If we can make s[start] == s[end], consider two possibilities:
                    // 1. Use `cost` operations to make them equal and take the middle palindrome
                    // 2. Do not modify them and just take the middle palindrome
                    if (j >= cost) {
                        dp[start][end][j] = Math.max(2 + dp[start + 1][end - 1][j - cost], dp[start + 1][end - 1][j]);
                    }

                    // Always maximize with cases where we ignore one of the ends
                    dp[start][end][j] = Math.max(dp[start][end][j], Math.max(dp[start][end - 1][j], dp[start + 1][end][j]));
                }
            }
        }

        return dp[0][n - 1][k];
    }
}
