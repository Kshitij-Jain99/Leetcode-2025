// 4 Sum
// Approach-3: Optimal
// TC = O(N^3), SC = O(no. of the quadruplets){used to store the answer} -> O(1)

import java.util.*;

public class B3 {
    public static List<List<Integer>> fourSum(int[] nums, int target){
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        
        Arrays.sort(nums);
        for(int i = 0; i<n; i++){
            if(i>0 && nums[i] == nums[i-1]) continue;  // avoid the duplicates while moving i:
            for(int j = i + 1; j<n; j++){
                if( j> i + 1 && nums[j] == nums[j-1]) continue;  // avoid the duplicates while moving j:

                //2 pointers
                int k = j + 1;
                int l = n -1;
                while(k < l){
                    long sum =  (long)nums[i] + nums[j] + nums[k] + nums[l];
                    if( sum == target) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        temp.add(nums[l]);
                        ans.add(temp);
                        k++;
                        l--;

                        //skipping duplicates
                        while(k < l && nums[k] == nums[k-1]) k++;
                        while(k < l && nums[l] == nums[l+1]) l--;
                    } else if(sum <target) k++;
                    else l--;
                }
            }
        }
        return ans;
    }
}
