package Thread.frb230806v01;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BallThread extends Thread {
    private Graphics g;
    private ArrayList<Shape> arrayList;

    public BallThread(Graphics g, ArrayList<Shape> arrayList) {
        this.g = g;
        this.arrayList = arrayList;
    }

    public void run() {
        while (true) {
            BufferedImage bufferedImage = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
            Graphics gph = bufferedImage.getGraphics();
            for (Shape shape : this.arrayList) {
                shape.x++;
                gph.fillOval(shape.x, shape.y, 30, 30);
            }
            g.drawImage(bufferedImage, 100,100,null);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
