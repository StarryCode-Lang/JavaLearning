package HFMTree.fan231014;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class HFMTree {
    public List<TreeNode> listNode = new ArrayList<>();
    public HashMap<String, Integer> hm;
    //字符对应的编码
    public HashMap<String, String> codingMap = new HashMap<>();
    //原始文件二进制长度
    public int fileLength = 0;

    /**
     * 读取文件内容
     *
     * @param path 文件路径
     * @return 文件内容
     */
    public String readFile(String path) {
        File file = new File(path);
        StringBuilder dataStr = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                dataStr.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dataStr.toString();
    }

    /**
     * 统计字符出现的次数
     *
     * @param dataStr 文件内容
     */
    public void number(String dataStr) {
        hm = new HashMap<>();
        for (int i = 0; i < dataStr.length(); i++) {
            String key = String.valueOf(dataStr.charAt(i));
            hm.put(key, hm.getOrDefault(key, 0) + 1);
        }
        listNode = new ArrayList<>();
        for (String s : hm.keySet()) {
            TreeNode node = new TreeNode(hm.get(s), s);
            listNode.add(node);
        }
    }

    /**
     * 根据字符编码生成编码字符串
     *
     * @param dataStr 文件内容
     * @return 编码字符串
     */
    public String setCode(String dataStr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < dataStr.length(); i++) {
            stringBuilder.append(codingMap.get(String.valueOf(dataStr.charAt(i))));
        }
        //输出二进制编码
        System.out.println(stringBuilder);
        fileLength = stringBuilder.length();
        System.out.println("fileLength:" + fileLength);
        return stringBuilder.toString();
    }

    /**
     * 将编码字符串写入文件
     *
     * @param codingStr 编码字符串
     * @param filePath  输出文件路径
     */
    public void writeData(String codingStr, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            int dataNum = codingStr.length() / 8;
            int last = codingStr.length() % 8;
            int len = 8;
            //十进制数（每8位一组）
            for (int i = 0; i < dataNum; i++) {
                String byteStr = codingStr.substring(i * len, (i + 1) * len);
                int num = Integer.parseInt(byteStr, 2);
                //整型转字符串
                String strNum = Integer.toString(num);
                writer.write(strNum);
                writer.newLine();
                System.out.println(num);
            }
            if (last > 0) {
                String lastByteStr = codingStr.substring(dataNum * len);
                int num = Integer.parseInt(lastByteStr, 2);
                //整型转字符串
                String strNum = Integer.toString(num);
                writer.write(strNum);
            }
            System.out.println("写文件成功！");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对节点列表进行排序
     *
     * @param listNode 节点列表
     */
    public void sort(List<TreeNode> listNode) {
        listNode.sort(Comparator.comparingInt(a -> a.data));
    }

    /**
     * 创建哈夫曼树
     *
     * @param listNode 节点列表
     * @return 哈夫曼树的根节点
     */
    public TreeNode createTree(List<TreeNode> listNode) {
        while (listNode.size() > 1) {
            sort(listNode);
            TreeNode leftNode = listNode.remove(0);
            TreeNode rightNode = listNode.remove(0);
            TreeNode parentNode = new TreeNode(leftNode.data + rightNode.data);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            listNode.add(parentNode);
        }
        return listNode.remove(0);
    }

    /**
     * 生成字符编码映射表
     *
     * @param root 哈夫曼树的根节点
     * @param code 编码
     */
    public void generateCodingMap(TreeNode root, String code) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            codingMap.put(root.c, code);
        }
        generateCodingMap(root.left, code + "0");
        generateCodingMap(root.right, code + "1");
    }

    /**
     * 压缩文件
     *
     * @param inputFilePath  输入文件路径
     * @param outputFilePath 输出文件路径
     */
    public void compress(String inputFilePath, String outputFilePath) {
        String dataStr = readFile(inputFilePath);
        number(dataStr);
        TreeNode root = createTree(listNode);
        generateCodingMap(root, "");
        String codingStr = setCode(dataStr);
        writeData(codingStr, outputFilePath);
        System.out.println("codingMap:" + codingMap);
        //把codingMap存入文件
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Desktop\\codingMap.txt"))) {
            for (String key : codingMap.keySet()) {
                writer.write(key + ":" + codingMap.get(key) + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String inputPath = "D:\\Desktop\\abcd.txt";
        String outputPath = "D:\\Desktop\\compress.txt";
        HFMTree hfmTree = new HFMTree();
        hfmTree.compress(inputPath, outputPath);
    }
}