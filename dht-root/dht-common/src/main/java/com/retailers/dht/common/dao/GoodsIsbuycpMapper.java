package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsIsbuycp;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：是否购买砍价商品表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-09 10:10:14
 */
public interface GoodsIsbuycpMapper {

	/**
	 * 添加是否购买砍价商品表
	 * @param goodsIsbuycp
	 * @return
	 * @author fanghui
	 * @date 2017-12-09 10:10:14
	 */
	public int saveGoodsIsbuycp(GoodsIsbuycp goodsIsbuycp);
	/**
	 * 编辑是否购买砍价商品表
	 * @param goodsIsbuycp
	 * @return
	 * @author fanghui
	 * @date 2017-12-09 10:10:14
	 */
	public int updateGoodsIsbuycp(GoodsIsbuycp goodsIsbuycp);
	/**
	 * 根据Ibcp删除是否购买砍价商品表
	 * @param ibcp
	 * @return
	 * @author fanghui
	 * @date 2017-12-09 10:10:14
	 */
	public int deleteGoodsIsbuycpByIbcp(Long ibcp);
	/**
	 * 根据Ibcp查询是否购买砍价商品表
	 * @param ibcp
	 * @return
	 * @author fanghui
	 * @date 2017-12-09 10:10:14
	 */
	public GoodsIsbuycp queryGoodsIsbuycpByIbcp(Long ibcp);
	/**
	 * 查询是否购买砍价商品表列表
	 * @param pagination 分页对象
	 * @return  是否购买砍价商品表列表
	 * @author fanghui
	 * @date 2017-12-09 10:10:14
	 */
	public List<GoodsIsbuycp> queryGoodsIsbuycpList(Pagination<GoodsIsbuycp> pagination);

}
