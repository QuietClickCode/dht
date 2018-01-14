
package com.retailers.dht.common.service;

import com.retailers.auth.vo.ZTreeVo;
import com.retailers.dht.common.entity.GoodsClassification;
import com.retailers.dht.common.vo.GoodsClassificationVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.exception.AppException;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
	 * 取得商品类型树
	 * @param cpId 优惠卷id
	 * @return
	 */
	public List<ZTreeVo> querGoodsClassificationTree(Long id,Long cpId);

	public List<GoodsClassificationVo> queryGoodsClassificationListByParentId(Long ggId);

	public List<GoodsClassification> selectAllGClassList();
	/**
	 * 取得传入节点的所有父级节点（包括当前 节点）
	 * @param curNode 当前节点
	 * @return
	 */
	public List<Long> queryGoodsClassificationParents(Long curNode);

	/**
	 * 取得当前节点下的所有子节点
	 * @param curNode 当前节点
	 * @return
	 */
	public List<Long> queryGoodsClassificationChilds(Long curNode);

	/**
	 * 取得商品种类顶层
	 * @return
	 */
	public List<GoodsClassification> queryParent();

}


