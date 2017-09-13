package com.retailers.dht.attachment.utils;

import com.retailers.tools.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


/**
 * 默认参数取得
 *
 * @author zhongp
 *
 */
public class Config {
	static Logger logger = LoggerFactory.getLogger(Config.class);

	//图片存放地址
	public static String savePath = "";
	private static Properties properties;
	//上传文件类型
	public static String fileUploadType="";
	//图片压缩类型
	public static String imageCompressType="";
	static{
		initConfig();
	}
	private static void initConfig(){
		//取得配制文件
		properties = new Properties();
		try {
			properties.load(Config.class.getResourceAsStream("/config.properties"));
		} catch (Exception e) {
			logger.error(StringUtils.getErrorInfoFromException(e));
		} finally {
		}
		try{
			savePath = properties.getProperty("savePath");
			fileUploadType = properties.getProperty("uploadFileType");
			imageCompressType = properties.getProperty("imageCompressType");
		}catch(Exception e){
			logger.error(StringUtils.getErrorInfoFromException(e));
		}
	}
}

