// 3566. Partition Array into Two Equal Product Subsets
// Approach-4: Java in-build BigInteger
// TC: O(N), SC: O(1)
/*
 1. subset1 == target, subset2 == target  -> subset1 * subset2 == target * target
 2. product of all integers in nums == (target * target )
 3. Since the array size is small we could use BigInteger
 4. product could exceed the limits of a regular long or int.
 */
import java.math.BigInteger;
public class A4 {
    public boolean checkEqualPartitions(int[] nums, long target){
        int n = nums.length;
        BigInteger big = BigInteger.ONE;
        for(int i = 0; i<n; i++){
            big = big.multiply(new BigInteger("" + nums[i]));
        }
        BigInteger targetSquare = new BigInteger("" + target);
        targetSquare = targetSquare.multiply(new BigInteger(target+""));
        return targetSquare.equals(big);

    }
}
