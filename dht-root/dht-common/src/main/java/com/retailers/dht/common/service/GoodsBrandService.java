
package com.retailers.dht.common.service;
import com.retailers.dht.common.vo.GoodsBrandVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsBrand;
import java.util.Map;
/**
 * 描述：商品品牌表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-07 09:42:29
 */
public interface GoodsBrandService {

	/**
	 * 添加商品品牌表
	 * @param goodsBrand
	 * @return
	 * @author fanghui
	 * @date 2017-10-07 09:42:29
	 */
	public boolean saveGoodsBrand(GoodsBrand goodsBrand);
	/**
	 * 编辑商品品牌表
	 * @param goodsBrand
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsBrand(GoodsBrand goodsBrand);
	/**
	 * 根据id查询商品品牌表
	 * @param gbId
	 * @return goodsBrand
	 * @author fanghui
	 * @date 2017-10-07 09:42:29
	 */
	public GoodsBrand queryGoodsBrandByGbId(Long gbId);
	/**
	 * 查询商品品牌表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-07 09:42:29
	 */
	public Pagination<GoodsBrandVo> queryGoodsBrandList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gbId删除商品品牌表
	 * @param gbId
	 * @return
	 * @author fanghui
	 * @date 2017-10-07 09:42:29
	 */
	public boolean deleteGoodsBrandByGbId(Long gbId);

}


