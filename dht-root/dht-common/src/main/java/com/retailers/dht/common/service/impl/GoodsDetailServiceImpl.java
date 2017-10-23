
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;

import com.retailers.dht.common.constant.AttachmentConstant;
import com.retailers.dht.common.entity.GoodsDetail;
import com.retailers.dht.common.dao.GoodsDetailMapper;
import com.retailers.dht.common.service.AttachmentService;
import com.retailers.dht.common.service.GoodsDetailService;
import com.retailers.dht.common.vo.GoodsDetailVo;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品详情表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-21 10:51:10
 */
@Service("goodsdetailService")
public class GoodsDetailServiceImpl implements GoodsDetailService {
	@Autowired
	private GoodsDetailMapper goodsDetailMapper;
	@Autowired
	private AttachmentService attachmentService;
	public GoodsDetail saveGoodsDetail(GoodsDetail goodsDetail) {
		Long gdImgid = goodsDetail.getGdImgid();
		int status = goodsDetailMapper.saveGoodsDetail(goodsDetail);
		if(!ObjectUtils.isEmpty(gdImgid)){
			if(gdImgid != -1 && status ==1){
				attachmentService.editorAttachment(gdImgid);
			}
		}

		return status == 1 ? goodsDetail : null;
	}
	public boolean updateGoodsDetail(GoodsDetail goodsDetail) {
		int status = goodsDetailMapper.updateGoodsDetail(goodsDetail);
		return status == 1 ? true : false;
	}
	public GoodsDetailVo queryGoodsDetailByGdId(Long gdId) {
		GoodsDetailVo goodsDetailVo = goodsDetailMapper.queryGoodsDetailByGdId(gdId);
		goodsDetailVo.setImgUrl(AttachmentConstant.IMAGE_SHOW_URL+goodsDetailVo.getImgUrl());
		return goodsDetailVo;
	}

	public Pagination<GoodsDetail> queryGoodsDetailList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsDetail> page = new Pagination<GoodsDetail>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsDetail> list = goodsDetailMapper.queryGoodsDetailList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsDetailByGdId(Long gdId) {
		int status = goodsDetailMapper.deleteGoodsDetailByGdId(gdId);
		return status == 1 ? true : false;
	}
}

