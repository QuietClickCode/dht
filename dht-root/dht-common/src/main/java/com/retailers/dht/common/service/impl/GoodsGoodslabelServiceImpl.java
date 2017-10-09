
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsGoodslabel;
import com.retailers.dht.common.dao.GoodsGoodslabelMapper;
import com.retailers.dht.common.service.GoodsGoodslabelService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品与标签关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-09 13:43:52
 */
@Service("goodsgoodslabelService")
public class GoodsGoodslabelServiceImpl implements GoodsGoodslabelService {
	@Autowired
	private GoodsGoodslabelMapper goodsGoodslabelMapper;
	public boolean saveGoodsGoodslabel(GoodsGoodslabel goodsGoodslabel) {
		int status = goodsGoodslabelMapper.saveGoodsGoodslabel(goodsGoodslabel);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsGoodslabel(GoodsGoodslabel goodsGoodslabel) {
		int status = goodsGoodslabelMapper.updateGoodsGoodslabel(goodsGoodslabel);
		return status == 1 ? true : false;
	}
	public GoodsGoodslabel queryGoodsGoodslabelByGlrId(Long glrId) {
		return goodsGoodslabelMapper.queryGoodsGoodslabelByGlrId(glrId);
	}

	public Pagination<GoodsGoodslabel> queryGoodsGoodslabelList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsGoodslabel> page = new Pagination<GoodsGoodslabel>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsGoodslabel> list = goodsGoodslabelMapper.queryGoodsGoodslabelList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsGoodslabelByGlrId(Long glrId) {
		int status = goodsGoodslabelMapper.deleteGoodsGoodslabelByGlrId(glrId);
		return status == 1 ? true : false;
	}
}

