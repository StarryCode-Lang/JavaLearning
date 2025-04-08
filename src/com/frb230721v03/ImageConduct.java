package com.frb230721v03;

//import org.apache.commons.lang3.text.CompositeFormat;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class ImageConduct {
    //图片初始绘制坐标参数
    final int X = 0;
    final int Y = 0;
    int OutputSpeed = 5;  //清晰度，输出速度

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

    private BufferedImage offScreenBuffer;
    private Graphics offScreenGraphics;

    public ImageConduct() {
        // Initialize off-screen buffer
        offScreenBuffer = new BufferedImage(10000, 10000, BufferedImage.TYPE_INT_ARGB);
        offScreenGraphics = offScreenBuffer.getGraphics();
    }

    // Add a new method to draw the off-screen buffer onto the main screen
    public void drawOffScreenBuffer(Graphics g) {
        g.drawImage(offScreenBuffer, 0, 0, null);
    }

    //原图
    public void OriginalImage(int[][] ori) {
        // Use off-screen graphics instead of 'g'
        for (int i = 0; i < ori.length; i += OutputSpeed) {
            for (int j = 0; j < ori[i].length; j += OutputSpeed) {
                int rgb = ori[i][j];
                Color color = new Color(rgb);
                offScreenGraphics.setColor(color);
                offScreenGraphics.fillRect((X + i) / 8, (Y + j) / 8, 1, 1);
            }
        }
    }

    //二值化
    public void BlackAndWhiteImage(int[][] bwi) {
        for (int i = 0; i < bwi.length; i += OutputSpeed) {
            for (int j = 0; j < bwi[0].length; j += OutputSpeed) {
                int v = bwi[i][j];
                Color color = new Color(v);
                //g.setColor(color);
                if (color.getRed() > 155) {
                    offScreenGraphics.setColor(Color.WHITE);
                    offScreenGraphics.fillRect(i / 8, j / 8, 1, 1);
                } else {
                    offScreenGraphics.setColor(Color.BLACK);
                    offScreenGraphics.fillRect(i / 8, j / 8, 1, 1);
                }
            }
        }
    }

    //马赛克
    public void Mosaic(int[][] ms) {
        for (int i = 0; i < ms.length; i += OutputSpeed * 6) {
            for (int j = 0; j < ms[0].length; j += OutputSpeed * 6) {
                int v = ms[i][j];
                Color color = new Color(v);
                offScreenGraphics.setColor(color);
                offScreenGraphics.fillRect((X + i) / 8, (Y + j) / 8, 10, 10);
            }
        }
    }

    //反色
    public void OppositeImage(int[][] oi) {
        for (int i = 0; i < oi.length; i += OutputSpeed) {
            for (int j = 0; j < oi[0].length; j += OutputSpeed) {
                int v = oi[i][j];
                Color color = new Color(v);
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                Color ocolor = new Color(255 - red, 255 - green, 255 - blue);
                offScreenGraphics.setColor(ocolor);
                offScreenGraphics.fillRect((X + i) / 8, (Y + j) / 8, 1, 1);
            }
        }
    }

    //脸部提亮
    public void FaceBrightening(int[][] fb) {
        for (int i = 0; i < fb.length; i += OutputSpeed) {
            for (int j = 0; j < fb[0].length; j += OutputSpeed) {
                int v = fb[i][j];
                Color color = new Color(v);
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int Bred = red > 220 ? 250 : red + 30;
                int Bgreen = green > 220 ? 250 : green + 30;
                int Bblue = blue > 220 ? 250 : blue + 30;
                int gray = (red + green + blue) / 3;
                Color Bcolor = new Color(Bred, Bgreen, Bblue);
                if (gray < 60) {
                    offScreenGraphics.setColor(color);
                } else {
                    offScreenGraphics.setColor(Bcolor);
                }
                offScreenGraphics.fillRect((X + i) / 8, (Y + j) / 8, 1, 1);
            }
        }
    }

    //普通黑白
    public void GrayscaleImage(int[][] gi) {
        for (int i = 0; i < gi.length; i += OutputSpeed) {
            for (int j = 0; j < gi[0].length; j += OutputSpeed) {
                int v = gi[i][j];
                Color color = new Color(v);
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int gray = (int) (red * 0.41 + green * 0.36 + blue * 0.23);
                Color Gcolor = new Color(gray, gray, gray);
                offScreenGraphics.setColor(Gcolor);
                offScreenGraphics.fillRect((X + i) / 8, (Y + j) / 8, 1, 1);
            }
        }
    }

    //油画
    public void PaintingImage(int[][] pi) {
        for (int i = 0; i < pi.length; i += OutputSpeed) {
            for (int j = 0; j < pi[0].length; j += OutputSpeed) {
                int v = pi[i][j];
                Color color = new Color(v);
                Random random = new Random();
                int r = random.nextInt(15) + 5;
                offScreenGraphics.setColor(color);
                offScreenGraphics.fillOval((X + i) / 8, (Y + j) / 8, r, r);
            }
        }
    }

    //融合特效
    public void FusionImage(String Path1, String Path2) {
        int[][] imga = toArry(Path1);
        int[][] imgb = toArry(Path2);
        int w = Math.min(imga.length, imgb.length);
        int h = Math.min(imga[0].length, imgb[0].length);
        for (int i = 0; i < w; i += (OutputSpeed / 2)) {
            for (int j = 0; j < h; j += (OutputSpeed / 2)) {
                Color ca = new Color(imga[i][j]);
                Color cb = new Color(imgb[i][j]);
                int red = (int) (ca.getRed() * 0.4 + cb.getRed() * 0.6);
                int green = (int) (ca.getGreen() * 0.4 + cb.getGreen() * 0.6);
                int blue = (int) (ca.getBlue() * 0.4 + cb.getBlue() * 0.6);
                Color cn = new Color(red, green, blue);
                offScreenGraphics.setColor(cn);
                offScreenGraphics.fillRect((X + i) / 4, (Y + j) / 4, 1, 1);
            }
        }
    }

    //轮廓提取
    public void ContourExtraction(int[][] ce) {
        for (int i = 0; i < ce.length; i += OutputSpeed) {
            for (int j = 0; j < ce[0].length; j += OutputSpeed) {
                int v = ce[i][j];
                Color color = new Color(v);
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int gray = (int) (red * 0.41 + green * 0.36 + blue * 0.23);
                //取出右下斜方向的像素值 灰度化 作比较
                int nv = ce[i + 2][j + 2];
                Color ncolor = new Color(nv);
                int nred = ncolor.getRed();
                int ngreen = ncolor.getGreen();
                int nblue = ncolor.getBlue();
                int ngray = (int) (nred * 0.41 + ngreen * 0.36 + nblue * 0.23);
                if (Math.abs(ngray - gray) > 15) {
                    offScreenGraphics.setColor(Color.BLACK);
                } else {
                    offScreenGraphics.setColor(Color.WHITE);
                }
                offScreenGraphics.fillRect((X + i) / 8, (Y + j) / 8, 1, 1);
            }
        }
    }

    //清空
    public void Empty(Graphics g) {
        offScreenGraphics.setColor(Color.WHITE);
        offScreenGraphics.fillRect(0, 0, 10000, 10000);
    }

    //门格分形
    public void MengePaint(Graphics g) {

    }

    public void drawButtonLevel(Graphics g, Point p0, int d, int dx, int dy) {

    }
}