//Q2. Find Maximum Area of a Triangle
// Approach-3: Using  Maps + TreeSets
// TC = O(n.Logk + m.Logk + m + logk) = O(n.Logk + m){k is the number of points sharing the same x/y.}, SC = O(N)

import java.util.*;

class B3 {
    public long maxArea(int[][] coords) {
        int n = coords.length;
        if (n < 3) return -1;

        HashMap<Integer, TreeSet<Integer>> mapX = new HashMap<>(); // x -> all y values
        HashMap<Integer, TreeSet<Integer>> mapY = new HashMap<>(); // y -> all x values

        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;

        // Group points and track global bounds
        for (int[] point : coords) {
            int x = point[0], y = point[1];

            mapX.computeIfAbsent(x, key -> new TreeSet<>()).add(y);
            mapY.computeIfAbsent(y, key -> new TreeSet<>()).add(x);

            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);
        }

        long maxArea = 0;

        // Case 1: Vertical base (same x)
        for (Map.Entry<Integer, TreeSet<Integer>> entry : mapX.entrySet()) {
            int x = entry.getKey();
            TreeSet<Integer> ySet = entry.getValue();

            if (ySet.size() <= 1) continue;

            int base = ySet.last() - ySet.first();
            long heightLeft = Math.abs(x - minX);
            long heightRight = Math.abs(x - maxX);

            maxArea = Math.max(maxArea, base * heightLeft);
            maxArea = Math.max(maxArea, base * heightRight);
        }

        // Case 2: Horizontal base (same y)
        for (Map.Entry<Integer, TreeSet<Integer>> entry : mapY.entrySet()) {
            int y = entry.getKey();
            TreeSet<Integer> xSet = entry.getValue();

            if (xSet.size() <= 1) continue;

            int base = xSet.last() - xSet.first();
            long heightDown = Math.abs(y - minY);
            long heightUp = Math.abs(y - maxY);

            maxArea = Math.max(maxArea, base * heightDown);
            maxArea = Math.max(maxArea, base * heightUp);
        }

        return maxArea > 0 ? maxArea : -1;
    }
}
