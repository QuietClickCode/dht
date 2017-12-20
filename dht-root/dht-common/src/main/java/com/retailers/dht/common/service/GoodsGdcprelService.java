
package com.retailers.dht.common.service;
import com.retailers.dht.common.entity.GoodsDetail;
import com.retailers.dht.common.vo.GoodsDetailVo;
import com.retailers.dht.common.vo.GoodsGdcprelVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsGdcprel;

import java.util.List;
import java.util.Map;
/**
 * 描述：商品详情与砍价关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-05 16:15:28
 */
public interface GoodsGdcprelService {

	/**
	 * 添加商品详情与砍价关系表
	 * @param goodsGdcprel
	 * @return
	 * @author fanghui
	 * @date 2017-12-05 16:15:28
	 */
	public boolean saveGoodsGdcprel(GoodsGdcprel goodsGdcprel);
	/**
	 * 编辑商品详情与砍价关系表
	 * @param goodsGdcprel
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsGdcprel(GoodsGdcprel goodsGdcprel);
	/**
	 * 根据id查询商品详情与砍价关系表
	 * @param gdcpId
	 * @return goodsGdcprel
	 * @author fanghui
	 * @date 2017-12-05 16:15:28
	 */
	public GoodsGdcprel queryGoodsGdcprelByGdcpId(Long gdcpId);
	/**
	 * 查询商品详情与砍价关系表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-12-05 16:15:28
	 */
	public Pagination<GoodsGdcprel> queryGoodsGdcprelList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gdcpId删除商品详情与砍价关系表
	 * @param gdcpId
	 * @return
	 * @author fanghui
	 * @date 2017-12-05 16:15:28
	 */
	public boolean deleteGoodsGdcprelByGdcpId(Long gdcpId);

	public List<GoodsGdcprelVo> queryGoodsGdcprelListsByGid(Long gid, Long cpId);

	public boolean saveGoodsGdcprelByJson(String data);

	public GoodsGdcprelVo queryGoodsGdcprelVoLists(Map params);

	public GoodsGdcprelVo queryCheckOrderData(Long gdcpId,Long uid);

	public boolean editGoodsInventorys(Map<Long,Long> goodsGdcprelMap);

}


