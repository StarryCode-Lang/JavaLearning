package com.frb230722v04;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class actionListener implements ActionListener {


    //图像地址
    String ImagePath = "ruibo.jpg";
    String ImagePath1 = "D:\\Desktop\\IMG_20230716_190620.jpg";
    String ImagePath2 = "D:\\Desktop\\Screenshot_20230721_192406.jpg";
    public static Graphics g;
    //1图片转二维数组; 2图像处理滤镜绘制方法
    ImageConduct ic = new ImageConduct();
    int[][] ict;

    //构造方法调用图像
    public actionListener(JTextField jtf) {
        try {
            ict = ic.toArry(ImagePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //重写，图像绘制方式选择
    public void actionPerformed(ActionEvent e) {
        String btnstr = e.getActionCommand();
        System.out.println("点击了" + btnstr);
        if (btnstr.equals("二值化")) {
            ic.BlackAndWhiteImage(ict);
        } else if (btnstr.equals("原图")) {
            ic.OriginalImage(ict);    //////////////***********
        } else if (btnstr.equals("门格海绵分形")) {
            //门格海绵分形绘制
            ic.MengePaint(g);
        } else if (btnstr.equals("马赛克")) {
            ic.Mosaic(ict);
        } else if (btnstr.equals("反色")) {
            ic.OppositeImage(ict);
        } else if (btnstr.equals("脸部提亮")) {
            ic.FaceBrightening(ict);
        } else if (btnstr.equals("普通黑白")) {
            ic.GrayscaleImage(ict);
        } else if (btnstr.equals("油画")) {
            ic.PaintingImage(ict);
        } else if (btnstr.equals("轮廓提取")) {
            ic.ContourExtraction(ict);
        } else if (btnstr.equals("融合特效")) {
            ic.FusionImage(ImagePath1, ImagePath2);
        } else if (btnstr.equals("图象解密")) {
            ic.Decode(ict);
        } else if (btnstr.equals("清空")) {
            ic.Empty(g);
            System.out.println("已清空");
        }
        ic.drawOffScreenBuffer(g);
    }
}