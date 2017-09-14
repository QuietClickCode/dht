package com.retailers.dht.attachment;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.attachment.utils.Config;
import com.retailers.dht.attachment.utils.ImageUtils;
import com.retailers.tools.utils.ImageUtil;
import com.retailers.tools.utils.Md5Encrypt;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * 附件管理 删除未使用的附件
 * @author zhongp
 * @version  1.0.1
 */
public class FilesRemoveServlet extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(FilesUploadServlet.class);
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        logger.info("开始删除文件");
        long curTime = System.currentTimeMillis();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.getSession().getServletContext();
        /**
         * 签 名
         */
        String sign = "";
        String t="";
        Map<String, String> params = new HashMap<String, String>();
        //上传结果
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status",0);
        map.put("msg","success");
        try {
            Enumeration paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = (String) paramNames.nextElement();
                String[] paramValues = request.getParameterValues(paramName);
                if (paramValues.length == 1) {
                    String paramValue = paramValues[0];
                    if (paramValue.length() != 0) {
                        System.out.println("参数：" + paramName + "=" + paramValue);
                        params.put(paramName, paramValue);
                    }
                }
            }
            String removeFile=params.get("removeFile");
            if (!params.containsKey("time") || ObjectUtils.isEmpty(params.get("time"))) {
                logger.error("参数不正确");
                map.put("status", 1);
                map.put("msg", "time不能为空");
            } else if (!params.containsKey("uploadSign") || ObjectUtils.isEmpty(params.get("uploadSign"))) {
                logger.error("参数不正确");
                map.put("status", 1);
                map.put("msg", "uploadSign不能为空");
            } else if (ObjectUtils.isNotEmpty(removeFile)) {
                sign = params.get("uploadSign").toString();
                t = params.get("time").toString();
                String sign_ = Md5Encrypt.md5(Config.validateKeys + t);
                if (!sign_.equals(sign)) {
                    map.put("status", 1);
                    map.put("msg", "签名未授权");
                } else {
                    List<String> list=new ArrayList<String>();
                    String[] strs=removeFile.split(";");
                    for(String str:strs){
                        if(str.indexOf("{}")>=0){
                            for(String type: Config.compressTypes){
                                list.add(StringUtils.formates(str,type));
                            }
                        }
                    }
                    Map<String,Boolean> fileExe=new HashMap<String, Boolean>();
                    //删除文件
                    for(String fileDir:list){
                        System.out.println(fileDir);
                        File file=new File(fileDir);
                        if(file.exists()){
                            file.delete();
                            fileExe.put(fileDir,true);
                        }else {
                            fileExe.put(fileDir,false);
                        }

                    }
                    map.put("msg",fileExe);
                }
            }
            logger.info("上传文件成功!");
        }catch(Exception e){
            e.printStackTrace();
            map.put("status",1);
            map.put("msg",e.getMessage());
        }
        out.write(JSON.toJSONString(map));
    }
}
