package com.frb230720v02;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageConduct {
    //图片初始绘制坐标参数
    final int X = 0;
    final int Y = 0;

    //储存图像为二维数组
    public int[][] toArry(String path) {
        //取得照片
        File f = new File(path);
        //提取照片信息
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(f);
        } catch (Exception ef) {
            ef.printStackTrace();
        }

        int w = bi.getWidth();
        int h = bi.getHeight();
        int[][] data = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int v = bi.getRGB(i, j);
                data[i][j] = v;
            }
        }
        System.out.println("read Image file successfully----w:" + w + " h:" + h);
        return data;
    }
    //黑白
    public void Black_And_White_Image(int[][] bwi, Graphics g) {
        int outputSpeed = 5;
        for (int i = 80; i < bwi.length; i += outputSpeed) {
            for (int j = 80; j < bwi[0].length; j += outputSpeed) {
                int v = bwi[i][j];
                Color color = new Color(v);
                //g.setColor(color);
                if (color.getRed() > 155) {
                    g.setColor(Color.WHITE);
                    g.fillRect(i / 8, j / 8, 1, 1);
                } else {
                    g.setColor(Color.BLACK);
                    g.fillRect(i / 8, j / 8, 1, 1);
                }
            }
        }
    }
    //门格分形
    public void MengePaint(Graphics g){

    }
    //public void drawButtonLevel
}