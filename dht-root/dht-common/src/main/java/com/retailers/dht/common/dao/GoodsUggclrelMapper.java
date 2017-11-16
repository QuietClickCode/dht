package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsUggclrel;
import com.retailers.dht.common.vo.GoodsUggclrelVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：商品用户评论关系表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-16 13:54:57
 */
public interface GoodsUggclrelMapper {

	/**
	 * 添加商品用户评论关系表
	 * @param goodsUggclrel
	 * @return
	 * @author fanghui
	 * @date 2017-11-16 13:54:57
	 */
	public int saveGoodsUggclrel(GoodsUggclrel goodsUggclrel);
	/**
	 * 编辑商品用户评论关系表
	 * @param goodsUggclrel
	 * @return
	 * @author fanghui
	 * @date 2017-11-16 13:54:57
	 */
	public int updateGoodsUggclrel(GoodsUggclrel goodsUggclrel);
	/**
	 * 根据UggclId删除商品用户评论关系表
	 * @param uggclId
	 * @return
	 * @author fanghui
	 * @date 2017-11-16 13:54:57
	 */
	public int deleteGoodsUggclrelByUggclId(Long uggclId);
	/**
	 * 根据UggclId查询商品用户评论关系表
	 * @param uggclId
	 * @return
	 * @author fanghui
	 * @date 2017-11-16 13:54:57
	 */
	public GoodsUggclrel queryGoodsUggclrelByUggclId(Long uggclId);
	/**
	 * 查询商品用户评论关系表列表
	 * @param pagination 分页对象
	 * @return  商品用户评论关系表列表
	 * @author fanghui
	 * @date 2017-11-16 13:54:57
	 */
	public List<GoodsUggclrel> queryGoodsUggclrelList(Pagination<GoodsUggclrel> pagination);

	public List<GoodsUggclrelVo> queryGoodsUggclrelListVo(Pagination<GoodsUggclrelVo> pagination);

	public GoodsUggclrelVo queryGoodsclsumandavg(Long gid);

	public List<GoodsUggclrelVo> queryHasGoodscls(Long gid);
}
