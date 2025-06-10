// Two sum Problem
// Approach-3: Two pointers
// TC = O(N), SC = O(1)

import java.util.Arrays;

public class A3 {
    // I Variant
    public static String twoSum(int n, int []arr, int target) {
        Arrays.sort(arr);
        int left = 0, right = n - 1;
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                return "YES";
            } else if (sum < target) left++;
            else right--;
        }
        return "NO";
   }
}
