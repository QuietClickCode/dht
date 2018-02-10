
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.FamerUser;

import java.util.List;
import java.util.Map;
/**
 * 描述：农夫用户关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-02-07 17:16:31
 */
public interface FamerUserService {

	/**
	 * 添加农夫用户关系表
	 * @param famerUser
	 * @return
	 * @author fanghui
	 * @date 2018-02-07 17:16:31
	 */
	public boolean saveFamerUser(FamerUser famerUser);
	/**
	 * 编辑农夫用户关系表
	 * @param famerUser
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateFamerUser(FamerUser famerUser);
	/**
	 * 根据id查询农夫用户关系表
	 * @param fuId
	 * @return famerUser
	 * @author fanghui
	 * @date 2018-02-07 17:16:31
	 */
	public FamerUser queryFamerUserByFuId(Long fuId);
	/**
	 * 查询农夫用户关系表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2018-02-07 17:16:31
	 */
	public Pagination<FamerUser> queryFamerUserList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据fuId删除农夫用户关系表
	 * @param fuId
	 * @return
	 * @author fanghui
	 * @date 2018-02-07 17:16:31
	 */
	public boolean deleteFamerUserByFuId(Long fuId);

	public Pagination<String> queryFamerUserImgUrlList(Map<String, Object> params, int pageNo, int pageSize);
}


