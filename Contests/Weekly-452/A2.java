// 3566. Partition Array into Two Equal Product Subsets
// Approach-2: Math
// TC: O(N), SC: O(1)
/*
  1. Two subsets, A and B, such that: product(A) == target, product(B) == target
       total product of the array must be: product(A) * product(B) = target * target
  2. total product of all elements = target² --> every element in the array must divide the target (or be composed of prime factors within target).
 */
public class A2 {
    public boolean checkEqualPartitions(int[] nums, long target){
        for(int num: nums)  if(target % num != 0) return false;
        return true;
    }
}
