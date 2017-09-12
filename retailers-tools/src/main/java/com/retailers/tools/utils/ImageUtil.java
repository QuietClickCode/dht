package com.retailers.tools.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 图片压缩工具.
 */
public class ImageUtil {


    public static void resizePNG(String fromFile, String toFile, int outputWidth, int outputHeight, boolean proportion) {
        try {
            File f2 = new File(fromFile);

            BufferedImage bi2 = ImageIO.read(f2);
            int newWidth;
            int newHeight;
            // 判断是否是等比缩放
            if (proportion == true) {
                // 为等比缩放计算输出的图片宽度及高度
                double rate1 = ((double) bi2.getWidth(null)) / (double) outputWidth + 0.1;
                double rate2 = ((double) bi2.getHeight(null)) / (double) outputHeight + 0.1;
                // 根据缩放比率大的进行缩放控制
                double rate = rate1 < rate2 ? rate1 : rate2;
                newWidth = (int) (((double) bi2.getWidth(null)) / rate);
                newHeight = (int) (((double) bi2.getHeight(null)) / rate);
            } else {
                newWidth = outputWidth; // 输出的图片宽度
                newHeight = outputHeight; // 输出的图片高度
            }
            BufferedImage to = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

            Graphics2D g2d = to.createGraphics();

            to = g2d.getDeviceConfiguration().createCompatibleImage(newWidth, newHeight, Transparency.TRANSLUCENT);

            g2d.dispose();
            g2d = to.createGraphics();

            Image from = bi2.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
            g2d.drawImage(from, 0, 0, null);
            g2d.dispose();
            ImageIO.write(to, "png", new File(toFile));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void resizeJPG(String imgsrc, String imgdist, int widthdist, int heightdist) {
        try {
            File srcfile = new File(imgsrc);
            if (!srcfile.exists()) {
                return;
            }
            Image src = ImageIO.read(srcfile);

            BufferedImage tag= new BufferedImage(widthdist, heightdist, BufferedImage.TYPE_INT_RGB);

//            tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist,  Image.SCALE_SMOOTH), 0, 0,  null);
            tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist,  Image.SCALE_AREA_AVERAGING), 0, 0,  null);

//            FileOutputStream out = new FileOutputStream(imgdist);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(tag);
//            out.close();
            ImageIO.write(tag, "jpg", new File(imgdist));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static BufferedImage getBufferedImage(String imgPath) throws IOException {

        return ImageIO.read(new FileInputStream(new File(imgPath)));
    }
}
