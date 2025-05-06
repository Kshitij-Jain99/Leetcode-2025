//790. Domino and Tromino Tiling
// Appraoch-B: Recursion+ Memoization
// TC = O(N), SC = O(N)

public class May5_B {
    final int M = 1000000007;
    int[] t = new int[1001];

    public int solve(int n){
        if(n == 1 || n == 2) return n;
        if(n == 3) return 5;
        if(t[n] != -1) return t[n];

        t[n] = (int)((2L*solve(n-1)%M + solve(n-3))%M);
        return t[n];
    }

    public int numTilings(int n){
        // initialize memoization array with -1
        for(int i = 0; i<= n; i++ ){
            t[i] = -1;
        }

        return solve(n);
    }
}
