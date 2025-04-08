package com.frb230723v05;

import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class actionListener implements ActionListener {

    //图像地址
    String ImagePath = "ruibo.jpg";
    String ImagePath1 = "D:\\Desktop\\IMG_20230716_190620.jpg";
    String ImagePath2 = "D:\\Desktop\\Screenshot_20230721_192406.jpg";
    static Graphics g;
    //1图片转二维数组; 2图像处理滤镜绘制方法
    int[][] ict;
    private volatile boolean flag = true;//用于开启/关闭视频画面
    private String msg = String.valueOf(0);
    private final byte[] msgByte = msg.getBytes();
    //图片初始绘制坐标参数
    final int X = 0;
    final int Y = 0;
    int OutputSpeed = 5;  //清晰度，输出速度
    private final BufferedImage offScreenBuffer = new BufferedImage(10000, 10000, BufferedImage.TYPE_INT_ARGB);
    private final Graphics offScreenGraphics = offScreenBuffer.getGraphics();

    // Add a new method to draw the off-screen buffer onto the main screen
    public void drawOffScreenBuffer(Graphics g) {
        g.drawImage(offScreenBuffer, 0, 0, null);
    }

    //构造方法调用图像
    public actionListener() {
        try {
            ict = toArry(ImagePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //重写，图像绘制方式选择
    public void actionPerformed(ActionEvent e) {
        String btnstr = e.getActionCommand();
        System.out.println("点击了" + btnstr);
        switch (btnstr) {
            case "二值化" -> BlackAndWhiteImage(ict);
            case "原图" -> OriginalImage(ict);
            case "马赛克" -> Mosaic(ict);
            case "反色" -> OppositeImage(ict);
            case "脸部提亮" -> FaceBrightening(ict);
            case "普通黑白" -> GrayscaleImage(ict);
            case "油画" -> PaintingImage(ict);
            case "融合特效" -> FusionImage(ImagePath1, ImagePath2);
            case "轮廓提取" -> ContourExtraction(ict);
            case "图像加密" -> {
                System.out.println("请输入要加密的字符串:");
                Scanner scanner = new Scanner(System.in);
                msg = scanner.nextLine();
                System.out.println("您输入的字符串是：" + msg);
                Decode(ImagePath);
            }
            case "开启视频" -> Video();
            case "关闭视频" -> {
                CloseVideo();
            }
            case "清空" -> {
                Empty();
                System.out.println("已清空");
            }
        }
        drawOffScreenBuffer(g);
    }

    //显示视频画面
    public void Video() {
        Webcam webcam = Webcam.getDefault();
        webcam.open();
        do {
            BufferedImage bi = webcam.getImage();
            g.drawImage(bi, 100, 100, null);
        } while (flag);
    }

    //关闭视频画面
    public void CloseVideo() {
        flag = false;

    }

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

        int w = 0, h = 0;
        if (bi != null) {
            w = bi.getWidth();
            h = bi.getHeight();
        }
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
                if (((i + 2) < ce.length) && ((j + 2) < ce[0].length)) {
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
                }
                offScreenGraphics.fillRect((X + i) / 8, (Y + j) / 8, 1, 1);
            }
        }
    }

    //清空
    public void Empty() {
        offScreenGraphics.setColor(Color.WHITE);
        offScreenGraphics.fillRect(0, 0, 10000, 10000);
        flag = true;
    }

    //二维数组转换为三维数组
    public int[][][] dim2todim3(int[][] data2) {
        int w = data2.length;
        int h = data2[0].length;
        int[][][] data3 = new int[w][h][4];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int val = data2[i][j];
                int[] b = new int[4];
                b[0] = (val & 0xff);
                b[1] = ((val >> 8) & 0xff);
                b[2] = ((val >> 16) & 0xff);
                b[3] = ((val >> 24) & 0xff);
                data3[i][j] = b;
            }
        }
        return data3;
    }

    //图像解密
    public void Decode(String ImagePath) {
        int[][][] data = dim2todim3(toArry(ImagePath));
        BufferedImage bi = new BufferedImage(data.length, data[0].length, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < data.length; i += 2) {
            for (int j = 0; j < data[0].length; j += 2) {
                int[] b = data[i][j];
                if (j < msgByte.length) {
                    b[3] = msgByte[j];
                    System.out.println("写入图片了一个字节:" + b[3]);
                }

                int result = b[3] << 24 | (b[2] << 16) | (b[1] << 8) | (b[0]);
                //b[3]是空余的
                Color color = new Color(b[2], b[1], b[0]);
                offScreenGraphics.setColor(color);
                offScreenGraphics.fillOval(i / 8, j / 8, 1, 1);
                bi.setRGB(i, j, result);
            }
        }
        try {
            ImageIO.write(bi, "jpg", new File("newP.jpg"));
            System.out.println("图片输出成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}