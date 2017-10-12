package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsSpecification;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：商品规格表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-11 22:19:21
 */
public interface GoodsSpecificationMapper {

	/**
	 * 添加商品规格表
	 * @param goodsSpecification
	 * @return
	 * @author fanghui
	 * @date 2017-10-11 22:19:21
	 */
	public int saveGoodsSpecification(GoodsSpecification goodsSpecification);
	/**
	 * 编辑商品规格表
	 * @param goodsSpecification
	 * @return
	 * @author fanghui
	 * @date 2017-10-11 22:19:21
	 */
	public int updateGoodsSpecification(GoodsSpecification goodsSpecification);
	/**
	 * 根据GsId删除商品规格表
	 * @param gsId
	 * @return
	 * @author fanghui
	 * @date 2017-10-11 22:19:21
	 */
	public int deleteGoodsSpecificationByGsId(Long gsId);
	/**
	 * 根据GsId查询商品规格表
	 * @param gsId
	 * @return
	 * @author fanghui
	 * @date 2017-10-11 22:19:21
	 */
	public GoodsSpecification queryGoodsSpecificationByGsId(Long gsId);
	/**
	 * 查询商品规格表列表
	 * @param pagination 分页对象
	 * @return  商品规格表列表
	 * @author fanghui
	 * @date 2017-10-11 22:19:21
	 */
	public List<GoodsSpecification> queryGoodsSpecificationList(Pagination<GoodsSpecification> pagination);

}
