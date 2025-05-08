//1920. Build Array from Permutation
// Approach - C: in-place Bit Manipulation
// TC = O(N), SC = O(1)
/*
 1. original value is stored in the lower 10 bits.
 2. new value is stored in the higher bits.
 */
public class May6_C {
    public int[] buildArray(int[] nums){
        for(int i =0; i<nums.length; i++)
        //Masking nums[nums[i]] with 1023 ((1 << 10) - 1) to ensure it's within 10 bits. Shifting the result left by 10
        nums[i] += (nums[nums[i]]&1023) <<10;
        
        for(int i =0; i<nums.length; i++)
        // extract the new value by right-shifting each nums[i] by 10 bits.
        nums[i] >>= 10;

    return nums;    
    
    }
    
}
