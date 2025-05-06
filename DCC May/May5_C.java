//790. Domino and Tromino Tiling
// Appraoch-C: Bottom-up DP
// TC = O(N), SC = O(N)
/*
 1. If only dominoes would been used, we get :
    If only trominoes were used we get:
2. Consider 2*N tile divided into two part: F(N-1) and F(1), F(N-2) and F(2), F(N-3) and F(3), ... , F(0) and F(N).
3. So, we can use the above cases to get the recurrence relation. 
4. Recurrence relation: 
    Other cases are subproblems covered in addition, So:
    f(1) = 1, f(2) = 2, f(3,4,5...) = 2{Tromino will give unique cases/arrangements}
    f(N) = 1*f(N-1) + 1*f(N-2) + 2·f(N-3) + 2·f(N-4) + 2·f(N-5) + ... + 2·f(0)         -- (i)
    f(N-1) = 1*f(N-2) + 1*f(N-3) + 2·f(N-4) + 2·f(N-5) + ... + 2·f(0)                 -- (ii)
    f(N) - f(N-1) = f(N-1) + f(N-3)
    ⇒ f(N) = 2·f(N-1) + f(N-3)
 */
public class May5_C {
    public int numTilings(int n){
        int MOD = 1_000_000_007;

        if(n <= 1) return 1;
        if(n == 2) return 2;
        if(n == 3) return 5;

        long[] dp = new long[n+1];
        dp[0] = 1; dp[1] = 1; dp[2] = 2; dp[3] = 5;

        for(int i = 4; i<= n; i++){
            dp[i] = (2*dp[i-1]%MOD + dp[i-3]) % MOD;
        }
      return (int) dp[n];

    }
}
