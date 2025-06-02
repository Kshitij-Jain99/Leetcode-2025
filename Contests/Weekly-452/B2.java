
//3567. Minimum Absolute Difference in Sliding Submatrix
// Appraoch-2: Optimized Approch{Sliding Window + TreeMap}
// TC =  O((m−k+1)(n−k+1) * (k.log k + d)){d-distinct elements}, SC=  O((m − k + 1)(n − k + 1))
/*
 1. Why Optimization is Needed?
   a. Brute-force overhead:
     - For every k x k submatrix: copying k² elements -> Sorting them → O(k² log k)
     - Repeating this for every (m−k+1) × (n−k+1) submatrix.
   b. Redundant work: 
     -  Adjacent windows share most of their elements.

  2. Optimizations:
   a. Use a more efficient data structure: TreeMap<Integer, Integer>
     - Keeps elements sorted, Stores frequencies (to handle duplicates)
     - Can iterate in sorted order to compute min difference
     -Supports O(log n) insert/delete
 */
import java.util.*;

public class B2 {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] ans = new int[m - k + 1][n - k + 1];

        //Iterate Over All Possible Starting Rows for Submatrices
        for (int i = 0; i <= m - k; i++) {
            TreeMap<Integer, Integer> freqMap = new TreeMap<>();

            // Build initial window for row i, col 0 upto k rows
            for (int r = i; r < i + k; r++) {
                for (int c = 0; c < k; c++) {
                    //Add all elements to freqMap and count their frequency.
                    freqMap.put(grid[r][c], freqMap.getOrDefault(grid[r][c], 0) + 1);
                }
            }
            //After building curr window, compute min diff for the first submatrix
            ans[i][0] = computeMinDiff(freqMap);

            // Slide window to right-Column by column
            for (int j = 1; j <= n - k; j++) {
                for (int r = i; r < i + k; r++) {
                    // remove the leftmost column
                    int left = grid[r][j - 1];
                    freqMap.put(left, freqMap.get(left) - 1);
                    if (freqMap.get(left) == 0)
                        freqMap.remove(left);

                    // add the new rightmost column
                    int right = grid[r][j + k - 1];
                    freqMap.put(right, freqMap.getOrDefault(right, 0) + 1);
                }
                ans[i][j] = computeMinDiff(freqMap);
            }
        }
        return ans;
    }

    private int computeMinDiff(TreeMap<Integer, Integer> freqMap) {
        if (freqMap.size() == 1)
            return 0;

        int minDiff = Integer.MAX_VALUE;
        Integer prev = null;
        for (int val : freqMap.keySet()) {
            if (prev != null) {
                minDiff = Math.min(minDiff, val - prev);
                if (minDiff == 0)
                    return 0;
            }
            prev = val;
        }
        return minDiff;
    }
}
