//3472. Longest Palindromic Subsequence After at Most K Operations
//Approach 1 : Top Down Memoization(Recursive 3D DP) 
// TC = O(n*n*k), SC = O(n*n*k) + O(n -> Recursion stack)
/*More inuitive appraoch->The core idea is to either skip a character or modify it to match the other side, and recursively compute the maximum palindromic subsequence. */
class B1 {
    public int solve(int left, int right, int ops, String s, int[][][] dp){
        if(left > right) return 0;  //invalid case
        if(left == right) return 1;  // Single char string
        if(dp[left][right][ops] != -1) return dp[left][right][ops]; //memoization case , i.e result for that state is calculated then return it.
         
        // left skip or right skip
        int maxLength = Math.max(solve(left + 1, right, ops, s, dp), solve(left, right-1, ops, s, dp));
        //forward cost and backward cost to make left and right characters same
        int opsReq = Math.min(Math.abs(s.charAt(left) -  s.charAt(right)), 26 - Math.abs(s.charAt(left) - s.charAt(right)));

        // when we can perform 3rd operation of changing the characters
        if(ops >= opsReq){
            //This increases the palindrome length by 2 (since we matched two characters) then check for rem. substring
            maxLength = Math.max(maxLength, 2+ solve(left + 1, right - 1, ops - opsReq, s, dp ));
        }
        dp[left][right][ops] = maxLength;
        return maxLength;
    }

    public int longestPalindromicSubsequence(String s, int k) {
        int n = s.length();
        int[][][] dp = new int[n][n][k + 1];

        for(int i =0; i<n; i++){
            for(int j =0; j<n; j++){
                for(int l =0; l<=k; l++){ // l -> operations used, k is max operations allowed
                    dp[i][j][l] = -1; //uncompuetd state
                }
            }
        }
        return solve(0, n-1, k, s, dp);
    }
}