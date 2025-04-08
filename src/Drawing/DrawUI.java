package Drawing;

import javax.swing.*;
import java.awt.*;

public class DrawUI {
    public void showUI() {
        JFrame jf = new JFrame();
        jf.setSize(900, 750);
        jf.setTitle("画图工具");
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        //设置窗体背景颜色
        jf.getContentPane().setBackground(Color.WHITE);
        //流式布局管理器
        jf.setLayout(new FlowLayout());

        //按钮
        DrawMouse mouse = new DrawMouse(null);
        String [] bottons = {"线段","等腰三角形","任意三角形","矩形","实心矩形","圆或椭圆","多边形","橡皮擦","撤回"};
        for (String button : bottons) {
            JButton buttons = new JButton(button);
            jf.add(buttons);
            buttons.addActionListener(mouse);
        }
        int numButtonc = 5;
        JButton[] buttoncs = new JButton[numButtonc];
        String [] botCol = {"RED","BLUE","YELLOW","GREEN"};
        for(int i = 0; i< botCol.length; i++){
            JButton buttonc = new JButton(botCol[i]);
            buttonc.setBackground(Color.WHITE);
            jf.add(buttonc);
            buttonc.addActionListener(mouse);
        }
        jf.setVisible(true);
        //画笔
        Graphics g=jf.getGraphics();
        mouse.gr=g;

        //鼠标拖动时间动作监听器
        jf.addMouseMotionListener(mouse);
        jf.addMouseListener(mouse);


    }
    public static void main (String[] args) {
        DrawUI ui = new DrawUI();
        ui.showUI();
    }
}
