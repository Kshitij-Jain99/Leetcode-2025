//1920. Build Array from Permutation
// Approach - A: ans array
// TC = O(N), SC = O(N)

public class May6_A {
    public int[] buildArray(int[] nums){
        int[] ans = new int[nums.length];
        for(int i = 0; i<nums.length; i++){
            ans[i] = nums[nums[i]];
        }
        return ans;
    }
}
