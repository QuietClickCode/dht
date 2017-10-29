
package com.retailers.dht.common.service;
import com.retailers.dht.common.vo.GoodsGgcrelVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsGgcrel;
import java.util.Map;
/**
 * 描述：赠品与商品关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-27 17:14:07
 */
public interface GoodsGgcrelService {

	/**
	 * 添加赠品与商品关系表
	 * @param goodsGgcrel
	 * @return
	 * @author fanghui
	 * @date 2017-10-27 17:14:07
	 */
	public boolean saveGoodsGgcrel(String gcIds,Long gid);
	/**
	 * 编辑赠品与商品关系表
	 * @param goodsGgcrel
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsGgcrel(GoodsGgcrel goodsGgcrel);
	/**
	 * 根据id查询赠品与商品关系表
	 * @param ggcId
	 * @return goodsGgcrel
	 * @author fanghui
	 * @date 2017-10-27 17:14:07
	 */
	public GoodsGgcrel queryGoodsGgcrelByGgcId(Long ggcId);
	/**
	 * 查询赠品与商品关系表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-27 17:14:07
	 */
	public Pagination<GoodsGgcrelVo> queryGoodsGgcrelList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据ggcId删除赠品与商品关系表
	 * @param ggcIds
	 * @return
	 * @author fanghui
	 * @date 2017-10-27 17:14:07
	 */
	public boolean deleteGoodsGgcrelByGgcId(String ggcIds);

}


