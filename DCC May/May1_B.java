// 2071. Maximum Number of Tasks You Can Assign
// Approach B(Better): Binary Search + Greedy + Deque(Task Queue)
/*  TC =O((N log N + M log M){Sorting} + log K * mid{BS+ mid check}) -> Better
    SC = O(mid) {Deque} ->same
  */
/*
 1. Use a double-ended queue (Deque) to simulate assignment:
    Add all tasks ≤ w + strength to queue (those a worker could do with a pill)
    Prefer to assign easiest task (from front) if possible without pill
    Otherwise, assign hardest task (from back) using a pill
 2.  Matching logic: Assign easiest tasks first(Front-> assign without pill), use pills for hardest(Back)
 3. Iteration: Left to right, push assignable tasks into Deque
 4. Worker target-pairing:Implicit via w + strength window
 5. Deque naturally works with duplicates, and very intuitive with greedy
 6. Deque appraoch takes O(1) for popping, everything else remains same
 7. This is not monotonic queue.
 */
import java.util.*;

public class May1_B {
    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(tasks);
        Arrays.sort(workers);

        int left = 0, right = Math.min(tasks.length, workers.length);

        while (left < right) {
            int mid = (left + right + 1) / 2; //This variant ensures the midpoint always favors the right side in case of even splits — i.e., it's a "right-biased mid".
            if (canFinish(mid, tasks, workers, pills, strength)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private boolean canFinish(int mid, int[] tasks, int[] workers, int pills, int strength) {
        Deque<Integer> queue = new ArrayDeque<>();
        int i = 0;
        int taskIndex = 0;

        for (int j = workers.length - mid; j < workers.length; j++) {
            int w = workers[j];

            while (taskIndex < mid && tasks[taskIndex] <= w + strength) {
                queue.addLast(tasks[taskIndex]);
                taskIndex++;
            }

            if (queue.isEmpty()) return false;

            if (queue.peekFirst() <= w) {
                queue.pollFirst();
            } else {
                if (pills == 0) return false;
                pills--;
                queue.pollLast();
            }
        }

        return true;
    }
}
