# Java Learning Project

## Overview
This Java project is a comprehensive learning resource that covers various fundamental concepts in Java programming, including data structures, algorithms, multi - threading, and file handling. It features implementations of Huffman coding for file compression and decompression, multi - threaded applications for simple animations, recursive art drawing, hash map implementations using linked lists, and queue operations.

## Project Structure
The project is structured into several main directories, each focusing on a different area of Java programming:
- **`HFMTree`**: Contains all the code related to Huffman coding. This includes classes for reading text files, counting character frequencies, building Huffman trees, encoding and decoding text, and compressing and decompressing files.
- **`Thread`**: Demonstrates multi - threading concepts through a simple bouncing balls simulation.
- **`Recursion`**: Showcases the use of recursion in an art drawing application.
- **`LZWCompressor`**: Contains code for LZW compression, including reading text files.

## Features

### Huffman Coding (`HFMTree`)
- **Frequency Counting**: Analyzes a given text file to calculate the frequency of each character.
- **Tree Construction**: Builds a Huffman tree based on the character frequencies, which is used for encoding and decoding.
- **Encoding and Decoding**: Converts the original text into a binary code using the Huffman tree and can revert the binary code back to the original text.
- **File Compression and Decompression**: Writes the encoded data to a file and reads it back for decompression, handling padding and byte conversion.

### Multi - threading (`Thread`)
- **Bouncing Balls Simulation**: Uses threads to create an animation of bouncing balls, demonstrating how to manage concurrent tasks in Java.

### Recursion (`Recursion`)
- **Art Drawing**: Implements recursive algorithms to draw various art patterns on a graphical user interface.

### LZW Compression (`LZWCompressor`)
- **Text File Reading**: Reads text files and prepares the data for LZW compression.

## Prerequisites
- **Java Development Kit (JDK)**: Version 20 or later is required to compile and run the project.
- **Integrated Development Environment (IDE)**: IntelliJ IDEA is recommended for a seamless development experience, but any Java - compatible IDE can be used.

## Libraries and Frameworks
This project primarily uses standard Java libraries. However, there are some library references in the project's configuration files:
- **`bridj - 0.7.0`**: Although not actively used in the core functionality of the project, it is referenced in the `.idea/libraries/bridj_0_7_0.xml` file. This library is likely used for native code integration and is not necessary for the main features of this learning project. The referenced libraries include:
  - `webcam - capture - 0.3.12.jar`
  - `slf4j - api - 1.7.2.jar`
  - `bridj - 0.7.0.jar`

## How to Run

### Clone the Repository
First, clone the project repository to your local machine:
```sh
git clone <repository-url>
cd JavaLearning
```

### Open the Project in IntelliJ IDEA
1. Launch IntelliJ IDEA.
2. Select `File` > `Open` and navigate to the `JavaLearning` directory.
3. Open the project.

### Configure the Project
- Ensure that the JDK is properly configured in IntelliJ IDEA. Go to `File` > `Project Structure` > `Project Settings` > `Project` and select the appropriate JDK version.

### Run the Main Classes
- **Huffman Coding**: The main class for Huffman coding can be found in the `HFMTree` directory. Right - click on the main class (usually named `HFMTree` or `Main`) and select `Run`. You may need to provide the path to the input text file.
- **Multi - threading**: In the `Thread` directory, find the `Main` class. Right - click on it and select `Run` to start the bouncing balls simulation.
- **Recursion**: In the `Recursion` directory, run the `Main` class to start the art drawing application.
- **LZW Compression**: In the `LZWCompressor` directory, run the main class to perform LZW compression on a text file.

## Code Examples

### Huffman Coding - Reading a File and Counting Frequencies
```java
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

public void number(String dataStr) {
    HashMap<String, Integer> hm = new HashMap<>();
    for (int i = 0; i < dataStr.length(); i++) {
        String key = String.valueOf(dataStr.charAt(i));
        hm.put(key, hm.getOrDefault(key, 0) + 1);
    }
    List<TreeNode> listNode = new ArrayList<>();
    for (String s : hm.keySet()) {
        TreeNode node = new TreeNode(hm.get(s), s);
        listNode.add(node);
    }
}
```

### Multi - threading - Starting the Bouncing Balls Simulation
```java
public class Main extends JFrame {
    private final ArrayList<Shape> arrayList = new ArrayList<>();

    public void initUI() {
        this.setTitle("Bouncing Balls");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        FlowLayout fl = new FlowLayout();
        this.setLayout(fl);
        Button bu = new Button("Start");
        this.add(bu);
        this.setVisible(true);
        Graphics g = this.getGraphics();
        BuAction buAction = new BuAction(g, arrayList);
        bu.addActionListener(buAction);
        BallThread ballThread = new BallThread(g, arrayList);
        ballThread.start();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.initUI();
    }
}
```

## Contributing
Contributions to this project are welcome. If you have any improvements or new features to add, please follow these steps:
1. Fork the repository.
2. Create a new branch for your feature: `git checkout -b feature - name`.
3. Make your changes and commit them: `git commit -m "Add new feature"`.
4. Push your changes to your fork: `git push origin feature - name`.
5. Open a pull request in the original repository.

## License
This project is licensed under the [MIT License](LICENSE).
