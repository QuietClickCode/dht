package com.retailers.tools.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.net.URL;
import java.util.Hashtable;

/**
 * 图片处理公用工具
 * @author zhongp
 * @version 1.0.1
 * @data 2018/1/13
 */
public class ShareImageUtils {
    /**
     * 图片默认宽度
     */
    private static int DEFAULT_IMG_WIDTH=550;
    /**
     * 图片头高度
     */
    private static int IMG_HEAD_HEIGHT=120;
    /**
     * 商品区域图片高度
     */
    private static  int GOODS_IMG_HEIGHT=500;

    //字符集编码格式
    private static final String CHARSET = "UTF-8";
    /**
     * 二维码尺寸宽度
     */
    private static final int QRCODE_WIDTH = 120;
    /**
     * 二维码尺寸高度
     */
    private static final int QRCODE_HEIGHT = 130;
    /**
     * 分享默认背景较长片
     */
    private static final String DEFAULT_IMAGE="d:\\share_image.png";
    /**
     * 标题 字体大小
     */
    private static final int TITLE_FONT_SIZE=26;
    /**
     * 标题 字体颜色
     */
    private static final Color TITLE_FONT_COLOR=Color.BLACK;
    /**
     * 价格 字体大小
     */
    private static final int PRICE_FONT_SIZE=16;
    /**
     * 价格 字体颜色
     */
    private static final Color PRICE_FONT_COLOR=Color.RED;
    private static final int FONT_TOP_HEIGHT=20;

    public static void main(String[] args)throws Exception {
        String goodsImgUrl="http://dht.kuaiyis.com/attachment/goods/2018/01/02/0a017bf6583676949a70662f164bd25d_originalfile.jpg";
        generateShareImage("测试商品","￥12.36","www.baidu.com",goodsImgUrl,null);
    }
    /**
     * 生成分享图片
     * @param goodsNm 商品名称
     * @param goodsPrice 商品价格
     * @param url 分享地址
     * @param firstImg 商品首张图片
     */
    public static void generateShareImage(String goodsNm, String goodsPrice, String url, String firstImg, OutputStream out)throws Exception{
        BufferedImage textImg=addText(goodsNm,goodsPrice);
        //取得商品首张图片
        BufferedImage goodsImg=generateGoodsImg(firstImg);
        Thumbnails.of(textImg)
                .scale(1f)
                .watermark(new com.retailers.tools.image.Positions(new Point(0,120)), goodsImg, 1f)//商品图片
                .watermark(new com.retailers.tools.image.Positions(new Point(98,647)), generateQRcode(url), 1f)//分享二维码
                .outputFormat("png")
                .toOutputStream(out);
    }
    /**
     * 生成商品图片
     * @param imgUrl 图片地址
     * @return
     */
    private static BufferedImage generateGoodsImg(String imgUrl)throws Exception{
        BufferedImage rtnBufferImage=null;
        if(ObjectUtils.isNotEmpty(imgUrl)){
            //商品图片
            rtnBufferImage= Thumbnails.of(new URL(imgUrl)).
                    sourceRegion(net.coobird.thumbnailator.geometry.Positions.CENTER, DEFAULT_IMG_WIDTH, GOODS_IMG_HEIGHT).
                    size(DEFAULT_IMG_WIDTH, GOODS_IMG_HEIGHT).asBufferedImage();
        }
        return rtnBufferImage;
    }


    /**
     * 生成图片文字
     * @param pressText 水印文字， 如：中国证券网
     * @param fontName 字体名称，    如：宋体
     * @param fontStyle 字体样式，如：粗体和斜体(Font.BOLD|Font.ITALIC)
     * @param fontSize 字体大小，单位为像素
     * @param color 字体颜色
     * @param y 水印文字距离目标图片上侧的偏移量，如果y<0, 则在正中间
     * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
     */
    private static BufferedImage pressText(String pressText,String fontName,int fontStyle,int fontSize,Color color,int y,float alpha)throws Exception{
        BufferedImage bi = new BufferedImage(DEFAULT_IMG_WIDTH, IMG_HEAD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.setFont(new Font(fontName, fontStyle, fontSize));
        g.setColor(color);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
        //取得文字宽度
        Font f = new Font(fontName, fontStyle, fontSize);
        FontMetrics fm = new JLabel().getFontMetrics(f);
        //高度
        int width_wi = fm.stringWidth(pressText);
        int height_wi = fontSize;
        int x = (DEFAULT_IMG_WIDTH/2-width_wi/2);
        g.drawString(pressText, x, y+height_wi);//水印文件
        g.dispose();
        return bi;
    }

    private static BufferedImage addText(String goodsNm,String price)throws Exception{
        File file = new File(DEFAULT_IMAGE);
        Image image = ImageIO.read(file);
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.drawImage(image,0,0, width, height, null);
        //取得文字宽度
        Font titleFont = new Font(goodsNm, Font.BOLD, TITLE_FONT_SIZE);
        g.setFont(titleFont);
        g.setColor(TITLE_FONT_COLOR);
        FontMetrics fmTitle = new JLabel().getFontMetrics(titleFont);
        //高度
        int title_width = fmTitle.stringWidth(goodsNm);
        int title_height=fmTitle.getHeight();
        //取得标题 宽度
        title_width = (DEFAULT_IMG_WIDTH/2-title_width/2);
        g.drawString(goodsNm, title_width, FONT_TOP_HEIGHT+title_height);//商品名称
        //添加价格
        //取得文字宽度
        Font priceFont = new Font(price, Font.BOLD, PRICE_FONT_SIZE);
        g.setFont(titleFont);
        g.setColor(PRICE_FONT_COLOR);
        FontMetrics fmPrice = new JLabel().getFontMetrics(titleFont);
        title_width = fmPrice.stringWidth(price);
        //取得标题 宽度
        title_width = (DEFAULT_IMG_WIDTH/2-title_width/2);
        int price_height=fmPrice.getHeight();
        System.out.println(price_height);
        g.drawString(price, title_width, FONT_TOP_HEIGHT+title_height+price_height);//商品价格
        g.dispose();
        return  bi;
    }

    /**
     * 二维码生成
     * @param qrCodeContext 二维码内容
     * @return
     * @throws Exception
     */
    private static BufferedImage generateQRcode(String qrCodeContext)throws Exception{
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(qrCodeContext, BarcodeFormat.QR_CODE, QRCODE_WIDTH, QRCODE_HEIGHT, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }
}
