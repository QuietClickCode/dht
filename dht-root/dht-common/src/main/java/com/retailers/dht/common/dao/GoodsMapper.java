package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.vo.GoodsVo;
import com.retailers.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
	 * 根据商品id批量取得商品
	 * @param gids
	 * @return
	 */
	public List<Goods> queryGoodsByGids(@Param("gids") List<Long> gids);
	/**
	 * 查询商品表列表
	 * @param pagination 分页对象
	 * @return  商品表列表
	 * @author fanghui
	 * @date 2017-10-12 17:29:40
	 */
	public List<GoodsVo> queryGoodsList(Pagination<GoodsVo> pagination);

	public List<GoodsVo> querySamegclassGoods(Pagination<GoodsVo> pagination);

	/**
	 * 取得商品列表
	 * @param gt 商品类型
	 * @return
	 */
	public List<Goods> queryGoodsByGt(Long gt);

	public List<GoodsVo> queryGoodsListByGclass(Pagination<GoodsVo> pagination);

	/**
	 * 批量出售商品
	 * @param infos
	 * gid:2 商品id
 	 * num:3 购买数量
	 * @return
	 */
	public int sellGoods(@Param("infos")List<Map<String,Long>> infos);
}
