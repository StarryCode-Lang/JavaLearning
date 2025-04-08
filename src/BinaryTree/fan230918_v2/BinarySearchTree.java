package BinaryTree.fan230918_v2;

import java.util.Stack;

public class BinarySearchTree {
    private Node root;

    private static class Node {
        private final int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * 向二叉搜索树中插入节点
     */
    public void insert(int data) {
        root = insertNode(root, data);
    }

    private Node insertNode(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }

        if (data < root.data) {
            root.left = insertNode(root.left, data);
        } else if (data > root.data) {
            root.right = insertNode(root.right, data);
        }

        return root;
    }

    /**
     * 中序遍历二叉搜索树（非递归）
     */
    public void inorderTraversal() {
        inorderIterative(root);
    }

    private void inorderIterative(Node root) {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            System.out.print(current.data + " ");

            current = current.right;
        }
    }

    /**
     * 前序遍历二叉搜索树（非递归）
     */
    public void preorderTraversal() {
        preorderIterative(root);
    }

    private void preorderIterative(Node root) {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            System.out.print(current.data + " ");

            if (current.right != null) {
                stack.push(current.right);
            }

            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }

    /**
     * 后序遍历二叉搜索树（非递归）
     */
    public void postorderTraversal() {
        postorderIterative(root);
    }

    private void postorderIterative(Node root) {
        if (root == null) {
            return;
        }

        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);

        while (!stack1.isEmpty()) {
            Node current = stack1.pop();
            stack2.push(current);

            if (current.left != null) {
                stack1.push(current.left);
            }

            if (current.right != null) {
                stack1.push(current.right);
            }
        }

        while (!stack2.isEmpty()) {
            Node current = stack2.pop();
            System.out.print(current.data + " ");
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        bst.insert(7);
        bst.insert(5);
        bst.insert(9);
        bst.insert(6);
        bst.insert(3);
        bst.insert(8);
        bst.insert(10);
        bst.insert(4);

        System.out.println("中序遍历:");
        bst.inorderTraversal();

        System.out.println("\n前序遍历:");
        bst.preorderTraversal();

        System.out.println("\n后序遍历:");
        bst.postorderTraversal();
    }
}