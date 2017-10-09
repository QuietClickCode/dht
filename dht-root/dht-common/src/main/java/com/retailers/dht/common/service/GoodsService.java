
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.Goods;
import java.util.Map;
/**
 * 描述：商品表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-09 17:29:36
 */
public interface GoodsService {

	/**
	 * 添加商品表
	 * @param goods
	 * @return
	 * @author fanghui
	 * @date 2017-10-09 17:29:36
	 */
	public boolean saveGoods(Goods goods);
	/**
	 * 编辑商品表
	 * @param goods
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoods(Goods goods);
	/**
	 * 根据id查询商品表
	 * @param gid
	 * @return goods
	 * @author fanghui
	 * @date 2017-10-09 17:29:36
	 */
	public Goods queryGoodsByGid(Long gid);
	/**
	 * 查询商品表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-09 17:29:36
	 */
	public Pagination<Goods> queryGoodsList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gid删除商品表
	 * @param gid
	 * @return
	 * @author fanghui
	 * @date 2017-10-09 17:29:36
	 */
	public boolean deleteGoodsByGid(Long gid);

}


