//2460. Apply Operations to an Array.
// Appraoch A: Naive Appraoch: Extra array used
// TC = O(N), SC = O(N)
public class Mar1_A {
    public int[] applyOperations(int[] nums) {
        int n = nums.length;
        for(int i =0; i<n-1; i++){
            if(nums[i] == nums[i+1]){
                nums[i] *= 2;
                nums[i+1] = 0;
            }
          
        }
        int[] res = new int[n];
        int idx = 0;  //to track pos where next non-zero no. should be placed

        for(int num : nums){
            if(num != 0){
                res[idx++] = num;
            }
        }
        return res;
    }

}
