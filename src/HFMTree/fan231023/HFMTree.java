package HFMTree.fan231023;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 哈夫曼树   "aaaadfasdfasghggafsdgh" =>  "1010100010101010010"
 * 练习：压缩文本文件
 * 1.根据给定的字符串内容(读取文本文件)，统计没个字符出现的频率,创建对应的节点
 * 2.根据节点的权值创建哈夫曼树，统计字符编码
 * 3.把字符数据替换成对应的编码
 * 4.把字符编码没每8个一组转成byte写入文件：最后一个字节不足要补全
 * 5.把码表写入文件
 * 6.添加图形化界面
 * <p>
 * 解压文本文件
 * 1.读取文件数据，把byte数据转成二进制
 * 2.根据码表把二进制数据还原成对应的字符内容，再写入文件
 */
public class HFMTree {
    public List<TreeNode> listNode = new ArrayList<>();
    public HashMap<String, Integer> hm;
    //字符对应的编码
    public HashMap<String, String> codingMap = new HashMap<>();


    public void compress(String path, String outputPath) {
        String dataStr = readFile(path);
        System.out.println("dataStr:" + dataStr);
        number(dataStr);
        TreeNode root = createTree(listNode);
        setCode(root);
        inorder(root);
        String dataCoding = setCoding(dataStr);
        System.out.println("dataCoding:" + dataCoding);
        writerData(dataCoding);
    }

    //读取文本数据
    public String readFile(String path) {
        File file = new File(path);
        FileReader fr = null;
        String dataStr = "";
        try {
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String s = "";

            while ((s = br.readLine()) != null) {
                dataStr += s;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dataStr;
    }

    //统计字符频率
    public void number(String dataStr) {
        hm = new HashMap<>();
        for (int i = 0; i < dataStr.length(); i++) {
            if (!hm.containsKey(dataStr.charAt(i) + "")) {
                //添加当前字符到哈希表中
                hm.put(dataStr.charAt(i) + "", 1);
            } else {
                hm.put(dataStr.charAt(i) + "", hm.get(dataStr.charAt(i) + "") + 1);
            }
        }
        //根据字符数据创建节点
        for (String s : hm.keySet()) {
            TreeNode node = new TreeNode(hm.get(s), s);
            listNode.add(node);
        }
    }

    //把字符数据替换成对应的编码
    public String setCoding(String dataStr) {
        //把字符编码转成byte写入文件
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < dataStr.length(); i++) {
            stringBuilder.append(codingMap.get(dataStr.charAt(i) + ""));
        }
        return stringBuilder.toString();
    }

    //把字符的编码数据转成byte写入文件
    public void writerData(String codingStr) {
        try {
            FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\chen\\Desktop\\compress.txt"));

            int dataNum = codingStr.length() / 8; // 合成的字节数
            int last = codingStr.length() % 8;
            int len = 8;
            int data = 0;
            for (int j = 0; j < dataNum; j++) {
                for (int i = 0; i < len; i++) {
                    data = Integer.parseInt(codingStr.substring(j * len, (j + 1) * len), 2);
                }
                fos.write(data);
                fos.flush();
            }
            String s1 = "";
            for (int i = 0; i < 8 - last; i++) {
                s1 += "0";
            }
            String lastStr = codingStr.substring(dataNum * 8, dataNum * 8 + last) + s1;
            byte ls = (byte) Integer.parseInt(lastStr, 2);
            fos.write(ls);
            //最后补位
            fos.write(8 - last);
            fos.flush();

            System.out.println("写文件成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //对ListNode 进排序(快排)
    public void sort(List<TreeNode> listNode) {
        for (int i = 0; i < listNode.size(); i++) {
            for (int j = i + 1; j < listNode.size(); j++) {
                if (listNode.get(i).data > listNode.get(j).data) {
                    TreeNode temp = listNode.get(i);
                    listNode.set(i, listNode.get(j));
                    listNode.set(j, temp);
                }
            }
        }
    }

    //创建哈夫曼树
    public TreeNode createTree(List<TreeNode> listNode) {
        while (listNode.size() > 1) {
            //对节点排序
            sort(listNode);
            //从ListNode 找出最小的两个节点
            TreeNode leftNode = listNode.remove(0);
            TreeNode rightNode = listNode.remove(0);

            //根据最小的两个节点创建父节点
            TreeNode node = new TreeNode(leftNode.data + rightNode.data);
            node.left = leftNode;
            node.right = rightNode;

            //把子树添加List中，重新排序
            listNode.add(node);
        }
        //返回哈夫曼树的根节点
        return listNode.remove(0);
    }

    //哈夫曼：统计叶子节点的哈夫曼编码
    public void setCode(TreeNode root) {
        if (root.left != null) {
            root.left.c = root.c + "0";
            setCode(root.left);
        }
        if (root.right != null) {
            root.right.c = root.c + "1";
            setCode(root.right);
        }
    }

    //打印叶子节点
    public void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            //判断是否是叶子节点
            if (root.left == null && root.right == null) {
                System.out.println(root.data + " : " + root.c);
                codingMap.put(root.c, root.c);
            }
            inorder(root.right);
        }
    }

    public static void main(String[] args) {
        String path = "file\\msg.txt";

    }


}
