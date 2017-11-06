package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsGglrel;
import com.retailers.dht.common.vo.GoodsGglrelVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：商品与标签关系表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-24 14:28:44
 */
public interface GoodsGglrelMapper {

	/**
	 * 添加商品与标签关系表
	 * @param goodsGglrel
	 * @return
	 * @author fanghui
	 * @date 2017-10-24 14:28:44
	 */
	public int saveGoodsGglrel(GoodsGglrel goodsGglrel);
	/**
	 * 编辑商品与标签关系表
	 * @param goodsGglrel
	 * @return
	 * @author fanghui
	 * @date 2017-10-24 14:28:44
	 */
	public int updateGoodsGglrel(GoodsGglrel goodsGglrel);
	/**
	 * 根据GglId删除商品与标签关系表
	 * @param gglId
	 * @return
	 * @author fanghui
	 * @date 2017-10-24 14:28:44
	 */
	public int deleteGoodsGglrelByGglId(Long gglId);
	/**
	 * 根据GglId查询商品与标签关系表
	 * @param gglId
	 * @return
	 * @author fanghui
	 * @date 2017-10-24 14:28:44
	 */
	public GoodsGglrel queryGoodsGglrelByGglId(Long gglId);
	/**
	 * 查询商品与标签关系表列表
	 * @param pagination 分页对象
	 * @return  商品与标签关系表列表
	 * @author fanghui
	 * @date 2017-10-24 14:28:44
	 */
	public List<GoodsGglrelVo> queryGoodsGglrelList(Pagination<GoodsGglrelVo> pagination);

	public List<GoodsGglrelVo> queryGclassGoodsGglrelLists(String gclassIds);
}
