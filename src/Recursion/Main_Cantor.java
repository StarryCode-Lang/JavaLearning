package Recursion;

import javax.swing.*;
import java.awt.*;

public class Main_Cantor extends JPanel{
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setSize(900,750);
        jf.setTitle("康托尔三分集");
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(new Main_Cantor());
        jf.setVisible(true);
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        int x1 = 250, y1 = 45, x2 = 650, y2 = 45;
        int storey = 6;
        Cantor(g, x1, y1, x2, y2, storey);
    }
    public void Cantor(Graphics g, int x1, int y1, int x2, int ignoredY2, int storey){
        if (storey != 0){
            int y = y1 + 50;
            int segmentWidth = (x2 - x1) / 3;
            int xLeft = x1 + segmentWidth;
            int xRight = x2 - segmentWidth;
            g.drawLine(x1, y, x2, y); // 绘制当前层的线段
            Cantor(g, x1, y + 50, xLeft, y + 50, storey - 1);
            Cantor(g, xRight, y + 50, x2, y + 50, storey - 1);
        }
    }
}