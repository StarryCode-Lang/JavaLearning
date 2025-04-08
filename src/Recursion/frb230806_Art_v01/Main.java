package Recursion.frb230806_Art_v01;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private JPanel buttonPanel;

    public void initUI() {
        this.setTitle("Art Drawing");
        this.setSize(1250, 800);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 创建按钮面板
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        BuAction acl = new BuAction(); // 在创建BuAction对象之前获取Graphics对象
        this.addButton(acl); // 将BuAction对象传递给addButton方法

        //创建绘图面板
        JPanel drawingPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };

        this.setLayout(new BorderLayout());
        this.add(drawingPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.NORTH);
        this.setVisible(true);

        BuAction.g = drawingPanel.getGraphics();
    }

    public void addButton(BuAction acl) {
        //创建一个数组，数组中的每一个元素都为一个按钮对象，数组长度暂且定为10
        String[] btnstrs = {"长河落日", "夜之精灵", "三尺红绫"};
        for (String btnstr : btnstrs) {
            CustomButton button = new CustomButton(btnstr);
            button.setFont(new Font("宋体", Font.PLAIN, 20)); // 设置按钮字仓为宋体
            this.add(button);
            button.addActionListener(acl);
            buttonPanel.add(button);
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.initUI();
        main.setVisible(true);
    }
}


