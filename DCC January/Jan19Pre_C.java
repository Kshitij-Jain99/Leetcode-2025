//42. Trapping Rain Water
// Approach C : Monotonic Stack- Left and Right Maximum
//TC = O(n) SC = O(n)
/*
 1. To find the boundaries(leftMax and rightMax) on both sides we need 2 monotonic stack
 2. Monotonically increasing stacks on both sides
 */

import java.util.List;
import java.util.Stack;

public class Jan19Pre_C {
    public int trap(List<Integer> height){
        int n = height.size();
        int water = 0;

        Stack<Integer> stack = new Stack<>();  //store height idx
        
        for(int r = 0; r<n; r++){
            while(!stack.isEmpty() && height.get(stack.peek()) < height.get(r)){
                // Get the index of the height at the top of the stack
                int mid = stack.pop();
                if(stack.isEmpty()) break; // If the stack becomes empty, no more water can be trapped
                int l = stack.peek(); // Get the index of the next height from the top of the stack
                // Calculate the minimum height of the two borders
                int minHeight = Math.min(height.get(r) - height.get(mid), height.get(l) - height.get(mid));
                int width = r - l -1; // // Calculate the width between the left and right borders
                water += minHeight*width;
            }
            stack.push(r); //push current idx into stack
        }
        return water;
    }
}
