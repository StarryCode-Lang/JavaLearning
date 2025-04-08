package HFMTree.fan231028;

import java.io.*;
import java.util.*;

public class HFMTree {
    private TreeNode root;
    private HashMap<String, Integer> hm = new HashMap<>();
    private HashMap<String, String> codingMap = new HashMap<>();

    /**
     * 压缩文件
     *
     * @param filePath   输入文件路径
     * @param outputPath 输出文件路径
     */
    public void compress(String filePath, String outputPath) {
        try (FileReader fr = new FileReader(filePath);
             BufferedReader br = new BufferedReader(fr);
             FileOutputStream fos = new FileOutputStream(new File(outputPath))) {

            // 读取文件并构建频率映射
            StringBuilder dataStr = new StringBuilder();
            String s;
            while ((s = br.readLine()) != null) {
                dataStr.append(s);
            }
            buildFrequencyMap(dataStr.toString());

            // 构建Huffman树
            buildHuffmanTree();

            // 构建编码映射
            buildCodingMap(root, "");

            // 写入编码映射
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(codingMap);

            // 写入编码字符串
            String codingStr = setCoding(dataStr.toString());
            writeCodingString(codingStr, fos);

            System.out.println("文件压缩成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 构建频率映射
     *
     * @param dataStr 输入字符串
     */
    private void buildFrequencyMap(String dataStr) {
        for (int i = 0; i < dataStr.length(); i++) {
            String c = dataStr.charAt(i) + "";
            if (hm.containsKey(c)) {
                hm.put(c, hm.get(c) + 1);
            } else {
                hm.put(c, 1);
            }
        }
    }

    /**
     * 构建Huffman树
     */
    private void buildHuffmanTree() {
        List<TreeNode> listNode = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : hm.entrySet()) {
            TreeNode node = new TreeNode(entry.getValue(), entry.getKey());
            listNode.add(node);
        }

        while (listNode.size() > 1) {
            Collections.sort(listNode, Comparator.comparingInt(node -> node.data));
            TreeNode left = listNode.get(0);
            TreeNode right = listNode.get(1);
            TreeNode parent = new TreeNode(left.data + right.data);
            parent.left = left;
            parent.right = right;
            listNode.remove(left);
            listNode.remove(right);
            listNode.add(parent);
        }

        root = listNode.get(0);
    }

    /**
     * 构建编码映射
     *
     * @param node 当前节点
     * @param code 当前编码
     */
    private void buildCodingMap(TreeNode node, String code) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                codingMap.put(node.c, code);
            }
            buildCodingMap(node.left, code + "0");
            buildCodingMap(node.right, code + "1");
        }
    }

    /**
     * 设置编码字符串
     *
     * @param dataStr 输入字符串
     * @return 编码字符串
     */
    private String setCoding(String dataStr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < dataStr.length(); i++) {
            stringBuilder.append(codingMap.get(dataStr.charAt(i) + ""));
        }
        return stringBuilder.toString();
    }

    /**
     * 写入编码字符串
     *
     * @param codingStr 编码字符串
     * @param fos       输出流
     * @throws IOException IO异常
     */
    private void writeCodingString(String codingStr, FileOutputStream fos) throws IOException {
        int dataNum = codingStr.length() / 8;
        int last = codingStr.length() % 8;
        int len = 8;
        int data = 0;
        for (int j = 0; j < dataNum; j++) {
            data = Integer.parseInt(codingStr.substring(j * len, (j + 1) * len), 2);
            fos.write(data);
        }
        if (last > 0) {
            String s1 = "";
            for (int i = 0; i < 8 - last; i++) {
                s1 += "0";
            }
            String lastStr = codingStr.substring(dataNum * 8, dataNum * 8 + last) + s1;
            byte ls = (byte) Integer.parseInt(lastStr, 2);
            fos.write(ls);
        }
        fos.write(8 - last);
    }

    public static void main(String[] args) {
        HFMTree hfmTree = new HFMTree();
        hfmTree.compress("D:\\Desktop\\abcd.txt", "D:\\Desktop\\compress.txt");
    }
}