package com.retailers.hnc.common.upload;

import com.alibaba.fastjson.JSONObject;
import com.retailers.mybatis.common.constant.AttachmentConstant;
import com.retailers.mybatis.common.dao.AttachmentMapper;
import com.retailers.mybatis.common.entity.Attachment;
import com.retailers.tools.http.HttpClientManager;
import com.retailers.tools.utils.Md5Encrypt;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 上传至远程服务器
 */
@Component
public class RemoteServerUpload implements FileUploader{
    Logger logger= LoggerFactory.getLogger(RemoteServerUpload.class);

    @Autowired
    private AttachmentMapper attachmentMapper;

    /**
     *
     * @param stream 图处流
     * @param type 上传类型（图片用途）
     * @param fileName 文件名
     * @param isCompress 是否压缩
     * @param isAddWatermark 是否添加水印
     * @return
     */
    public Map<String,String> upload(InputStream stream, String type, String fileName, boolean isCompress, boolean isAddWatermark) {
        logger.info("开始进入远程服务器数据上传，传入类型：{}，文件名称：{}，是否压缩：{}，是否添加水印：{}",type,fileName,isCompress,isAddWatermark);
        String remote_url = AttachmentConstant.UPLOAD_ATTACHEMNT_URL;//"http://image.kuaiyis.com/filesUpload";// 第三方服务器请求地址
        Map<String,String> rtnMap=new HashMap<String, String>();
        String result="";
        try {
            CloseableHttpClient httpClient= HttpClientManager.getHttpClient();
            long curT = System.currentTimeMillis();
            HttpPost httpPost = new HttpPost(remote_url);
            String fileType=getMimeType(fileName);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("realNameAuthentication", stream, ContentType.MULTIPART_FORM_DATA, fileName);// 文件流
            builder.addTextBody("time", curT+"");//
            builder.addTextBody("uploadSign", Md5Encrypt.md5("99695f8e24bd27ee2f70dba1b19785c6"+curT));//
            builder.addTextBody("uploadType", type);
            builder.addTextBody("isCompress", isCompress+"");
            builder.addTextBody("isAddWatermark", isAddWatermark+"");// 类似浏览器表单提交，对应input的name和value
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);// 执行提交
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                int statusCode = response.getStatusLine().getStatusCode();
                logger.info("附件服务器返回状态:{}",statusCode);
                rtnMap.put("status",statusCode+"");
                if(statusCode == HttpStatus.SC_OK){
                    // 将响应内容转换为字符串
                    result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
                    JSONObject json= JSONObject.parseObject(result);
                    if(json.containsKey("status")&&json.getInteger("status")==0){
                        JSONObject obj=json.getJSONObject("realNameAuthentication");
                        if(ObjectUtils.isNotEmpty(obj)){
                            Attachment attachment=setAttachment(fileType,fileName,obj.getString("savePath"),obj.getString("showPath"));
                            attachmentMapper.saveAttachment(attachment);
                            rtnMap.put("attachmentId",attachment.getId()+"");
                            result=obj.getString("showPath");
                            if(isCompress){
                                result= StringUtils.formates(result,"middle");
                            }
                        }
                    }
                }else{

                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        logger.info("远程服务器数据上传结束");
        rtnMap.put("savePath",result);
        return rtnMap;
    }

    /**
     * 附件属性设置
     * @param fileType
     * @param fileName
     * @param savePath
     * @param showUrl
     * @return
     */
    private Attachment setAttachment(String fileType,String fileName,String savePath,String showUrl){
        Attachment attachment=new Attachment();
        attachment.setStatus(AttachmentConstant.ATTACHMENT_STATUS_NO);
        attachment.setType(fileType);
        attachment.setName(fileName);
        attachment.setSavePath(savePath);
        attachment.setShowUrl(showUrl);
        attachment.setCreateTime(new Date());
        attachment.setSaveType(AttachmentConstant.ATTACHMENT_SAVE_TYPE_REMOTE);
        return attachment;
    }

    /**
     *
     * @param stream 图处流
     * @param type 上传类型（图片用途）
     * @param fileName 文件名
     * @return
     */
    public Map<String,String> upload(InputStream stream, String type, String fileName) {
        return upload(stream,type,fileName,false,false);
    }

    private  String getMimeType(String fileUrl) throws java.io.IOException {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String type = fileNameMap.getContentTypeFor(fileUrl);
        return type;
    }
}
