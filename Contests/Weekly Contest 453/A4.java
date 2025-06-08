//3576. Transform Array to All Equal Elements
// Appraoch-4: Count & Flip Simulation
// TC = O(N), SC = O(N){Better than 5}
/*
 1. Counts how many -1s and 1s are in the array.
 2. If one of them can't be transformed (i.e., both counts are odd), it returns false early.
 3. It then simulates two scenarios:
    -Try to make everything 1
    -Try to make everything -1
 4. If either succeeds within k steps, return true
 */
import java.util.Arrays;

public class A4 {
    
    public boolean canMakeEqual(int[] nums, int k) {
        int n = nums.length, temp = k;
        //Count -1s and 1s
        long count1 = Arrays.stream(nums).filter(i -> i == -1).count();
        long count2 = Arrays.stream(nums).filter(i -> i == 1).count();
        if (count1 % 2 == 1 && count2 % 2 == 1) {
            return false;
        }

        int[] array = new int[n];
        System.arraycopy(nums, 0, array, 0, n);
        //All 1's conversion
        for (int i = 0; i < n - 1; i++) {
            if (k < 0) {
                break;
            }
            if (nums[i] == -1) {
                nums[i + 1] = -1 * nums[i + 1];
                k--;
            }
        }
        if (k >= 0 && nums[n - 1] == 1) {
            return true;
        }

        //All -1's conversion
        for (int i = 0; i < n - 1; i++) {
            if (temp < 0) {
                break;
            }
            if (array[i] == 1) {
                array[i + 1] = -1 * array[i + 1];
                temp--;
            }
        }
        return temp >= 0 && array[n - 1] == -1;
    }
}
