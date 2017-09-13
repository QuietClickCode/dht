package com.retailers.dht.attachment.utils;

import com.retailers.tools.utils.DateUtil;
import com.retailers.tools.utils.Md5Encrypt;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.List;

/**
 * 图片处理工具类
 * @author zhongp
 * @version 1.0.1
 */
public class ImageUtils {
    static Logger logger = LoggerFactory.getLogger(ImageUtils.class);
    //水印图片地址
    private static  BufferedImage waterMark;
    //水印图片宽度
    private static int WATER_MARK_WIDTH=0;
    //水印图片高度
    private static int WATER_MARK_HEIGHT=0;
    private static List<Map<String,Integer>> compressRatio=new ArrayList<Map<String, Integer>>();
    private static List<String> ysdj=new ArrayList<String>();
    private static Map<String,String> imageCompressType=new HashMap<String, String>();
    static {
        try{
            waterMark=ImageIO.read(new File("d:\\water.png"));
            WATER_MARK_WIDTH =waterMark.getWidth();
            WATER_MARK_HEIGHT=waterMark.getHeight();
            Map<String,Integer> smallImage=new HashMap<String, Integer>();
            smallImage.put("width",640);
            smallImage.put("height",480);
            Map<String,Integer> middleImage=new HashMap<String, Integer>();
            middleImage.put("width",1080);
            middleImage.put("height",720);
            compressRatio.add(smallImage);
            compressRatio.add(middleImage);
            //缩略图 小图
            ysdj.add("small");
            //缩略图中图
            ysdj.add("middle");
            //原始文件
            ysdj.add("originalfile");
            if(ObjectUtils.isNotEmpty(Config.imageCompressType)){
                String[] imageCompressType_=Config.imageCompressType.split(",");
                for(String key:imageCompressType_){
                    imageCompressType.put(key.toLowerCase(),key.toLowerCase());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private static String pathDir = File.separator;
    /**
     * 图片保存
     * @param file 图片
     * @param dir
     * @param isCompress 是否压缩(压缩结果 为三种 小图 240*360，中图640*960，源图)
     * @param isAddWatermark 是否添加水印
     * @return
     * @throws Exception
     */
    public static String saveImage(FileItem file, String dir, boolean isCompress, boolean isAddWatermark)throws Exception{
//    public static String saveImage(File file,String dir, boolean isCompress, boolean isAddWatermark)throws Exception{
        logger.info("上传参数名称:{},文件名称:{},是否压缩：{},是否添加水印:{}，上传文件用途:{}",file.getFieldName(),file.getName(),isCompress, isAddWatermark,dir);
        //文件名
        String fileNm = file.getName();
        //随机生成的文件名称
        String newFileNm = generateFileName(fileNm);
        //文件类型
        String fileType= FilenameUtils.getExtension(fileNm);
        String isCom="";
        if(isCompress){
            isCom="{}";
        }else{
            isCom=ysdj.get(2);
        }
        if(ObjectUtils.isNotEmpty(fileType)){
            //组装保存文件的路径
            String savePath =Config.savePath+pathDir+dir+pathDir+getUrl();
            File sFile=new File(savePath);
            if(!sFile.exists()){
                sFile.mkdirs();
            }
            //判断是否是图片类型附件
            if(imageCompressType.containsKey(fileType.toLowerCase())){
                //取得原图片
                BufferedImage bufferedImage=ImageIO.read(file.getInputStream());
                //判断是否压缩 压缩
                if(isCompress){
                    int count=0;
                    for(Map<String,Integer> map:compressRatio){
                        keepAspectRatio(bufferedImage,fileType,map.get("width"),map.get("height"),fileSavePath(savePath,newFileNm,fileType,ysdj.get(count)),isAddWatermark);
                        count++;
                    }
                }
                keepAspectRatio(bufferedImage,fileType,bufferedImage.getWidth(),bufferedImage.getHeight(),fileSavePath(savePath,newFileNm,fileType,ysdj.get(2)),isAddWatermark);
            }else{
                File outFile = new File(fileSavePath(savePath,newFileNm,fileType,isCom));
                logger.info("保存文件地址：{}",outFile.getAbsolutePath());
                file.write(outFile);
            }
        }
//        logger.info("文件保存地址:{}","/"+dir.get(path)+"/"+getUrls()+"/"+fileNm);
        return "/"+dir+"/"+getUrls()+"/"+StringUtils.formate(newFileNm,isCom)+"."+fileType;
    }

    private static String fileSavePath(String savePath,String fileName,String fileType,String alias){
        return savePath+pathDir+StringUtils.formate(fileName,alias)+"."+fileType;
    }
    /**
     * 取得文件相对路径（用于后端文件保存)
     * @return
     */
    private static String getUrl(){
        Date curDate =new Date();
        String rtnUrl="";
        //取得年
        rtnUrl+= DateUtil.dateToString(curDate, DateUtil.DATE_YEAR);
        //取得月
        rtnUrl+=pathDir+DateUtil.dateToString(curDate, DateUtil.DATE_MONTH);
        //取得日
        rtnUrl+=pathDir+DateUtil.dateToString(curDate, DateUtil.DATE_DAY);
        return rtnUrl;
    }

    /**
     * 取得文件相对路径（返回给前端）
     * @return
     */
    private static String getUrls(){
        Date curDate =new Date();
        String rtnUrl="";
        //取得年
        rtnUrl+=DateUtil.dateToString(curDate, DateUtil.DATE_YEAR);
        //取得月
        rtnUrl+="/"+DateUtil.dateToString(curDate, DateUtil.DATE_MONTH);
        //取得日
        rtnUrl+="/"+DateUtil.dateToString(curDate, DateUtil.DATE_DAY);
        return rtnUrl;
    }

    /**
     * 生成随机名
     * @param fileName
     * @return
     */
    private static String generateFileName(String fileName) {
        UUID uuid = UUID.randomUUID();
        String uuidStr= Md5Encrypt.md5(StringUtils.formate(uuid.toString(),fileName));
        return uuidStr;
    }

    /**
     * 给图片添加水印
     * @param file
     * @throws Exception
     */
    public static void addWaterMark(File file)throws Exception{
        BufferedImage image = ImageIO.read(file);
        int imageWidth = image.getWidth();
        int imageHeitht = image.getHeight();
        long t=System.currentTimeMillis();
        List<Point> list = watermarkPoint(imageWidth,imageHeitht);
        Thumbnails.Builder<BufferedImage> abc =Thumbnails.of(image);
        abc.size(imageHeitht,imageHeitht);
        for(Point point:list){
            abc.watermark(new com.retailers.dht.attachment.utils.Positions(point),waterMark,0.2f);
        }
        abc.outputQuality(1.0f)
                .outputFormat("jpg")
                .toFile("d:\\abdefafa");
        System.out.println("执行时间:"+(System.currentTimeMillis()-t));
    }

    /**
     * 水印坐标(用于生成平铺水印)
     * @param width 原图宽度
     * @param height 原图高度
     * @return
     */
    private static List<Point> watermarkPoint(int width,int height){
        List<Point> rtn=new ArrayList<Point>();
        //取得水印
        int wNum=width/WATER_MARK_WIDTH;
        if(wNum==0){
            wNum=1;
        }else if(wNum*WATER_MARK_WIDTH<width){
            wNum=wNum+1;
        }
        int hNum=height/WATER_MARK_HEIGHT;
        if(hNum==0){
            hNum=1;
        }else if(hNum*WATER_MARK_HEIGHT<height){
            hNum=hNum+1;
        }
        for(int i=0;i<wNum;i++){
            for(int j=0;j<hNum;j++){
                rtn.add(new Point(i*WATER_MARK_WIDTH,j*WATER_MARK_HEIGHT));
            }
        }
        return rtn;
    }

    /**
     * 等比例压缩图片
     * @param image 源图片
     * @param imageType 保存图片类型
     * @param width 压缩后的宽度
     * @param height 压缩后的高度
     * @param savePath 保存地址
     * @param isAddWatermark 是否压缩图片
     * @throws Exception
     */
    private static void keepAspectRatio(BufferedImage image,String imageType,int width,int height,String savePath,boolean isAddWatermark)throws Exception{
        Thumbnails.Builder<BufferedImage> abc =Thumbnails.of(image);
        abc.size(width,height);
        if(isAddWatermark){
            List<Point> list = watermarkPoint(width,height);
            for(Point point:list){
                abc.watermark(new com.retailers.dht.attachment.utils.Positions(point),waterMark,0.2f);
            }
        }
        abc.outputFormat(imageType).toFile(savePath);
    }

    public static void main(String[] args)throws Exception{
//        addWaterMark(new File("d:\\abc.jpg"));
//       String url= saveImage(new File("d:\\abc.jpg"),"head",true,true);
//        System.out.println(url);
    }
}
