package com.retailers.dht.manage.controller;

import com.retailers.dht.common.upload.FileUploader;
import com.retailers.dht.common.upload.UploadFacatory;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.common.constant.AttachmentConstant;
import com.retailers.mybatis.common.service.AttachmentService;
import com.retailers.tools.utils.StringUtils;
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

    Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    @Autowired
    private AttachmentService attachmentService;

    /**
     * 上传图片 元水印
     * @return
     */
    @RequestMapping("/imageUpload")
    @ResponseBody
    public Map<String,String> imageUpload(@RequestParam("dht_image_upload") CommonsMultipartFile upfile,@RequestParam("imageUse")String type,
                                          @RequestParam(value = "isWatermark",defaultValue = "false")Boolean isWatermark,@RequestParam(value = "isCompress",defaultValue = "false")Boolean isCompress ){
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
        Map<String,String> rtn = uploader.upload(stream, type,upfile.getOriginalFilename(),isCompress,isWatermark);
        Map<String,String> imgMap = new HashMap();
        imgMap.put("state", "SUCCESS");
//        imgMap.put("url", AttachmentConstant.IMAGE_SHOW_URL+rtn.get("savePath"));
        imgMap.put("url", StringUtils.concat(AttachmentConstant.IMAGE_SHOW_URL,rtn.get("savePath"),"?random=",rtn.get("attachmentId")));
//        imgMap.put("url", StringUtils.concat(AttachmentConstant.IMAGE_SHOW_URL,rtn.get("savePath")));
        imgMap.put("title", upfile.getOriginalFilename());
//        imgMap.put("title", rtn.get("attachmentId"));
        imgMap.put("original", rtn.get("attachmentId"));
        logger.info("上传图片结束");
        return imgMap;
    }

}
