package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsGdcprel;
import com.retailers.dht.common.vo.GoodsGdcprelVo;
import com.retailers.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 描述：商品详情与砍价关系表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-05 16:15:28
 */
public interface GoodsGdcprelMapper {

	/**
	 * 添加商品详情与砍价关系表
	 * @param goodsGdcprel
	 * @return
	 * @author fanghui
	 * @date 2017-12-05 16:15:28
	 */
	public int saveGoodsGdcprel(GoodsGdcprel goodsGdcprel);
	/**
	 * 编辑商品详情与砍价关系表
	 * @param goodsGdcprel
	 * @return
	 * @author fanghui
	 * @date 2017-12-05 16:15:28
	 */
	public int updateGoodsGdcprel(GoodsGdcprel goodsGdcprel);
	/**
	 * 根据GdcpId删除商品详情与砍价关系表
	 * @param gdcpId
	 * @return
	 * @author fanghui
	 * @date 2017-12-05 16:15:28
	 */
	public int deleteGoodsGdcprelByGdcpId(Long gdcpId);
	/**
	 * 根据GdcpId查询商品详情与砍价关系表
	 * @param gdcpId
	 * @return
	 * @author fanghui
	 * @date 2017-12-05 16:15:28
	 */
	public GoodsGdcprel queryGoodsGdcprelByGdcpId(Long gdcpId);
	/**
	 * 查询商品详情与砍价关系表列表
	 * @param pagination 分页对象
	 * @return  商品详情与砍价关系表列表
	 * @author fanghui
	 * @date 2017-12-05 16:15:28
	 */
	public List<GoodsGdcprel> queryGoodsGdcprelList(Pagination<GoodsGdcprel> pagination);

	public List<GoodsGdcprelVo> queryGoodsGdcprelListsByGid(@Param("gid") Long gid, @Param("cpId") Long cpId);
}
