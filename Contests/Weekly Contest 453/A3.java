//3576. Transform Array to All Equal Elements
// Appraoch-3{Optimal}: Greedy Pairing & Counting
// TC = O(2*N), SC = O(1)
/*
 1. It pairs up mismatched elements and computes the total number of required operations to make them equal by simulating optimal pair flips.
 2. Specifically, it counts how far apart mismatched elements are and sums those distances as the minimum number of operations.
 3. Each time we find two mismatched elements, we perform a "virtual" flip (not actually flipping, just counting how many flips would be needed).
 3. 
 */
public class A3 {
    public boolean canMakeEqual(int[] nums, int k) {
        return check(nums, 1, k) || check(nums, -1, k);
    }

    private boolean check(int[] nums, int target, int k) {
        int minOps = 0;
        int firstUnmatchedIdx = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target) {
                if (firstUnmatchedIdx == -1) {
                    firstUnmatchedIdx = i; // Store the first unmatched index
                } else {
                    // Compute the distance between current and previous unmatched index
                    minOps += i - firstUnmatchedIdx;
                    firstUnmatchedIdx = -1; // Reset after pairing
                }
            }
        }
        
        // If there's an unmatched element left, return false
        if (firstUnmatchedIdx != -1) {
            return false;
        }
        
        return minOps <= k;
    }
}