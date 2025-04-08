package com.frb230720v02;

//import com.cburch.logisim.std.io.extra.Switch;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class actionListener implements ActionListener {

    //图像地址
    String ImagePath = "ruibo.jpg";
    Graphics g;
    //1图片转二维数组; 2图像处理滤镜绘制方法
    ImageConduct ic = new ImageConduct();
    int[][] ict;

    //构造方法调用图像
    public actionListener() {
        try {
            ict = ic.toArry(ImagePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //绘制门格海绵分形
    private int w = 80, h = 60;
    private int dx = 40, dy = 30;  //立方体的深度和高度;
    private int nx5 = 0, ny5 = 0;
    private int nx4 = 0, ny4 = 0;

    //重写，图像绘制方式选择
    public void actionPerformed(ActionEvent e) {
        String btnstr = e.getActionCommand();
        System.out.println("点击了" + btnstr);
        if (btnstr.equals("黑白")) {
            ic.Black_And_White_Image(ict, g);
        } else if (btnstr.equals("门格海绵分形")) {
            //门格海绵分形绘制
            ic.MengePaint(g);
        } else if (btnstr.equals("马赛克")) {
        }
        /* else if (btnstr.equals("反片")) {
            ic.drawImage_03(ict, g);
        } else if (btnstr.equals("脸部提亮")) {
            ic.drawImage_04(ict, g);
        } else if (btnstr.equals("灰度")) {
            ic.drawImage_05(ict, g);
        } else if (btnstr.equals("二值化")) {
            ic.drawImage_06(ict, g);
        } else if (btnstr.equals("美白")) {
            ic.drawImage_07(ict, g);
        } else if (btnstr.equals("油画")) {
            ic.drawImage_08(ict, g);
        } else if (btnstr.equals("轮廓提取")) {
            System.out.println("btnstr=-" + btnstr);
            ic.drawImage_09(ict, g);
        } else if (btnstr.equals("撤回")) {
        }*/
    }
}