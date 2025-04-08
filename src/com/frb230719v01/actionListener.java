package com.frb230719v01;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class actionListener implements ActionListener {
    Graphics g;
    public actionListener(){
        this.g = g=null;
    }
    public void actionPerformed(ActionEvent e){
        System.out.println("ok");
        int[][] ia = toArry();
        int outputSpeed = 5;
        for(int i=80;i<ia.length;i+=outputSpeed){
            for(int j=80;j<ia[0].length;j+=outputSpeed){
                int v=ia[i][j];
                Color color=new Color(v);
                g.setColor(color);
                if(color.getRed()>155){
                    g.setColor(Color.WHITE);
                    g.fillRect(i/8, j/8, 1, 1);
                }else{
                    g.setColor(Color.BLACK);
                    g.fillRect(i/8, j/8, 1, 1);
                }

            }
        }
    }
    public int[][] toArry(){
        //取得照片
        File f = new File("ruibo.jpg");
        //提取照片信息
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(f);
        }catch (Exception ef){
            ef.printStackTrace();
        }

        int w = bi.getWidth();
        int h = bi.getHeight();
        int[][] data = new int[w][h];
        for(int i=0; i<w; i++){
            for(int j=0; j<h; j++){
                int v = bi.getRGB(i,j);
                data[i][j] = v;
            }
        }
        System.out.println("read Image file successfully----w:"+w+" h:"+h);
        return data;
    }
}