package HFMTree.fan231014;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Decoding {
    public List<TreeNode> listNode = new ArrayList<>();
    public HashMap<String, String> codingMap = new HashMap<>();

    // 读取码表文件，将其存入codingMap中
    public void readCodingMap() {
        try (BufferedReader reader = new BufferedReader(new FileReader("D:\\Desktop\\codingMap.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] keyAndValue = line.split(":");
                codingMap.put(keyAndValue[0], keyAndValue[1]);
            }
            System.out.println("codingMap:" + codingMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 解压缩方法
    public void decompress(String inputFilePath, String outputFilePath) {
        StringBuilder codingStr = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] decimalNumbers = line.split(" ");
                for (String decimalNumber : decimalNumbers) {
                    int decimal = Integer.parseInt(decimalNumber);
                    StringBuilder binary = new StringBuilder(Integer.toBinaryString(decimal));
                    while (binary.length() < 8) {
                        binary.insert(0, "0");
                    }
                    codingStr.append(binary);
                }
            }
            System.out.println("codingStr:" + codingStr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        StringBuilder decodedStr = new StringBuilder();
        StringBuilder currentCode = new StringBuilder();
        for (int i = 0; i < codingStr.length(); i++) {
            currentCode.append(codingStr.charAt(i));
            String code = currentCode.toString();
            if (codingMap.containsValue(code)) {
                String key = getKeyByValue(codingMap, code);
                decodedStr.append(key);
                currentCode.setLength(0);
            }
        }
        System.out.println("decodedStr:" + decodedStr.substring(0, decodedStr.length() - 2));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write(decodedStr.substring(0, decodedStr.length() - 2));
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 根据value获取对应的key
    private String getKeyByValue(HashMap<String, String> map, String value) {
        for (String key : map.keySet()) {
            if (map.get(key).equals(value)) {
                return key;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Decoding decoding = new Decoding();
        decoding.readCodingMap();
        decoding.decompress("D:\\Desktop\\compress.txt", "D:\\Desktop\\decode.txt");
    }
}