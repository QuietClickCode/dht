
package com.retailers.hnc.common.service;
import com.retailers.hnc.common.vo.OpeningVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.hnc.common.entity.Opening;

import java.util.List;
import java.util.Map;
/**
 * 描述：开盘表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-22 16:36:52
 */
public interface OpeningService {

	/**
	 * 添加开盘表
	 * @param opening
	 * @return
	 * @author fanghui
	 * @date 2017-12-22 16:36:52
	 */
	public boolean saveOpening(Opening opening,String floors);
	/**
	 * 编辑开盘表
	 * @param opening
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateOpening(Opening opening,String floors);
	/**
	 * 根据id查询开盘表
	 * @param oid
	 * @return opening
	 * @author fanghui
	 * @date 2017-12-22 16:36:52
	 */
	public Opening queryOpeningByOid(Long oid);
	/**
	 * 查询开盘表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-12-22 16:36:52
	 */
	public Pagination<Opening> queryOpeningList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据oid删除开盘表
	 * @param oid
	 * @return
	 * @author fanghui
	 * @date 2017-12-22 16:36:52
	 */
	public boolean deleteOpeningByOid(Long oid);

	public Pagination<OpeningVo> queryOpeningVoList(Map<String, Object> params, int pageNo, int pageSize);

	public List<OpeningVo> queryOFrelByOid(Long oid);

	public Opening queryEarlyOpening();
}


