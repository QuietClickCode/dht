package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.CutPricePrice;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：砍价价格初始化表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-07 15:52:00
 */
public interface CutPricePriceMapper {

	/**
	 * 添加砍价价格初始化表
	 * @param cutPricePrice
	 * @return
	 * @author fanghui
	 * @date 2017-12-07 15:52:00
	 */
	public int saveCutPricePrice(CutPricePrice cutPricePrice);
	/**
	 * 编辑砍价价格初始化表
	 * @param cutPricePrice
	 * @return
	 * @author fanghui
	 * @date 2017-12-07 15:52:00
	 */
	public int updateCutPricePrice(CutPricePrice cutPricePrice);
	/**
	 * 根据CppId删除砍价价格初始化表
	 * @param cppId
	 * @return
	 * @author fanghui
	 * @date 2017-12-07 15:52:00
	 */
	public int deleteCutPricePriceByCppId(Long cppId);
	/**
	 * 根据CppId查询砍价价格初始化表
	 * @param cppId
	 * @return
	 * @author fanghui
	 * @date 2017-12-07 15:52:00
	 */
	public CutPricePrice queryCutPricePriceByCppId(Long cppId);
	/**
	 * 查询砍价价格初始化表列表
	 * @param pagination 分页对象
	 * @return  砍价价格初始化表列表
	 * @author fanghui
	 * @date 2017-12-07 15:52:00
	 */
	public List<CutPricePrice> queryCutPricePriceList(Pagination<CutPricePrice> pagination);

}
