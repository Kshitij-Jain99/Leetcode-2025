//42. Trapping Rain Water.
// Approach E: Monotonic Stack(deque as stack):Better  
//TC = O(N),  SC = O(N)
/*
 1. The deque is used here as a monotonic stack to store indices of the height array in non-increasing order of height values.
 2. use the deque to store indices of walls in descending order of height.
 3. If a taller wall (right wall) is found, we calculate the water trapped using the heights of the left wall, middle (basin), and right wall.
 4. front of the deque (deque.getFirst()) can represent the leftmost wall when needed.
 5. end of the deque (deque.getLast()) to: Compare and remove shorter walls when a taller right wall is found.
 6. A stack (LIFO structure) could also work here, but a deque is better suited because:
 A. It provides more flexibility (e.g., adding/removing from both ends), though this specific problem mainly uses it like a stack.
 B. The deque's API in Java (addLast, removeLast, etc.) makes the operations more intuitive and descriptive in this context.
 7. The front of the deque always holds the index of the left wall for the current trapping calculation.
 8. The last element in the deque is the index of the middle basin. This is the wall that is too short to stop water from being trapped.
 9. The current index (rightIndex) in the loop is the right wall. As we iterate through the array, we simulate the process of water flowing until it encounters a taller right wall.

 */
import java.util.Deque;
import java.util.LinkedList;

public class Jan19Pre_E {
    public int trap(int[] height) {
        // Handle edge cases: If less than 3 heights, no water can be trapped
        if (height == null || height.length < 3) return 0;

        Deque<Integer> deque = new LinkedList<>(); // To store indices of the height array
        int totalWater = 0; // Total water trapped

        for (int rightIndex = 0; rightIndex < height.length; rightIndex++) {
            // Process whenever a taller "right wall" is found
            while (!deque.isEmpty() && height[deque.getLast()] <= height[rightIndex]) {
                int middleWallIndex = deque.removeLast(); // Index of the middle wall
                int middleWallHeight = height[middleWallIndex]; // Height of the middle wall

                // Check if a left wall exists
                if (!deque.isEmpty()) {
                    int leftWallIndex = deque.getLast(); // Index of the left wall
                    int leftWallHeight = height[leftWallIndex]; // Height of the left wall

                    // Calculate trapped water above the middle wall
                    int minWallHeight = Math.min(height[rightIndex], leftWallHeight);
                    int waterHeight = minWallHeight - middleWallHeight;
                    int basinWidth = rightIndex - leftWallIndex - 1;

                    totalWater += waterHeight * basinWidth; // Add trapped water
                }
            }

            // Add the current index to the deque
            deque.addLast(rightIndex);
        }

        return totalWater;
    }
}
