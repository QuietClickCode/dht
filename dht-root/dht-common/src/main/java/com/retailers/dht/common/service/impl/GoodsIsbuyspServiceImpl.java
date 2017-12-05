
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsIsbuysp;
import com.retailers.dht.common.dao.GoodsIsbuyspMapper;
import com.retailers.dht.common.service.GoodsIsbuyspService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：是否购买活动商品表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-05 15:09:07
 */
@Service("goodsisbuyspService")
public class GoodsIsbuyspServiceImpl implements GoodsIsbuyspService {
	@Autowired
	private GoodsIsbuyspMapper goodsIsbuyspMapper;
	public boolean saveGoodsIsbuysp(GoodsIsbuysp goodsIsbuysp) {
		int status = goodsIsbuyspMapper.saveGoodsIsbuysp(goodsIsbuysp);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsIsbuysp(GoodsIsbuysp goodsIsbuysp) {
		int status = goodsIsbuyspMapper.updateGoodsIsbuysp(goodsIsbuysp);
		return status == 1 ? true : false;
	}
	public GoodsIsbuysp queryGoodsIsbuyspByIbsp(Long ibsp) {
		return goodsIsbuyspMapper.queryGoodsIsbuyspByIbsp(ibsp);
	}

	public Pagination<GoodsIsbuysp> queryGoodsIsbuyspList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsIsbuysp> page = new Pagination<GoodsIsbuysp>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsIsbuysp> list = goodsIsbuyspMapper.queryGoodsIsbuyspList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsIsbuyspByIbsp(Long ibsp) {
		int status = goodsIsbuyspMapper.deleteGoodsIsbuyspByIbsp(ibsp);
		return status == 1 ? true : false;
	}
}

