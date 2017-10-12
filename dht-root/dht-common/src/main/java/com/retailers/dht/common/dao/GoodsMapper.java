package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.Goods;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：商品表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-12 17:29:40
 */
public interface GoodsMapper {

	/**
	 * 添加商品表
	 * @param goods
	 * @return
	 * @author fanghui
	 * @date 2017-10-12 17:29:40
	 */
	public int saveGoods(Goods goods);
	/**
	 * 编辑商品表
	 * @param goods
	 * @return
	 * @author fanghui
	 * @date 2017-10-12 17:29:40
	 */
	public int updateGoods(Goods goods);
	/**
	 * 根据Gid删除商品表
	 * @param gid
	 * @return
	 * @author fanghui
	 * @date 2017-10-12 17:29:40
	 */
	public int deleteGoodsByGid(Long gid);
	/**
	 * 根据Gid查询商品表
	 * @param gid
	 * @return
	 * @author fanghui
	 * @date 2017-10-12 17:29:40
	 */
	public Goods queryGoodsByGid(Long gid);
	/**
	 * 查询商品表列表
	 * @param pagination 分页对象
	 * @return  商品表列表
	 * @author fanghui
	 * @date 2017-10-12 17:29:40
	 */
	public List<Goods> queryGoodsList(Pagination<Goods> pagination);

}
