package com.retailers.dht.manage.controller;

import com.retailers.auth.constant.SystemConstant;
import com.retailers.auth.entity.SysUser;
import com.retailers.auth.service.SysUserService;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 主页
 * @author zhongp
 *
 */
@Controller
@SessionAttributes
public class MainController extends BaseController {
	@Autowired
	SysUserService sysUserService;

	@RequestMapping(value={"/index","/","login"})
	public String index(HttpServletRequest request){
		return "sys_user/login";
	}
	@RequestMapping(value={"/home"})
	public String home(HttpServletRequest request){
//		SysUser sysUser=getCurLoginUser(request);
//		if(ObjectUtils.isEmpty(sysUser)){
//			SysUser info = new SysUser();
//			info.setUid(-1l);
//			info.setUaccount("admin");
//			info.setUname("测试");
//			setCurLoginUser(request,info);
//		}
		return "index";
	}

	@RequestMapping(value={"/main"})
	public String main(HttpServletRequest request){
		SysUser info = new SysUser();
		info.setUid(1l);
		info.setUname("测试");
		request.getSession().setAttribute(SystemConstant.LOG_USER_SESSION_KEY,info);
		return "main";
	}

	@RequestMapping("/simulateLogin")
	@ResponseBody
	public BaseResp simulateLogin(HttpServletRequest request, Long userId){
		SysUser info = sysUserService.querySysUserByUid(userId);
		setCurLoginUser(request,info);
		return success("模拟用户登陆成功");
	}

	@RequestMapping("/MP_verify_{m}.txt")
//	@ResponseBody
	public void verify(@PathVariable("m") String m, HttpServletRequest request, HttpServletResponse response)throws IOException {
		response.setContentType("text/plain;charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getOutputStream().write(m.getBytes("UTF-8"));
		if (null != response.getOutputStream()) {
			response.getOutputStream().close();
		}
//		String contentPath = request.getRealPath("/");
//		String tempFilePath = contentPath + File.separator + "mp" + File.separator;
//		File mpDir = new File(tempFilePath);
//		if(!mpDir.exists()){
//			mpDir.mkdir();
//		}
//		String fileName = "MP_verify_"+m+".txt";
//		m = m.replace("-", "/");
//		File f = new File(tempFilePath+fileName);
//		FileOutputStream out = null;
//		try {
//			out = new FileOutputStream(f);
//			out.write(m.getBytes());
//			this.download(response, f);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}finally{
//			if(out!=null){
//				out.close();
//			}
//		}
	}

	private void download(HttpServletResponse response,File f){
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		try{
			response.setContentType("text/html;charset=utf-8");
			response.setContentType("text/plain;charset=UTF-8");
			long fileLength = f.length();
//			response.setContentType("application/octet-stream;");
			response.setHeader("Content-disposition", "attachment; filename=" + new String(f.getName().getBytes("GBK"), "ISO-8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));

			bis = new BufferedInputStream(new FileInputStream(f));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[1024 * 2];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}finally{
			if (bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 统计页面展示
	 * @return
	 */
	@RequestMapping("/statistics")
	public String statistics(HttpServletRequest request){
		return "statistics/home";
	}
}
