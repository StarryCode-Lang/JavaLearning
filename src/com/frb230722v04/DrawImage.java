package com.frb230722v04;

import javax.swing.*;
import java.awt.*;

import static com.frb230722v04.actionListener.g;

public class DrawImage extends JFrame {
    actionListener acl = new actionListener(new JTextField());

    public void createUI() {
        this.setTitle("公元2023年 美颜相机-v0.1 作者:范芮搏");
        this.setSize(1200, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.BLACK);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 6, 2, 2)); // 使用GridLayout以网格形式排列按钮
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

        JLabel jLabel = new JLabel("请输入要加密的信息:");
        JTextField jTextField = new JTextField(40);
        buttonPanel.add(jLabel);
        buttonPanel.add(jTextField);


        this.setContentPane(contentPane);
        this.setVisible(true);

        g = imagePanel.getGraphics(); // 将图形上下文g指向imagePanel的Graphics对象
    }

    public void addButton(JPanel buttonPanel) {
        String[] btnstrs = {"原图", "二值化", "马赛克", "反色", "脸部提亮", "普通黑白", "油画", "轮廓提取", "融合特效", "图象解密", "门格海绵分形", "清空"};
        for (int i = 0; i < btnstrs.length; i++) {
            CustomButton button = (CustomButton) new CustomButton(btnstrs[i]);
            buttonPanel.add(button);
            button.addActionListener(acl);
        }
    }


    public static void main(String[] args) {
        DrawImage drawImage = new DrawImage();
        drawImage.createUI();
    }
}

class CustomButton extends JButton {
    private static final int BORDER_RADIUS = 15;

    public CustomButton(String text) {
        super(text);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);

        // Set the font and text color
        setFont(new Font("宋体", Font.PLAIN, 20));
        setForeground(Color.WHITE);

        // Set the button gradient background
        setBackground(new Color(40, 130, 220));
        setPreferredSize(new Dimension(60, 40));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();

        // Create the gradient for the background
        GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(40, 130, 220), 0, height, new Color(30, 100, 180));
        g2d.setPaint(gradientPaint);

        // Create a rounded rectangle for the button
        g2d.fillRoundRect(0, 0, width, height, BORDER_RADIUS, BORDER_RADIUS);
        g2d.dispose();

        super.paintComponent(g);
    }
}
