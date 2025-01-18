//1368. Minimum Cost to Make at Least One Valid Path in a Grid
// Approach B(optimized): 0-1 BFS: BFS + Dijkstra's Algorithm
// TC = O(NM), SC = O(NM)

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Jan18_B {
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int[][] cost = new int[m][n];
        for (int[] row : cost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        Deque<int[]> dq = new ArrayDeque<>();

        dq.offerFirst(new int[]{0, 0});
        cost[0][0] = 0;

        while (!dq.isEmpty()) {
            int[] cell = dq.pollFirst();
            int x = cell[0], y = cell[1];

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dirs[dir][0], ny = y + dirs[dir][1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    int newCost = cost[x][y] + (dir != grid[x][y] - 1 ? 1 : 0);
                    if (newCost < cost[nx][ny]) {
                        cost[nx][ny] = newCost;
                        if (dir != grid[x][y] - 1) {
                            dq.offerLast(new int[]{nx, ny});
                        } else {
                            dq.offerFirst(new int[]{nx, ny});
                        }
                    }
                }
            }
        }

        return cost[m - 1][n - 1];
    }
}
