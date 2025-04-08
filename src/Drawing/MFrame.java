package Drawing;

import javax.swing.*;
import java.awt.*;
//自定义窗体类
public class MFrame extends JFrame {
    private Shape[] shapeArr;

    //定义set，初始化属性
    public void setShapeArr(Shape[] shapeArr){
        this.shapeArr = shapeArr;
    }

    //重写paint方法
    public void paint(Graphics g){
        //1.保留绘制组件的功能：调用父类中的paint方法
        super.paint(g);

        System.out.println("paint...");

        //2.绘制保存的图形
        for (Shape shape : shapeArr) {
            if (shape != null) {
                shape.drawShape(g);
            }
        }

    }
}
