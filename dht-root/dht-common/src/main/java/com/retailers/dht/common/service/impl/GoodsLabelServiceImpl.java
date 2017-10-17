
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsLabel;
import com.retailers.dht.common.dao.GoodsLabelMapper;
import com.retailers.dht.common.service.GoodsLabelService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品标签表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-09 13:44:22
 */
@Service("goodslabelService")
public class GoodsLabelServiceImpl implements GoodsLabelService {
	@Autowired
	private GoodsLabelMapper goodsLabelMapper;
	public boolean saveGoodsLabel(GoodsLabel goodsLabel) {
		int status = goodsLabelMapper.saveGoodsLabel(goodsLabel);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsLabel(GoodsLabel goodsLabel) {
		int status = goodsLabelMapper.updateGoodsLabel(goodsLabel);
		return status == 1 ? true : false;
	}
	public GoodsLabel queryGoodsLabelByGlId(Long glId) {
		return goodsLabelMapper.queryGoodsLabelByGlId(glId);
	}

	public Pagination<GoodsLabel> queryGoodsLabelList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsLabel> page = new Pagination<GoodsLabel>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsLabel> list = goodsLabelMapper.queryGoodsLabelList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsLabelByGlId(Long glId) {
		GoodsLabel goodsLabel = goodsLabelMapper.queryGoodsLabelByGlId(glId);
		goodsLabel.setIsDelete(1L);
		int status = goodsLabelMapper.updateGoodsLabel(goodsLabel);
		return status == 1 ? true : false;
	}
}

