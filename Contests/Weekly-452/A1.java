// 3566. Partition Array into Two Equal Product Subsets
// Approach-1: Brute Force(Recursion)
// TC: O(2^N){Recursion Tree}, SC: O(N){Recursion stack}
/*
 1. Product of set1=target AND Product of set2=target
 2. Trying every possible assignment of each element to either subset.
 3. c1, c2 ->Count of elements in subset 1 and subset 2
    p1, p2 -> Product of elements in subset 1 and subset 2
 */
public class A1 {
    boolean solve(int[] nums, int i, long p1, long p2, int c1, int c2, long target){
        if(i == nums.length) {
            return  c1>0 && c2>0 && p1 == target && p2 == target; //base case
        }
        //add current element to set1
        boolean a = solve(nums, i+1, p1*nums[i], p2, c1+1, c2, target);

        //add current element to set2
        boolean b = solve(nums, i+1, p1, p2*nums[i], c1, c2+1, target);
        return a|b;
    }
    public boolean checkEqualPartitions(int[] nums, long target){
        return solve(nums, 0, 1, 1,0,0, target);
    }
}
