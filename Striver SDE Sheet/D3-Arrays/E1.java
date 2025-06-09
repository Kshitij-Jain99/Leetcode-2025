//Grid Unique Paths
// Approach-1: Brute Force
// TC = O(2^(n + m)), SC  = O(n + m){max path length}

public class E1 {

    // Recursive method to count unique paths from (i,j) to (n-1,m-1)
    public int countPaths(int i, int j, int n, int m) {
        // Base Case: Reached destination
        if (i == (n - 1) && j == (m - 1)) return 1;

        // Out of bounds
        if (i >= n || j >= m) return 0;

        // Move down or right
        return countPaths(i + 1, j, n, m) + countPaths(i, j + 1, n, m);
    }

    public static void main(String[] args) {
        E1 solver = new E1();

        // Direct function call with fixed values
        int totalPaths = solver.countPaths(0, 0, 3, 3);

        System.out.println("Total Unique Paths: " + totalPaths);
    }
}

