package Thread.frb230806v01;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class BuAction implements ActionListener {
    private Graphics g=null;
    private ArrayList<Shape> arrayList;

    public BuAction(Graphics g, ArrayList<Shape> arrayList) {
        this.g = g;
        this.arrayList = arrayList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Random random = new Random();
        int x = random.nextInt(500);
        int y = random.nextInt(500);
        Shape shape = new Shape();
        shape.x = x;
        shape.y = y;
        //存入队列
        this.arrayList.add(shape);

    }
}
