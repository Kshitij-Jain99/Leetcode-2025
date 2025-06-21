//Q2. Find Maximum Area of a Triangle
// Approach-2: Using Maps
// TC = O(4*N), SC = O(N)
/*
 1. Two points form a vertical side (same x, different y), or vise versa
    Farthest possible point in the opposite direction for max. area
 2. Group points by x and y coordinates
    - xGroups (x → [minY, maxY])
    - yGroups (y → [minX, maxX])
 3. Track Global Bounds: minX, maxX, minY, maxY
    - determine how far a point can "stretch" to form a triangle
 4. Check Vertical Triangle (Same x)
    - For each x in xGroups, check if it has at least two different y-values.
    - If yes:It forms a vertical base: base = maxY - minY (for this x).
    -Try extending this base horizontally to either minX or maxX (excluding itself) for height.
 5. Check Horizonatal triangle(Same y)
 6. Track maxArea found during the checks.
 */
import java.util.*;

class B2 {
        public long maxArea(int[][] coords) {
        int n = coords.length;
        if (n < 3) return -1;
        
        Map<Integer, int[]> xGroups = new HashMap<>(); // x -> [minY, maxY]
        Map<Integer, int[]> yGroups = new HashMap<>(); // y -> [minX, maxX]
        
        // Build groups and track min/max values
        for (int[] coord : coords) {
            int x = coord[0], y = coord[1];
            //old is the previous [minY, maxY] AND cur is the new [y, y]
            //map.merge(key, newValue, (oldValue, newValue) -> result)
            xGroups.merge(x, new int[]{y, y}, (old, cur) -> 
                new int[]{Math.min(old[0], cur[0]), Math.max(old[1], cur[1])});
            
            yGroups.merge(y, new int[]{x, x}, (old, cur) -> 
                new int[]{Math.min(old[0], cur[0]), Math.max(old[1], cur[1])});
        }
        
        // Find global bounds
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
        
        for (int[] coord : coords) {
            minX = Math.min(minX, coord[0]);
            maxX = Math.max(maxX, coord[0]);
            minY = Math.min(minY, coord[1]);
            maxY = Math.max(maxY, coord[1]);
        }
        
        long maxArea = -1;
        
        // Case 1: Vertical sides (same x)
        for (Map.Entry<Integer, int[]> entry : xGroups.entrySet()) {
            int x = entry.getKey();
            int[] yRange = entry.getValue();
            
            if (yRange[0] != yRange[1]) { // Has vertical span
                long base = yRange[1] - yRange[0];
                long height = Math.max(Math.abs(x - minX), Math.abs(x - maxX));
                if (height > 0) {
                    maxArea = Math.max(maxArea, base * height);
                }
            }
        }
        
        // Case 2: Horizontal sides (same y)
        for (Map.Entry<Integer, int[]> entry : yGroups.entrySet()) {
            int y = entry.getKey();
            int[] xRange = entry.getValue();
            
            if (xRange[0] != xRange[1]) { // Has horizontal span
                long base = xRange[1] - xRange[0];
                long height = Math.max(Math.abs(y - minY), Math.abs(y - maxY));
                if (height > 0) {
                    maxArea = Math.max(maxArea, base * height);
                }
            }
        }
        
        return maxArea;
    }
}
