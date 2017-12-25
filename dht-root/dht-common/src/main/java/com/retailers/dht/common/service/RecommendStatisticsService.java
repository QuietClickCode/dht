
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.RecommendStatistics;
import java.util.Map;
/**
 * 描述：推荐返现金额Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-25 22:18:33
 */
public interface RecommendStatisticsService {

	/**
	 * 添加推荐返现金额
	 * @param recommendStatistics
	 * @return
	 * @author zhongp
	 * @date 2017-12-25 22:18:33
	 */
	public boolean saveRecommendStatistics(RecommendStatistics recommendStatistics);
	/**
	 * 编辑推荐返现金额
	 * @param recommendStatistics
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateRecommendStatistics(RecommendStatistics recommendStatistics);
	/**
	 * 根据id查询推荐返现金额
	 * @param rsId
	 * @return recommendStatistics
	 * @author zhongp
	 * @date 2017-12-25 22:18:33
	 */
	public RecommendStatistics queryRecommendStatisticsByRsId(Long rsId);
	/**
	 * 查询推荐返现金额列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-12-25 22:18:33
	 */
	public Pagination<RecommendStatistics> queryRecommendStatisticsList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据rsId删除推荐返现金额
	 * @param rsId
	 * @return
	 * @author zhongp
	 * @date 2017-12-25 22:18:33
	 */
	public boolean deleteRecommendStatisticsByRsId(Long rsId);

}


