package BinaryTree.fan230916_v1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTree {
    private Node root;

    private static class Node {
        private int data;
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

    //向二叉搜索树中插入节点

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

    //中序遍历二叉搜索树

    public void inorderTraversal() {
        inorder(root);
    }

    private void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    //前序遍历二叉搜索树
    public void preorderTraversal() {
        preorder(root);
    }

    private void preorder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    //后序遍历二叉搜索树
    public void postorderTraversal() {
        postorder(root);
    }

    private void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }

    //在二叉搜索树中搜索特定的值

    public boolean search(int data) {
        return searchNode(root, data);
    }

    private boolean searchNode(Node root, int data) {
        if (root == null) {
            return false;
        }

        if (data == root.data) {
            return true;
        } else if (data < root.data) {
            return searchNode(root.left, data);
        } else {
            return searchNode(root.right, data);
        }
    }

    //从二叉搜索树中删除特定的值
    public void delete(int data) {
        root = deleteNode(root, data);
    }

    private Node deleteNode(Node root, int data) {
        if (root == null) {
            return null;
        }

        if (data < root.data) {
            root.left = deleteNode(root.left, data);
        } else if (data > root.data) {
            root.right = deleteNode(root.right, data);
        } else {
            // Case 1: No child or only one child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Case 2: Two children
            root.data = findMinValue(root.right);
            root.right = deleteNode(root.right, root.data);
        }

        return root;
    }

    private int findMinValue(Node root) {
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    //获取二叉搜索树中的最小值
    public int getMinValue() {
        if (root == null) {
            throw new IllegalStateException("树为空");
        }

        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.data;
    }

    //获取二叉搜索树中的最大值
    public int getMaxValue() {
        if (root == null) {
            throw new IllegalStateException("树为空");
        }

        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.data;
    }

    //检查二叉搜索树是否为空

    public boolean isEmpty() {
        return root == null;
    }

    //获取二叉搜索树的高度

    public int getHeight() {
        return calculateHeight(root);
    }

    private int calculateHeight(Node root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = calculateHeight(root.left);
        int rightHeight = calculateHeight(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    //输出二叉树的每一层
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                list.add(node.data);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(list);
        }
        return result;
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

        System.out.println("\n搜索 6: " + bst.search(6));
        System.out.println("搜索 2: " + bst.search(2));

        System.out.println("最小值: " + bst.getMinValue());
        System.out.println("最大值: " + bst.getMaxValue());

        System.out.println("树是否为空: " + bst.isEmpty());
        System.out.println("树的高度: " + bst.getHeight());

        bst.delete(6);
        System.out.println("删除节点 6 后的中序遍历:");
        bst.inorderTraversal();
        System.out.println();

        System.out.println("二叉搜索树的层序遍历:");
        List<List<Integer>> list = bst.levelOrder(bst.root);
        System.out.println(list);
    }
}