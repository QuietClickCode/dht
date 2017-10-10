package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsCopy;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：商品副本表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-10 17:54:32
 */
public interface GoodsCopyMapper {

	/**
	 * 添加商品副本表
	 * @param goodsCopy
	 * @return
	 * @author fanghui
	 * @date 2017-10-10 17:54:32
	 */
	public int saveGoodsCopy(GoodsCopy goodsCopy);
	/**
	 * 编辑商品副本表
	 * @param goodsCopy
	 * @return
	 * @author fanghui
	 * @date 2017-10-10 17:54:32
	 */
	public int updateGoodsCopy(GoodsCopy goodsCopy);
	/**
	 * 根据Gid删除商品副本表
	 * @param gid
	 * @return
	 * @author fanghui
	 * @date 2017-10-10 17:54:32
	 */
	public int deleteGoodsCopyByGid(Long gid);
	/**
	 * 根据Gid查询商品副本表
	 * @param gid
	 * @return
	 * @author fanghui
	 * @date 2017-10-10 17:54:32
	 */
	public GoodsCopy queryGoodsCopyByGid(Long gid);
	/**
	 * 查询商品副本表列表
	 * @param pagination 分页对象
	 * @return  商品副本表列表
	 * @author fanghui
	 * @date 2017-10-10 17:54:32
	 */
	public List<GoodsCopy> queryGoodsCopyList(Pagination<GoodsCopy> pagination);

}
