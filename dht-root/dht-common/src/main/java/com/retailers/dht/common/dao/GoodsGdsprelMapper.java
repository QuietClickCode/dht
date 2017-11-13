package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsGdsprel;
import com.retailers.dht.common.vo.GoodsGdsprelVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：商品与特价关系表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-08 17:29:16
 */
public interface GoodsGdsprelMapper {

	/**
	 * 添加商品与特价关系表
	 * @param goodsGdsprel
	 * @return
	 * @author fanghui
	 * @date 2017-11-08 17:29:16
	 */
	public int saveGoodsGdsprel(GoodsGdsprel goodsGdsprel);
	/**
	 * 编辑商品与特价关系表
	 * @param goodsGdsprel
	 * @return
	 * @author fanghui
	 * @date 2017-11-08 17:29:16
	 */
	public int updateGoodsGdsprel(GoodsGdsprel goodsGdsprel);
	/**
	 * 根据GdspId删除商品与特价关系表
	 * @param gdspId
	 * @return
	 * @author fanghui
	 * @date 2017-11-08 17:29:16
	 */
	public int deleteGoodsGdsprelByGdspId(Long gdspId);
	/**
	 * 根据GdspId查询商品与特价关系表
	 * @param gdspId
	 * @return
	 * @author fanghui
	 * @date 2017-11-08 17:29:16
	 */
	public GoodsGdsprel queryGoodsGdsprelByGdspId(Long gdspId);
	/**
	 * 查询商品与特价关系表列表
	 * @param pagination 分页对象
	 * @return  商品与特价关系表列表
	 * @author fanghui
	 * @date 2017-11-08 17:29:16
	 */
	public List<GoodsGdsprel> queryGoodsGdsprelList(Pagination<GoodsGdsprel> pagination);

	public List<GoodsGdsprelVo> queryGoodsGdsprelListsByGid(Long gid);
}
