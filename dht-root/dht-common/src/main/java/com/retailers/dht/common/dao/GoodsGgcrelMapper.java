package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsGgcrel;
import com.retailers.dht.common.vo.GoodsGgcrelVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：赠品与商品关系表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-27 17:14:07
 */
public interface GoodsGgcrelMapper {

	/**
	 * 添加赠品与商品关系表
	 * @param goodsGgcrel
	 * @return
	 * @author fanghui
	 * @date 2017-10-27 17:14:07
	 */
	public int saveGoodsGgcrel(GoodsGgcrel goodsGgcrel);
	/**
	 * 编辑赠品与商品关系表
	 * @param goodsGgcrel
	 * @return
	 * @author fanghui
	 * @date 2017-10-27 17:14:07
	 */
	public int updateGoodsGgcrel(GoodsGgcrel goodsGgcrel);
	/**
	 * 根据GgcId删除赠品与商品关系表
	 * @param ggcId
	 * @return
	 * @author fanghui
	 * @date 2017-10-27 17:14:07
	 */
	public int deleteGoodsGgcrelByGgcId(Long ggcId);
	/**
	 * 根据GgcId查询赠品与商品关系表
	 * @param ggcId
	 * @return
	 * @author fanghui
	 * @date 2017-10-27 17:14:07
	 */
	public GoodsGgcrel queryGoodsGgcrelByGgcId(Long ggcId);
	/**
	 * 查询赠品与商品关系表列表
	 * @param pagination 分页对象
	 * @return  赠品与商品关系表列表
	 * @author fanghui
	 * @date 2017-10-27 17:14:07
	 */
	public List<GoodsGgcrelVo> queryGoodsGgcrelList(Pagination<GoodsGgcrelVo> pagination);

}
