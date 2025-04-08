package Thread.frb230806v01;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main extends JFrame {
    //创建队列对象
    private final ArrayList<Shape> arrayList = new ArrayList<>();
    public void initUI(){
        this.setTitle("Bouncing Balls");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        FlowLayout fl=new FlowLayout();
        this.setLayout(fl);
        Button bu = new Button("Start");
        this.add(bu);
        this.setVisible(true);
        Graphics g = this.getGraphics();
        BuAction buAction = new BuAction(g,  arrayList);
        bu.addActionListener(buAction);
        BallThread ballThread = new BallThread(g, arrayList);
        ballThread.start();
    }
    public static void main(String[] args) {
        Main main = new Main();
        main.initUI();
    }
}
