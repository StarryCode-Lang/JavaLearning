package com.frb230720v02;

import javax.swing.*;
import java.awt.*;

public class DrawImage extends JFrame {
    actionListener actionListener = new actionListener();

    public void createUI() {
        this.setTitle("美颜相机-v0.1");
        this.setSize(1200, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.BLACK);

        JPanel buttonPanel = new JPanel(new GridLayout(0, 4)); // 使用GridLayout以网格形式排列按钮
        addButton(buttonPanel);
        JScrollPane scrollPane = new JScrollPane(buttonPanel); // 添加滚动面板
        contentPane.add(scrollPane, BorderLayout.NORTH);

        JPanel imagePanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // 在此处绘制图片
            }
        };
        imagePanel.setPreferredSize(new Dimension(800, 500));
        imagePanel.setBackground(Color.WHITE);
        contentPane.add(imagePanel, BorderLayout.CENTER);

        this.setContentPane(contentPane);
        this.setVisible(true);

        actionListener.g = imagePanel.getGraphics(); // 将图形上下文g指向imagePanel的Graphics对象
    }

    public void addButton(JPanel buttonPanel) {
        String[] btnstrs = {"黑白", "马赛克", "反色", "脸部提亮", "灰度", "二值化", "美白", "油画", "融合特效", "轮廓提取", "图像缩小", "撤回", "门格海绵分形"};
        for (int i = 0; i < btnstrs.length; i++) {
            JButton button = new JButton(btnstrs[i]);
            buttonPanel.add(button);
            button.addActionListener(actionListener);
        }
    }

    public static void main(String[] args) {
        DrawImage drawImage = new DrawImage();
        drawImage.createUI();
    }
}