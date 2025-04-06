//1863. Sum of All Subset XOR Totals
// Appraoch A: Absolute Brute Force
// TC = O(N*2^N), SC = O(N*2^N)
/*
 1. Generate all subsets ->Recursion and BT
 2. Store each subset -> List of List
 3. Iterate over each subset: XOR all elements in the subset.
 4. Keep a running sum of these XOR values and return it as the final result.
 */

 import java.util.*;

public class Apr5_A {
    public int subsetXORSum(int[] nums){
        List<List<Integer>> lists = new ArrayList<>();
        generateSubsets(nums,0,new ArrayList<>(), lists);
        int xorSum = 0;

        for(List<Integer> list: lists){
            if(list.size() == 1) {
                xorSum += list.get(0);
            }
            else{
                int count = list.get(0);
                for(int i =1; i<list.size(); i++){
                    count ^= list.get(i);
                }
                xorSum += count;
            }
        }
        return xorSum;

    }
    public void generateSubsets(int nums[], int ind, List<Integer> curr, List<List<Integer>> lists){
        lists.add(new ArrayList<>(curr)); // add even empty subset
    
        for(int i = ind; i < nums.length; i++){
            curr.add(nums[i]);
            generateSubsets(nums, i + 1, curr, lists);
            curr.remove(curr.size() - 1); // backtrack
        }
    }
    
}
