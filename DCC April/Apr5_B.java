//1863. Sum of All Subset XOR Totals
// Appraoch B: Brute Force - Backtracking + Divide and Conquer
// TC = O(2^n), SC = O(N)

/*
 1. Perform Depth First Search on the array.
 2. Resursively call the same function by taking the current value of the array and not taking the current value.->Recursion Tree of nums
 3. Return the xorsum if the base condition is hit.
 4. Finally return the sum of take and not take.

 Optimizations:
 1. No storing of subsets is needed.
 2. 
 */
public class Apr5_B {
    public int dfs(int[] nums, int index, int currXOR){
        if(index == nums.length) return currXOR;

        int take = dfs(nums, index+1, currXOR^nums[index]);
        int notTake = dfs(nums, index+1, currXOR);

        return take + notTake;
    }

    public int susbsetXORSum(int[] nums){
        return dfs(nums,0,0);
    }
}
