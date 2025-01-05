//3410. Maximize Subarray Sum After Removing All Occurrences of One Element
// TC = O(N), SC = O(N)
//Approach C: Kadane with DP[see leetcode sol by EqualKirby]

import java.util.HashMap;
import java.util.Map;

class D3 {
    public long maxSubarraySum(int[] nums) {
        Map<Long, Long> pre = new HashMap<>();

        long res = nums[0];
        long prefix = 0;
        long low = 0;
        pre.put(0L, 0L);

        for (int n : nums) {
            prefix += n;
            res = Math.max(res, prefix - low);

            if (n < 0) {
                if (pre.containsKey((long) n)) {
                    pre.put((long) n, Math.min(pre.get((long) n), pre.get(0L)) + n);
                } else {
                    pre.put((long) n, pre.get(0L) + n);
                }
                low = Math.min(low, pre.get((long) n));
            }

            pre.put(0L, Math.min(pre.get(0L), prefix));
            low = Math.min(low, pre.get(0L));
        }

        return res;
    }
}
