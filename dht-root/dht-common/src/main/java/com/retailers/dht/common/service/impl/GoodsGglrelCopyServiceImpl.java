
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsGglrelCopy;
import com.retailers.dht.common.dao.GoodsGglrelCopyMapper;
import com.retailers.dht.common.service.GoodsGglrelCopyService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品与标签关系副本表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-24 17:09:22
 */
@Service("goodsgglrelcopyService")
public class GoodsGglrelCopyServiceImpl implements GoodsGglrelCopyService {
	@Autowired
	private GoodsGglrelCopyMapper goodsGglrelCopyMapper;
	public boolean saveGoodsGglrelCopy(GoodsGglrelCopy goodsGglrelCopy) {
		int status = goodsGglrelCopyMapper.saveGoodsGglrelCopy(goodsGglrelCopy);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsGglrelCopy(GoodsGglrelCopy goodsGglrelCopy) {
		int status = goodsGglrelCopyMapper.updateGoodsGglrelCopy(goodsGglrelCopy);
		return status == 1 ? true : false;
	}
	public GoodsGglrelCopy queryGoodsGglrelCopyByGglId(Long gglId) {
		return goodsGglrelCopyMapper.queryGoodsGglrelCopyByGglId(gglId);
	}

	public Pagination<GoodsGglrelCopy> queryGoodsGglrelCopyList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsGglrelCopy> page = new Pagination<GoodsGglrelCopy>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsGglrelCopy> list = goodsGglrelCopyMapper.queryGoodsGglrelCopyList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsGglrelCopyByGglId(Long gglId) {
		int status = goodsGglrelCopyMapper.deleteGoodsGglrelCopyByGglId(gglId);
		return status == 1 ? true : false;
	}
}

