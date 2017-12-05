
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsIsbuysp;
import java.util.Map;
/**
 * 描述：是否购买活动商品表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-05 15:09:07
 */
public interface GoodsIsbuyspService {

	/**
	 * 添加是否购买活动商品表
	 * @param goodsIsbuysp
	 * @return
	 * @author fanghui
	 * @date 2017-12-05 15:09:07
	 */
	public boolean saveGoodsIsbuysp(GoodsIsbuysp goodsIsbuysp);
	/**
	 * 编辑是否购买活动商品表
	 * @param goodsIsbuysp
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsIsbuysp(GoodsIsbuysp goodsIsbuysp);
	/**
	 * 根据id查询是否购买活动商品表
	 * @param ibsp
	 * @return goodsIsbuysp
	 * @author fanghui
	 * @date 2017-12-05 15:09:07
	 */
	public GoodsIsbuysp queryGoodsIsbuyspByIbsp(Long ibsp);
	/**
	 * 查询是否购买活动商品表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-12-05 15:09:07
	 */
	public Pagination<GoodsIsbuysp> queryGoodsIsbuyspList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据ibsp删除是否购买活动商品表
	 * @param ibsp
	 * @return
	 * @author fanghui
	 * @date 2017-12-05 15:09:07
	 */
	public boolean deleteGoodsIsbuyspByIbsp(Long ibsp);

}


