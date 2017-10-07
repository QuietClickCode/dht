package com.retailers.dht.attachment;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.attachment.utils.Config;
import com.retailers.dht.attachment.utils.ImageUtils;
import com.retailers.tools.utils.Md5Encrypt;
import com.retailers.tools.utils.ObjectUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
					if (fileItem.isFormField()) {
						params.put(fileItem.getFieldName(), fileItem.getString());
						if (fileItem.getFieldName().equals("uploadType")) {
							uploadType = fileItem.getString();
							logger.info("取得文件上传类型{}", uploadType);
						}
					}
				}
			}
			logger.error("时间己过期,上传时间：" + params.get("time") + ";系统时间：" + curTime);
			if (!params.containsKey("time") || ObjectUtils.isEmpty(params.get("time"))) {
				logger.error("参数不正确");
				map.put("status", 1);
				map.put("msg", "time不能为空");
			} else if (!params.containsKey("uploadSign") || ObjectUtils.isEmpty(params.get("uploadSign"))) {
				logger.error("参数不正确");
				map.put("status", 1);
				map.put("msg", "uploadSign不能为空");
			} else if (ObjectUtils.isNotEmpty(items)) {
				sign = params.get("uploadSign").toString();
				t = params.get("time").toString();
				boolean isCompress = false;
				if (params.containsKey("isCompress") && ObjectUtils.isNotEmpty(params.get("isCompress"))) {
					isCompress = Boolean.parseBoolean(params.get("isCompress").toString());
				}
				boolean isAddWatermark = false;
				if (params.containsKey("isAddWatermark") && ObjectUtils.isNotEmpty(params.get("isAddWatermark"))) {
					isAddWatermark = Boolean.parseBoolean(params.get("isAddWatermark").toString());
				}
				String sign_ = Md5Encrypt.md5(Config.validateKeys + t);
				if (!sign_.equals(sign)) {
					map.put("status", 1);
					map.put("msg", "签名未授权");
				} else {
					if (ObjectUtils.isEmpty(uploadType)) {
						uploadType = "other";
					}
					//上传列表回传值
					for (FileItem fileItem : items) {
						if (!fileItem.isFormField()) {
							Map<String,String> saveInfo = ImageUtils.saveImage(fileItem, uploadType, isCompress, isAddWatermark);
							map.put(fileItem.getFieldName(), saveInfo);
						}
					}
				}
			}
			logger.info("上传文件成功!");
		}catch (FileUploadException e) {
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
}
