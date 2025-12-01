
//Grid Unique Paths
// Approach-3: Combinatorics
// TC = O(Max.(m,n)), SC  = O(1)
/*
 1. (0,0) -> (m-1, n-1) --> move = m-1 + n-1 = m+n-2
 2. Number of unique paths = C(m+n-2, m-1) = C(m+n-2, n-1)
 */
public class E3 {
    public static int uniquePaths(int m, int n){
        int N = n+m-2;
        int r = m-1;
        double res = 1;

        for(int i =1; i<= r; i++) res = res*(N - r + i)/i;

        return (int)res;
    }
}
