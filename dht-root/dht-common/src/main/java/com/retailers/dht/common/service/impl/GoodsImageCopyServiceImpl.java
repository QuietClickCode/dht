
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsImageCopy;
import com.retailers.dht.common.dao.GoodsImageCopyMapper;
import com.retailers.dht.common.service.GoodsImageCopyService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品图片副本表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-17 14:25:01
 */
@Service("goodsimagecopyService")
public class GoodsImageCopyServiceImpl implements GoodsImageCopyService {
	@Autowired
	private GoodsImageCopyMapper goodsImageCopyMapper;
	public boolean saveGoodsImageCopy(GoodsImageCopy goodsImageCopy) {
		int status = goodsImageCopyMapper.saveGoodsImageCopy(goodsImageCopy);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsImageCopy(GoodsImageCopy goodsImageCopy) {
		int status = goodsImageCopyMapper.updateGoodsImageCopy(goodsImageCopy);
		return status == 1 ? true : false;
	}
	public GoodsImageCopy queryGoodsImageCopyByGicId(Long gicId) {
		return goodsImageCopyMapper.queryGoodsImageCopyByGicId(gicId);
	}

	public Pagination<GoodsImageCopy> queryGoodsImageCopyList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsImageCopy> page = new Pagination<GoodsImageCopy>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsImageCopy> list = goodsImageCopyMapper.queryGoodsImageCopyList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsImageCopyByGicId(Long gicId) {
		int status = goodsImageCopyMapper.deleteGoodsImageCopyByGicId(gicId);
		return status == 1 ? true : false;
	}
}

