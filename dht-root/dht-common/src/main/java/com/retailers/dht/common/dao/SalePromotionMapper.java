package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.SalePromotion;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：商品促销表DAO
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-30 15:28:44
 */
public interface SalePromotionMapper {

	/**
	 * 添加商品促销表
	 * @param salePromotion
	 * @return
	 * @author wangjue
	 * @date 2017-10-30 15:28:44
	 */
	public int saveSalePromotion(SalePromotion salePromotion);
	/**
	 * 编辑商品促销表
	 * @param salePromotion
	 * @return
	 * @author wangjue
	 * @date 2017-10-30 15:28:44
	 */
	public int updateSalePromotion(SalePromotion salePromotion);
	/**
	 * 根据SpId删除商品促销表
	 * @param spId
	 * @return
	 * @author wangjue
	 * @date 2017-10-30 15:28:44
	 */
	public int deleteSalePromotionBySpId(Long spId);
	/**
	 * 根据SpId查询商品促销表
	 * @param spId
	 * @return
	 * @author wangjue
	 * @date 2017-10-30 15:28:44
	 */
	public SalePromotion querySalePromotionBySpId(Long spId);
	/**
	 * 查询商品促销表列表
	 * @param pagination 分页对象
	 * @return  商品促销表列表
	 * @author wangjue
	 * @date 2017-10-30 15:28:44
	 */
	public List<SalePromotion> querySalePromotionList(Pagination<SalePromotion> pagination);

}
