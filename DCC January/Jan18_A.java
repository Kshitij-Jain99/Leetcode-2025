//1368. Minimum Cost to Make at Least One Valid Path in a Grid
// Approach A: Dijkstra's Algorithm
// TC = O(m*n* + log(4*m*n)), SC = O(m*n)

import java.util.Arrays;
import java.util.PriorityQueue;

public class Jan18_A {
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int[][] cost = new int[m][n];
        for (int[] row : cost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); //a, b array

        pq.offer(new int[]{0, 0, 0}); // {currentCost, x, y}
        cost[0][0] = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currCost = current[0], x = current[1], y = current[2];
// skip this cell because a better cost was already computed for it.
            if (currCost > cost[x][y]) continue;
 
            for (int dir = 0; dir < 4; ++dir) {
                //neighbour coordinates
                int nx = x + dirs[dir][0], ny = y + dirs[dir][1];
                //if (nx, ny) is outside the grid (invalid cell).
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;

                int nextCost = currCost + (dir != grid[x][y] - 1 ? 1 : 0);
                if (nextCost < cost[nx][ny]) {
                    cost[nx][ny] = nextCost;
                    pq.offer(new int[]{nextCost, nx, ny});
                }
            }
        }

        return cost[m - 1][n - 1];
    }
}
