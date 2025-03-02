//3467. Transform Array by Parity
// Appraoch 1 - In-built sorting 
// TC = O(nlogn), SC = O(1)
import java.util.Arrays;
public class A1 {
    public int[] transformArray(int[] nums) {
        // Appraoch -1  
        for(int i =0; i<nums.length; i++){
            if(nums[i] % 2 == 0){
                nums[i] = 0;
            }
            else{
                nums[i] = 1;
            }
        }
        Arrays.sort(nums);
        return nums;
    }
}

