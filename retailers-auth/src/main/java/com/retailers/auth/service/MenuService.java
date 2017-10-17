package com.retailers.auth.service;


import com.retailers.auth.bean.FunctionBean;
import com.retailers.auth.bean.MenuBean;
import com.retailers.auth.bean.ResourseBean;
import com.retailers.auth.entity.Menu;
import com.retailers.auth.vo.MenuVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MenuService {

	/**
	 * 注册菜单节点
	 *
	 * @param resourseBeans
	 * @return
	 */
	public Map<String,Integer> registerMenuNode(Set<ResourseBean> resourseBeans);

	/**
	 * 注册菜单
	 *
	 * @param menuBeans
	 * @return
	 */
	public Map<String,Integer> registerMenu(Set<MenuBean> menuBeans,Map<String,Integer> parseId);

	/**
	 * 注册菜单下功能按钮（用于页面操作权限控制）
	 *
	 * @param functions
	 * @return
	 */
	public Map<String,Integer> registerFunction(Set<FunctionBean> functions,Map<String,Integer> parseId);

	/**
	 *  取得用户拥有的权限菜单
	 * @param userId 用户id
	 * @return
	 */
	public List<Menu> queryUserMenu(Long userId);

	/**
	 * 取得树形结构的资源列表
	 * @return
	 */
	public List<MenuVo> queryMenuTree();

	/**
	 * 删除菜单
	 * @param menuId
	 * @return
	 */
	public long removeResource(Long menuId);

	/**
	 * 取得资源树型结构 排除传入的节点id(排除按钮）
	 * @param menuId
	 * @return
	 */
	public List<MenuVo> queryMenuNode(Long menuId);

	/**
	 * 编辑菜单
	 * @param menu
	 * @return
	 */
	public boolean editorResource(MenuVo menu)throws Exception;

}
