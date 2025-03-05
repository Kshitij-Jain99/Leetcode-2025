//3469. Find Minimum Cost to Remove Array Elements
// Approach-3:  Iterative Dynamic Programming (Bottom-Up) with a 1D DP Array
// Time Complexity: O(n^2), Space Complexity: O(N)-> 1D-Array

/*
 1. Previous approaches were recursive and used memoization, leading to function call overhead and risk of deep recursion (stack overflow).
 2. Instead of recalculating states recursively, this approach iteratively updates a 1D DP array by considering the minimum cost at each step.
 3. The time complexity remains O(N²), but the constant factors are smaller, making it run faster.

 */

import java.util.Arrays;

public class C3 {
    static int INF = (int)1e9;
    public int minCost(int[] nums) {

        int n = nums.length;
        if(n % 2 == 0)
            nums = Arrays.copyOf(nums, ++n);
            
        int[] dp = new int[n];
       
        for(int j = 1; j < n - 1; j += 2){
           
            int cost1 = INF, cost2 = INF;
            int max = Math.max(nums[j], nums[j+1]); 
            
            for (int i = 0; i < j; ++i){
                cost1 = Math.min(cost1, dp[i] + Math.max(nums[i], nums[j+1])); 
                cost2 = Math.min(cost2, dp[i] + Math.max(nums[i], nums[j])); 
                dp[i] += max; 
            }
                
            dp[j] = cost1;
            dp[j+1] = cost2;
        }
        
        int result = INF;     
        for (int i = 0; i < n; ++i)
            result = Math.min(result, dp[i] + nums[i]);
            
        return result;
    }
}
