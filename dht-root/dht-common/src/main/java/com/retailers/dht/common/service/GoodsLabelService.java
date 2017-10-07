
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsLabel;
import java.util.Map;
/**
 * 描述：商品标签表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-07 12:03:22
 */
public interface GoodsLabelService {

	/**
	 * 添加商品标签表
	 * @param goodsLabel
	 * @return
	 * @author fanghui
	 * @date 2017-10-07 12:03:22
	 */
	public boolean saveGoodsLabel(GoodsLabel goodsLabel);
	/**
	 * 编辑商品标签表
	 * @param goodsLabel
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsLabel(GoodsLabel goodsLabel);
	/**
	 * 根据id查询商品标签表
	 * @param glId
	 * @return goodsLabel
	 * @author fanghui
	 * @date 2017-10-07 12:03:22
	 */
	public GoodsLabel queryGoodsLabelByGlId(Long glId);
	/**
	 * 查询商品标签表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-07 12:03:22
	 */
	public Pagination<GoodsLabel> queryGoodsLabelList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据glId删除商品标签表
	 * @param glId
	 * @return
	 * @author fanghui
	 * @date 2017-10-07 12:03:22
	 */
	public boolean deleteGoodsLabelByGlId(Long glId);

}


