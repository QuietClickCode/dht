package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.RecommendStatistics;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：推荐返现金额DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-25 22:18:33
 */
public interface RecommendStatisticsMapper {

	/**
	 * 添加推荐返现金额
	 * @param recommendStatistics
	 * @return
	 * @author zhongp
	 * @date 2017-12-25 22:18:33
	 */
	public int saveRecommendStatistics(RecommendStatistics recommendStatistics);

	/**
	 * 批量添加推广奖励
	 * @param recommendStatistics
	 * @return
	 */
	public int saveRecommendStatisticss(List<RecommendStatistics> recommendStatistics);
	/**
	 * 编辑推荐返现金额
	 * @param recommendStatistics
	 * @return
	 * @author zhongp
	 * @date 2017-12-25 22:18:33
	 */
	public int updateRecommendStatistics(RecommendStatistics recommendStatistics);
	/**
	 * 根据RsId删除推荐返现金额
	 * @param rsId
	 * @return
	 * @author zhongp
	 * @date 2017-12-25 22:18:33
	 */
	public int deleteRecommendStatisticsByRsId(Long rsId);
	/**
	 * 根据RsId查询推荐返现金额
	 * @param rsId
	 * @return
	 * @author zhongp
	 * @date 2017-12-25 22:18:33
	 */
	public RecommendStatistics queryRecommendStatisticsByRsId(Long rsId);
	/**
	 * 查询推荐返现金额列表
	 * @param pagination 分页对象
	 * @return  推荐返现金额列表
	 * @author zhongp
	 * @date 2017-12-25 22:18:33
	 */
	public List<RecommendStatistics> queryRecommendStatisticsList(Pagination<RecommendStatistics> pagination);

}
