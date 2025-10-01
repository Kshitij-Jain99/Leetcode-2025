//3699. Number of ZigZag Arrays I: https://leetcode.com/problems/number-of-zigzag-arrays-i/description/
//3700. Number of ZigZag Arrays II: https://leetcode.com/problems/number-of-zigzag-arrays-ii/
import java.util.*;

public class I {
    // Approach-1(TLE): Brute Force(DFS/Backtracking)
    // TC = O((r-l+1)^n), SC = O(n)
/*
 1. Generating all possible arrays of length n from the range [l, r] and checking whether each one forms a valid ZigZag sequence.
 2. Zig-zag condition check:
    - odd index (1, 3, 5...) → arr[i] > arr[i-1] (increasing step).
    - even index (2, 4, 6...) → arr[i] < arr[i-1] (decreasing step).
 3. If it violates the condition, we prune (stop exploring this branch).
 4. If we successfully place numbers at all n positions, we have found a valid ZigZag array → increment count.
 5. At each position, we try up to (r-l+1) numbers.
 6. If n=10, l=1, r=10 → 10^10 ≈ 10 billion arrays to check. -> TLE
    */
    int mod = (int)1e9+7;
    public int count = 0;

    public int zigZagArrays1(int n, int l, int r) {
        backtrack(new ArrayList<>(), n, l, r);
        return (count * 2) % mod;
    }

    private void backtrack(List<Integer> arr, int n, int l, int r) {
        if (arr.size() == n) {
            count++;
            return;
        }
        for (int x = l; x <= r; x++) {
            int size = arr.size();
            if (size > 0) {
                int prev = arr.get(size-1);
                if ((size % 2 == 1 && x <= prev) || (size % 2 == 0 && x >= prev)) {
                    continue; // violates zigzag condition
                }
            }
            arr.add(x);
            backtrack(arr, n, l, r);
            arr.remove(arr.size()-1);
        }
    }

    
    // Approach-2: Bottom up DP + Prefix(inc step)/Suffix(dec step) Sum
    // TC = O(n * (r-l+1)), SC = O(r+1)
/*
 1. Prefix Sum / Suffix Sum Optimization – avoids recomputing summations for each state by accumulating running sums.
 2. Array Rolling Technique – uses two arrays (dp and next_dp) instead of full 2D DP, saving memory.
 3. We want to count arrays of length n where consecutive numbers alternate (inc/dec).
 4. Base case: When length = 1, every number in [l, r] is a valid array → initialize dp[x] = 1.
 5. Instead of iterating for each possible (prev, next) pair (which is O((r-l+1)^2) per step), we use prefix/suffix sums:
    - Increasing step: next_dp[x] = sum(dp[y] for y < x) → use prefix sum.
    - Decreasing step: next_dp[x] = sum(dp[y] for y > x) → use suffix sum.
    */
    public int zigZagArrays2(int n, int l, int r){
        int mod = (int)((1e9) + 7);

        int[] dp = new int[r+1];    //Better -> dp[r-l+1] such that ele. before dp[l] are not considered, but it does not affect much
        Arrays.fill(dp,1);    //initially for length = 1, dp[x] = 1 for all x in [l,r]

        for(int i = 1; i<n; i++){
            int[] next_dp = new int[r+1];
            
            //inc, dec, inc, dec, ... -> We can reverse it by changing if to else
            if(i % 2 == 1){  // odd -> increasing, i = 1, 3....
                long pre = 0;
                for(int x = l; x<=r; x++){  // l -> r
                    next_dp[x] = (int)pre;
                    pre = (pre + dp[x]) % mod;
                } 
            } else{   // even  -> decreasing, i = 2,4...
                long suff = 0;
                for(int x = r; x>=l; x--){   // r -> l
                    next_dp[x] = (int)suff;
                    suff = (suff + dp[x]) % mod;
                }
            }
            dp = next_dp;
        }
        
        long count = 0;
        for(int x =l; x<=r; x++){
            count = (count + dp[x]) % mod;
        }
        return (int)((count*2) % mod);
    }

    //Approach-3: Matrix Exponentiation (Code by Akash_Kumar)
    //TC = O(2.(m)^3.log n) = O(m^3.log n), SC = O(2.(m)^2) = O(m^2) where m = r-l+1
    /*
     1. This problem can be modeled as matrix exponentiation over states representing "next comparison direction" and current value.
     2. Let ( m = r - l + 1 ). Encode states in a vector of length 2*m:
        - First m entries: next comparison must be down
        - Last m entries: next comparison must be up
     3. Build a transition matrix T of size 2*m × 2*m:
        - From up, x → down, y for all y > x
        - From down, x → up, y for all y < x
     4. Initialize the vector v0 with all ones (starting from any value, can go up or down).
     5. Use fast matrix exponentiation to compute T^(n-1) and multiply by v0.
     6. Sum all entries of the resulting vector for the answer.

     */
     static final int MOD = 1_000_000_007;

     public int zigZagArrays3(int n, int l, int r){
        int m = r-l+1;         //number of values
        int size = 2*m;        //total state space

        if(n == 1) return m;

        //Build transition matrix
        long[][] T = new long[size][size];
        
        for(int i = 0; i<m; i++){
            // from down, i -> up, j for j<i
            for(int j = 0; j < i; j++) T[i][j + m] = 1;

            // from up, i -> down, j for j > i
            for(int j = i+1; j < m; j++) T[i + m][j] = 1;
        }

        // Exponentiate T^(n - 1)
        long[][] Tn = matrixPower(T, n-1);     

        //Initialize vector v0: all ones
        long[] v0 = new long[size];
        Arrays.fill(v0, 1L);

        // Multiply Tn * v0
        long[] result = multiplyMatrixVector(Tn, v0);

        // Sum all entries
        long total = 0;
        for (long val : result) total = (total + val) % MOD;

        return (int) total;
     }

      private long[][] matrixPower(long[][] mat, int exp) {
        int n = mat.length;
        long[][] res = new long[n][n];

        // Initialize res as identity matrix
        for (int i = 0; i < n; i++) res[i][i] = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = multiplyMatrices(res, mat);
            }
            mat = multiplyMatrices(mat, mat);
            exp >>= 1;
        }

        return res;
    }

    private long[][] multiplyMatrices(long[][] A, long[][] B) {
        int n = A.length;
        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < n; j++) {
                    res[i][j] = (res[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return res;
    }

    private long[] multiplyMatrixVector(long[][] mat, long[] vec) {
        int n = mat.length;
        long[] res = new long[n];
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = 0; j < n; j++) {
                sum = (sum + mat[i][j] * vec[j]) % MOD;
            }
            res[i] = sum;
        }
        return res;
    }
}
