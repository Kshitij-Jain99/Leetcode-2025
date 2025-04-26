//2444. Count Subarrays With Fixed Bounds
// Approach-A: Boundary tracking/ last occurrence tracking/ Position tracking with inclusion/exclusion
// TC = O(N), SC = O(1)
/*
 1. Any element outside the [minK, maxK] range makes the entire window invalid.
 2. Any valid subarray must contain at least one minK and one maxK.
 3. For each position, we can count valid subarrays ending at that position by tracking:
    The last invalid position (where values went out of bounds)
    The last positions where minK and maxK were seen
 4. Algorithm:
    a.Iterate through each element in the array. If current element is invalid (outside [minK, maxK]):
        Update invalidIdx to current index
        Reset minKIdx and maxKIdx since they can't be part of any future valid subarray
    b.If current element is valid:
        Update minKIdx if current element equals minK
        Update maxKIdx if current element equals maxK
        If we've seen both minK and maxK at least once:
            The earliest valid start is max(invalidIdx + 1, min(minKIdx, maxKIdx))
            The number of valid subarrays ending at current position is min(minKIdx, maxKIdx) - invalidIdx
 */
/*
 1. This appraoch Isn't a Classic Sliding Window:
    a. It doesn't maintain explicit window boundaries
    b. It tracks specific positions (invalidIdx, minKIdx, maxKIdx) to compute counts
 2. See example form Deepseek
 */
public class Apr26_A {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long validSubarrays = 0;  // result
        int invalidIdx = -1;     //where nums[i] was outside [minK, maxK]
        int minKIdx = -1;        //last occurrence of minK
        int maxKIdx = -1;       //last occurrence of maxK

        for (int i = 0; i < nums.length; ++i) {
            //invalid cases, already covered in Consarints, not needed
            if (nums[i] < minK || nums[i] > maxK) {
                invalidIdx = i;
                minKIdx = -1;  //Reset
                maxKIdx = -1;  // Reset
                continue;     //Skip further processing of this element
            }
            if (nums[i] == minK) minKIdx = i;
            if (nums[i] == maxK) maxKIdx = i;
            
            if (minKIdx != -1 && maxKIdx != -1) {
                int lastValid = Math.min(minKIdx, maxKIdx);
                validSubarrays += Math.max(lastValid - invalidIdx, 0); //gives the number of valid starting points for subarrays ending at current index i

            }
        }
        return validSubarrays;
    }
}
