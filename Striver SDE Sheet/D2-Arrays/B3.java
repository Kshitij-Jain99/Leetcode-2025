// Merge Overlapping Subintervals
// Approach-3: Optimal(in-place)->Linear Merge
// TC = O(N*LogN), SC = O(1)
/*
 Optimization:
 1. In-Place Merging:
 2. Pointer-Based Merging:
    a. Uses a pointer k to track the position of the last merged interval in the array.
    b. Iterates through the intervals and merges them directly in the array by updating the k-th interval when overlaps are found.
    c. Non-overlapping intervals are placed at the k+1-th position.
 3. Returns a copy of the merged intervals up to index k
 */
import java.util.*;

public class B3 {
    public int[][] merge(int[][] intervals){
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));

        int k = 0; //initially points to first interval
        for(int i=1; i<intervals.length;i++){
            if(intervals[k][1] >= intervals[i][0]){ //overlap
                intervals[k][1] = Math.max(intervals[k][1], intervals[i][1]);
            } else{ // No Overlap
                k++;
                intervals[k] = intervals[i];
            }
        }
        return Arrays.copyOfRange(intervals, 0, k+1); //merged intervals
    }
}
