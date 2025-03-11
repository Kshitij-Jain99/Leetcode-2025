//3473. Sum of K Subarrays With Length at Least M
// Approach 4: Most Optimized
// TC = O(n*k), k=n worst case, SC = O(n)  (Single DP array)
/*
 1. Dynamic Programming with optimized space
 
 */
class Solution {
    public int maxSum(int[] nums, int k, int m) {
        int n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int[] f = new int[n + 1];
        int[] d = new int[n + 1];
        for (int i = 1; i <= k; i++) {
            for (int j = i * m - m; j < i * m; j++) {
                d[j] = f[j] - prefix[j];
                f[j] = Integer.MIN_VALUE / 2;
            }
            int maxVal = Integer.MIN_VALUE;
            for (int j = i * m; j <= n - (k - i) * m; j++) {
                maxVal = Math.max(maxVal, d[j - m]);
                d[j] = f[j] - prefix[j];
                f[j] = Math.max(f[j - 1], maxVal + prefix[j]);
            }
        }
        return f[n];
    }
}
/*
 There's a significant real-world performance difference despite both approaches having the same theoretical time complexity (O(n*k)). Let me explain why Approach 4 runs so much faster in practice (14ms vs 470ms):
Reasons for the speed difference:
1. Loop structure efficiency:
Approach 4 has a more streamlined loop structure that processes elements sequentially
Approach 3 has nested loops with more complex logic and condition checking

2. Object creation overhead:
Approach 3 creates a new curr array for each iteration of the outer loop with curr = new int[k + 1][2]
This frequent object allocation and garbage collection adds significant overhead

3. Cache locality:
Approach 4 accesses memory in a more sequential pattern
Better cache locality means fewer cache misses, which dramatically improves real-world performance

4. Branching and conditional logic:
Approach 3 has multiple conditional branches (checking for m=1, m=2, m=3)
Frequent branch mispredictions in modern CPUs can significantly slow execution

5. Array rotation approach:
Approach 3 uses array assignments (next3DP = next2DP; next2DP = nextDP; nextDP = curr) to rotate state
Approach 4 directly updates the existing arrays in place

6. Constant factors:
The theoretical O(n*k) notation hides constant factors that affect real-world performance
Approach 4 likely has much smaller constant factors in its operations
 */