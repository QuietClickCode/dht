
package com.retailers.dht.common.service;
import com.retailers.dht.common.vo.GoodsSpecilgoodscredentialVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsSpecilgoodscredential;
import java.util.Map;
/**
 * 描述：特殊商品证件表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-26 17:15:12
 */
public interface GoodsSpecilgoodscredentialService {

	/**
	 * 添加特殊商品证件表
	 * @param goodsSpecilgoodscredential
	 * @return
	 * @author fanghui
	 * @date 2017-10-26 17:15:12
	 */
	public boolean saveGoodsSpecilgoodscredential(GoodsSpecilgoodscredential goodsSpecilgoodscredential);
	/**
	 * 编辑特殊商品证件表
	 * @param goodsSpecilgoodscredential
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsSpecilgoodscredential(GoodsSpecilgoodscredential goodsSpecilgoodscredential);
	/**
	 * 根据id查询特殊商品证件表
	 * @param gsgId
	 * @return goodsSpecilgoodscredential
	 * @author fanghui
	 * @date 2017-10-26 17:15:12
	 */
	public GoodsSpecilgoodscredential queryGoodsSpecilgoodscredentialByGsgId(Long gsgId);
	/**
	 * 查询特殊商品证件表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-26 17:15:12
	 */
	public Pagination<GoodsSpecilgoodscredentialVo> queryGoodsSpecilgoodscredentialList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gsgId删除特殊商品证件表
	 * @param gsgId
	 * @return
	 * @author fanghui
	 * @date 2017-10-26 17:15:12
	 */
	public boolean deleteGoodsSpecilgoodscredentialByGsgId(Long gsgId);

}


