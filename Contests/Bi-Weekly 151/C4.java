//3469. Find Minimum Cost to Remove Array Elements
// Approach-4:  Greedy-like Iterative DP (Bottom-Up) with a Optimized 1D DP with minimal updates
// Time Complexity: O(n^2), Space Complexity: O(N)-> 1D-Array
// Most Optimized

/*
 1. Unlike Approach 3, which iterates normally, this approach processes from the rightmost elements to the left, ensuring minimal updates in mc[].
    This avoids unnecessary computations and reduces constant factors, making it potentially faster.
 2. Approach 3 explicitly pairs elements, leading to more nested loops.
    This approach avoids explicit pairwise iteration, making the logic more compact
 3. 
 */
public class C4 {
    public static int minCost(int[] nums) {
		int[] mc = nums.clone(); // [index of isolated element to the left added to current nums suffix]
		int n = nums.length;
		if ((n & 1) == 0) {
			int x = nums[n - 1];
			for (int i = n - 2; i >= 0; i--)
				if (mc[i] < x)
					mc[i] = x;
		}
		for (int i = (n - 3) | 1; i > 0; i -= 2) {
			int x = nums[i];
			int y = nums[i + 1];
			int mcx = mc[i];
			int mcy = mc[i + 1];
			for (int j = i - 1; j >= 0; j--) {
				int z = nums[j];
				int mcz = mc[j];
				mc[j] = Math.min(Math.max(x, y) + mcz, Math.min(Math.max(y, z) + mcx, Math.max(z, x) + mcy));
			}
		}
		return mc[0];
	}
}
