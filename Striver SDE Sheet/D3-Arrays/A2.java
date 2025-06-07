//Search in a 2D Matrix - I
// Appraoch-2: Better using Binary Search
// TC = O(N + LogM), SC = O(1)
// binary search rather we are only applying it once for a particular row.

import java.util.ArrayList;

public class A2 {
    public static boolean binarySearch(ArrayList<Integer> nums, int target) {
        int n = nums.size();
        int low = 0, high = n-1;

        while(low <= high){
            int mid = (low+high)/2;
            if(nums.get(mid) == target) return true;
            else if(target > nums.get(mid)) low = mid+1;
            else high = mid-1;
        }
        return false;
    }
     public static boolean searchMatrix(ArrayList<ArrayList<Integer>> matrix, int target){
        int n = matrix.size();
        int m = matrix.get(0).size();

        for(int i = 0; i<n; i++){
             if (matrix.get(i).get(0) <= target && target <= matrix.get(i).get(m - 1)){
                return binarySearch(matrix.get(i), target);
             }
        }
        return false;
     }
}
