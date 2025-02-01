//827. Making A Large Island
// Approach B: Island Size Tracking[DFS]
// TC = O(N^2), SC = O(N^2)
import java.util.*;
//Fixed code reduces unnecessary operations, leading to a nearly 50% performance improvement.
//Small optimizations add up, especially in large N × N grids, leading to significant runtime differences.
public class Jan31_B {
    private int[] dir = {-1, 0, 1, 0, -1};

    private boolean isValid(int n, int x, int y) {
        return (x >= 0 && x < n && y >= 0 && y < n);
    }
  //islandNumber = UID, (x,y) -> curr corrdinates
    private int markIsland(int[][] grid, int islandNumber, int n, int x, int y) {
        grid[x][y] = islandNumber; //marking curr island
        int count = 1;  //size at  least 1
        for (int i = 0; i < 4; ++i) {
            int newX = x + dir[i];
            int newY = y + dir[i + 1]; //More efficient memory access = faster execution.
            /*This one works but Time is 171ms in this case
             int newY = y + dir[i + 1];  // Risky
there was an out-of-bounds access issue, which could cause unpredictable memory access patterns and cache inefficiencies.
             */
            if (isValid(n, newX, newY) && grid[newX][newY] == 1) {  //only make call if it is valid cell and land cell(1)
                count += markIsland(grid, islandNumber, n, newX, newY);
            }
        }
        return count;
    }

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        Map<Integer, Integer> islandSize = new HashMap<>();
        int islandNumber = 2;

        // Mark islands
        for (int x = 0; x < n; ++x) {
            for (int y = 0; y < n; ++y) {
                if (grid[x][y] == 1) {
                    int island = markIsland(grid, islandNumber, n, x, y);
                    islandSize.put(islandNumber, island);
                    islandNumber++;
                }
            }
        }

        // Try to convert each 0 to 1 one by one
        int maxSize = 0;
        for (int x = 0; x < n; ++x) {
            for (int y = 0; y < n; ++y) {
                if (grid[x][y] == 0) {
                    Set<Integer> islands = new HashSet<>(); //for unique island
                    for (int i = 0; i < 4; ++i) {
                        int newX = x + dir[i];
                        int newY = y + dir[(i + 1) % 4]; // Fixed indexing issue
                        /* 171 ms
                         all neighboring cells are added to the HashSet islands, including 0 values.
                         if (isValid(n, newX, newY)) {
                            islands.add(grid[newX][newY]);
                        }
                         *//* 95ms
                            Avoiding Unnecessary HashSet Inserts (Optimization in Fixed Code)
                            we ensure that only valid island numbers (>1) are added to the HashSet islands
                            Fewer insertions = faster execution.
                            */
                        if (isValid(n, newX, newY) && grid[newX][newY] > 1) { // Ignore 0s
                            islands.add(grid[newX][newY]); 
                        }
                    }
                    // Iterate and find total size of the current island
                    int currIsland = 1;
                    for (int key : islands) {
                        currIsland += islandSize.getOrDefault(key, 0);
                    }
                    maxSize = Math.max(maxSize, currIsland);
                }
            }
        }
        return maxSize == 0 ? n * n : maxSize;
        //if maxsize =0 -> no zero was in grid  -> 1 island of size n*n
    }
}
