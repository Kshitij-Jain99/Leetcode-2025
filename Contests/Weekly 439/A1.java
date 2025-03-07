//3471. Find the Largest Almost Missing Integer
// Appraoch - 1
// TC = O(n), SC = O(1)
class A1 {
    public int largestInteger(int[] nums, int k) {
        if (k > nums.length) return -1; // Handle invalid case where k is greater than array length
        
      int freq[] = new int[51];
      for(int i : nums) freq[i]++;

      if(k == nums.length){ // return the largest number, even if its freq>1
        for(int i = 50; i>=0; i--){
            if(freq[i]>0) return i;
        }
      }

      else if(k == 1){  // return the largest number which is unique with freq=1
        for(int i = 50; i>=0; i--){
            if(freq[i] == 1) return i;
        }
      }
      else{ //other k values
        if(freq[nums[0]] == 1 && freq[nums[nums.length-1]] != 1) return nums[0];
        else if(freq[nums[0]] != 1 && freq[nums[nums.length-1]] == 1) {
            return nums[nums.length-1];
        }
        else if(freq[nums[0]] == 1 && freq[nums[nums.length-1]] == 1) {
            return nums[0] >= nums[nums.length -1] ? nums[0]: nums[nums.length-1];
        }
      }
      return -1;
    }
}
