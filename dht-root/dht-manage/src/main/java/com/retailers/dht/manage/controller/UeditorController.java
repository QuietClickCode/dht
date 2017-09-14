package com.retailers.dht.manage.controller;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.common.service.AttachmentService;
import com.retailers.dht.common.upload.FileUploader;
import com.retailers.dht.common.upload.UploadFacatory;
import com.retailers.tools.base.BaseController;
import com.retailers.tools.base.BaseResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * ueditor 配置管理
 */
@Controller
@RequestMapping("ueditor")
public class UeditorController extends BaseController {
    Logger logger = LoggerFactory.getLogger(UeditorController.class);
    @Autowired
    private AttachmentService attachmentService;

    /**
     * 百度富文本统一接口
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/reqUeditorConfig")
    public Map<String,Object> getConfigJson(HttpServletRequest request, HttpServletResponse response){
        return getConfig(request,response);
    }

    /**
     * 初始化配置
     * @return
     */
    private Map<String,Object> getConfig(HttpServletRequest request, HttpServletResponse response){
        String ctx =request.getContextPath();
        Map<String,Object> config = new HashMap<String,Object>();//总配置对象
		/* 上传图片配置项 */
        config.put("imageActionName", "uploadimage");
        //提交的图片表单名称
        config.put("imageFieldName", "dht_image_upload");
        //上传大小限制，单位B
        config.put("imageMaxSize", "2048000");
        //图片访问路径前缀
        config.put("imageUrlPrefix", "http://image.kuaiyis.com/attachment");
        config.put("imageCompressEnable", false);
        config.put("imageCompressBorder", 1600);
        config.put("imageInsertAlign", "none");
        String [] imageFormat = new String[5];	//图片上传格式配置
        imageFormat[0] = ".png";
        imageFormat[1] = ".jpg";
        imageFormat[2] = ".jpeg";
        imageFormat[3] = ".gif";
        imageFormat[4] = ".bmp";

        config.put("imageAllowFiles", imageFormat);
        //指定文件上传路径和返回路径，支持格式化
//        config.put("imagePathFormat", "/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}");
        return config;
    }

    /**
     * 上传图片
     * @return
     */
    @ResponseBody
    @RequestMapping("/imageUpload")
    public  Map<String,String> uploadImg(@RequestParam("dht_image_upload") CommonsMultipartFile upfile){
        logger.info("进入图片上传");
        InputStream stream=null;
        try {
            stream=upfile.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileUploader uploader= UploadFacatory.getUploaer();
        String path = uploader.upload(stream, "goods",upfile.getOriginalFilename());
        Map<String,String> imgMap = new HashMap();
        imgMap.put("state", "SUCCESS");
        imgMap.put("url", path);
        imgMap.put("title", upfile.getOriginalFilename());
        imgMap.put("original", upfile.getOriginalFilename());
        logger.info("进入图片结束");
        System.out.println(JSON.toJSON(imgMap));
        return imgMap;
    }
}
