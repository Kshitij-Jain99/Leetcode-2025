// 2071. Maximum Number of Tasks You Can Assign
// Approach C(Much better): Binary Search + Greedy + Deque(Workers Queue)
/*  TC =O((N log N + M log M){Sorting} + log K * mid{BS+ mid check}) -> Better
    SC = O(mid) {Deque} ->same
  */

/*
 1. revious deque approach inserted eligible tasks into the queue, This approach inserts eligible workers into the queue
 2. So in Approach B, you match tasks to workers, but here you’re matching workers to tasks — it’s logically flipped, but achieves the same goal.
 3. AppraochB: Assign easiest task first, pill for hardest, AppraochC: Assign strongest worker first, pill for weakest

 */

import java.util.*;

class May1_C {

    public int maxTaskAssign(
        int[] tasks,
        int[] workers,
        int pills,
        int strength
    ) {
        int n = tasks.length, m = workers.length;
        Arrays.sort(tasks);
        Arrays.sort(workers);
        int left = 1, right = Math.min(m, n), ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(tasks, workers, pills, strength, mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    // Check if pills and strength can be used in mid tasks
    private boolean check(
        int[] tasks,
        int[] workers,
        int pills,
        int strength,
        int mid
    ) {
        int p = pills;
        int m = workers.length;
        Deque<Integer> ws = new ArrayDeque<>();
        int ptr = m - 1;
        // Enumerate each task from largest to smallest
        for (int i = mid - 1; i >= 0; --i) {
            while (ptr >= m - mid && workers[ptr] + strength >= tasks[i]) {
                ws.addFirst(workers[ptr]);
                --ptr;
            }
            if (ws.isEmpty()) {
                return false;
            } else if (ws.getLast() >= tasks[i]) {
                // If the largest element in the deque is greater than or equal to tasks[i]
                ws.pollLast();
            } else {
                if (p == 0) {
                    return false;
                }
                --p;
                ws.pollFirst();
            }
        }
        return true;
    }
}
