package com.retailers.auth.service.impl;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.annotation.SystemAopLog;
import com.retailers.auth.bean.FunctionBean;
import com.retailers.auth.bean.MenuBean;
import com.retailers.auth.bean.ResourseBean;
import com.retailers.auth.constant.MenuConstant;
import com.retailers.auth.dao.MenuMapper;
import com.retailers.auth.entity.Menu;
import com.retailers.auth.service.MenuService;
import com.retailers.auth.service.SysUserResPermissionService;
import com.retailers.auth.utils.CheckUserPermissionUtils;
import com.retailers.auth.vo.MenuVo;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private SysUserResPermissionService sysUserResPermissionService;
	public int registerMenuNode(ResourseBean bean) {
		boolean isAdd=false;

		if(ObjectUtils.isNotEmpty(bean)){
			Map<String,Object> map=queryRes(bean.getChangeRes(),bean.getResourse());
			Menu menu = (Menu) map.get("menu");
			isAdd= Boolean.parseBoolean(map.get("add")+"");
			if(ObjectUtils.isEmpty(menu.getIsChange())||menu.getIsChange().intValue()== MenuConstant.MENU_IS_CHANGE_YES){
				menu.setIsValid(bean.getIsValid());
				menu.setLabel(bean.getLabel());
				menu.setSort(bean.getSort());
				menu.setDescription(bean.getDescription());
				menu.setIcon(bean.getIcon());
				//判断是否存在上级
				if(ObjectUtils.isNotEmpty(bean.getParentRes())) {
					Integer parseInt = queryParseInt(bean.getParentRes());
					menu.setParentId(parseInt);
				}
			}
			menu.setResourse(bean.getResourse());
			menu.setType(MenuConstant.MENU_TYPE_RES);
			if(isAdd){
				menuMapper.saveMenu(menu);
			}else{
				menuMapper.updateMenu(menu);
			}
			return menu.getId();
		}

		return -1;
	}

	private Map<String,Object> queryRes(String changeRes,String resourse){
		Menu menu=null;
		boolean isAdd=false;
		//判断是否切换过资源
		if(ObjectUtils.isNotEmpty(changeRes)){
			//判断被切换的资源是否存在
			menu = menuMapper.queryMenuByRes(changeRes);
			//被切换的资源己不存在
			if(ObjectUtils.isEmpty(menu)){
				//判断新资源是否己存在
				menu = menuMapper.queryMenuByRes(resourse);
				if(ObjectUtils.isEmpty(menu)){
					menu = new Menu();
					isAdd =true;
				}
			}
		}else{
			menu = menuMapper.queryMenuByRes(resourse);
			if(ObjectUtils.isEmpty(menu)){
				menu = new Menu();
				isAdd =true;
			}
		}
		Map<String,Object> rtn=new HashMap<String,Object>();
		rtn.put("menu",menu);
		rtn.put("add",isAdd);
		return rtn;
	}


	/**
	 * 取得上级资源
	 * @param parentRes 父级资源resouce(唯一值)
	 * @return
	 */
	private Integer queryParseInt(String parentRes){
		Menu parseMenu = menuMapper.queryMenuByRes(parentRes);
		if(ObjectUtils.isEmpty(parseMenu)){
			parseMenu = new Menu();
			parseMenu.setResourse(parentRes);
			menuMapper.saveMenu(parseMenu);
		}
		return  parseMenu.getId();
	}

	public int registerMenu(MenuBean bean) {
		boolean isAdd=false;
		if(ObjectUtils.isNotEmpty(bean)){
			Map<String,Object> map=queryRes(bean.getChangeRes(),bean.getResourse());
			Menu menu = (Menu) map.get("menu");
			isAdd= Boolean.parseBoolean(map.get("add")+"");
			if(ObjectUtils.isEmpty(menu.getIsChange())||menu.getIsChange().intValue()==MenuConstant.MENU_IS_CHANGE_YES){
				menu.setDescription(bean.getDescription());
				menu.setIcon(bean.getIcon());
				menu.setIsValid(bean.getIsValid());
				menu.setLabel(bean.getLabel());
				menu.setSort(bean.getSort());
				//判断是否存在上级
				if(ObjectUtils.isNotEmpty(bean.getParentRes())) {
					Integer parseInt = queryParseInt(bean.getParentRes());
					menu.setParentId(parseInt);
				}
			}
			menu.setResourse(bean.getResourse());
			menu.setType(MenuConstant.MENU_TYPE_MENU);
			menu.setUrl(bean.getUrl());
			if(isAdd){
				menuMapper.saveMenu(menu);
			}else{
				menuMapper.updateMenu(menu);
			}
			return menu.getId();
		}

		return -1;
	}
	public int registerFunction(FunctionBean bean) {
		boolean isAdd=false;
		if(ObjectUtils.isNotEmpty(bean)){
			Map<String,Object> map=queryRes(bean.getChangeRes(),bean.getResourse());
			Menu menu = (Menu) map.get("menu");
			isAdd= Boolean.parseBoolean(map.get("add")+"");
			if(ObjectUtils.isEmpty(menu.getIsChange())||menu.getIsChange().intValue()==MenuConstant.MENU_IS_CHANGE_YES){
				menu.setIcon(bean.getIcon());
				menu.setIsValid(bean.getIsValid());
				menu.setLabel(bean.getLabel());
				menu.setSort(bean.getSort());
				menu.setDescription(bean.getDescription());
				//判断是否存在上级
				if(ObjectUtils.isNotEmpty(bean.getParentRes())) {
					Integer parseInt = queryParseInt(bean.getParentRes());
					menu.setParentId(parseInt);
				}
			}
			menu.setResourse(bean.getResourse());
			menu.setType(MenuConstant.MENU_TYPE_FUNCTION);
			menu.setUrl(bean.getUrl());
			if(isAdd){
				menuMapper.saveMenu(menu);
			}else{
				menuMapper.updateMenu(menu);
			}
			return menu.getId();
		}

		return -1;
	}

	@SystemAopLog(name="sss")
	public List<Menu> queryUserMenu(Long userId){
		List<MenuVo> list = new ArrayList<MenuVo>();
		if(userId.intValue()==-1){
			list=menuMapper.queryAllMenu(false);
		}else{
			list= menuMapper.queryUserMenu(userId,false);
		}
		Map<Long,MenuVo> allMenus= new HashMap<Long, MenuVo>();
		for(MenuVo vo:list){
			allMenus.put(vo.getId(),vo);
		}
		Map<Long,Map<Long,Long>> child=new HashMap<Long, Map<Long, Long>>();
		queryChildNode(list,child);
		Map<String,String> allowMenuUrl= new HashMap<String, String>();
		List<Menu> rtnList = queryMenuTree(null,child,allMenus,allowMenuUrl);
		CheckUserPermissionUtils.permUrl.put(userId,allowMenuUrl);
		Collections.sort(rtnList);
		sysUserResPermissionService.loadUserResPermission(userId);
		return rtnList;
	}

	/**
	 * 取得树形结构的资源列表
	 * @return
	 */
	public List<MenuVo> queryMenuTree() {
		List<MenuVo> list =menuMapper.queryMenuTree();
		List<MenuVo> rtnList=new ArrayList<MenuVo>();
		Map<Long,Map<Long,Long>> child=new HashMap<Long, Map<Long, Long>>();
		queryChildNode(list,child);
		Map<Long,Long> alloShow=new HashMap<Long, Long>();
		queryAllowMenu(null,child,alloShow);
		for(MenuVo vo:list){
			if(ObjectUtils.isEmpty(vo.getParentId())){
				vo.setLevel(1l);
				rtnList.add(vo);
			}else{
				if(alloShow.containsKey(vo.getParentId())){
					rtnList.add(vo);
				}
				vo.setLevel(2l);
			}
		}
		return rtnList;
	}
	/**
	 * 取得树形结构的资源列表
	 * @return
	 */
	public List<MenuVo> queryMenuNode(Long menuId) {
		List<MenuVo> list =menuMapper.queryMenuNode(menuId);
		List<MenuVo> rtnList=new ArrayList<MenuVo>();
		Map<Long,Map<Long,Long>> child=new HashMap<Long, Map<Long, Long>>();
		queryChildNode(list,child);
		Map<Long,Long> alloShow=new HashMap<Long, Long>();
		queryAllowMenu(null,child,alloShow);
		for(MenuVo vo:list){
			if(ObjectUtils.isEmpty(vo.getParentId())){
				rtnList.add(vo);
			}else{
				if(alloShow.containsKey(vo.getParentId())){
					rtnList.add(vo);
				}
			}
		}
		return rtnList;
	}
	@Transactional(rollbackFor =  Exception.class)
	public boolean editorResource(MenuVo menu)throws Exception {
		MenuVo vo =menuMapper.queryMenuByid(menu.getId());
		if(ObjectUtils.isEmpty(vo)){
			throw new Exception("修改节点不存在");
		}
		//判断是否变更节点
		if(vo.getType().intValue()!=MenuConstant.MENU_TYPE_FUNCTION&&(ObjectUtils.isNotEmpty(menu.getParentId())&&ObjectUtils.isNotEmpty(vo.getParentId())&&menu.getParentId().intValue()!=vo.getParentId().intValue())){
			List<MenuVo> list =menuMapper.queryMenuTree();
			MenuVo parentVo=null;
			for(MenuVo v:list){
				if(compareTo(v.getParentId(),menu.getParentId())){
					if(v.getIsDelete().intValue()!=MenuConstant.MENU_IS_VALID_YES){
						throw new Exception("上级节点己变更");
					}
					if(v.getIsDelete().intValue()!=MenuConstant.MENU_IS_DELETE_NO){
						throw new Exception("上级节点己变更");
					}
					break;
				}
			}
			Map<Long,Map<Long,Long>> child=new HashMap<Long, Map<Long, Long>>();
			queryChildNode(list,child);
			checkLoop(child,menu.getParentId(),vo.getId());
		}
		//判断是否是节点
		if(vo.getType().intValue()==MenuConstant.MENU_TYPE_FUNCTION){
			menu.setParentId(vo.getParentId());
		}
		BeanUtils.copyProperties(menu,vo,new String[]{"id","version","resourse","type","url"});
		vo.setIsChange(MenuConstant.MENU_IS_CHANGE_NO);
		int total =menuMapper.editResource(vo);
		return true;
	}

	/**
	 * 取得节点下的所有子节点
	 * @param list 树型菜单
	 * @param child
	 */
	private void queryChildNode(List<MenuVo> list,Map<Long,Map<Long,Long>> child){
		for(MenuVo vo:list){
			Long parentId=vo.getParentId();
			if(ObjectUtils.isEmpty(parentId)){
				parentId=-1l;
			}
			if(child.containsKey(parentId)){
				child.get(parentId).put(vo.getId(),vo.getIsDelete());
			}else{
				Map<Long,Long> maps=new HashMap<Long, Long>();
				maps.put(vo.getId(),vo.getIsDelete());
				child.put(parentId,maps);
			}
		}
	}
	/**
	 * 取得允许展示节点，排除无节点的数据
	 * @param parentId
	 * @param map
	 * @param allowMap
	 */
	private void queryAllowMenu(Long parentId,Map<Long,Map<Long,Long>> map,Map<Long,Long>allowMap){
		if(ObjectUtils.isEmpty(parentId)){
			parentId=-1l;
		}
		Map<Long,Long> child=map.get(parentId);
		if(ObjectUtils.isNotEmpty(child)&&!child.isEmpty()){
			for(Long key:child.keySet()){
				if(child.get(key)==MenuConstant.MENU_IS_VALID_YES){
					allowMap.put(key,key);
					if(map.containsKey(key)){
						queryAllowMenu(key,map,allowMap);
					}
				}
			}
		}
	}

	/**
	 * 取得用户有权限的树结构
	 * @param parentId
	 * @param map
	 * @param allMenus
	 * @return
	 */
	private List<Menu> queryMenuTree(Long parentId,Map<Long,Map<Long,Long>> map,Map<Long,MenuVo> allMenus,Map<String,String> allowMenu){
		if(ObjectUtils.isEmpty(parentId)){
			parentId=-1l;
		}
		List<Menu> rtn = new ArrayList<Menu>();
		Map<Long,Long> child=map.get(parentId);
		if(ObjectUtils.isNotEmpty(child)&&!child.isEmpty()){
			for(Long key:child.keySet()){
				Menu menu=new Menu();
				BeanUtils.copyProperties(allMenus.get(key),menu);
				if(ObjectUtils.isNotEmpty(menu.getUrl())){
					allowMenu.put(StringUtils.replaceFirstChart(menu.getUrl(),CheckUserPermissionUtils.URL_START_CHART,CheckUserPermissionUtils.URL_START_REPLACE),menu.getUrl());
				}
				if(child.get(key)==MenuConstant.MENU_IS_VALID_YES){
					if(map.containsKey(key)){
						List<Menu> childs = queryMenuTree(key,map,allMenus,allowMenu);
						Collections.sort(childs);
						menu.setChild(childs);
					}
				}
				rtn.add(menu);
			}
		}
		return rtn;
	}


	/**
	 * 判断选反的上级节点是否是子节点
	 * @param child
	 * @param curNode
	 * @param childNode
	 * @throws Exception
	 */
	private void checkLoop(Map<Long,Map<Long,Long>> child,Long curNode,Long childNode)throws Exception{
		if(ObjectUtils.isEmpty(curNode)){
			curNode=-1l;
		}
		Map<Long,Long> map=child.get(curNode);
		if(ObjectUtils.isNotEmpty(map)){
			for(Long key:map.keySet()){
				if(key.intValue()==childNode.intValue()){
					throw new Exception("上级节点不是能自己的下级节点");
				}
				checkLoop(child,key,childNode);
			}
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public long removeResource(Long menuId) {
		return menuMapper.removeMenu(menuId);
	}

	public boolean compareTo(Long id,Long id1){
		if(ObjectUtils.isEmpty(id)&&ObjectUtils.isEmpty(id1)){
			return true;
		}else if(ObjectUtils.isNotEmpty(id)&&ObjectUtils.isNotEmpty(id1)){
			if(id.intValue()==id1.intValue()){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}
