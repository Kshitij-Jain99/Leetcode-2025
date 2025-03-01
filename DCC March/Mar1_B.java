//2460. Apply Operations to an Array.
// Appraoch B: Optimized Two pointers: In-place
// TC = O(N), SC = O(1)

public class Mar1_B {
    public int[] applyOperations(int[] nums) {
        int n = nums.length;
        for(int i =0; i<n-1; i++){
            if(nums[i] == nums[i+1]){
                nums[i] *= 2;
                nums[i+1] = 0;
            }
          
        }
       
        int idx = 0;  //to track pos where next non-zero no. should be placed
        
        for(int i=0; i<n; i++){
            if(nums[i] != 0){
                nums[idx++] = nums[i];
            }
        }
        //fill rem pos with 0
        while(idx<n){
            nums[idx++] = 0;
        }
        return nums;
    }
}
