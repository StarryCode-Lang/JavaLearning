package HFMTree.fan230924_v02;

import java.util.ArrayList;
import java.util.List;

public class HFMTree {
    /**
     * 创建哈夫曼树
     *
     * @param listNode 存储节点的列表
     * @return 哈夫曼树的根节点
     */
    public TreeNode createTree(List<TreeNode> listNode) {
        // 对节点列表进行快速排序
        BinaryTreeQuickSort quickSort = new BinaryTreeQuickSort();
        List<TreeNode> sortList = quickSort.quickSort(listNode);
        System.out.println("初始树的排序：");
        printTree(sortList);

        // 创建哈夫曼树
        System.out.println("创建哈夫曼树过程中的排序：");
        while (listNode.size() > 1) {
            // 从排序后的列表中找出最小的两个节点
            TreeNode leftNode = sortList.remove(0);
            TreeNode rightNode = sortList.remove(0);
            // 根据最小的两个节点创建父节点
            TreeNode node = new TreeNode(leftNode.data + rightNode.data);
            // 将子树添加到列表中，重新排序
            node.left = leftNode;
            node.right = rightNode;
            listNode.add(node);

            sortList = quickSort.quickSort(listNode);
            printTree(sortList);
        }
        // 返回哈夫曼树的根节点
        return listNode.remove(0);
    }

    //编码
    public void encode(TreeNode root) {
        if (root.left != null) {
            root.left.code = root.code + "0";
            encode(root.left);
        }
        if (root.right != null) {
            root.right.code = root.code + "1";
            encode(root.right);
        }
    }

    //打印叶子节点
    public void printLeaf(TreeNode root) {
        if (root != null) {
            //判断是否为叶子节点
            if (root.left == null && root.right == null) {
                System.out.println(root.data + " : " + root.code);
            }
            printLeaf(root.left);
            printLeaf(root.right);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 2, 4, 5, 6};
        List<TreeNode> listNode = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            listNode.add(new TreeNode(arr[i]));
        }
        // 输出当前的树
        System.out.println("当前的树:");
        printTree(listNode);

        HFMTree hfmTree = new HFMTree();
        TreeNode treeNode = hfmTree.createTree(listNode);
        hfmTree.encode(treeNode);
        hfmTree.printLeaf(treeNode);
    }

    /**
     * 输出树
     *
     * @param listNode 树的节点列表
     */
    public static void printTree(List<TreeNode> listNode) {
        for (TreeNode node : listNode) {
            System.out.print(node.data + " ");
        }
        System.out.println();
    }

    /**
     * 输出哈夫曼树
     *
     * @param root 哈夫曼树的根节点
     */
    public static void printTree(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.println(root.data + " ");
        printTree(root.left);
        printTree(root.right);
    }

    /**
     * 哈夫曼树的节点类
     */
    static class TreeNode {
        public String code;
        public int data;  //字符出现的次数
        public char c;//当前的字符内容
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int data) {
            this.data = data;
        }
    }
}