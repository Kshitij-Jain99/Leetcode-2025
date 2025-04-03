//2874. Maximum Value of an Ordered Triplet II
//Approach-B: Optimized Greedy with Dynamic Tracking
// TC = O(n), SC = O(1)

public class Apr2_B {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        long maxDiff = 0;
        long maxLeft = 0;
        long maxVal = 0;
        for (int i = 0; i < n; ++i) {
            maxVal = Math.max(maxVal, maxDiff * nums[i]);
            maxDiff = Math.max(maxDiff, maxLeft - nums[i]);
            maxLeft = Math.max(maxLeft, nums[i]);
        }
        return maxVal;
    }    
}
