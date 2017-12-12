package com.retailers.hnc.manage.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.SystemControllerLog;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.auth.entity.Menu;
import com.retailers.auth.entity.SysUser;
import com.retailers.auth.service.MenuService;
import com.retailers.auth.vo.MenuVo;
import com.retailers.hnc.manage.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单列表
 * @author zhongp
 *
 */
@Controller
@RequestMapping("menu")
public class MenuController extends BaseController {
	@Autowired
	MenuService menuService;

	@RequestMapping("/userMenus")
	@ResponseBody
	public BaseResp queryMenuByUserId(String t){
		List<Menu> menu=menuService.queryUserMenu(-1l);
		List<Map<String, Object>> menus = new ArrayList<Map<String,Object>>();
		itemMenu(menu,menus);
		return success(menus);
	}

	@RequestMapping("/queryUserMenus")
	@ResponseBody
	public BaseResp queryUserMenus(HttpServletRequest request){
		SysUser sysUser = getCurLoginUser(request);
		List<Menu> menu= new ArrayList<Menu>();
		if(ObjectUtils.isNotEmpty(sysUser)){
			menu=menuService.queryUserMenu(sysUser.getUid());
		}
		return success(menu);
	}
	/**
	 * 打开菜单列表
	 * @return
	 */
	@RequestMapping("/openMenus")
	@com.retailers.auth.annotation.Menu(label="资源列表", description = "描述", resourse = "menu.queryMenuByUserId",sort=2,parentRes="sys.menu.manager")
	@SystemControllerLog(description="取得菜单信息")
	@CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirect = "http://www.baidu.com")
	public String openMenus(){
		return "menu/menu";
	}

	@RequestMapping("queryMenuList")
	@Function(label="取得资源列表", description = "描述", resourse = "menu.queryMenuList",sort=2,parentRes="menu.queryMenuByUserId")
	@CheckSession(key =SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirect = "http://www.baidu.com")
	@ResponseBody
	public Map<String,Object> queryMenuList(){
		List<MenuVo> menu=menuService.queryMenuTree();
		Map<String,Object> rtn=new HashMap<String, Object>();
		rtn.put("total",100);
		rtn.put("rows",menu);
		return rtn;
	}

	/**
	 * 取得资源树型结构 排除传入的节点id(排除按钮）
	 * @param menuId
	 * @return
	 */
	@RequestMapping("queryMenuNode")
	@CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirect = "http://www.baidu.com")
	@ResponseBody
	public BaseResp queryMenuNode(Long menuId){
		List<MenuVo> list = menuService.queryMenuNode(menuId);
		return success(list);
	}

	/**
	 * 删除菜单资 源
	 * @param request
	 * @param id 菜单id
	 * @return
	 */
	@RequestMapping("/removeResource")
	@Function(label="移除资源", description = "描述", resourse = "menu.removeResource",sort=2,parentRes="menu.queryMenuByUserId")
	@SystemControllerLog(description="删除选中的资源")
	@CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirect = "http://www.baidu.com")
	@ResponseBody
	public BaseResp removeResource(HttpServletRequest request,Long id){
		menuService.removeResource(id);
		return success(null);
	}


	/**
	 * 删除菜单资 源
	 * @param request
	 * @param menu 资源数据
	 * @return
	 */
	@RequestMapping("/editorResource")
	@Function(label="编辑资源", description = "对资源名称，排序进行编辑", resourse = "menu.editorResource",sort=2,parentRes="menu.queryMenuByUserId")
	@SystemControllerLog(description="删除选中的资源")
	@CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirect = "http://www.baidu.com")
	@ResponseBody
	public BaseResp editorResource(HttpServletRequest request,MenuVo menu){
		try{
            menuService.editorResource(menu);
        }catch (Exception e){
            e.printStackTrace();
        }
		return success(null);
	}

	/**
	 *
	 * @param menu
	 */
	private void itemMenu(List<Menu> menu,List<Map<String, Object>> menus){
		if(!menu.isEmpty()&&menu.size()>0){
			for(Menu m:menu){
				Map<String, Object> menuMap= new HashMap<String, Object>();
				menuMap.put("id", m.getId());
				menuMap.put("pId", m.getParentId());
				menuMap.put("name", m.getLabel());
				if(menus.size()==0){
					menuMap.put("open", true);
				}
				menuMap.put("path", m.getUrl());
				menuMap.put("res", m.getResourse());
				menuMap.put("icon", m.getIcon());
				menus.add(menuMap);
				if(ObjectUtils.isNotEmpty(m.getChild())){
					itemMenu(m.getChild(),menus);
				}
			}
		}
	}
}
