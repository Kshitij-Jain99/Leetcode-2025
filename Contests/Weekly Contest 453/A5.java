//3576. Transform Array to All Equal Elements
// Appraoch-5: Product Check & Simulation
// TC = O(2*N){Better than 3, 4}, SC = O(N)
/*
 1. Product Check:
   - Computes the product of all elements (prod).
   - Determines possible targets (1 or -1) by checking if prod can match 1 or -1 when raised to n (array length).
   - If no valid target exists, returns false.
 2. Simulation:
   - For each valid target, clones the array and simulates flipping operations:
     a. Flips a[i] and a[i+1] whenever a[i] != target.
     b. Counts operations (ops) until either ops > k or the end of the array is reached.
   - If the last element matches the target and ops ≤ k, returns true.
 3. Fallback: If no simulation succeeds, returns false.
 */
import java.util.ArrayList;
import java.util.List;

public class A5 {
    public boolean canMakeEqual(int[] nums, int k) {
        int n = nums.length;
        if (n == 1) return true;

        int prod = 1;
        for (int x : nums) prod *= x;

        List<Integer> targets = new ArrayList<>();
        for (int target : new int[]{1, -1}) {
            int tPowN = (n % 2 == 0 ? 1 : target);
            if (tPowN == prod) targets.add(target);
        }
 
        if (targets.isEmpty()) return false; //early rejection check

        for (int target : targets) {
            int ops = 0;
            int[] a = nums.clone();
            for (int i = 0; i < n - 1 && ops <= k; i++) {
                if (a[i] != target) {
                    a[i] = -a[i];
                    a[i + 1] = -a[i + 1];
                    ops++;
                }
            }
            
            if (ops <= k && a[n - 1] == target) {
                return true;
            }
        }

        return false;
    }
}
