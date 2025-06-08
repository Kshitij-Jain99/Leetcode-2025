//3576. Transform Array to All Equal Elements
// Appraoch-2: Greedy Simulation appraoch
// TC = O(4*2*N){4N per call for 2 call{-1, 1}}, SC = O(N)
/*
 1. First try making all elements -1:
   - Skip initial -1s → none at start, so i = 0
   - Flip pairs starting at index 0 where needed
   - Eventually fails because not enough flips or arrangement
 2. Then try making all elements 1:
   - Skip initial 1s → i moves to first mismatch
   - Flip pairs greedily
   - Succeeds by flipping pairs at appropriate indices
 3. Greedy left-to-right fixing — it always tries to fix the first mismatch it finds and moves on.
 */
public class A2 {
    public boolean canMakeEqual(int[] nums, int k){
        // make all elements equal either all -1 or all 1 within k flips.
        //It calls a helper method make twice:{-1, 1}
        return make(nums, -1, k) || make(nums, 1, k);
    }

    private boolean make(int[] nums, int num, int k){
        int i = 0, temp =k;
        int[] copy = nums.clone();  

        while(i<nums.length && nums[i] == num) i++;

        //From the first mismatch index i, we scan forward through the array.
        for(int j = i; j<nums.length-1; j++){ 
            if(copy[j] != num && temp > 0){
                copy[j] *= -1;
                copy[j+1] *= -1;
                temp--;
            }
        }
        for(i = 0; i<nums.length; i++){
            if(copy[i] != num) return false;
        }
            return true;
    }

}
