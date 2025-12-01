//Reverse Pairs
//Approach-4: BST-based solution{TLE}
// TC = O(N^2), SC = O(2.N) 

/*
 1. this homemade BST is not self-balanced and the time complexity can go as bad as O(n^2)
 2. To guarantee O(nlogn) performance, use one of the self-balanced BST's (e.g. Red-black tree, AVL tree, etc.).
 */
public class F5 {

    // Node structure for the BST
    class Node {
        int val;      // Value at the node
        int count;    // Count of nodes in the right subtree + self (≥ this.val)
        Node left, right;

        Node(int val) {
            this.val = val;
            this.count = 1;  // Each node counts itself
        }
    }

    // Search for how many nodes are greater than the given value
    private int search(Node root, long val) {
        if (root == null) return 0;

        if (val < root.val) {
            // All right + root are valid
            return root.count + search(root.left, val);
        } else if (val > root.val) {
            // Search right only
            return search(root.right, val);
        } else {
            // Exact match — return count of this node
            return root.count;
        }
    }

    // Insert into BST while maintaining count
    private Node insert(Node root, int val) {
        if (root == null) return new Node(val);

        if (val == root.val) {
            root.count++; // Duplicate value
        } else if (val < root.val) {
            root.left = insert(root.left, val);
        } else {
            root.count++; // Current node is less, add to count
            root.right = insert(root.right, val);
        }
        return root;
    }

    // Main function to count reverse pairs
    public int reversePairs(int[] nums) {
        int count = 0;
        Node root = null;

        for (int i = nums.length - 1; i >= 0; i--) {
            count += search(root, (long) nums[i]);
            root = insert(root, 2 * nums[i]);
        }

        return count;
    }

    // Sample test
    public static void main(String[] args) {
        F5 solution = new F5();
        int[] nums = {1, 3, 2, 3, 1};
        System.out.println("Reverse Pairs Count: " + solution.reversePairs(nums)); // Expected: 2
    }
}
