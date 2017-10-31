
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.SalePromotion;
import com.retailers.dht.common.dao.SalePromotionMapper;
import com.retailers.dht.common.service.SalePromotionService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品促销表Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-30 15:28:44
 */
@Service("salepromotionService")
public class SalePromotionServiceImpl implements SalePromotionService {
	@Autowired
	private SalePromotionMapper salePromotionMapper;
	public boolean saveSalePromotion(SalePromotion salePromotion) {
		int status = salePromotionMapper.saveSalePromotion(salePromotion);
		return status == 1 ? true : false;
	}
	public boolean updateSalePromotion(SalePromotion salePromotion) {
		int status = salePromotionMapper.updateSalePromotion(salePromotion);
		return status == 1 ? true : false;
	}
	public SalePromotion querySalePromotionBySpId(Long spId) {
		return salePromotionMapper.querySalePromotionBySpId(spId);
	}

	public Pagination<SalePromotion> querySalePromotionList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<SalePromotion> page = new Pagination<SalePromotion>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<SalePromotion> list = salePromotionMapper.querySalePromotionList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteSalePromotionBySpId(Long spId) {
		int status = salePromotionMapper.deleteSalePromotionBySpId(spId);
		return status == 1 ? true : false;
	}
}

