package LZWCompressor.fan231101_Image;
//import java.io.*;
//import java.util.*;
//import javax.imageio.*;
//import java.awt.image.*;
//import java.awt.*;
//
//public class LZWImageCompression {
//    public static void main(String[] args) {
//        // 输入图像文件和输出压缩文件的路径
//        String inputImagePath = "D:\\Desktop\\input.jpg";
//        String outputCompressedPath = "D:\\Desktop\\output.lzw";
//        try {
//            // 1. 读取输入图像
//            BufferedImage image = ImageIO.read(new File(inputImagePath));
//            // 2. 初始化LZW压缩器
//            LZWCompressor compressor = new LZWCompressor();
//            // 3. 创建输出流来写入压缩数据
//            FileOutputStream outputStream = new FileOutputStream(outputCompressedPath);
//            // 4. 压缩图像并写入输出流
//            compressor.compressImage(image, outputStream);
//            // 5. 关闭输出流
//            outputStream.close();
//            System.out.println("图像已成功压缩并保存到 " + outputCompressedPath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//class LZWCompressor {
//    private static final int MAX_DICT_SIZE = 4096;//最大允许的字典大小（LZW算法中的限制）
//
//    public void compressImage(BufferedImage image, OutputStream output) throws IOException {
//        // 1. 初始化LZW字典，字典包含了0到255之间的单字符编码
//        HashMap<String, Integer> dictionary = new HashMap<>();
//        for (int i = 0; i < 256; i++) {
//            dictionary.put("" + (char) i, i);
//        }
//        int dictSize = 256;
//        // 2. 初始化编码器
//        StringBuilder current = new StringBuilder();//构建当前处理的像素值的字符串
//        ArrayList<Integer> compressedData = new ArrayList<>();//存储压缩后的数据
//        int bits = 8;//编码位数
//        // 3. 遍历图像像素并进行LZW压缩
//        for (int y = 0; y < image.getHeight(); y++) {
//            for (int x = 0; x < image.getWidth(); x++) {//遍历每一个像素
//                Color color = new Color(image.getRGB(x, y));
//                //获取红、绿、蓝三个颜色通道的值，使用位运算将这三个通道的值合并成一个32位整数
//                int pixel = (color.getRed() << 16) | (color.getGreen() << 8) | color.getBlue();
//                //将pixel值转换为一个6位十六进制字符串，并将它追加到名为current的StringBuilder对象中
//                current.append(String.format("%06x", pixel));
//                //检查当前构建的字符串current是否存在于LZW字典中
//                if (!dictionary.containsKey(current.toString())) {
//                    if (dictSize < MAX_DICT_SIZE) {
//                        //将当前构建的字符串current添加到字典中，并分配一个新的编码值dictSize给它
//                        dictionary.put(current.toString(), dictSize++);
//                    }
//                    //删除current字符串的最后6个字符，以准备处理下一个像素
//                    current.delete(current.length() - 6, current.length());
//                    //如果current不为空，表示它包含了部分像素数据，但不在字典中
//                    if (!current.isEmpty()) {
//                        //添加到名为compressedData的列表中，以便后续写入输出流
//                        compressedData.add(dictionary.get(current.toString()));
//                    }
//
//                    //清空current
//                    current = new StringBuilder();
//                }
//            }
//        }
//        System.out.println(compressedData);
//        // 4. 10入压缩数据到输出流
//        DataOutputStream dataOutput = new DataOutputStream(output);
//        //写入编码位数bits到输出流，以便解压时可以知道编码的位数
//        dataOutput.writeShort(bits);
//        for (int code : compressedData) {
//            dataOutput.writeShort(code);
//            System.out.println(code);
//        }
//    }
//}


import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class LZWCompressor {

    public static void main(String[] args) {
        compress("D:\\Desktop\\input.jpg", "D:\\Desktop\\output.lzw");
    }

    public static void compress(String inputFileName, String outputFileName) {
        try {
            // 打开输入文件，准备读取
            FileInputStream input = new FileInputStream(inputFileName);
            // 打开输出文件，准备写入压缩数据
            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(outputFileName));

            int dictionarySize = 256;
            // 初始化字典，包含0到255之间的单字符编码
            HashMap<String, Integer> dictionary = new HashMap<>();
            for (int i = 0; i < dictionarySize; i++) {
                dictionary.put("" + (char) i, i);
            }

            String current = "";
            int nextCode = dictionarySize;
            int i = 1;
            int character;
            while ((character = input.read()) != -1) {
                System.out.println("character" + i + " " + character);
                String currentPlusCharacter = current + (char) character;
                if (dictionary.containsKey(currentPlusCharacter)) {
                    current = currentPlusCharacter;
                } else {
                    // 写入当前字符的编码
                    output.write(dictionary.get(current));
                    // 如果字典未满，将currentPlusCharacter添加到字典中并分配新编码
                    if (nextCode < 4096) {
                        dictionary.put(currentPlusCharacter, nextCode++);
                    }
                    current = "" + (char) character;
                }
            }

            if (!current.isEmpty()) {
                // 写入最后一个字符的编码
                output.write(dictionary.get(current));
            }

            // 关闭输入和输出流
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}