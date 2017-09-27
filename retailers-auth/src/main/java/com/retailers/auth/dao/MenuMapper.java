package com.retailers.auth.dao;
import com.retailers.auth.entity.Menu;
import com.retailers.auth.vo.MenuVo;
import org.apache.ibatis.annotations.Param;
import java.util.List;


/**
 * 描述：MenuDAO
 * @author zhongp
 * @version 1.0
 * @since 1.0
 * @date 2016-06-07
 */
public interface MenuMapper {

	/**
	 * 添加Menu
	 * @author zhongp
	 * @date 2016-06-07
	 */
	public int saveMenu(Menu menu);
	/**
	 * 编辑Menu
	 * @author zhongp
	 * @date 2016-06-07
	 */
	public int updateMenu(Menu menu);
	/**
	 * 根据Id删除Menu
	 * @author zhongp
	 * @date 2016-06-07
	 */
	public int deleteMenuById(Integer id);
	/**
	 * 根据Id查询Menu
	 * @author zhongp
	 * @date 2016-06-07
	 */
	public Menu queryMenuById(Integer id);

	/**
	 * 根据资源路径取得菜单
	 * @param resourse
	 * @return
	 */
	public Menu queryMenuByRes(String resourse);
	/**
	 * 查询Menu列表
	 * @author zhongp
	 * @date 2016-06-07
	 */
	/*public List<Menu> queryMenuList(Pagination<Menu> pagination);*/
	/**
	 * 根据菜单 id或资源路径取得菜单值
	 * @param id
	 * @param resourse
	 * @return
	 */
	public List<Menu> getAllMenus(@Param("id") Integer id, @Param("resourse") String resourse);

	/**
	 *
	 * @return
	 */
	public List<MenuVo> queryMenuTree();

	/**
	 * 取得所有菜单
	 * @param menuId
	 * @return
	 */
	public List<MenuVo> queryMenuNode(Long menuId);

	/**
	 * 删除资源（逻辑删除)
	 * @param menuId
	 * @return
	 */
	public long removeMenu(Long menuId);

	/**
	 * 取得当前节点菜单
	 * @param menuId
	 * @return
	 */
	public MenuVo queryMenuByid(Long menuId);

	/**
	 * 编辑Menu
	 * @author zhongp
	 * @date 2016-06-07
	 */
	public int editResource(MenuVo menu);

	/**
	 * 取得所有需要权限的url
	 * @return
	 */
	public List<String> queryNeedPermissUrl();

	/**
	 * 取得用户有权限的菜单按钮
	 * @param userId
	 * @return
	 */
	public List<MenuVo> queryUserMenu(@Param("userId") Long userId, @Param("all") Boolean all);

	/**
	 * 取得所有菜单
	 * @return
	 */
	public List<MenuVo> queryAllMenu(@Param("all") Boolean all);

}
