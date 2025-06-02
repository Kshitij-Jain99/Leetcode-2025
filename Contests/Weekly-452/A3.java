// 3566. Partition Array into Two Equal Product Subsets
// Approach-3: Brute Force by Bit Masking{Binary partitioning of nums}
// TC: O(n*2^(n)){2ⁿ masks, and each mask may take up to n operations}, SC: O(1)
/*
 1. Each integer mask from 1 to (1 << n) - 2 represents a binary partitioning of nums:
    Bit 1 → element goes to subset 1
    Bit 0 → element goes to subset 2
 2. We exclude: {Need non-empty subsets}
    Mask 0 → all bits 0 → subset1 is empty {All 0s}
    Mask (1 << n) - 1 → all bits 1 → subset2 is empty {All 1s}
 3. We will try all valid binary masks from 1 to 2^n - 2.   
 4. Calculate products of both subsets.
    If either product exceeds the target, we break early (optimization).
    If both equal target, return true.

 */
public class A3 {
    public boolean checkEqualPartitions(int[] nums, long target){
        int n = nums.length;

        for(int mask = 1; mask < (1<<n) -1; mask++){
            long prod1 = 1, prod2 = 1;

            for(int i = 0; i<n; i++){
                if( (mask & (1<<i)) != 0 ){
                    prod1 *= nums[i];
                } else{
                    prod2 *= nums[i];
                }
              
                if(prod1 > target || prod2 > target) break;
            }
            if(prod1 == target && prod2 == target){
                return true;
            }
        }
        return false;
    }
}
