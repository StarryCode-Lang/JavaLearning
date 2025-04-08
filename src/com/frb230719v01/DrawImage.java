package com.frb230719v01;

import javax.swing.*;
import java.awt.*;

public class DrawImage extends JFrame {
    actionListener ba = new actionListener();

    public void comUI() {
        this.setTitle("美颜相机-v0.1");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.BLACK);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        addButton(buttonPanel);
        contentPane.add(buttonPanel, BorderLayout.NORTH);

        JPanel imagePanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // 在此处绘制图片
            }
        };
        imagePanel.setPreferredSize(new Dimension(800, 500));
        imagePanel.setBackground(Color.BLACK);
        contentPane.add(imagePanel, BorderLayout.CENTER);

        this.setContentPane(contentPane);
        this.setVisible(true);

        ba.g = imagePanel.getGraphics(); // 将图形上下文g指向imagePanel的Graphics对象
    }

    public void addButton(JPanel buttonPanel) {
        String[] btnstrs = {"原图", "黑白", "马赛克", "反片", "脸部提亮", "灰度", "二值化", "美白", "油画", "轮廓提取", "撤回"};
        for (int i = 0; i < btnstrs.length; i++) {
            JButton bu = new JButton(btnstrs[i]);
            buttonPanel.add(bu);
            bu.addActionListener(ba);
        }
    }

    public static void main(String args[]) {
            DrawImage di = new DrawImage();
            di.comUI();
    }
}
