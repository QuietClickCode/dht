package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsBrand;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：商品品牌表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-07 09:42:29
 */
public interface GoodsBrandMapper {

	/**
	 * 添加商品品牌表
	 * @param goodsBrand
	 * @return
	 * @author fanghui
	 * @date 2017-10-07 09:42:29
	 */
	public int saveGoodsBrand(GoodsBrand goodsBrand);
	/**
	 * 编辑商品品牌表
	 * @param goodsBrand
	 * @return
	 * @author fanghui
	 * @date 2017-10-07 09:42:29
	 */
	public int updateGoodsBrand(GoodsBrand goodsBrand);
	/**
	 * 根据GbId删除商品品牌表
	 * @param gbId
	 * @return
	 * @author fanghui
	 * @date 2017-10-07 09:42:29
	 */
	public int deleteGoodsBrandByGbId(Long gbId);
	/**
	 * 根据GbId查询商品品牌表
	 * @param gbId
	 * @return
	 * @author fanghui
	 * @date 2017-10-07 09:42:29
	 */
	public GoodsBrand queryGoodsBrandByGbId(Long gbId);
	/**
	 * 查询商品品牌表列表
	 * @param pagination 分页对象
	 * @return  商品品牌表列表
	 * @author fanghui
	 * @date 2017-10-07 09:42:29
	 */
	public List<GoodsBrand> queryGoodsBrandList(Pagination<GoodsBrand> pagination);

}
