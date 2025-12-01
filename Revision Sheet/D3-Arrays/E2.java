//Grid Unique Paths
// Approach-2: DP(Top down)
// TC = O(N*M){Max. states}, SC  = O(N*M){DP table}

import java.util.Arrays;

public class E2 {
    public static void main(String args[]) {
        int m = 3, n = 2;

        // Initialize DP array
        int[][] dp = new int[m][n];
        for (int[] row : dp) Arrays.fill(row, -1);

        // Call recursive countPaths with memoization
        int numPaths = countPaths(0, 0, m, n, dp);

        System.out.println("Unique paths: " + numPaths);
    }

    public static int countPaths(int i, int j, int m, int n, int[][] dp) {
        if (i == m - 1 && j == n - 1) return 1;
        if (i >= m || j >= n) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        return dp[i][j] = countPaths(i + 1, j, m, n, dp) + countPaths(i, j + 1, m, n, dp);
    }
}


