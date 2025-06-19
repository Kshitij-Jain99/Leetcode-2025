// 84. Largest Rectangle in Histogram
// Approach-2: Using Monotonic Increasing Stack
// TC = O(3*N){3 loops}, SC = O(3*N){Stack + leftSmall[] + rightSmall[]}
/* 
   TC: Every index i is pushed to and popped from the stack at most once. -> O(N)
 Idea: How far can we extend left and right while maintaining at least this height?
 1. Finding smallest left and right bars for each index i
 2. Using this info to compute the maximum area rectangle for each bar, treating it as the shortest bar in that rectangle.
 3. rightSmall[i] is either equal to or after leftSmall[i].We never allow left > right in the stack logic
 4. If rightSmall[i] < leftSmall[i], the width would be zero or negative — which makes no sense for a rectangle width.
    But this never happens because:
      Our left and right logic is built with monotonic stacks
      We ensure we only consider ranges where all bars are ≥ current bar
      So the rectangle span always includes at least the current bar
 */
import java.util.*;
public class A2 {
    public static int largestRectangleArea(int[] heights){
        int n = heights.length;
        Stack<Integer> st = new Stack<>();
        int leftSmall[] = new int[n]; //index of the nearest smaller element to the left of i
        int rightSmall[] = new int[n]; // index of the nearest smaller element to the right of i

        //Compute leftSmall[]
        for(int i = 0; i<n; i++){ //left to right
            while(!st.isEmpty() && heights[st.peek()] >= heights[i]) st.pop();
            if(st.isEmpty()) leftSmall[i] = 0;   //no smaller to the left → assign 0.
            else leftSmall[i] = st.peek() + 1;   //If a smaller bar is found → the rectangle can start just after that (st.peek() + 1).
            st.push(i);  //indices in increasing order
        }
        //clear the stack to be-reused
        while(!st.isEmpty()) st.pop();

        //Compute rightSmall[]
        for(int i = n-1; i>=0; i--){ //right to left.
            while(!st.isEmpty() && heights[st.peek()] >= heights[i]) st.pop();
            if(st.isEmpty()) rightSmall[i] = n-1; //no smaller to the right → assign n-1 (end of array).
            else rightSmall[i] = st.peek()-1;  //If a smaller bar is found → it ends just before that (st.peek() - 1).
            st.push(i);
        }

        int maxA = 0;
         for (int i = 0; i < n; i++) {
            maxA = Math.max(maxA, heights[i] * (rightSmall[i] - leftSmall[i] + 1));
        }
        return maxA;
    }
}
