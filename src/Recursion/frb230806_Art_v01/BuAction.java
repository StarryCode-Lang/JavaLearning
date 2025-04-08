package Recursion.frb230806_Art_v01;

import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class BuAction implements ActionListener {

    static Graphics g;

    @Override
    public void actionPerformed(ActionEvent e) {
        String btnstr = e.getActionCommand();
        System.out.println("点击了" + btnstr);
        switch (btnstr) {
            case "长河落日" -> DrawChanghe();
            case "夜之精灵" -> {
                System.out.println("456");
                if (g != null) {
                    g.fillOval(900, 30, 30, 30);
                }

            }
            case "三尺红绫" -> {
                System.out.println("789");
            }
        }
    }

    public void DrawChanghe() {
        double a = -2.0;
        //double b = -2.0;
        double c = -1.2, d = 2;
        double x = 0f, y = 0f;
        //使用循环计算出 x,y 每次迭代的值
        for (double b = -2; b < 2; b += 0.01) {
            a = b;
            try {
                Thread.sleep(10);
            } catch (Exception ignored) {
            }
            BufferedImage bi = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
            Graphics g2 = bi.getGraphics();
            g2.setColor(Color.GREEN);

            for (int i = 0; i < 20000; i++) {
                //计算下一次的x,y
                double temx = Math.sin(a * y) - Math.cos(b * x);
                double temy = Math.sin(c * x) + Math.cos(d * y);

                //对x1,y1转型，放大，移动到屏幕坐标系：
                int x1 = (int) (temx * 130 + 400);
                int y1 = (int) (temy * 130 + 300);
                g2.fillOval(x1, y1, 2, 2);
                //
                x = temx;
                y = temy;
            }
            //生成一张图片之后,则画到界面上:
            g.drawImage(bi, 0, 0, null);

        }
    }
}
