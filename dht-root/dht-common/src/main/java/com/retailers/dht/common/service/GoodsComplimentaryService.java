
package com.retailers.dht.common.service;
import com.retailers.dht.common.vo.GoodsComplimentaryVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsComplimentary;
import java.util.Map;
/**
 * 描述：赠品表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-27 14:58:13
 */
public interface GoodsComplimentaryService {

	/**
	 * 添加赠品表
	 * @param goodsComplimentary
	 * @return
	 * @author fanghui
	 * @date 2017-10-27 14:58:13
	 */
	public boolean saveGoodsComplimentary(GoodsComplimentary goodsComplimentary);
	/**
	 * 编辑赠品表
	 * @param goodsComplimentary
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsComplimentary(GoodsComplimentary goodsComplimentary);
	/**
	 * 根据id查询赠品表
	 * @param gcId
	 * @return goodsComplimentary
	 * @author fanghui
	 * @date 2017-10-27 14:58:13
	 */
	public GoodsComplimentary queryGoodsComplimentaryByGcId(Long gcId);
	/**
	 * 查询赠品表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-27 14:58:13
	 */
	public Pagination<GoodsComplimentaryVo> queryGoodsComplimentaryList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gcId删除赠品表
	 * @param gcId
	 * @return
	 * @author fanghui
	 * @date 2017-10-27 14:58:13
	 */
	public boolean deleteGoodsComplimentaryByGcId(Long gcId);

}


