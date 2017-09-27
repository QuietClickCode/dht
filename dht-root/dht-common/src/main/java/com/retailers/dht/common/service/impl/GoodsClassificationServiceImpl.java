
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsClassification;
import com.retailers.dht.common.dao.GoodsClassificationMapper;
import com.retailers.dht.common.service.GoodsClassificationService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：部门人员表Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-27 16:28:01
 */
@Service("goodsclassificationService")
public class GoodsClassificationServiceImpl implements GoodsClassificationService {
	@Autowired
	private GoodsClassificationMapper goodsClassificationMapper;
	public boolean saveGoodsClassification(GoodsClassification goodsClassification) {
		int status = goodsClassificationMapper.saveGoodsClassification(goodsClassification);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsClassification(GoodsClassification goodsClassification) {
		int status = goodsClassificationMapper.updateGoodsClassification(goodsClassification);
		return status == 1 ? true : false;
	}
	public GoodsClassification queryGoodsClassificationByGgId(Long ggId) {
		return goodsClassificationMapper.queryGoodsClassificationByGgId(ggId);
	}

	public Pagination<GoodsClassification> queryGoodsClassificationList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsClassification> page = new Pagination<GoodsClassification>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsClassification> list = goodsClassificationMapper.queryGoodsClassificationList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsClassificationByGgId(Long ggId) {
		int status = goodsClassificationMapper.deleteGoodsClassificationByGgId(ggId);
		return status == 1 ? true : false;
	}
}

