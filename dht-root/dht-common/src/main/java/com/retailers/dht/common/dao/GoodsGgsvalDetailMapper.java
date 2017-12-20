package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsDetail;
import com.retailers.dht.common.entity.GoodsGgsvalDetail;
import com.retailers.dht.common.vo.GoodsInventoryVo;
import com.retailers.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

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
	public int saveGoodsGgsvalDetails(@Param("list") List<GoodsGgsvalDetail> list);
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

	public List<GoodsInventoryVo> queryGoodsInventoryLists(Pagination<GoodsInventoryVo> pagination);

	public int deleteGoodsGgsvalDetailByGid(@Param("gid") Long gid);

	public int editGoodsInventorys(@Param("list") List<GoodsDetail> list);

}
