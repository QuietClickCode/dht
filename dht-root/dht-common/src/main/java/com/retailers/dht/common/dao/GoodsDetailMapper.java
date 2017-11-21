package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsDetail;
import com.retailers.dht.common.vo.GoodsDetailVo;
import com.retailers.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 描述：商品详情表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-21 10:51:10
 */
public interface GoodsDetailMapper {

	/**
	 * 添加商品详情表
	 * @param goodsDetail
	 * @return
	 * @author fanghui
	 * @date 2017-10-21 10:51:10
	 */
	public int saveGoodsDetail(GoodsDetail goodsDetail);
	/**
	 * 编辑商品详情表
	 * @param goodsDetail
	 * @return
	 * @author fanghui
	 * @date 2017-10-21 10:51:10
	 */
	public int updateGoodsDetail(GoodsDetail goodsDetail);
	/**
	 * 根据GdId删除商品详情表
	 * @param gdId
	 * @return
	 * @author fanghui
	 * @date 2017-10-21 10:51:10
	 */
	public int deleteGoodsDetailByGdId(Long gdId);
	/**
	 * 根据GdId查询商品详情表
	 * @param gdId
	 * @return
	 * @author fanghui
	 * @date 2017-10-21 10:51:10
	 */
	public GoodsDetailVo queryGoodsDetailByGdId(Long gdId);
	/**
	 * 查询商品详情表列表
	 * @param pagination 分页对象
	 * @return  商品详情表列表
	 * @author fanghui
	 * @date 2017-10-21 10:51:10
	 */
	public List<GoodsDetail> queryGoodsDetailList(Pagination<GoodsDetail> pagination);

	public List<GoodsDetailVo> queryGoodsDetailOnce(Long gid);

	public List<GoodsDetailVo> queryGoodsDetailVoLists(@Param("params") Map params);
}
