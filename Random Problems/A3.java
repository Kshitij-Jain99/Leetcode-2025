// 84. Largest Rectangle in Histogram
// Approach-3: Using Optimized Monotonic Increasing Stack
// TC = O(N), SC = O(N)
import java.util.*;

public class A3 {
    static int largestRectangleArea(int histo[]) {
        Stack<Integer> st = new Stack<>();
        int maxA = 0;
        int n = histo.length;

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && histo[st.peek()] >= histo[i]) {
                int height = histo[st.pop()];
                int width = st.isEmpty() ? i : i - st.peek() - 1;
                maxA = Math.max(maxA, width * height);
            }
            st.push(i);
        }

        // Final cleanup: process remaining items in stack
        while (!st.isEmpty()) {
            int height = histo[st.pop()];
            int width = st.isEmpty() ? n : n - st.peek() - 1;
            maxA = Math.max(maxA, width * height);
        }

        return maxA;
    }
}
