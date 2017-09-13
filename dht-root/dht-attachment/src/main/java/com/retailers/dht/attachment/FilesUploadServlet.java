package com.retailers.dht.attachment;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.attachment.utils.Config;
import com.retailers.dht.attachment.utils.ImageUtils;
import com.retailers.tools.utils.Md5Encrypt;
import com.retailers.tools.utils.ObjectUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 附件上传管理 多文件上传
 *
 * @author zhongp
 *
 */
public class FilesUploadServlet extends HttpServlet {
	Logger logger = LoggerFactory.getLogger(FilesUploadServlet.class);
	private static final long serialVersionUID = 1L;
	private ServletFileUpload upload;
	//上传文件最大数据
	private final long MAXSize = 4194304 * 2L;// 4*2MB
	Map<String, String> dir=new HashMap<String, String>();
	private String keys="99695f8e24bd27ee2f70dba1b19785c6";
	private Map<String,String> imageType=new HashMap<String, String>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FilesUploadServlet() {
		super();
	}

	/**
	 * 设置文件上传的初始化信息
	 *
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		FileItemFactory factory = new DiskFileItemFactory();
		this.upload = new ServletFileUpload(factory);
		this.upload.setSizeMax(this.MAXSize);
		String fileUploadType = Config.fileUploadType;
		if(ObjectUtils.isNotEmpty(fileUploadType)){
			String[] fileUploadTypes=fileUploadType.split(",");
			String value="";
			for(String key:fileUploadTypes){
				value=key;
				if(key.indexOf(":")>0){
					value=key.split(":")[1];
					key=key.split(":")[0];
				}
				dir.put(key, value);
			}
		}
		logger.info("取得文件保存地址{},服务器地址：{}",Config.savePath);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("文件上传开始");
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
		Map<String, Object> params = new HashMap<String, Object>();
		//上传结果
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status",0);
		map.put("msg","success");
		try {
			List<FileItem> items = this.upload.parseRequest(request);
			String uploadType = "";
			if (items != null && !items.isEmpty()) {
				for (FileItem fileItem : items) {
					if(fileItem.isFormField()){
						params.put(fileItem.getFieldName(), fileItem.getString());
						if(fileItem.getFieldName().equals("uploadType")){
							uploadType = fileItem.getString();
							logger.info("取得文件上传类型{}",uploadType);
						}
					}
				}
			}
			logger.error("时间己过期,上传时间："+params.get("time")+";系统时间："+curTime);
			if(!params.containsKey("time")|| ObjectUtils.isEmpty(params.get("time"))){
				logger.error("参数不正确");
				map.put("status",1);
				map.put("msg","time不能为空");
			}else if(!params.containsKey("uploadSign")||ObjectUtils.isEmpty(params.get("uploadSign"))){
				logger.error("参数不正确");
				map.put("status",1);
				map.put("msg","uploadSign不能为空");
			}else if (ObjectUtils.isNotEmpty(items)) {
				sign=params.get("uploadSign").toString();
				t = params.get("time").toString();
				boolean isCompress=false;
				if(params.containsKey("isCompress")&&ObjectUtils.isNotEmpty(params.get("isCompress"))){
					isCompress=Boolean.parseBoolean(params.get("isCompress").toString());
				}
				boolean isAddWatermark=false;
				if(params.containsKey("isAddWatermark")&&ObjectUtils.isNotEmpty(params.get("isAddWatermark"))){
					isAddWatermark=Boolean.parseBoolean(params.get("isAddWatermark").toString());
				}
				String sign_= Md5Encrypt.md5(keys+t);
				if(!sign_.equals(sign)){
					map.put("status",1);
					map.put("msg","签名未授权");
				}else{
					if(ObjectUtils.isEmpty(uploadType)){
						uploadType = "realNm";
					}
					//上传列表回传值
					for (FileItem fileItem : items) {
						if(!fileItem.isFormField()){
							String id= ImageUtils.saveImage(fileItem,uploadType,isCompress,isAddWatermark);
							map.put(fileItem.getFieldName(),id);
						}
					}
				}
			}
			logger.info("上传文件成功!");
		} catch(FileSizeLimitExceededException e){
			e.printStackTrace();
			map.put("status",1);
			map.put("msg","附件超过最大值");
		} catch (FileUploadException e) {
			e.printStackTrace();
			map.put("status",1);
			map.put("msg",e.getMessage());
		} catch(Exception e){
			e.printStackTrace();
			map.put("status",1);
			map.put("msg",e.getMessage());
		}
		out.write(JSON.toJSONString(map));
	}
//
//	/**
//	 * 保存文件
//	 * @param file
//	 * @return
//	 * @throws Exception
//	 */
//	public  String  saveImage(FileItem file,String path) throws Exception{
//		logger.info("上传文件名:{},文件路径:{},附件名：{}",file.getName(),path,file.getFieldName());
//		String filedNm = file.getFieldName();
//		//文件名
//		String fileNm = file.getName();
//		fileNm = generateFileName(fileNm);
//		if(StringUtils.isEmpty(filedNm)){
//			filedNm = FilenameUtils.getExtension(fileNm);
//		}
//		//组装保存文件的路径
//		String savePath = fileAllPath+pathDir+dir.get(path)+pathDir+getUrl();
//		if(!new File(savePath).exists()){
//			new File(savePath).mkdirs();
//		}
//		File outFile = new File(savePath+pathDir+fileNm);
//		file.write(outFile);
//		logger.info("文件保存地址:{}","/"+dir.get(path)+"/"+getUrls()+"/"+fileNm);
//		return "/"+dir.get(path)+"/"+getUrls()+"/"+fileNm;
//	}
//
//	public String getUrl(){
//		Date curDate =new Date();
//		String rtnUrl="";
//		//取得年
//		rtnUrl+= DateUtil.dateToString(curDate, DateUtil.DATE_YEAR);
//		//取得月
//		rtnUrl+=pathDir+DateUtil.dateToString(curDate, DateUtil.DATE_MONTH);
//		//取得日
//		rtnUrl+=pathDir+DateUtil.dateToString(curDate, DateUtil.DATE_DAY);
//		return rtnUrl;
//	}
//	public String getUrls(){
//		Date curDate =new Date();
//		String rtnUrl="";
//		//取得年
//		rtnUrl+=DateUtil.dateToString(curDate, DateUtil.DATE_YEAR);
//		//取得月
//		rtnUrl+="/"+DateUtil.dateToString(curDate, DateUtil.DATE_MONTH);
//		//取得日
//		rtnUrl+="/"+DateUtil.dateToString(curDate, DateUtil.DATE_DAY);
//		return rtnUrl;
//	}
//	/**
//	 * 随机生成唯一物理文件名
//	 *
//	 * @param fileName
//	 * @return
//	 */
//	public static String generateFileName(String fileName) {
//		if (null == fileName) {
//			fileName = "";
//		}
//		String extension = FilenameUtils.getExtension(fileName);
//		UUID uuid = UUID.randomUUID();
//		if (extension.equalsIgnoreCase("")) {
//			return  uuid.toString();
//		} else {
//			return  uuid.toString() + "." + extension;
//		}
//	}
}
