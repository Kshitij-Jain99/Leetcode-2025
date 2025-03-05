//3469. Find Minimum Cost to Remove Array Elements
// Approach-2: bitwise encoding and memoization (Bitwise HashMap DP
// Time Complexity: O(n^2), Space Complexity: O(n^2 -> HashMap
/*
 1. This implementation uses bitwise encoding and memoization (HashMap) to track states efficiently instead of using a 2D DP table
 2. State is represented as an integer encoding three indices (i, j, k) where
    a. i (first index) is stored in the highest 10 bits.
    b. j (second index) is stored in the middle 10 bits
    c. k (third index) is stored in the lowest 10 bits.
    We can DP over it since array length is only 1000
    This allows efficient storage and retrieval of previously computed results using a HashMap (dp).
 3. Since each unique state (i, j, k) is stored in dp and computed only once, and the recursion makes at most 3 calls per state, the worst case is O(N²) similar to the previous approach.
 4. Performace: HashMap lookup (slightly slower due to hashing)
 5. Advantages with respect to Appraoch 1: 
    a. Uses HashMap instead of a fixed-size array, saving space when N is large and reducing memory wastage.
    b. Avoids large DP table allocation which might be beneficial in memory-constrained environments.
    c. Bitwise encoding reduces memory footprint compared to a standard 2D DP array.
 6. Time Efficient ->2D DP approach is better.
    optimized memory usage with HashMap-based memoization, the bitwise DP approach is an interesting alternative.
    */
import java.util.HashMap;
import java.util.Map;

public class C2 {
    public int minCost(int[] nums) {
        int n = nums.length;
        // Base case
        if (n == 1) {
            return nums[0];
        } else if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        Map<Integer, Integer> dp = new HashMap<>();
        return count(dp, nums, 1026);
    }

    static int mask = 0x3FF;
    
    static int count(Map<Integer, Integer> dp, int[] nums, int state) {
        int i = state >> 20, j = (state >> 10) & mask, k = state & mask;
        if (k >= nums.length) {
            if (j >= nums.length) {
                return nums[i];
            } else {
                return Math.max(nums[i], nums[j]);
            }
        }
        if (dp.containsKey(state)) {
            return dp.get(state);
        }
        // i j
        int a = k + 1, b = k + 2;
        int next = b | (a << 10);
        int res = Math.max(nums[i], nums[j]) + count(dp, nums, next | (k << 20));
        // i k
        int tmp = Math.max(nums[i], nums[k]) + count(dp, nums, next | (j << 20));
        res = Math.min(res, tmp);
        // j k
        tmp = Math.max(nums[k], nums[j]) + count(dp, nums, next | (i << 20));
        res = Math.min(res, tmp);
        dp.put(state, res);
        return res;
    }
}
