//2874. Maximum Value of an Ordered Triplet II
//Approach-A: Greedy + Prefix/Sum Maximation
// TC = O(n), SC = O(n)

public class Apr2n3_A {
    public long maximumTripletValue(int[] nums){
        int n = nums.length;

        //1
        int[] rightMax = new int[n];
        int maxVal = nums[n-1];
        for(int k = n-2; k > 0; --k){
            rightMax[k] = maxVal;
            maxVal = Math.max(maxVal, nums[k]);
        }
     
        //2
        long maxTriplet = 0;
        maxVal = nums[0];
        for(int i =1; i<n-1; ++i){
            maxTriplet = Math.max(maxTriplet, (long)(maxVal - nums[i])*rightMax[i]);
            maxVal = Math.max(maxVal, nums[i]);
        }
        return maxTriplet;
    }
}
