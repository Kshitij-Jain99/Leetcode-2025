//Reverse Pairs
//Approach-5: BST-based solution{AVL Trees}
// TC = O(N.LogN), SC = O(N) 

class F6 {
class Node {
    int val;
    Node left;
    Node right;
    int height;
    int count;
    int[] weight;

    public Node(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
        this.height = 1;
        this.count = 1;
        this.weight = new int[]{0, 0};
    }
}

class AVL {
    Node root;

    public AVL() {
        this.root = null;
    }

    private int getHeight(Node root) {
        return root != null ? root.height : 0;
    }

    private int getWeight(Node root) {
        if (root == null) {
            return 0;
        }
        return root.count + root.weight[0] + root.weight[1];
    }

    private void fixValues(Node root) {
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        root.weight[0] = getWeight(root.left);
        root.weight[1] = getWeight(root.right);
    }

    private Node leftRotate(Node root) {
        Node newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;

        fixValues(newRoot.left);
        fixValues(newRoot);

        return newRoot;
    }

    private Node rightRotate(Node root) {
        Node newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;

        fixValues(newRoot.right);
        fixValues(newRoot);

        return newRoot;
    }

    public Node insert(int val, Node root) {
        if (root == null) {
            return new Node(val);
        }
        if (root.val == val) {
            root.count += 1;
            return root;
        }

        if (val < root.val) {
            if (root.left != null) {
                root.left = insert(val, root.left);
            } else {
                root.left = new Node(val);
            }
        } else {
            if (root.right != null) {
                root.right = insert(val, root.right);
            } else {
                root.right = new Node(val);
            }
        }

        int l = getHeight(root.left);
        int r = getHeight(root.right);

        if (l - r > 1) {
            if (getHeight(root.left.right) > getHeight(root.left.left)) {
                root.left = leftRotate(root.left);
            }
            return rightRotate(root);
        } else if (r - l > 1) {
            if (getHeight(root.right.left) > getHeight(root.right.right)) {
                root.right = rightRotate(root.right);
            }
            return leftRotate(root);
        }

        if (val < root.val) {
            root.weight[0] += 1;
        } else {
            root.weight[1] += 1;
        }

        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        return root;
    }

    public int search(double target, Node root) {
        if (root == null) {
            return 0;
        }

        if (root.val == target) {
            return root.weight[0];
        }
        if (root.val < target) {
            return root.count + root.weight[0] + search(target, root.right);
        }

        return search(target, root.left);
    }
}

class Solution {
    public int reversePairs(int[] nums) {
        AVL avl = new AVL();
        int count = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            count += avl.search(nums[i] / 2.0, avl.root);
            avl.root = avl.insert(nums[i], avl.root);
        }
        return count;
    }
}
}
