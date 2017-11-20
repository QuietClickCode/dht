
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.BuyCar;
import java.util.Map;
/**
 * 描述：购物车表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-20 15:01:54
 */
public interface BuyCarService {

	/**
	 * 添加购物车表
	 * @param buyCar
	 * @return
	 * @author fanghui
	 * @date 2017-11-20 15:01:54
	 */
	public boolean saveBuyCar(BuyCar buyCar);
	/**
	 * 编辑购物车表
	 * @param buyCar
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateBuyCar(BuyCar buyCar);
	/**
	 * 根据id查询购物车表
	 * @param bcId
	 * @return buyCar
	 * @author fanghui
	 * @date 2017-11-20 15:01:54
	 */
	public BuyCar queryBuyCarByBcId(Long bcId);
	/**
	 * 查询购物车表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-11-20 15:01:54
	 */
	public Pagination<BuyCar> queryBuyCarList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据bcId删除购物车表
	 * @param bcId
	 * @return
	 * @author fanghui
	 * @date 2017-11-20 15:01:54
	 */
	public boolean deleteBuyCarByBcId(Long bcId);

}


