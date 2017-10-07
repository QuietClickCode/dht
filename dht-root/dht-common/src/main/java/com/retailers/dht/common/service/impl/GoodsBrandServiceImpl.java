
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsBrand;
import com.retailers.dht.common.dao.GoodsBrandMapper;
import com.retailers.dht.common.service.GoodsBrandService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品品牌表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-07 09:42:29
 */
@Service("goodsbrandService")
public class GoodsBrandServiceImpl implements GoodsBrandService {
	@Autowired
	private GoodsBrandMapper goodsBrandMapper;
	public boolean saveGoodsBrand(GoodsBrand goodsBrand) {
		int status = goodsBrandMapper.saveGoodsBrand(goodsBrand);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsBrand(GoodsBrand goodsBrand) {
		int status = goodsBrandMapper.updateGoodsBrand(goodsBrand);
		return status == 1 ? true : false;
	}
	public GoodsBrand queryGoodsBrandByGbId(Long gbId) {
		return goodsBrandMapper.queryGoodsBrandByGbId(gbId);
	}

	public Pagination<GoodsBrand> queryGoodsBrandList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsBrand> page = new Pagination<GoodsBrand>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsBrand> list = goodsBrandMapper.queryGoodsBrandList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsBrandByGbId(Long gbId) {
		int status = goodsBrandMapper.deleteGoodsBrandByGbId(gbId);
		return status == 1 ? true : false;
	}
}

