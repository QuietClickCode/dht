package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.BuyCar;
import com.retailers.dht.common.vo.GoodsVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：购物车表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-20 15:01:54
 */
public interface BuyCarMapper {

	/**
	 * 添加购物车表
	 * @param buyCar
	 * @return
	 * @author fanghui
	 * @date 2017-11-20 15:01:54
	 */
	public int saveBuyCar(BuyCar buyCar);
	/**
	 * 编辑购物车表
	 * @param buyCar
	 * @return
	 * @author fanghui
	 * @date 2017-11-20 15:01:54
	 */
	public int updateBuyCar(BuyCar buyCar);
	/**
	 * 根据BcId删除购物车表
	 * @param bcId
	 * @return
	 * @author fanghui
	 * @date 2017-11-20 15:01:54
	 */
	public int deleteBuyCarByBcId(Long bcId);
	/**
	 * 根据BcId查询购物车表
	 * @param bcId
	 * @return
	 * @author fanghui
	 * @date 2017-11-20 15:01:54
	 */
	public BuyCar queryBuyCarByBcId(Long bcId);
	/**
	 * 查询购物车表列表
	 * @param pagination 分页对象
	 * @return  购物车表列表
	 * @author fanghui
	 * @date 2017-11-20 15:01:54
	 */
	public List<BuyCar> queryBuyCarList(Pagination<BuyCar> pagination);

	public List<GoodsVo> queryGoodsVoList(Pagination<GoodsVo> pagination);

}
