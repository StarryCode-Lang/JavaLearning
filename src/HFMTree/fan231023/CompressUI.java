package HFMTree.fan231023;

import HFMTree.fan231012.HFMTree;

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
        btn3.addActionListener(listener);
        btn4.addActionListener(listener);

        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new CompressUI().initUI();
    }
}

