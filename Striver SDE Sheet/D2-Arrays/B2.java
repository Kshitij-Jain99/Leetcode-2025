// Merge Overlapping Subintervals
// Approach-2: Better
// TC = O(N*LogN{Sorting} + N), SC = O(N)
/*
 Optimization:
 1.Single iteration, better meging logic
 */
import java.util.*;
public class B2 {
    public static List<List<Integer>> mergeOverlappingIntervals(int[][] arr){
        int n = arr.length;
        Arrays.sort(arr, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return a[0] - b[0];
            }
        });

        List<List<Integer>> ans = new ArrayList<>();

        for(int i =0; i<n; i++){
            //case-2: current interval cannot be merged with previous one
            if(ans.isEmpty() || arr[i][0] > ans.get(ans.size()-1).get(1)){
                ans.add(Arrays.asList(arr[i][0], arr[i][1]));
            }

            //case-1: current interval can be merged with previous one
            else{
                ans.get(ans.size() -1).set(1, Math.max(ans.get(ans.size()-1).get(1), arr[i][1]));
            }
            
        }
        return ans;
    }
}
