
package com.retailers.dht.common.service;
import com.retailers.dht.common.vo.GoodsDetailVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsDetail;

import java.util.List;
import java.util.Map;
/**
 * 描述：商品详情表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-21 10:51:10
 */
public interface GoodsDetailService {

	/**
	 * 添加商品详情表
	 * @param goodsDetail
	 * @return
	 * @author fanghui
	 * @date 2017-10-21 10:51:10
	 */
	public GoodsDetail saveGoodsDetail(GoodsDetail goodsDetail);
	/**
	 * 编辑商品详情表
	 * @param goodsDetail
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsDetail(GoodsDetail goodsDetail);
	/**
	 * 根据id查询商品详情表
	 * @param gdId
	 * @return goodsDetail
	 * @author fanghui
	 * @date 2017-10-21 10:51:10
	 */
	public GoodsDetailVo queryGoodsDetailByGdId(Long gdId);
	/**
	 * 查询商品详情表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-21 10:51:10
	 */
	public Pagination<GoodsDetail> queryGoodsDetailList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gdId删除商品详情表
	 * @param gdId
	 * @return
	 * @author fanghui
	 * @date 2017-10-21 10:51:10
	 */
	public boolean deleteGoodsDetailByGdId(Long gdId);

	public List<GoodsDetailVo> queryGoodsDetailOnce(Long gid);

	public boolean addMyData(String mydata,Long gid);
}


