
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsCopy;
import com.retailers.dht.common.dao.GoodsCopyMapper;
import com.retailers.dht.common.service.GoodsCopyService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品副本表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-12 17:30:11
 */
@Service("goodscopyService")
public class GoodsCopyServiceImpl implements GoodsCopyService {
	@Autowired
	private GoodsCopyMapper goodsCopyMapper;
	public boolean saveGoodsCopy(GoodsCopy goodsCopy) {
		int status = goodsCopyMapper.saveGoodsCopy(goodsCopy);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsCopy(GoodsCopy goodsCopy) {
		int status = goodsCopyMapper.updateGoodsCopy(goodsCopy);
		return status == 1 ? true : false;
	}
	public GoodsCopy queryGoodsCopyByGcId(Long gcId) {
		return goodsCopyMapper.queryGoodsCopyByGcId(gcId);
	}

	public Pagination<GoodsCopy> queryGoodsCopyList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsCopy> page = new Pagination<GoodsCopy>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsCopy> list = goodsCopyMapper.queryGoodsCopyList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsCopyByGcId(Long gcId) {
		int status = goodsCopyMapper.deleteGoodsCopyByGcId(gcId);
		return status == 1 ? true : false;
	}
}

