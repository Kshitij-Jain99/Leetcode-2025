//3467. Transform Array by Parity
// Appraoch 1 - Counting 
// TC = O(2N), SC = O(1)
public class A2 {
    public int[] transformArray(int[] nums){
    int zeroCount = 0;
    //no need to count 1 as the remaining elements will be 1
   for(int i =0; i<nums.length; i++){
       if(nums[i]%2 == 0){
           zeroCount++;
       }
   }

       for(int j = 0; j<zeroCount; j++){
           nums[j] = 0;
       }

       for(int j = zeroCount; j<nums.length; j++){
           nums[j] = 1;
       }
   
   return nums;
  }
}
