package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsIsbuysp;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：是否购买活动商品表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-05 15:09:07
 */
public interface GoodsIsbuyspMapper {

	/**
	 * 添加是否购买活动商品表
	 * @param goodsIsbuysp
	 * @return
	 * @author fanghui
	 * @date 2017-12-05 15:09:07
	 */
	public int saveGoodsIsbuysp(GoodsIsbuysp goodsIsbuysp);
	/**
	 * 编辑是否购买活动商品表
	 * @param goodsIsbuysp
	 * @return
	 * @author fanghui
	 * @date 2017-12-05 15:09:07
	 */
	public int updateGoodsIsbuysp(GoodsIsbuysp goodsIsbuysp);
	/**
	 * 根据Ibsp删除是否购买活动商品表
	 * @param ibsp
	 * @return
	 * @author fanghui
	 * @date 2017-12-05 15:09:07
	 */
	public int deleteGoodsIsbuyspByIbsp(Long ibsp);
	/**
	 * 根据Ibsp查询是否购买活动商品表
	 * @param ibsp
	 * @return
	 * @author fanghui
	 * @date 2017-12-05 15:09:07
	 */
	public GoodsIsbuysp queryGoodsIsbuyspByIbsp(Long ibsp);
	/**
	 * 查询是否购买活动商品表列表
	 * @param pagination 分页对象
	 * @return  是否购买活动商品表列表
	 * @author fanghui
	 * @date 2017-12-05 15:09:07
	 */
	public List<GoodsIsbuysp> queryGoodsIsbuyspList(Pagination<GoodsIsbuysp> pagination);

}
