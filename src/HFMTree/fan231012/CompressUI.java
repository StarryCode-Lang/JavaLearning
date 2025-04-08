package HFMTree.fan231012;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

//压缩工具
//去压缩大文件？
public class CompressUI {

    public void initUI() {
        JFrame jf = new JFrame("压缩工具");
        jf.setSize(450, 500);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jf.setLayout(new FlowLayout());
        JTextField jtf = new JTextField();
        jtf.setPreferredSize(new Dimension(330, 30));
        jf.add(jtf);

        JButton btn = new JButton("选择压缩文件");
        jf.add(btn);

        JButton btn2 = new JButton("确认压缩");
        jf.add(btn2);

        JTextField jtf2 = new JTextField();
        jtf2.setPreferredSize(new Dimension(330, 30));
        jf.add(jtf2);

        JButton btn3 = new JButton("选择解压缩文件");
        jf.add(btn3);

        JButton btn4 = new JButton("解压缩");
        jf.add(btn4);

        ButtonListener listener = new ButtonListener(jtf, jtf2, jf);
        btn.addActionListener(listener);
        btn2.addActionListener(listener);

        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new CompressUI().initUI();
    }
}

//事件处理类
class ButtonListener implements ActionListener {
    private JTextField jtf, jtf2;
    private JFrame jf;
    private String inputPath = "";
    private String outputPath = "D:\\Desktop\\compress.txt";
    private String decodingPath = "D:\\Desktop\\decode.txt";

    public ButtonListener(JTextField jtf, JTextField jtf2, JFrame jf) {
        this.jtf = jtf;
        this.jtf2 = jtf2;
        this.jf = jf;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        switch (s) {
            case "选择压缩文件":
                JFileChooser jFileChooser = new JFileChooser();
                int returnValue = jFileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = jFileChooser.getSelectedFile();
                    inputPath = file.toString();
                    jtf.setText(inputPath);
                    System.out.println("inputPath:" + inputPath);
                }
                break;
            case "确认压缩":
                new HFMTree().compress(inputPath, outputPath);
                jf.dispose();
                break;

            case "选择解压缩文件":
                JFileChooser jFileChooser2 = new JFileChooser();
                int returnValue2 = jFileChooser2.showOpenDialog(null);
                if (returnValue2 == JFileChooser.APPROVE_OPTION) {
                    File file = jFileChooser2.getSelectedFile();
                    decodingPath = file.toString();
                    jtf2.setText(decodingPath);
                    System.out.println("decodingPath:" + decodingPath);
                }
                break;
            case "解压缩":
                new HFMTree().decompress(outputPath, decodingPath);
                jf.dispose();
                break;
        }
    }
}
