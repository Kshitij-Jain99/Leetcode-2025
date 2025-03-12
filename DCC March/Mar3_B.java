//2161. Partition Array According to Given Pivot
// Appraoch 1: Two Pointers
// TC = O(N+N), SC = O(1)

import java.util.*;

public class Mar3_B {
    public int[] pivotArray(int[] nums, int pivot){
        int n = nums.length;
        int[] result = new int[n];

        int left = 0, right = n-1;
        for(int i = 0, j= n-1; i<n; i++, j--){ // i and j moving at same rate so i<n will work for j as well
            //this maintain relative order of elements
            // i handle only lower elements and j handle only greater elements than pivot
            if(nums[i] < pivot) result[left++] = nums[i]; // fill result from left side
            if(nums[j] > pivot) result[right--] = nums[j]; //fill result from right side
        }
       // the gap b/w Lest and right conatains ele. which are equal to pivot
        while(left <= right){
            result[left++] = pivot; // put element and then increase left

            
        }
        return result;
    }
}
