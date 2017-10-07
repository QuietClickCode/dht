
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsGoodslabel;
import java.util.Map;
/**
 * 描述：商品与标签关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-07 12:04:20
 */
public interface GoodsGoodslabelService {

	/**
	 * 添加商品与标签关系表
	 * @param goodsGoodslabel
	 * @return
	 * @author fanghui
	 * @date 2017-10-07 12:04:20
	 */
	public boolean saveGoodsGoodslabel(GoodsGoodslabel goodsGoodslabel);
	/**
	 * 编辑商品与标签关系表
	 * @param goodsGoodslabel
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsGoodslabel(GoodsGoodslabel goodsGoodslabel);
	/**
	 * 根据id查询商品与标签关系表
	 * @param glrId
	 * @return goodsGoodslabel
	 * @author fanghui
	 * @date 2017-10-07 12:04:20
	 */
	public GoodsGoodslabel queryGoodsGoodslabelByGlrId(Long glrId);
	/**
	 * 查询商品与标签关系表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-07 12:04:20
	 */
	public Pagination<GoodsGoodslabel> queryGoodsGoodslabelList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据glrId删除商品与标签关系表
	 * @param glrId
	 * @return
	 * @author fanghui
	 * @date 2017-10-07 12:04:20
	 */
	public boolean deleteGoodsGoodslabelByGlrId(Long glrId);

}


