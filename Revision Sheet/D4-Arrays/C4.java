// Longest Consecutive Sequence in an Array
// Approach-4: Union-Find (Disjoint Set Union - DSU)
// TC = O(n α(n)) ≈ O(n) {where α(n) is the Inverse Ackermann function, nearly constant}
// SC = O(N) {for storing parents and sizes}

/*
 1. For grouping connected components efficiently.Here, consecutive numbers form connected components, and our goal is to find the largest component.
 2.  Create a parent and size array:
     parent[i]: Stores the representative (leader) of a set.
     size[i]: Tracks the size of each set.
 3. Union two numbers if they are consecutive using union(x, y).
 4. Find the largest component size.
 5. Why Use Union-Find?
     Efficiently groups consecutive elements.
✅  Handles large nums array efficiently.
✅  Scalable for real-world problems like social networks, graph connectivity.
 6. While HashSet is simpler and faster, Union-Find is useful when elements must be merged dynamically (e.g., network connections, clustering). 
 7. Union-Find (Disjoint Set) can be used but has extra overhead (O(n α(n)) time), making HashSet more efficient.
 */

import java.util.*;

public class C4 {
    class UnionFind {
        private HashMap<Integer, Integer> parent = new HashMap<>();
        private HashMap<Integer, Integer> size = new HashMap<>();

        public void add(int num) {
            if (!parent.containsKey(num)) {
                parent.put(num, num);
                size.put(num, 1);
            }
        }

        public int find(int num) {
            if (parent.get(num) != num) {
                parent.put(num, find(parent.get(num))); // Path compression
            }
            return parent.get(num);
        }

        public void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);

            if (rootA != rootB) {
                if (size.get(rootA) > size.get(rootB)) {
                    parent.put(rootB, rootA);
                    size.put(rootA, size.get(rootA) + size.get(rootB));
                } else {
                    parent.put(rootA, rootB);
                    size.put(rootB, size.get(rootA) + size.get(rootB));
                }
            }
        }

        public int getMaxSize() {
            int maxSize = 0;
            for (int s : size.values()) {
                maxSize = Math.max(maxSize, s);
            }
            return maxSize;
        }
    }

    public int longestConsecutive(int[] nums) {
        UnionFind uf = new UnionFind();

        // Step 1: Initialize DSU
        for (int num : nums) {
            uf.add(num);
        }

        // Step 2: Union consecutive numbers
        for (int num : nums) {
            if (uf.parent.containsKey(num - 1)) {
                uf.union(num, num - 1);
            }
            if (uf.parent.containsKey(num + 1)) {
                uf.union(num, num + 1);
            }
        }

        // Step 3: Find the largest component
        return uf.getMaxSize();
    }
}
