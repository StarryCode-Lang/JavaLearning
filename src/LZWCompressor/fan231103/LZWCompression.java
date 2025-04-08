package LZWCompressor.fan231103;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LZWCompression {
    public static void compress(String inputFile, String outputFile) throws IOException {
        String inputText = readTextFile(inputFile);
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            dictionary.put(String.valueOf((char) i), i);
        }

        int dictSize = 256;
        String current = "";
        StringBuilder result = new StringBuilder();
        for (char c : inputText.toCharArray()) {
            String currentChar = current + c;
            if (dictionary.containsKey(currentChar)) {
                current = currentChar;
            } else {
                result.append(dictionary.get(current)).append(" ");
                dictionary.put(currentChar, dictSize++);
                current = String.valueOf(c);
            }
        }

        if (!current.isEmpty()) {
            result.append(dictionary.get(current));
        }

        writeCompressedFile(result.toString(), outputFile);
    }

    private static String readTextFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    private static void writeCompressedFile(String compressedData, String outputFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        writer.write(compressedData);
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        String inputFile = "D:\\Desktop\\abcd.txt";
        String outputFile = "D:\\Desktop\\compressed.txt";
        compress(inputFile, outputFile);
        System.out.println("Compression complete.");
    }
}
