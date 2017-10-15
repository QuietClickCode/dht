
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsSpecification;
import com.retailers.dht.common.dao.GoodsSpecificationMapper;
import com.retailers.dht.common.service.GoodsSpecificationService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品规格表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-11 22:19:21
 */
@Service("goodsspecificationService")
public class GoodsSpecificationServiceImpl implements GoodsSpecificationService {
	@Autowired
	private GoodsSpecificationMapper goodsSpecificationMapper;
	public boolean saveGoodsSpecification(GoodsSpecification goodsSpecification) {
		int status = goodsSpecificationMapper.saveGoodsSpecification(goodsSpecification);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsSpecification(GoodsSpecification goodsSpecification) {
		int status = goodsSpecificationMapper.updateGoodsSpecification(goodsSpecification);
		return status == 1 ? true : false;
	}
	public GoodsSpecification queryGoodsSpecificationByGsId(Long gsId) {
		return goodsSpecificationMapper.queryGoodsSpecificationByGsId(gsId);
	}

	public Pagination<GoodsSpecification> queryGoodsSpecificationList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsSpecification> page = new Pagination<GoodsSpecification>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsSpecification> list = goodsSpecificationMapper.queryGoodsSpecificationList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsSpecificationByGsId(Long gsId) {
		int status = goodsSpecificationMapper.deleteGoodsSpecificationByGsId(gsId);
		return status == 1 ? true : false;
	}
}

