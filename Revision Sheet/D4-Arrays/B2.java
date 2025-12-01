// 4 Sum
// Approach-2: Better
// TC = O(LogM.N^3), SC = O(2 * no. of the quadruplets) + O(N)
  //M = no. of elements in the set, N = size of arr
/*
 1. Why we are not inserting all the array elements in the HashSet and then searching for the fourth element:
    - Assume the given array is {1, 2, -1, -2, 2, 0, -1} and the target = 0. Now, we will notice a situation like the following:
    - fourth element should be target-(nums[i]+nums[j]+nums[k]) = 0 - (1-1+0) = 0.
    -  if all the array elements are in the HashSet and we search for 0, we will end up taking the 0 at index k again. The quadruplets will be {nums[i], nums[j], nums[k], nums[k]} but this is absolutely incorrect.
    - That is why we need to only consider the elements that are in between the indices j and k.
    
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class B2 {
     public static List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length; // size of the array
        Set<List<Integer>> st = new HashSet<>();

        // checking all possible quadruplets:
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Set<Long> hashset = new HashSet<>(); //insert those array elements that are in between indices j and k.
                for (int k = j + 1; k < n; k++) {
                    // taking bigger data type
                    // to avoid integer overflow:
                    long sum = nums[i] + nums[j];
                    sum += nums[k];
                    long fourth = target - sum;
                    // search for the fourth element in the HashSet.
                    if (hashset.contains(fourth)) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        temp.add((int) fourth);
                        temp.sort(Integer::compareTo);
                        st.add(temp);
                    }
                    // put the kth element into the hashset:
                    hashset.add((long) nums[k]);
                }
            }
        }
        List<List<Integer>> ans = new ArrayList<>(st);
        return ans;
    }
}
