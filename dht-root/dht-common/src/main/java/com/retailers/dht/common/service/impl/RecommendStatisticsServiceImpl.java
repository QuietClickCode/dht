
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.RecommendStatistics;
import com.retailers.dht.common.dao.RecommendStatisticsMapper;
import com.retailers.dht.common.service.RecommendStatisticsService;
import com.retailers.dht.common.vo.RecommendStatisticsVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：推荐返现金额Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-25 22:18:33
 */
@Service("recommendstatisticsService")
public class RecommendStatisticsServiceImpl implements RecommendStatisticsService {
	@Autowired
	private RecommendStatisticsMapper recommendStatisticsMapper;
	public boolean saveRecommendStatistics(RecommendStatistics recommendStatistics) {
		int status = recommendStatisticsMapper.saveRecommendStatistics(recommendStatistics);
		return status == 1 ? true : false;
	}
	public boolean updateRecommendStatistics(RecommendStatistics recommendStatistics) {
		int status = recommendStatisticsMapper.updateRecommendStatistics(recommendStatistics);
		return status == 1 ? true : false;
	}
	public RecommendStatistics queryRecommendStatisticsByRsId(Long rsId) {
		return recommendStatisticsMapper.queryRecommendStatisticsByRsId(rsId);
	}

	public Pagination<RecommendStatistics> queryRecommendStatisticsList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<RecommendStatistics> page = new Pagination<RecommendStatistics>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<RecommendStatistics> list = recommendStatisticsMapper.queryRecommendStatisticsList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteRecommendStatisticsByRsId(Long rsId) {
		int status = recommendStatisticsMapper.deleteRecommendStatisticsByRsId(rsId);
		return status == 1 ? true : false;
	}

	public Pagination<RecommendStatisticsVo> queryRecommendLists(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<RecommendStatisticsVo> page = new Pagination<RecommendStatisticsVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<RecommendStatisticsVo> list = recommendStatisticsMapper.queryRecommendLists(page);
		page.setData(list);
		return page;
	}
}

