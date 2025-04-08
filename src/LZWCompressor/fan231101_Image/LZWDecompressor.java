package LZWCompressor.fan231101_Image;
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.util.*;
//
//public class LZWImageDecompression {
//    public static void main(String[] args) {
//        // 输入压缩文件和输出图像文件的路径
//        String inputCompressedPath = "D:\\Desktop\\output.lzw";
//        String outputImagePath = "D:\\Desktop\\output.jpg";
//        try {
//            // 1. 创建输入流来读取压缩数据
//            FileInputStream inputStream = new FileInputStream(inputCompressedPath);
//            // 2. 初始化LZW解压缩器
//            LZWDecompressor decompressor = new LZWDecompressor();
//            // 3. 解压缩数据并保存为图像
//            decompressor.decompressImage(inputStream, outputImagePath);
//            // 4. 关闭输入流
//            inputStream.close();
//            System.out.println("压缩文件已成功解压缩并保存为 " + outputImagePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//class LZWDecompressor {
//    public void decompressImage(FileInputStream input, String outputPath) throws IOException {
//        // 1. 读取编码位数
//        DataInputStream dataInput = new DataInputStream(input);
//        int dictSize = 256;
//        // 2. 初始化解压缩字典，与压缩时的字典初始状态相同
//        HashMap<Integer, String> dictionary = new HashMap<>();
//        for (int i = 0; i < 256; i++) {
//            dictionary.put(i, "" + (char) i);
//        }
//        // 3. 初始化解压缩数据
//        ArrayList<String> decompressedData = new ArrayList<>();
//        int currentCode = -1;
//        String currentString = "";
//        // 4. 读取压缩数据并解压缩
//        while (true) {
//            try {
//                int code = dataInput.readShort();
//                if (currentCode != -1) {
//                    String entry;
//                    if (code == dictSize) {
//                        entry = dictionary.get(currentCode) + currentString.charAt(0);
//                    } else if (dictionary.containsKey(code)) {
//                        entry = dictionary.get(code);
//                    } else {
//                        throw new IllegalArgumentException("Invalid code: " + code);
//                    }
//                    decompressedData.add(entry);
//                    dictionary.put(dictSize++, dictionary.get(currentCode) + entry.charAt(0));
//                } else {
//                    if (dictionary.containsKey(code)) {
//                        currentString = dictionary.get(code);
//                        decompressedData.add(currentString);
//                    } else {
//                        throw new IllegalArgumentException("Invalid code: " + code);
//                    }
//                }
//                currentCode = code;
//            } catch (EOFException e) {
//                break;
//            }
//        }
//        // 5. 保存解压缩后的数据为图像
//        saveDecompressedDataAsImage(decompressedData, outputPath);
//    }
//
//    private void saveDecompressedDataAsImage(ArrayList<String> data, String outputPath) throws IOException {
//        int width = data.get(0).length() / 6;
//        int height = data.size();
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//                String pixelString = data.get(y).substring(x * 6, (x + 1) * 6);
//                int pixelValue = Integer.parseInt(pixelString, 16);
//                image.setRGB(x, y, pixelValue);
//            }
//        }
//        ImageIO.write(image, "jpg", new File(outputPath));
//    }
//}

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class LZWDecompressor {

    public static void main(String[] args) {
        decompress("D:\\Desktop\\output.lzw", "D:\\Desktop\\output.jpg");
    }

    public static void decompress(String inputFileName, String outputFileName) {
        try {
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(inputFileName));
            FileOutputStream output = new FileOutputStream(outputFileName);

            int dictionarySize = 256;
            String[] dictionary = new String[4096];

            for (int i = 0; i < dictionarySize; i++) {
                dictionary[i] = "" + (char) i;
            }

            int currentCode = input.read();
            String currentString = "" + (char) currentCode;
            output.write(currentCode);

            int nextCode = dictionarySize;

            int code;
            while ((code = input.read()) != -1) {
                if (code < nextCode) {
                    String entry = dictionary[code];
                    output.write(entry.getBytes());
                    if (nextCode < 4096) {
                        dictionary[nextCode++] = currentString + entry.charAt(0);
                    }
                } else {
                    String entry = currentString + currentString.charAt(0);
                    output.write(entry.getBytes());
                    if (nextCode < 4096) {
                        dictionary[nextCode++] = entry;
                    }
                }
                currentString = dictionary[code];
            }

            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
