package HFMTree.fan230924_v02;

import java.util.List;
import java.util.Stack;

public class BinaryTreeQuickSort {
    public List<HFMTree.TreeNode> quickSort(List<HFMTree.TreeNode> list) {
        if(list == null || list.size()<=1){
            return list;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(list.size()-1);
        while(!stack.isEmpty()){
            int right = stack.pop();
            int left = stack.pop();
            int pivot = partition(list, left, right);
            if(left<pivot-1){
                stack.push(left);
                stack.push(pivot-1);
            }
            if(right>pivot+1){
                stack.push(pivot+1);
                stack.push(right);
            }
        }
        return list;
    }
    private int partition(List<HFMTree.TreeNode> list, int left, int right){
        HFMTree.TreeNode pivot = list.get(right);
        int i = left;
        for(int j=left;j<right;j++){
            if(list.get(j).data<pivot.data){
                swap(list, i, j);
                i++;
            }
        }
        swap(list, i, right);
        return i;
    }
    private void swap(List<HFMTree.TreeNode> list, int i, int j){
        HFMTree.TreeNode temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}

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