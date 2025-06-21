//Q2. Find Maximum Area of a Triangle
// Approach-1: Brute Force
// TC = O(N^3), SC = O(1)
/*
 1. Inside k loop need to check if either p1-p3 or p2-p3 is parallel
    - Three points could be colinear without any axis-parallel sides (e.g., (0,0), (1,1), (2,2))
    - These would give area=0 but wouldn't satisfy the problem's parallel-side requirement
    - The parallel-side check ensures we only consider valid triangles per problem constraints.
2. Area = Shoelace Formula (Most Common) = = ½ |(x₁(y₂ - y₃) + x₂(y₃ - y₁) + x₃(y₁ - y₂)|
    */
public class B1 {
    public long maxArea(int[][] coords){
        int n = coords.length;
        if(n < 3) return -1;
        long maxArea = 0;
        
        for(int i = 0; i<n; i++){
            for(int j  = i+1; j<n; j++){
                int[] p1 = coords[i];
                int[] p2 = coords[j];

                if(p1[0] != p2[0] && p1[1] != p2[1]) continue;
                for(int k = j+1; k<n; k++){
                    int[] p3 = coords[k];
                    //Non-colinear check, non-axis-parallel check
                    if(p1[0] == p2[0] || p1[1] == p2[1]  ||
                       p1[0] == p3[0] || p1[1] ==  p3[1] ||
                       p2[0] == p3[0] || p2[1] == p3[1]){
                        long area = Math.abs(
                            (long)p1[0] * (p2[1] - p3[1]) +
                            (long)p2[0] * (p3[1] - p1[1]) +
                            (long)p3[0] * (p1[1] - p2[1])
                        );
                        if(area > maxArea) {
                            maxArea = area;
                        }
                       }
                }
            }
        }
        return maxArea != 0? 2*maxArea:-1;
    }
}
