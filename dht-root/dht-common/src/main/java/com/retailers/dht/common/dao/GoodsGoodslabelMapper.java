package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsGoodslabel;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：商品与标签关系表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-09 13:43:52
 */
public interface GoodsGoodslabelMapper {

	/**
	 * 添加商品与标签关系表
	 * @param goodsGoodslabel
	 * @return
	 * @author fanghui
	 * @date 2017-10-09 13:43:52
	 */
	public int saveGoodsGoodslabel(GoodsGoodslabel goodsGoodslabel);
	/**
	 * 编辑商品与标签关系表
	 * @param goodsGoodslabel
	 * @return
	 * @author fanghui
	 * @date 2017-10-09 13:43:52
	 */
	public int updateGoodsGoodslabel(GoodsGoodslabel goodsGoodslabel);
	/**
	 * 根据GlrId删除商品与标签关系表
	 * @param glrId
	 * @return
	 * @author fanghui
	 * @date 2017-10-09 13:43:52
	 */
	public int deleteGoodsGoodslabelByGlrId(Long glrId);
	/**
	 * 根据GlrId查询商品与标签关系表
	 * @param glrId
	 * @return
	 * @author fanghui
	 * @date 2017-10-09 13:43:52
	 */
	public GoodsGoodslabel queryGoodsGoodslabelByGlrId(Long glrId);
	/**
	 * 查询商品与标签关系表列表
	 * @param pagination 分页对象
	 * @return  商品与标签关系表列表
	 * @author fanghui
	 * @date 2017-10-09 13:43:52
	 */
	public List<GoodsGoodslabel> queryGoodsGoodslabelList(Pagination<GoodsGoodslabel> pagination);

}
