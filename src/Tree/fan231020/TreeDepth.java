package Tree.fan231020;

public class TreeDepth {
    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(depth(root.leftChild) + 1, depth(root.rightBrother));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode("A");
        root.leftChild = new TreeNode("B");
        root.leftChild.leftChild = new TreeNode("C");
        root.leftChild.leftChild.rightBrother = new TreeNode("D");
        root.leftChild.rightBrother = new TreeNode("E");
        root.leftChild.rightBrother.leftChild = new TreeNode("F");
        root.leftChild.rightBrother.rightBrother = new TreeNode("G");
        TreeDepth treeDepth = new TreeDepth();
        System.out.println(treeDepth.depth(root));
    }
}
