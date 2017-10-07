package com.retailers.dht.manage.controller;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.common.service.AttachmentService;
import com.retailers.dht.common.upload.FileUploader;
import com.retailers.dht.common.upload.UploadFacatory;
import com.retailers.dht.manage.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传管理
 * @author zhongp
 * @version  1.0.1
 * @data 2017-10-07
 */
@Controller
@RequestMapping("file")
public class FileUploadController extends BaseController{

    Logger logger = LoggerFactory.getLogger(UeditorController.class);
    @Autowired
    private AttachmentService attachmentService;

    /**
     * 上传图片 元水印
     * @return
     */
    @ResponseBody
    @RequestMapping("/imageUpload")
    public Map<String,String> imageUpload(@RequestParam("dht_image_upload") CommonsMultipartFile upfile,@RequestParam("type")String type,@RequestParam("isWatermark")Boolean isWatermark,@RequestParam("isCompress")Boolean isCompress ){
        logger.info("进入图片上传，取得传入参数,图片类型：{}，是否添加水印：{}，是否压缩：{}",type,isWatermark,isCompress);
        return uploadImage(upfile,type,isWatermark,isCompress);
    }
    /**
     * 文件上传工具类
     * @param upfile 文件上传流
     * @param isWatermark 是否添中水印
     * @param isCompress 是否压缩
     * @return
     */
    private Map<String,String> uploadImage(CommonsMultipartFile upfile,String type,boolean isWatermark,boolean isCompress){
        logger.info("上传图片开始");
        InputStream stream=null;
        try {
            stream=upfile.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileUploader uploader= UploadFacatory.getUploaer();
        String path = uploader.upload(stream, type,upfile.getOriginalFilename(),isCompress,isWatermark);
        Map<String,String> imgMap = new HashMap();
        imgMap.put("state", "SUCCESS");
        imgMap.put("url", path);
        imgMap.put("title", upfile.getOriginalFilename());
        imgMap.put("original", upfile.getOriginalFilename());
        logger.info("上传图片结束");
        return imgMap;
    }

}
