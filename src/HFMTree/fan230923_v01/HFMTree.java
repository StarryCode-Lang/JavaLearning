package HFMTree.fan230923_v01;

import java.util.ArrayList;
import java.util.List;

public class HFMTree {
    public List<TreeNode> listNode = new ArrayList<>();

//对ListNode进行快速排序（递归算法）
//    public List<TreeNode> quickSort(List<TreeNode> list) {
//        if (list == null || list.size() <= 1) {
//            return list;
//        }
//        int pivotIndex = list.size() / 2;
//        TreeNode pivot = list.get(pivotIndex);
//        List<TreeNode> smaller = new ArrayList<>();
//        List<TreeNode> equal = new ArrayList<>();
//        List<TreeNode> larger = new ArrayList<>();
//        for (TreeNode treeNode : list) {
//            if (treeNode.data < pivot.data) {
//                smaller.add(treeNode);
//            } else if (treeNode.data > pivot.data) {
//                larger.add(treeNode);
//            } else {
//                equal.add(treeNode);
//            }
//        }
//        smaller = quickSort(smaller);
//        larger = quickSort(larger);
//        List<TreeNode> result = new ArrayList<>();
//        result.addAll(smaller);
//        result.addAll(equal);
//        result.addAll(larger);
//        return result;
//    }


}

class TreeNode {
    public int data;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {

    }

    public TreeNode(int data) {
        this.data = data;
    }
}

