package HFMTree.fan231023;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Decompress {

    public static void main(String[] args) {
        Decompress de = new Decompress();
        File file = new File("D:\\Desktop\\compress.txt");
        try {
            String s = de.read(file);

            System.out.println("s：" + s);
            String data = de.codeToStr(s);
            System.out.println("data：" + data);

            // Save decompressed data to file
            de.saveToFile(data, "D:\\Desktop\\out.txt");
            System.out.println("Decompressed data saved to out.txt");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public HashMap<String, String> codingMap;

    // 1.读取压缩后的数据
    public String read(File file) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        // 读码表
        codingMap = (HashMap<String, String>) ois.readObject();

        List<Integer> dataList = new ArrayList<>();

        // 读压缩数据
        int b;
        while ((b = fis.read()) != -1) {
            dataList.add(b);
        }

        StringBuilder stringBuilder = new StringBuilder();
        // 补位数
        int last = dataList.get(dataList.size() - 1);
        System.out.println("last:" + last);
        String typeStr = "";
        for (int i = 0; i < dataList.size() - 1; i++) {
            String s = Integer.toBinaryString(dataList.get(i));
            if (s.length() != 8) {
                // 前缀补0
                s = String.format("%8s", s).replace(' ', '0');
            }
            stringBuilder.append(s);
        }
        return stringBuilder.substring(0, stringBuilder.length() - last);
    }

    // 2.把bit 编码根码表比较，匹配对应的编码
    public String codeToStr(String code) {
        StringBuilder result = new StringBuilder();
        StringBuilder currentCode = new StringBuilder();
        for (char c : code.toCharArray()) {
            currentCode.append(c);
            if (codingMap.containsValue(currentCode.toString())) {
                for (String key : codingMap.keySet()) {
                    if (codingMap.get(key).equals(currentCode.toString())) {
                        result.append(key);
                        currentCode = new StringBuilder();
                        break;
                    }
                }
            }
        }
        return result.toString();
    }

    // 3.保存解压后的数据到文件
    public void saveToFile(String data, String filename) throws Exception {
        FileWriter writer = new FileWriter(filename);
        writer.write(data);
        writer.close();
    }
}