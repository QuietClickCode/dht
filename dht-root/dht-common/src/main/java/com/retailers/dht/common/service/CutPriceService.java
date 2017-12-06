
package com.retailers.dht.common.service;
import com.retailers.dht.common.vo.CutPriceVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.CutPrice;

import java.util.List;
import java.util.Map;
/**
 * 描述：砍价表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-05 16:14:43
 */
public interface CutPriceService {

	/**
	 * 添加砍价表
	 * @param cutPrice
	 * @return
	 * @author fanghui
	 * @date 2017-12-05 16:14:43
	 */
	public boolean saveCutPrice(CutPrice cutPrice);
	/**
	 * 编辑砍价表
	 * @param cutPrice
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateCutPrice(CutPrice cutPrice);
	/**
	 * 根据id查询砍价表
	 * @param cpId
	 * @return cutPrice
	 * @author fanghui
	 * @date 2017-12-05 16:14:43
	 */
	public CutPrice queryCutPriceByCpId(Long cpId);
	/**
	 * 查询砍价表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-12-05 16:14:43
	 */
	public Pagination<CutPrice> queryCutPriceList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据cpId删除砍价表
	 * @param cpId
	 * @return
	 * @author fanghui
	 * @date 2017-12-05 16:14:43
	 */
	public boolean deleteCutPriceByCpId(Long cpId);

	public List<CutPriceVo> queryCutPriceTree();
}


