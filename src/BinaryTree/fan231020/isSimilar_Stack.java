package BinaryTree.fan231020;

import java.util.Stack;

public class isSimilar_Stack {
    public boolean isSimilar(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root1);
        stack2.push(root2);
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            TreeNode node1 = stack1.pop();
            TreeNode node2 = stack2.pop();
            if (node1.val != node2.val) {
                return false;
            }
            if (node1.left != null) {
                stack1.push(node1.left);
            }
            if (node1.right != null) {
                stack1.push(node1.right);
            }
            if (node2.left != null) {
                stack2.push(node2.left);
            }
            if (node2.right != null) {
                stack2.push(node2.right);
            }
        }
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
