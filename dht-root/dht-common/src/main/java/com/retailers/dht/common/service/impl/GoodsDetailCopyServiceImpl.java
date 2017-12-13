
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsDetailCopy;
import com.retailers.dht.common.dao.GoodsDetailCopyMapper;
import com.retailers.dht.common.service.GoodsDetailCopyService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品详情副本表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-13 09:34:07
 */
@Service("goodsdetailcopyService")
public class GoodsDetailCopyServiceImpl implements GoodsDetailCopyService {
	@Autowired
	private GoodsDetailCopyMapper goodsDetailCopyMapper;
	public boolean saveGoodsDetailCopy(GoodsDetailCopy goodsDetailCopy) {
		int status = goodsDetailCopyMapper.saveGoodsDetailCopy(goodsDetailCopy);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsDetailCopy(GoodsDetailCopy goodsDetailCopy) {
		int status = goodsDetailCopyMapper.updateGoodsDetailCopy(goodsDetailCopy);
		return status == 1 ? true : false;
	}
	public GoodsDetailCopy queryGoodsDetailCopyByGdId(Long gdcId) {
		return goodsDetailCopyMapper.queryGoodsDetailCopyByGdcId(gdcId);
	}

	public Pagination<GoodsDetailCopy> queryGoodsDetailCopyList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsDetailCopy> page = new Pagination<GoodsDetailCopy>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsDetailCopy> list = goodsDetailCopyMapper.queryGoodsDetailCopyList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsDetailCopyByGdId(Long gdcId) {
		int status = goodsDetailCopyMapper.deleteGoodsDetailCopyByGdcId(gdcId);
		return status == 1 ? true : false;
	}
}

