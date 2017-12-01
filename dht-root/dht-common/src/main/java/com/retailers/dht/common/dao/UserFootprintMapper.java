package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.UserFootprint;
import com.retailers.dht.common.vo.GoodsVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：足迹表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-30 09:50:48
 */
public interface UserFootprintMapper {

	/**
	 * 添加足迹表
	 * @param userFootprint
	 * @return
	 * @author fanghui
	 * @date 2017-11-30 09:50:48
	 */
	public int saveUserFootprint(UserFootprint userFootprint);
	/**
	 * 编辑足迹表
	 * @param userFootprint
	 * @return
	 * @author fanghui
	 * @date 2017-11-30 09:50:48
	 */
	public int updateUserFootprint(UserFootprint userFootprint);
	/**
	 * 根据UfId删除足迹表
	 * @param ufId
	 * @return
	 * @author fanghui
	 * @date 2017-11-30 09:50:48
	 */
	public int deleteUserFootprintByUfId(Long ufId);
	/**
	 * 根据UfId查询足迹表
	 * @param ufId
	 * @return
	 * @author fanghui
	 * @date 2017-11-30 09:50:48
	 */
	public UserFootprint queryUserFootprintByUfId(Long ufId);
	/**
	 * 查询足迹表列表
	 * @param pagination 分页对象
	 * @return  足迹表列表
	 * @author fanghui
	 * @date 2017-11-30 09:50:48
	 */
	public List<UserFootprint> queryUserFootprintList(Pagination<UserFootprint> pagination);

	public List<UserFootprint> queryTodayDate(Long uid);

	public List<GoodsVo> queryUserFootprintVoList(Pagination<GoodsVo> pagination);

	public int delallfootprint(Long uid);
}
