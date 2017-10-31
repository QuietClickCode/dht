
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.SalePromotion;
import java.util.Map;
/**
 * 描述：商品促销表Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-30 15:28:44
 */
public interface SalePromotionService {

	/**
	 * 添加商品促销表
	 * @param salePromotion
	 * @return
	 * @author wangjue
	 * @date 2017-10-30 15:28:44
	 */
	public boolean saveSalePromotion(SalePromotion salePromotion);
	/**
	 * 编辑商品促销表
	 * @param salePromotion
	 * @return
	 * @author wangjue
	 * @date
	 */
	public boolean updateSalePromotion(SalePromotion salePromotion);
	/**
	 * 根据id查询商品促销表
	 * @param spId
	 * @return salePromotion
	 * @author wangjue
	 * @date 2017-10-30 15:28:44
	 */
	public SalePromotion querySalePromotionBySpId(Long spId);
	/**
	 * 查询商品促销表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author wangjue
	 * @date 2017-10-30 15:28:44
	 */
	public Pagination<SalePromotion> querySalePromotionList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据spId删除商品促销表
	 * @param spId
	 * @return
	 * @author wangjue
	 * @date 2017-10-30 15:28:44
	 */
	public boolean deleteSalePromotionBySpId(Long spId);

}


