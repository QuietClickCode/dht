
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsSpecification;
import java.util.Map;
/**
 * 描述：商品规格表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-11 22:19:21
 */
public interface GoodsSpecificationService {

	/**
	 * 添加商品规格表
	 * @param goodsSpecification
	 * @return
	 * @author fanghui
	 * @date 2017-10-11 22:19:21
	 */
	public GoodsSpecification saveGoodsSpecification(GoodsSpecification goodsSpecification);
	/**
	 * 编辑商品规格表
	 * @param goodsSpecification
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsSpecification(GoodsSpecification goodsSpecification);
	/**
	 * 根据id查询商品规格表
	 * @param gsId
	 * @return goodsSpecification
	 * @author fanghui
	 * @date 2017-10-11 22:19:21
	 */
	public GoodsSpecification queryGoodsSpecificationByGsId(Long gsId);
	/**
	 * 查询商品规格表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-11 22:19:21
	 */
	public Pagination<GoodsSpecification> queryGoodsSpecificationList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gsId删除商品规格表
	 * @param gsId
	 * @return
	 * @author fanghui
	 * @date 2017-10-11 22:19:21
	 */
	public boolean deleteGoodsSpecificationByGsId(Long gsId);

}


