package Drawing;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DrawMouse implements MouseListener, ActionListener, MouseMotionListener {
    Graphics gr;
    private int x1, y1, x2, y2, x3, y3;
    private int latestX, latestY, Xp, Yp;
    private String name;
    private int flag = 1;  //标记
    //保存创建的图形对象
    private final Shape[] shapeArr = new Shape[100];
    //操作数组的下标
    private int index = 0;

    public DrawMouse(Graphics gr){
        this.gr = gr;
    }
    public void actionPerformed(ActionEvent e){
        name = e.getActionCommand();
        System.out.println("name ="+name);
        gr.setColor(Color.BLACK);
    }
    public void hideLine (){
        gr.setColor(Color.WHITE);
        gr.drawLine(x1, y1, x2, y2);
        gr.setColor(Color.BLACK);
    }
    //点击
    public void mouseClicked(MouseEvent e){
        if("任意三角形".equals(name)){
            x3 = e.getX();
            y3 = e.getY();
            gr.drawLine(x1, y1, x3, y3);
            gr.drawLine(x2, y2, x3, y3);
            System.out.println("x3 ="+x3+"  y3 ="+y3);
            flag = 1;
            Shape shape_triangle = new Shape(x1, y1, x2, y2, x3, y3,"任意三角形");
            shapeArr[index++] = shape_triangle;
        }
        if("多边形".equals(name) && flag ==0){
            Xp = e.getX();
            Yp = e.getY();
            System.out.println("Xp ="+Xp+"  Yp ="+Yp);
            System.out.println("latestX ="+latestX+"  latestY ="+latestY);
            gr.drawLine(latestX, latestY, Xp, Yp);
            latestX = Xp;
            latestY = Yp;
            Shape shape_polygon = new Shape(latestX, latestY, Xp, Yp, "多边形");
            shapeArr[index++] = shape_polygon;
            //判断是否双击
            if(e.getClickCount() == 2){
                gr.drawLine(x1, y1, Xp, Yp);
                shapeArr[index++] = shape_polygon;
                flag =1;
            }
        }
    }
    //按下
    public void mousePressed(MouseEvent e){
        if(flag==1){
            x1 = e.getX();
            y1 = e.getY();
        }
        System.out.println("x1 ="+x1+"  y2 ="+y1);
    }
    //松开
    public void mouseReleased(MouseEvent e){
        if(flag == 1){
            x2 = e.getX();
            y2 = e.getY();
        }
        System.out.println("x2 = "+x2+"   y2 = "+y2);

        int w = Math.abs(x1- x2);
        int h = Math.abs(y1- y2);

        if("线段".equals(name)){
            gr.drawLine(x1, y1, x2, y2);
            Shape shape_line = new Shape(x1,y1,x2,y2,x3,y3,"线段");
            shapeArr[index++] = shape_line;
        }
        if("等腰三角形".equals(name)){
            gr.drawLine(x1, Math.max(y1, y2), x2, Math.max(y1, y2));
            gr.drawLine((x1 + x2) / 2, Math.min(y1, y2), x1, Math.max(y1, y2));
            gr.drawLine((x1 + x2) / 2, Math.min(y1, y2), x2, Math.max(y1, y2));
            hideLine();
            Shape shape__triangle = new Shape(x1,y1,x2,y2,x3,y3,"等腰三角形");
            shapeArr[index++] = shape__triangle;
        }
        if("任意三角形".equals(name)&&flag==1) {
            gr.drawLine(x1,y1,x2,y2);
            flag++;

        }
        if("多边形".equals(name) && flag==1) {
            gr.drawLine(x1,y1,x2,y2);

            //  更新初始点的坐标
            latestX = x2;
            latestY = y2;

            flag=0;
        }
        if("矩形".equals(name)){
            gr.drawRect(Math.min(x1, x2), Math.min(y1, y2), w, h);
            hideLine();
            Shape shape_rectangle = new Shape(x1,y1,x2,y2,x3,y3,"矩形");
            shapeArr[index++] = shape_rectangle;
        }
        if("圆或椭圆".equals(name)){
            gr.drawOval(Math.min(x1, x2), Math.min(y1, y2), w, h);
            hideLine();
            Shape shape_oval = new Shape(x1,y1,x2,y2,x3,y3,"圆或椭圆");
            shapeArr[index++] = shape_oval;
        }
        if("圆角矩形".equals(name)){
            gr.drawRoundRect(Math.min(x1, x2), Math.min(y1, y2), w, h, 30, 30);
            hideLine();
        }
    }

    public void mouseDragged(MouseEvent e) {
        gr.setColor(Color.WHITE);
        gr.drawLine(x1,y1,x2,y2);
        x2 = e.getX();
        y2 = e.getY();
        gr.setColor(Color.BLACK);
        gr.drawLine(x1,y1,x2,y2);
    }

    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseMoved(MouseEvent e) {}
}
