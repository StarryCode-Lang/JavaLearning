package HFMTree.fan231010;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HFMTree {
    public List<TreeNode> listNode = new ArrayList<>();
    public HashMap<String, Integer> hm = new HashMap<>();
    public HashMap<String, String> codingMap = new HashMap<>();

    public String readFile(String path) {
        File file = new File(path);
        StringBuilder datastr = new StringBuilder();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                datastr.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return datastr.toString();
    }

    public void number(String datastr) {
        hm = new HashMap<>();
        for (int i = 0; i < datastr.length(); i++) {
            String key = String.valueOf(datastr.charAt(i));
            hm.put(key, hm.getOrDefault(key, 0) + 1);
        }
        listNode = new ArrayList<>();
        for (String s : hm.keySet()) {
            TreeNode node = new TreeNode(hm.get(s), s.charAt(0));
            listNode.add(node);
        }
    }

    public String setCode(String datastr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < datastr.length(); i++) {
            stringBuilder.append(codingMap.get(String.valueOf(datastr.charAt(i))));
        }
        return stringBuilder.toString();
    }

    public void writeData(String codingStr, String filePath) {
        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            int dataNum = codingStr.length() / 8;
            int last = codingStr.length() % 8;
            int len = 8;
            for (int i = 0; i < dataNum; i++) {
                String byteStr = codingStr.substring(i * len, (i + 1) * len);
                int num = Integer.parseInt(byteStr, 2);
                outputStream.write(num);
            }
            if (last > 0) {
                String lastByteStr = codingStr.substring(dataNum * len);
                int num = Integer.parseInt(lastByteStr, 2);
                outputStream.write(num);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sort(List<TreeNode> listNode) {
        listNode.sort((a, b) -> a.data - b.data);
    }

    public TreeNode createTree(List<TreeNode> listNode) {
        while (listNode.size() > 1) {
            sort(listNode);
            TreeNode leftNode = listNode.get(0);
            TreeNode rightNode = listNode.get(1);
            TreeNode parentNode = new TreeNode(leftNode.data + rightNode.data, "");
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            listNode.remove(leftNode);
            listNode.remove(rightNode);
            listNode.add(parentNode);
        }
        return listNode.get(0);
    }

    public void generateCodingMap(TreeNode root, String code) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            codingMap.put(String.valueOf(root.c), code);
        }
        generateCodingMap(root.left, code + "0");
        generateCodingMap(root.right, code + "1");
    }

    public void compress(String inputFilePath, String outputFilePath) {
        String datastr = readFile(inputFilePath);
        number(datastr);
        TreeNode root = createTree(listNode);
        generateCodingMap(root, "");
        String codingStr = setCode(datastr);
        writeData(codingStr, outputFilePath);
    }

    public static void main(String[] args) {
        HFMTree hfmTree = new HFMTree();
        hfmTree.compress("input.txt", "output.bin");
    }
}

class TreeNode {
    public String code = "";  //统计节点的编码
    public int data;   //字符出现次数
    public char c;  //当前的字符内容
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {

    }

    public TreeNode(int data) {
        this.data = data;
    }

    public TreeNode(int data, String s) {
        this.data = data;
        this.code = s;
    }

    public TreeNode(Integer data, char c) {
        this.data = data;
        this.c = c;
    }
}