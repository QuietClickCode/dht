
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsGdcprel;
import com.retailers.dht.common.dao.GoodsGdcprelMapper;
import com.retailers.dht.common.service.GoodsGdcprelService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品详情与砍价关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-05 16:15:28
 */
@Service("goodsgdcprelService")
public class GoodsGdcprelServiceImpl implements GoodsGdcprelService {
	@Autowired
	private GoodsGdcprelMapper goodsGdcprelMapper;
	public boolean saveGoodsGdcprel(GoodsGdcprel goodsGdcprel) {
		int status = goodsGdcprelMapper.saveGoodsGdcprel(goodsGdcprel);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsGdcprel(GoodsGdcprel goodsGdcprel) {
		int status = goodsGdcprelMapper.updateGoodsGdcprel(goodsGdcprel);
		return status == 1 ? true : false;
	}
	public GoodsGdcprel queryGoodsGdcprelByGdcpId(Long gdcpId) {
		return goodsGdcprelMapper.queryGoodsGdcprelByGdcpId(gdcpId);
	}

	public Pagination<GoodsGdcprel> queryGoodsGdcprelList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsGdcprel> page = new Pagination<GoodsGdcprel>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsGdcprel> list = goodsGdcprelMapper.queryGoodsGdcprelList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsGdcprelByGdcpId(Long gdcpId) {
		int status = goodsGdcprelMapper.deleteGoodsGdcprelByGdcpId(gdcpId);
		return status == 1 ? true : false;
	}
}

