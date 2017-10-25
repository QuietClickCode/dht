
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsCommentlabel;
import com.retailers.dht.common.dao.GoodsCommentlabelMapper;
import com.retailers.dht.common.service.GoodsCommentlabelService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品评论标签表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-24 17:36:13
 */
@Service("goodscommentlabelService")
public class GoodsCommentlabelServiceImpl implements GoodsCommentlabelService {
	@Autowired
	private GoodsCommentlabelMapper goodsCommentlabelMapper;
	public boolean saveGoodsCommentlabel(GoodsCommentlabel goodsCommentlabel) {
		int status = goodsCommentlabelMapper.saveGoodsCommentlabel(goodsCommentlabel);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsCommentlabel(GoodsCommentlabel goodsCommentlabel) {
		int status = goodsCommentlabelMapper.updateGoodsCommentlabel(goodsCommentlabel);
		return status == 1 ? true : false;
	}
	public GoodsCommentlabel queryGoodsCommentlabelByGclId(Long gclId) {
		return goodsCommentlabelMapper.queryGoodsCommentlabelByGclId(gclId);
	}

	public Pagination<GoodsCommentlabel> queryGoodsCommentlabelList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsCommentlabel> page = new Pagination<GoodsCommentlabel>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsCommentlabel> list = goodsCommentlabelMapper.queryGoodsCommentlabelList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsCommentlabelByGclId(Long gclId) {
		GoodsCommentlabel goodsCommentlabel = queryGoodsCommentlabelByGclId(gclId);
		goodsCommentlabel.setIsDelete(1L);
		int status = goodsCommentlabelMapper.updateGoodsCommentlabel(goodsCommentlabel);
		return status == 1 ? true : false;
	}
}

