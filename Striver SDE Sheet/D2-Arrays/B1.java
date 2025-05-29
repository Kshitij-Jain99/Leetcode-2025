// Merge Overlapping Subintervals
// Approach-1: Brute Force
// TC = O(N*LogN{Sorting} + 2*N{2 loops}), SC = O(N)
/*
  While using loop i, we skip all the intervals that are merged with loop j. So, we can conclude that every interval is roughly visited twice(roughly, once for checking or skipping and once for merging). 
  So, the time complexity will be 2*N instead of N2.
  break and continue saved a lot of time in this approach.
 */
/*
 Standing at curr interval and looking ahead who can be merged with it.
 1. To club the closer intervals together.
 2. Sort by thier first element, if first elemnet is equal then sort by second element.
 3. Iterate through the sorted intervals and check if the current interval overlaps with the previous one.
 4. If they overlap, merge them by updating the end of the previous interval.
 5. If they do not overlap, add the current interval to the result list. 
 */
import java.util.*;
public class B1 {
    public static List<List<Integer>> mergeOverlappingIntervals(int[][] arr){
        int n = arr.length;
        Arrays.sort(arr, new Comparator<int[]>() {
            public int compare(int[] a, int[] b){
                return a[0] - b[0];
            }
        });

        List<List<Integer>> ans = new ArrayList<>(); //store all ans

        for(int i = 0; i<n; i++){ //select an interval
            int start = arr[i][0];
            int end = arr[i][1];

            //skip all merged intervals
            if(!ans.isEmpty() && end <= ans.get(ans.size()-1).get(1)) continue;

        for(int j = i+1; j<n; j++){ //check for rest of intervals
            if(arr[j][0] <= end){
                end = Math.max(end, arr[j][1]);
            } else{
                break;
            }
        }
        ans.add(Arrays.asList(start, end));
        }
        return ans;
    }
}
