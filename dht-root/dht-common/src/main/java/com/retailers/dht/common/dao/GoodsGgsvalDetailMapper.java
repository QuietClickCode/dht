package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsGgsvalDetail;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：商品与规格值详情表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-20 09:52:11
 */
public interface GoodsGgsvalDetailMapper {

	/**
	 * 添加商品与规格值详情表
	 * @param goodsGgsvalDetail
	 * @return
	 * @author fanghui
	 * @date 2017-10-20 09:52:11
	 */
	public int saveGoodsGgsvalDetail(GoodsGgsvalDetail goodsGgsvalDetail);
	/**
	 * 编辑商品与规格值详情表
	 * @param goodsGgsvalDetail
	 * @return
	 * @author fanghui
	 * @date 2017-10-20 09:52:11
	 */
	public int updateGoodsGgsvalDetail(GoodsGgsvalDetail goodsGgsvalDetail);
	/**
	 * 根据GgdId删除商品与规格值详情表
	 * @param ggdId
	 * @return
	 * @author fanghui
	 * @date 2017-10-20 09:52:11
	 */
	public int deleteGoodsGgsvalDetailByGgdId(Long ggdId);
	/**
	 * 根据GgdId查询商品与规格值详情表
	 * @param ggdId
	 * @return
	 * @author fanghui
	 * @date 2017-10-20 09:52:11
	 */
	public GoodsGgsvalDetail queryGoodsGgsvalDetailByGgdId(Long ggdId);
	/**
	 * 查询商品与规格值详情表列表
	 * @param pagination 分页对象
	 * @return  商品与规格值详情表列表
	 * @author fanghui
	 * @date 2017-10-20 09:52:11
	 */
	public List<GoodsGgsvalDetail> queryGoodsGgsvalDetailList(Pagination<GoodsGgsvalDetail> pagination);

}
