//1863. Sum of All Subset XOR Totals
// ApprOach C: Bit Manipulation
// TC = O(N), SC = O(1)
/*
 1. For each subset XOR values, we look at each bit in its binary representation.
 2. We calculate contribution(Setbit) of each bit position to the final result.
 3. Let us assume that a SET bit will contribute to half of total subsets (2^N/2).
    Contribution of ith Setbit = (2^(n-1)) * (2^i) = (No of subset contributions) * (weight of ith bit)
 4. Now how to tell if ith bit is set or not?
    Do bitwise OR of all nums elements -> 100|011|101 ->111 (even if 1 bit is set it is visible)
 5. Total contribution of all bits = Sum of all contributions of each bit position.
 */
public class Apr5_C {

    public int subsetXORSum(int[] nums){
        int n = nums.length;
        int sum = 0;
        for(int ele: nums){
            sum |= ele;
        }
        return sum * (1<<(n-1));
    }
}
