package HFMTree.fan231028;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Decompress {
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
        //把codingMap码表的key value互换
        HashMap<String, String> reverseCodingMap = new HashMap<>();
        for (String key : codingMap.keySet()) {
            reverseCodingMap.put(codingMap.get(key), key);
        }
        StringBuffer data = new StringBuffer();
        //每取出一个字节就在map中匹配一次
        int i = 0;
        int index = 0;
        int codeLen = code.length();
        while (i < codeLen) {
            index++;
            String key = code.substring(i, index);
            String value = reverseCodingMap.get(key);
            //返回Map中的所有键值
            if (value != null) {
                data.append(value);
                System.out.print(value);
                i = index;
            }
        }
        return data.toString();
    }

    // 3.保存解压后的数据到文件
    public void saveToFile(String data, String filename) throws Exception {
        FileWriter writer = new FileWriter(filename);
        writer.write(data);
        writer.close();
    }

    public void decode(String compressPath, String decodePath) {
        File file = new File(compressPath);
        try {
            String s = read(file);

            System.out.println("s：" + s);
            String data = codeToStr(s);
            System.out.println("data：" + data);
            
            saveToFile(data, decodePath);
            System.out.println("Decompressed data saved to " + decodePath);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    public static void main(String[] args) {
        Decompress decompress = new Decompress();
        decompress.decode("D:\\Desktop\\compress.txt", "D:\\Desktop\\decode.txt");
    }
}