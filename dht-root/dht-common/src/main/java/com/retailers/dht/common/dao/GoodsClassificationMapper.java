package com.retailers.dht.common.dao;

import com.retailers.dht.common.entity.GoodsClassification;
import com.retailers.dht.common.vo.GoodsClassificationVo;
import com.retailers.mybatis.pagination.Pagination;

import java.util.List;

/**
 * 描述：商品子类表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-09-29 14:58:38
 */
public interface GoodsClassificationMapper {

	/**
	 * 添加商品子类表
	 * @param goodsClassification
	 * @return
	 * @author fanghui
	 * @date 2017-09-29 14:58:38
	 */
	public int saveGoodsClassification(GoodsClassification goodsClassification);
	/**
	 * 编辑商品子类表
	 * @param goodsClassification
	 * @return
	 * @author fanghui
	 * @date 2017-09-29 14:58:38
	 */
	public int updateGoodsClassification(GoodsClassification goodsClassification);
	/**
	 * 根据GgId删除商品子类表
	 * @param ggId
	 * @return
	 * @author fanghui
	 * @date 2017-09-29 14:58:38
	 */
	public int deleteGoodsClassificationByGgId(Long ggId);
	/**
	 * 根据GgId查询商品子类表
	 * @param ggId
	 * @return
	 * @author fanghui
	 * @date 2017-09-29 14:58:38
	 */
	public GoodsClassification queryGoodsClassificationByGgId(Long ggId);
	/**
	 * 查询商品子类表列表
	 * @param pagination 分页对象
	 * @return  商品子类表列表
	 * @author fanghui
	 * @date 2017-09-29 14:58:38
	 */
	public List<GoodsClassification> queryGoodsClassificationList(Pagination<GoodsClassification> pagination);

	public List<GoodsClassificationVo> queryGoodsClassificationTree();

	public List<GoodsClassificationVo> queryGoodsClassificationNode(String ggId);

}
