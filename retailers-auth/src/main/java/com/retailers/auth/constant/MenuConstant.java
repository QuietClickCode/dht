package com.retailers.auth.constant;

/**
 * 菜单常定义
 *
 * @author zhongp
 *
 */
public class MenuConstant {
	/**
	 * 定义菜单类型 0 节点
	 */
	public static int MENU_TYPE_RES = 0;
	/**
	 * 定义菜单类型 1 菜单
	 */
	public static int MENU_TYPE_MENU = 1;
	/**
	 * 定义菜单类型 2 功能按钮
	 */
	public static int MENU_TYPE_FUNCTION = 2;
	/**
	 * 菜单是否有效 0 有效
	 */
	public static int MENU_IS_VALID_YES=0;
	/**
	 * 菜单是否有效 1 无效
	 */
	public static int MENU_IS_VALID_NO=1;
	/**
	 * 是否删除 否
	 */
	public static int MENU_IS_DELETE_NO=0;
	/**
	 * 是否删除 是
	 */
	public static int MENU_IS_DELETE_YES=1;

	/**
	 * 就否可变更当前节点 0 可变更（前端未修改节点属性）
	 */
	public static long MENU_IS_CHANGE_YES=0;
	/**
	 * 就否可变更当前节点 1 不可变更（前端己修改节点属性）
	 */
	public static long MENU_IS_CHANGE_NO=1;
}
