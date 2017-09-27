package com.retailers.auth.service;


import com.retailers.auth.bean.FunctionBean;
import com.retailers.auth.bean.MenuBean;
import com.retailers.auth.bean.ResourseBean;
import com.retailers.auth.entity.Menu;
import com.retailers.auth.vo.MenuVo;

import java.util.List;

public interface MenuService {

	/**
	 * 注册菜单节点
	 *
	 * @param resourseBean
	 * @return
	 */
	public int registerMenuNode(ResourseBean resourseBean);

	/**
	 * 注册菜单
	 *
	 * @param menuBean
	 * @return
	 */
	public int registerMenu(MenuBean menuBean);

	/**
	 * 注册菜单下功能按钮（用于页面操作权限控制）
	 *
	 * @param functionBean
	 * @return
	 */
	public int registerFunction(FunctionBean functionBean);

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
