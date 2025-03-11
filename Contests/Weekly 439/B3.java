//3472. Longest Palindromic Subsequence After at Most K Operations
//Approach 3 : Optimized Interval DP -> Better than 1, 2
// TC = O(n*n*k) -> faster constant factors, SC = O(n*n*k)
/*
 Optimizations:
 1. Early Pruning with Direct Cost Calculation: 
    It precomputes the total cost required to make the entire string a palindrome (cnt).
    This drastically reduces execution time for cases where K is large.
2. More Efficient DP Table Layout:
   Instead of dp[n][n][K], it optimizes the DP table as f[K][n][n], reducing cache misses and improving performance.
3. Iterates in Reverse Order (i from n-1 to 0):
   This order ensures that subproblems for smaller substrings are computed before they are used in larger substrings.
   This improves cache efficiency and speeds up execution.

 */

class B3 {
    public int longestPalindromicSubsequence(String S, int K) {
        char[] s = S.toCharArray(); //for faster access
        int n = s.length;
        int cnt = 0;
        for (int i = 0; i < n / 2; i++) {
            //cost to change s[i] into s[n-1-i] directly.
            int d = Math.abs(s[i] - s[n - 1 - i]);
            cnt += Math.min(d, 26 - d);
        }
        if (cnt <= K) {
            //This early pruning avoids unnecessary DP computation in favorable cases
            return n;
        }
//f[k][i][j] stores the LPS length in s[i..j] using at most k modifications
        int[][][] f = new int[K + 1][n][n];
        for (int k = 0; k <= K; k++) { // Iterate over all allowed operations
            for (int i = n - 1; i >= 0; i--) {  //left to right to get smaller substring computations first
                f[k][i][i] = 1; // Base case: Single character is always a palindrome
                
                for (int j = i + 1; j < n; j++) {
                    int res = Math.max(f[k][i + 1][j], f[k][i][j - 1]);
                    int d = Math.abs(s[i] - s[j]);
                    int op = Math.min(d, 26 - d);
                    if (op <= k) {
                        res = Math.max(res, f[k - op][i + 1][j - 1] + 2);
                    }
                    f[k][i][j] = res;
                }
            }
        }
        return f[K][0][n - 1];
    }
}

