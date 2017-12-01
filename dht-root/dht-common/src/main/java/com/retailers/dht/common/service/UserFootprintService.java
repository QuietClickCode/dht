
package com.retailers.dht.common.service;
import com.retailers.dht.common.vo.GoodsVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.UserFootprint;

import java.util.List;
import java.util.Map;
/**
 * 描述：足迹表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-30 09:50:48
 */
public interface UserFootprintService {

	/**
	 * 添加足迹表
	 * @param userFootprint
	 * @return
	 * @author fanghui
	 * @date 2017-11-30 09:50:48
	 */
	public boolean saveUserFootprint(UserFootprint userFootprint);
	/**
	 * 编辑足迹表
	 * @param userFootprint
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateUserFootprint(UserFootprint userFootprint);
	/**
	 * 根据id查询足迹表
	 * @param ufId
	 * @return userFootprint
	 * @author fanghui
	 * @date 2017-11-30 09:50:48
	 */
	public UserFootprint queryUserFootprintByUfId(Long ufId);
	/**
	 * 查询足迹表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-11-30 09:50:48
	 */
	public Pagination<UserFootprint> queryUserFootprintList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据ufId删除足迹表
	 * @param ufId
	 * @return
	 * @author fanghui
	 * @date 2017-11-30 09:50:48
	 */
	public boolean deleteUserFootprintByUfId(Long ufId);

	public List<UserFootprint> queryTodayDate(Long uid);

	public List<GoodsVo> queryUserFootprintVoList(Map<String, Object> params, int pageNo, int pageSize);

	public boolean delallfootprint(Long uid);
}


