
package com.retailers.dht.common.service;

import com.retailers.auth.vo.ZTreeVo;
import com.retailers.dht.common.entity.GoodsClassification;
import com.retailers.dht.common.vo.GoodsClassificationVo;
import com.retailers.mybatis.pagination.Pagination;

import java.util.List;
import java.util.Map;

/**
 * 描述：商品子类表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-09-29 14:58:38
 */
public interface GoodsClassificationService {

	/**
	 * 添加商品子类表
	 * @param goodsClassification
	 * @return
	 * @author fanghui
	 * @date 2017-09-29 14:58:38
	 */
	public boolean saveGoodsClassification(GoodsClassification goodsClassification);
	/**
	 * 编辑商品子类表
	 * @param goodsClassification
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsClassification(GoodsClassification goodsClassification);
	/**
	 * 根据id查询商品子类表
	 * @param ggId
	 * @return goodsClassification
	 * @author fanghui
	 * @date 2017-09-29 14:58:38
	 */
	public GoodsClassification queryGoodsClassificationByGgId(Long ggId);
	/**
	 * 查询商品子类表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-09-29 14:58:38
	 */
	public Pagination<GoodsClassification> queryGoodsClassificationList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据ggId删除商品子类表
	 * @param ggId
	 * @return
	 * @author fanghui
	 * @date 2017-09-29 14:58:38
	 */
	public boolean deleteGoodsClassificationByGgId(Long ggId);

	public List<GoodsClassificationVo> queryGoodsClassificationTree();

	public List<GoodsClassificationVo> queryGoodsClassificationNode(List<Long> ggIds);

	/**
	 * 取得商品子类
	 * @param couponId 优惠卷id
	 * @param gids 商品大类Ids
	 * @return
	 */
	public List<ZTreeVo> queryAllGoodsClassificationByGtId(Long couponId, List<Long> gids);

	public List<GoodsClassificationVo> queryGoodsClassificationListByParentId(Long ggId);
}


