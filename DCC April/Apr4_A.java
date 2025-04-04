//1123. Lowest Common Ancestor of Deepest Leaves
// Approach-A: 
// TC = O(N)->Every node is visited once in a post-order traversal, SC = O(H) where H is the height of the tree.

/*
 1. Assumptions:
    a. If we have only 1 deepest leaf then return leaf node itself.
    b. Deepest leaves always assumes the farthest 2 leaves at the last level, for LCA
 2. LCA based on height
 3. Traverse the tree recursively. At each node, compute the depth and LCA of the left and right subtrees.
 4. 
 */
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val){
        this.val = val;
    }
}

public class Apr4_A {
    
    class Result {
        int depth;
        TreeNode lca;

        Result(int depth, TreeNode lca) {
            this.depth = depth; //maximum depth of that subtree.
            this.lca = lca; //LCA of the deepest leaves found in that subtree.
        }
    }

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return helper(root).lca;
    }

    private Result helper(TreeNode node) {
        if (node == null) return new Result(0, null);

        Result left = helper(node.left);
        Result right = helper(node.right);

        if (left.depth == right.depth) {
            return new Result(left.depth + 1, node);
        } else if (left.depth > right.depth) {
            return new Result(left.depth + 1, left.lca);
        } else {
            return new Result(right.depth + 1, right.lca);
        }
    }
}
