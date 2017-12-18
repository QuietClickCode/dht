
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsIsbuycp;
import java.util.Map;
/**
 * 描述：是否购买砍价商品表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-09 10:10:14
 */
public interface GoodsIsbuycpService {

	/**
	 * 添加是否购买砍价商品表
	 * @param goodsIsbuycp
	 * @return
	 * @author fanghui
	 * @date 2017-12-09 10:10:14
	 */
	public boolean saveGoodsIsbuycp(GoodsIsbuycp goodsIsbuycp);
	/**
	 * 编辑是否购买砍价商品表
	 * @param goodsIsbuycp
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsIsbuycp(GoodsIsbuycp goodsIsbuycp);
	/**
	 * 根据id查询是否购买砍价商品表
	 * @param ibcp
	 * @return goodsIsbuycp
	 * @author fanghui
	 * @date 2017-12-09 10:10:14
	 */
	public GoodsIsbuycp queryGoodsIsbuycpByIbcp(Long ibcp);
	/**
	 * 查询是否购买砍价商品表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-12-09 10:10:14
	 */
	public Pagination<GoodsIsbuycp> queryGoodsIsbuycpList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据ibcp删除是否购买砍价商品表
	 * @param ibcp
	 * @return
	 * @author fanghui
	 * @date 2017-12-09 10:10:14
	 */
	public boolean deleteGoodsIsbuycpByIbcp(Long ibcp);

	public boolean queryIsBuycpByGdcpId(Long gdcpId,Long uid);

}


