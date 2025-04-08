package Drawing;

import java.awt.*;
public class Shape {
    public int x1, y1, x2, y2, x3, y3, latestX, latestY, Xp, Yp;
    public String name;
    public Shape(int x1,int y1,int x2,int y2, int x3, int y3, String name){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.name = name;
    }
    public Shape(int latestX, int latestY, int Xp, int Yp, String name) {
        this.latestX = latestX;
        this.latestY = latestY;
        this.Xp = Xp;
        this.Yp = Yp;
    }

    public void drawShape(Graphics g){
        int w = Math.abs(x1- x2);
        int h = Math.abs(y1- y2);
        switch (name){
            case "线段":
                g.drawLine(x1,y1,x2,y2);
                break;
            case "等腰三角形":
                g.drawLine(x1, Math.max(y1, y2), x2, Math.max(y1, y2));
                g.drawLine((x1 + x2) / 2, Math.min(y1, y2), x1, Math.max(y1, y2));
                g.drawLine((x1 + x2) / 2, Math.min(y1, y2), x2, Math.max(y1, y2));
                hideLine(g);
                break;
            case "任意三角形":
                g.drawLine(x1, y1, x2, y2);
                g.drawLine(x1, y1, x3, y3);
                g.drawLine(x2, y2, x3, y3);
                break;
            case "矩形":
                g.drawRect(Math.min(x1, x2), Math.min(y1, y2), w, h);
                hideLine(g);
                break;
            case "实心矩形":
            case "圆或椭圆":
                g.drawOval(Math.min(x1, x2), Math.min(y1, y2), w, h);
                hideLine(g);
                break;
            case "多边形":
            case "橡皮擦":
        }
    }

    private void hideLine(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawLine(x1, y1, x2, y2);
        g.setColor(Color.BLACK);
    }
}
