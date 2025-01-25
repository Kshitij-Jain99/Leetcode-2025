//42. Trapping Rain Water
// Approach B: Two pointer technique
//TC = O(n) SC = O(1)
/*
 1. Fixing extreme  max. one-side  border and calculating the water trapped
     by movving the other side border.
 2. This way water will be dependent on only min. heights.
 3. In case of l = r, we can increase either one, but here lets us r++
 4. when l>= r, stop [idx]
 */
public class Jan19Pre_B {
    public int trap(int[] height){
        int n = height.length;
        int leftMax = 0;
        int rightMax = 0;
        int l =0;
        int r = n-1;
        int ans = 0;

        while(l<r) {
            if(height[l]> leftMax){
                leftMax = Math.max(leftMax, height[l]);
            }
            if(height[r] > rightMax){
                rightMax = Math.max(rightMax, height[r]);
            }

            if(leftMax<rightMax){
                ans = ans + leftMax - height[l];
                l++;
            } else{
                ans = ans + rightMax - height[r];
                r--;
            }
        }
        return ans;
    }
}
