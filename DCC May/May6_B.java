//1920. Build Array from Permutation
// Approach - B: in-place by Encoding and Decoding
// TC = O(N), SC = O(1)
/*
 1. Using in-place modification, we also need to build the result array, while preserving the values given to us as input.
 2. x*y = xy and xy/y = x, so we can use this property to store the result in the original array.
 3. multiply all the numbers by x and keep updating positions.Using this, we don't lose our original values of the numbers and can restore them later as well.
 4.  Since all values in nums are in the range 0 to n-1, we can store two numbers in a single index using this encoding:
      nums[i] = original value + n × new value
 5. n? -> constraints are 1 <= nums.length <= 1000, therefore we can chose any number larger than 1000.
 6. % n extracts just the original old value
 7.  store two values inside each nums[i]: 
     oldVal: the original value (nums[i]) and newVal: the value we want to assign (nums[nums[i]])
 8. nums[i] like a 2-digit number:
    The ones place holds the oldVal
    The tens place holds the newVal, scaled by n
    When you do % n, you strip off the tens and higher digits, leaving just the ones place (original value).
 */
public class May6_B {
    public int[] buildArray(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int newVal = nums[nums[i]] % n;
            nums[i] += newVal * n;
        }
        for (int i = 0; i < n; i++) {
            nums[i] /= n;
        }
        return nums;
    }
}
