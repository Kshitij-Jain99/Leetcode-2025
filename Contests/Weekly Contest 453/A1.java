//3576. Transform Array to All Equal Elements
// Appraoch-1:Brute Force(Recursion + Backtracking): TLE
// TC = O((n-1)^k), SC = O(K)
/*
 1. Try all possible combinations of up to k operations.
   -Pick an index i  and flip nums[i] and nums[i+1].
   -Try applying this operation at all positions, for up to k times.
   -at each level, we can try n - 1 indices, and go up to k depth.
 2. After each sequence of operations, check if the array has become all equal (either all 1s or all -1s)
 */
public class A1 {
    public boolean canMakeEqual(int[] nums, int k){
        return backtrack(nums, k);
    }

    private boolean backtrack(int[] nums, int k){
        if(k < 0) return false;
        
        //check if elements are equal
        boolean allEqual = true;
        for(int i =0; i<nums.length; i++){
            if(nums[i] != nums[0]){
                allEqual = false;
                break;
            }
        }
        if(allEqual) return true;

        //Try all possible operations at each idx
        for(int i  =0; i<nums.length-1; i++){
            nums[i] *= -1;
            nums[i + 1] *= -1;

            if (backtrack(nums, k - 1)) return true; 

            // Backtrack (undo flip)
            nums[i] *= -1;
            nums[i + 1] *= -1;
        }
        return false;
    }
}
