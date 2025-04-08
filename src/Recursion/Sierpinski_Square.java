package Recursion;

import javax.swing.*;
import java.awt.*;

public class Sierpinski_Square extends JPanel{
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setSize(900,750);
        jf.setTitle("谢尔宾斯基正方形");
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(new Sierpinski_Square());
        jf.setVisible(true);
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        int x1 = 0, y1 = 0;
        int length = 700;
        int high = 700;
        g.drawRect(x1,y1,length,high);
        int storey = 8;
        Cantor(g,x1,y1,length,high,storey);
     }
    public void Cantor(Graphics g, int x1, int y1, int length, int high, int storey){
        if (storey != 0){
            int _length = length / 3;
            int _high = high / 3;
            int _x = x1 + _length;
            int _y = y1 + _high;
            g.drawRect(_x, _y, _length, _high);
            length /= 3;
            high /= 3;

            Cantor(g,x1,y1,length,high,storey-1);
            Cantor(g,x1+length,y1,length,high,storey-1);
            Cantor(g,x1+length*2,y1,length,high,storey-1);

            Cantor(g,x1,y1+high,length,high,storey-1);
            Cantor(g,x1+length*2,y1+high,length,high,storey-1);

            Cantor(g,x1,y1+high*2,length,high,storey-1);
            Cantor(g,x1+length,y1+high*2,length,high,storey-1);
            Cantor(g,x1+length*2,y1+high*2,length,high,storey-1);
        }
    }
}