package BinaryTree.fan231020;

class TreeDepth {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        TreeDepth treeDepth = new TreeDepth();
        System.out.println(treeDepth.maxDepth(root));
        treeDepth.LongRoad(root);
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public void LongRoad(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        if (maxDepth(root.left) > maxDepth(root.right)) {
            LongRoad(root.left);
        } else {
            LongRoad(root.right);
        }
    }
}