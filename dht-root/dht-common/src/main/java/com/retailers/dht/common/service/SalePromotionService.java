
package com.retailers.dht.common.service;

import com.retailers.dht.common.entity.SalePromotion;
import com.retailers.dht.common.vo.GoodsVo;
import com.retailers.dht.common.vo.SalePromotionVo;
import com.retailers.mybatis.pagination.Pagination;

import java.util.List;
import java.util.Map;
/**
 * 描述：商品促销表Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-11-02 16:28:41
 */
public interface SalePromotionService {

	/**
	 * 添加商品促销表
	 * @param salePromotion
	 * @return
	 * @author wangjue
	 * @date 2017-11-02 16:28:41
	 */
	public SalePromotion saveSalePromotion(SalePromotion salePromotion);
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
	 * @date 2017-11-02 16:28:41
	 */
	public SalePromotion querySalePromotionBySpId(Long spId);
	/**
	 * 查询商品促销表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author wangjue
	 * @date 2017-11-02 16:28:41
	 */
	public Pagination<SalePromotionVo> querySalePromotionList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据spId删除商品促销表
	 * @param spId
	 * @return
	 * @author wangjue
	 * @date 2017-11-02 16:28:41
	 */
	public boolean deleteSalePromotionBySpId(Long spId);

	public List<SalePromotionVo> querySalePromotionTree(List<SalePromotionVo> promotionVos);

	public Pagination<SalePromotionVo> querySalePromotionListWeb(Map<String, Object> params, int pageNo, int pageSize);

	public List<GoodsVo> queryHasNoSpGoods(String gname,Long parentId);

	public Pagination<SalePromotionVo> queryNextSalePromotionLists(Map<String, Object> params, int pageNo, int pageSize);
}


