package Tree.fan231020;

public class TreeNode {
    int val;
    String ch;
    TreeNode leftChild;
    TreeNode rightBrother;

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode leftChild, TreeNode rightBrother) {
        this.val = val;
        this.leftChild = leftChild;
        this.rightBrother = rightBrother;
    }

    TreeNode(String ch) {
        this.ch = ch;
    }

}
