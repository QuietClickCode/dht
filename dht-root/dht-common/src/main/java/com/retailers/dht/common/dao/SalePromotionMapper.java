package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.SalePromotion;
import com.retailers.dht.common.vo.SalePromotionVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：商品特价表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-08 17:28:44
 */
public interface SalePromotionMapper {

	/**
	 * 添加商品特价表
	 * @param salePromotion
	 * @return
	 * @author fanghui
	 * @date 2017-11-08 17:28:44
	 */
	public int saveSalePromotion(SalePromotion salePromotion);
	/**
	 * 编辑商品特价表
	 * @param salePromotion
	 * @return
	 * @author fanghui
	 * @date 2017-11-08 17:28:44
	 */
	public int updateSalePromotion(SalePromotion salePromotion);
	/**
	 * 根据SpId删除商品特价表
	 * @param spId
	 * @return
	 * @author fanghui
	 * @date 2017-11-08 17:28:44
	 */
	public int deleteSalePromotionBySpId(Long spId);
	/**
	 * 根据SpId查询商品特价表
	 * @param spId
	 * @return
	 * @author fanghui
	 * @date 2017-11-08 17:28:44
	 */
	public SalePromotion querySalePromotionBySpId(Long spId);
	/**
	 * 查询商品特价表列表
	 * @param pagination 分页对象
	 * @return  商品特价表列表
	 * @author fanghui
	 * @date 2017-11-08 17:28:44
	 */
	public List<SalePromotionVo> querySalePromotionList(Pagination<SalePromotionVo> pagination);

}
