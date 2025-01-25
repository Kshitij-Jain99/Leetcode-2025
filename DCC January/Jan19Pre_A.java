//42. Trapping Rain Water
// Approach A: Using Prefix and Suffix arrays 
//TC = O(3n) SC = O(2n)
 
public class Jan19Pre_A {
    public int trap(int[] height){
        if(height == null || height.length == 0) return 0;
    

    int n = height.length;
    int[] left = new int[n];
    int[] right = new int[n];

    //Fill left array
    left[0] = height[0];
    for(int i =1; i<n; i++){
        left[i] = Math.max(left[i-1], height[i]);
    }

    //Fill right array
    right[n - 1] = height[n -1];
    for(int i = n-2; i>= 0; i--){
        right[i] = Math.max(right[i+1], height[i]);
    }

    //Calculate the water trapped
    int totalWater = 0;
    for(int i =0; i<n; i++){
        totalWater += Math.min(left[i], right[i]) - height[i];
    }
    return totalWater;
  }  
}
