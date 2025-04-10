//368. Largest Divisible Subset
// Approach C: LIS Bottom Up
// TC =O(n^2), SC=O(n)

import java.util.*;

public class Apr6_C {
    public List<Integer> largestDivisibleSubset(int[] nums){
        int n = nums.length;
        if(n==0) return new ArrayList<>();

        Arrays.sort(nums); //Optmization-1
        int[] lisLength = new int[n];
        int[] prevIndex = new int[n];
        Arrays.fill(lisLength, 1);
        Arrays.fill(prevIndex, -1);

        int maxLen = 1;
        int index = 0;

        for(int i = 0; i<n; i++){
            for(int j = 0; j<i; j++){ //revese order-faster
                if(nums[i] % nums[j] == 0 && lisLength[j] + 1 > lisLength[i]){
                    lisLength[i] = lisLength[j] + 1;
                    prevIndex[i] = j;
                }
            }
          
            if(lisLength[i] > maxLen){
                maxLen = lisLength[i];
                index = i;
            }
        }

        //Reconstruct the subset
        List<Integer> result = new ArrayList<>();
        while(index != -1){
            result.add(nums[index]);
            index = prevIndex[index];
            
            Collections.reverse(result);

        }
        return result;
    }
    
}

