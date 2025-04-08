package LZWCompressor.fan231103;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LZWDecompression {
    public static void decompress(String compressedFile, String outputFile) throws IOException {
        String compressedData = readCompressedFile(compressedFile);
        String[] tokens = compressedData.split(" ");
        Map<Integer, String> dictionary = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            dictionary.put(i, String.valueOf((char) i));
        }

        int dictSize = 256;
        StringBuilder result = new StringBuilder();
        int previousCode = Integer.parseInt(tokens[0]);
        result.append(dictionary.get(previousCode));
        for (int i = 1; i < tokens.length; i++) {
            int currentCode = Integer.parseInt(tokens[i]);
            String entry;
            if (dictionary.containsKey(currentCode)) {
                entry = dictionary.get(currentCode);
            } else {
                entry = dictionary.get(previousCode) + dictionary.get(previousCode).charAt(0);
            }
            result.append(entry);
            dictionary.put(dictSize++, dictionary.get(previousCode) + entry.charAt(0));
            previousCode = currentCode;
        }

        writeDecompressedFile(result.toString(), outputFile);
    }

    private static String readCompressedFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        return sb.toString();
    }

    private static void writeDecompressedFile(String decompressedData, String outputFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        writer.write(decompressedData);
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        String compressedFile = "compressed.txt";
        String outputFile = "decompressed.txt";
        decompress(compressedFile, outputFile);
        System.out.println("Decompression complete.");
    }
}
