
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsConfigCopy;
import com.retailers.dht.common.dao.GoodsConfigCopyMapper;
import com.retailers.dht.common.service.GoodsConfigCopyService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品配置副本表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-12 10:37:11
 */
@Service("goodsconfigcopyService")
public class GoodsConfigCopyServiceImpl implements GoodsConfigCopyService {
	@Autowired
	private GoodsConfigCopyMapper goodsConfigCopyMapper;
	public boolean saveGoodsConfigCopy(GoodsConfigCopy goodsConfigCopy) {
		int status = goodsConfigCopyMapper.saveGoodsConfigCopy(goodsConfigCopy);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsConfigCopy(GoodsConfigCopy goodsConfigCopy) {
		int status = goodsConfigCopyMapper.updateGoodsConfigCopy(goodsConfigCopy);
		return status == 1 ? true : false;
	}
	public GoodsConfigCopy queryGoodsConfigCopyByGccId(Long gccId) {
		return goodsConfigCopyMapper.queryGoodsConfigCopyByGccId(gccId);
	}

	public Pagination<GoodsConfigCopy> queryGoodsConfigCopyList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsConfigCopy> page = new Pagination<GoodsConfigCopy>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsConfigCopy> list = goodsConfigCopyMapper.queryGoodsConfigCopyList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsConfigCopyByGccId(Long gccId) {
		int status = goodsConfigCopyMapper.deleteGoodsConfigCopyByGccId(gccId);
		return status == 1 ? true : false;
	}
}

