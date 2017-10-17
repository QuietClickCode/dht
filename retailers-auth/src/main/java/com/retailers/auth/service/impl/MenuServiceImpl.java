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

	/**
	 * 注册资上级资源
	 * @param resourseBeans
	 * @return
	 */
	public Map<String,Integer> registerMenuNode(Set<ResourseBean> resourseBeans) {
		List<String> resources=new ArrayList<String>();
		//添加的资源res
		Set<String> res=new HashSet<String>();
		//变更次源res
		Map<String,String> changeRes=new HashMap<String, String>();
		//资源对应的 资源id
		Map<String,Integer> pidMap=new HashMap<String, Integer>();
		//菜单资源
		Map<String,ResourseBean> mapBeans= new HashMap<String, ResourseBean>();
		Map<String,Integer> rtnMap=new HashMap<String, Integer>();
		for(ResourseBean bean:resourseBeans){
			resources.add(bean.getResourse());
			if(ObjectUtils.isNotEmpty(bean.getChangeRes())){
				resources.add(bean.getChangeRes());
				changeRes.put(bean.getChangeRes(),bean.getResourse());
			}
			res.add(bean.getResourse());
			mapBeans.put(bean.getResourse(),bean);
		}
		if(ObjectUtils.isNotEmpty(resources)){
			List<Menu> listMenus=menuMapper.queryMenuByResources(MenuConstant.MENU_TYPE_RES,resources);
			Map<String,Menu> czMenu=new HashMap<String, Menu>();
			if(ObjectUtils.isNotEmpty(listMenus)){
				for(Menu menu:listMenus){
					czMenu.put(menu.getResourse(),menu);
					pidMap.put(menu.getResourse(),menu.getId());
					res.remove(menu.getResourse());
					if(changeRes.containsKey(menu.getResourse())){
						pidMap.put(changeRes.get(menu.getResourse()),menu.getId());
						res.remove(changeRes.get(menu.getResourse()));
					}
				}
			}
			if(ObjectUtils.isNotEmpty(res)){
				List<Menu> menus = new ArrayList<Menu>();
				for(String str:res){
					Menu m= new Menu();
					copyResourse(mapBeans.get(str),m,MenuConstant.MENU_TYPE_RES);
					menus.add(m);
				}
				//批量添加
				menuMapper.saveMenus(menus);
				for(Menu menu:menus){
					czMenu.put(menu.getResourse(),menu);
					pidMap.put(menu.getResourse(),menu.getId());
				}
			}
			List<Menu> eidtor=new ArrayList<Menu>();
			for(ResourseBean bean:resourseBeans){
				Menu menu=czMenu.get(bean.getResourse());
				if(ObjectUtils.isEmpty(menu)){
					menu=czMenu.get(bean.getChangeRes());
				}
				copyResourse(bean,menu,MenuConstant.MENU_TYPE_RES);
				menu.setParentId(pidMap.get(bean.getParentRes()));
				eidtor.add(menu);
				rtnMap.put(menu.getResourse(),menu.getId());
			}
			menuMapper.updateMenus(eidtor);
		}
		return rtnMap;
	}

	/**
	 * 设置menu值
	 * @param bean
	 * @param menu
	 */
	private void copyResourse(ResourseBean bean,Menu menu,Integer type){
		if(ObjectUtils.isEmpty(menu.getIsChange())||menu.getIsChange().intValue()== MenuConstant.MENU_IS_CHANGE_YES){
			menu.setIsValid(bean.getIsValid());
			menu.setLabel(bean.getLabel());
			menu.setSort(bean.getSort());
			menu.setDescription(bean.getDescription());
			menu.setIcon(bean.getIcon());
		}
		menu.setResourse(bean.getResourse());
		menu.setType(type);
	}
	public Map<String,Integer> registerMenu(Set<MenuBean> menuBeans,Map<String,Integer> parseId) {
		Map<String,Integer> rtn=new HashMap<String, Integer>();
		List<String> resources=new ArrayList<String>();
		//变更次源res
		Map<String,String> changeRes=new HashMap<String, String>();
		Map<String,MenuBean> map=new HashMap<String, MenuBean>();
		Map<String,Menu> editorMenus=new HashMap<String, Menu>();
		for(MenuBean bean:menuBeans){
			resources.add(bean.getResourse());
			if(ObjectUtils.isNotEmpty(bean.getChangeRes())){
				resources.add(bean.getChangeRes());
				changeRes.put(bean.getChangeRes(),bean.getResourse());
			}
			map.put(bean.getResourse(),bean);
		}
		if(ObjectUtils.isNotEmpty(resources)) {
			List<Menu> listMenus = menuMapper.queryMenuByResources(MenuConstant.MENU_TYPE_MENU, resources);
			if(ObjectUtils.isNotEmpty(listMenus)){
				for(Menu menu:listMenus){
					if(changeRes.containsKey(menu.getResourse())){
						map.remove(changeRes.get(menu.getResourse()));
					}
					map.remove(menu.getResourse());
					editorMenus.put(menu.getResourse(),menu);
				}
			}
			List<Menu> addMenus=new ArrayList<Menu>();
			if(ObjectUtils.isNotEmpty(map)){
				for(String key:map.keySet()){
					Menu menu = new Menu();
					copyMenu(map.get(key),menu,MenuConstant.MENU_TYPE_MENU);
					addMenus.add(menu);
				}
				menuMapper.saveMenus(addMenus);
				for(Menu menu:addMenus){
					editorMenus.put(menu.getResourse(),menu);
				}
			}
			List<Menu> editorList=new ArrayList<Menu>();
			for(MenuBean bean:menuBeans){
				Menu menu=editorMenus.get(bean.getResourse());
				if(ObjectUtils.isEmpty(menu)){
					menu=editorMenus.get(bean.getChangeRes());
				}
				copyMenu(bean,menu,MenuConstant.MENU_TYPE_MENU);
				menu.setParentId(parseId.get(bean.getParentRes()));
				editorList.add(menu);
				rtn.put(menu.getResourse(),menu.getId());
			}
			menuMapper.updateMenus(editorList);
		}
		return rtn;
	}
	/**
	 * 设置menu值
	 * @param bean
	 * @param menu
	 */
	private void copyMenu(MenuBean bean,Menu menu,Integer type){
		if(ObjectUtils.isEmpty(menu.getIsChange())||menu.getIsChange().intValue()== MenuConstant.MENU_IS_CHANGE_YES){
			menu.setDescription(bean.getDescription());
			menu.setIsValid(bean.getIsValid());
			menu.setLabel(bean.getLabel());
			menu.setSort(bean.getSort());
			menu.setIcon(bean.getIcon());
		}
		menu.setResourse(bean.getResourse());
		menu.setUrl(bean.getUrl());
		menu.setType(type);
	}

	public Map<String,Integer> registerFunction(Set<FunctionBean> functions,Map<String,Integer> parseId){
		Map<String,Integer> rtn=new HashMap<String, Integer>();
		List<String> resources=new ArrayList<String>();
		//变更次源res
		Map<String,String> changeRes=new HashMap<String, String>();
		Map<String,FunctionBean> map=new HashMap<String, FunctionBean>();
		Map<String,Menu> editorMenus=new HashMap<String, Menu>();
		for(FunctionBean bean:functions){
			resources.add(bean.getResourse());
			if(ObjectUtils.isNotEmpty(bean.getChangeRes())){
				resources.add(bean.getChangeRes());
				changeRes.put(bean.getChangeRes(),bean.getResourse());
			}
			map.put(bean.getResourse(),bean);
		}
		if(ObjectUtils.isNotEmpty(resources)) {
			List<Menu> funs = menuMapper.queryMenuByResources(MenuConstant.MENU_TYPE_FUNCTION, resources);
			if(ObjectUtils.isNotEmpty(funs)){
				for(Menu menu:funs){
					map.remove(menu.getResourse());
					editorMenus.put(menu.getResourse(),menu);
					if(changeRes.containsKey(menu.getResourse())){
						map.remove(changeRes.get(menu.getResourse()));
					}
				}
			}
			List<Menu> addMenus=new ArrayList<Menu>();
			if(ObjectUtils.isNotEmpty(map)){
				for(String key:map.keySet()){
					Menu menu = new Menu();
					copyFunction(map.get(key),menu,MenuConstant.MENU_TYPE_FUNCTION);
					addMenus.add(menu);
				}
				menuMapper.saveMenus(addMenus);
				for(Menu menu:addMenus){
					editorMenus.put(menu.getResourse(),menu);
				}
			}
			List<Menu> editorList=new ArrayList<Menu>();
			for(FunctionBean bean:functions){
				Menu menu=editorMenus.get(bean.getResourse());
				if(ObjectUtils.isEmpty(menu)){
					menu=editorMenus.get(bean.getChangeRes());
				}
				copyFunction(bean,menu,MenuConstant.MENU_TYPE_FUNCTION);
				menu.setParentId(parseId.get(bean.getParentRes()));
				editorList.add(menu);
				rtn.put(menu.getResourse(),menu.getId());
			}
			menuMapper.updateMenus(editorList);
		}
		return rtn;
	}
	/**
	 * 设置function值
	 * @param bean
	 * @param menu
	 */
	private void copyFunction(FunctionBean bean,Menu menu,Integer type){
		if(ObjectUtils.isEmpty(menu.getIsChange())||menu.getIsChange().intValue()== MenuConstant.MENU_IS_CHANGE_YES){
			menu.setDescription(bean.getDescription());
			menu.setSort(bean.getSort());
			menu.setIcon(bean.getIcon());
			menu.setIsValid(bean.getIsValid());
			menu.setLabel(bean.getLabel());
		}
		menu.setResourse(bean.getResourse());
		menu.setUrl(bean.getUrl());
		menu.setType(type);
	}
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
