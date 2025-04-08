package HFMTree.fan231023;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

//事件处理类
public class ButtonListener implements ActionListener {
    private final JTextField jtf;
    private final JTextField jtf2;
    private final JFrame jf;
    private String inputPath = "";
    private final String outputPath = "D:\\Desktop\\compress.txt";
    private String decodingPath = "D:\\Desktop\\decode.txt";

    private File outFile = new File("D:\\Desktop\\compress.txt");

    public ButtonListener(JTextField jtf, JTextField jtf2, JFrame jf) {
        this.jtf = jtf;
        this.jtf2 = jtf2;
        this.jf = jf;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        JFileChooser jFileChooser = new JFileChooser();
        switch (s) {
            case "选择压缩文件":
                int returnValue = jFileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = jFileChooser.getSelectedFile();
                    inputPath = file.toString();
                    jtf.setText(inputPath);
                    System.out.println("inputPath:" + inputPath);
                }
                break;
            case "确认压缩":
                HFMTree hfmTree = new HFMTree();
                hfmTree.compress(inputPath, outputPath);
                jf.dispose();
                break;

            case "选择解压缩文件":
                int returnValue2 = jFileChooser.showOpenDialog(null);
                if (returnValue2 == JFileChooser.APPROVE_OPTION) {
                    File file = jFileChooser.getSelectedFile();
                    decodingPath = file.toString();
                    jtf2.setText(decodingPath);
                    System.out.println("decodingPath:" + decodingPath);
                }
                break;
            case "解压缩":
                Decompress decompress = new Decompress();

                jf.dispose();
                break;
        }
    }
}
