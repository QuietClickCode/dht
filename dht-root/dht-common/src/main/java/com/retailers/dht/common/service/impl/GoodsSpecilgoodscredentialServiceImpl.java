
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;

import com.retailers.dht.common.entity.GoodsSpecilgoodscredential;
import com.retailers.dht.common.dao.GoodsSpecilgoodscredentialMapper;
import com.retailers.dht.common.service.GoodsSpecilgoodscredentialService;
import com.retailers.dht.common.vo.GoodsSpecilgoodscredentialVo;
import com.retailers.mybatis.common.constant.AttachmentConstant;
import com.retailers.mybatis.common.service.AttachmentService;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：特殊商品证件表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-26 17:15:12
 */
@Service("goodsspecilgoodscredentialService")
public class GoodsSpecilgoodscredentialServiceImpl implements GoodsSpecilgoodscredentialService {
	@Autowired
	private GoodsSpecilgoodscredentialMapper goodsSpecilgoodscredentialMapper;
	@Autowired
	private AttachmentService attachmentService;
	public boolean saveGoodsSpecilgoodscredential(GoodsSpecilgoodscredential goodsSpecilgoodscredential) {
		Long gsgImgid = goodsSpecilgoodscredential.getGsgImgid();
		if(!ObjectUtils.isEmpty(gsgImgid)){
			attachmentService.editorAttachment(gsgImgid);
		}
		int status = goodsSpecilgoodscredentialMapper.saveGoodsSpecilgoodscredential(goodsSpecilgoodscredential);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsSpecilgoodscredential(GoodsSpecilgoodscredential goodsSpecilgoodscredential) {
		int status = goodsSpecilgoodscredentialMapper.updateGoodsSpecilgoodscredential(goodsSpecilgoodscredential);
		return status == 1 ? true : false;
	}
	public GoodsSpecilgoodscredential queryGoodsSpecilgoodscredentialByGsgId(Long gsgId) {
		return goodsSpecilgoodscredentialMapper.queryGoodsSpecilgoodscredentialByGsgId(gsgId);
	}

	public Pagination<GoodsSpecilgoodscredentialVo> queryGoodsSpecilgoodscredentialList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<GoodsSpecilgoodscredentialVo> page = new Pagination<GoodsSpecilgoodscredentialVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsSpecilgoodscredentialVo> list = goodsSpecilgoodscredentialMapper.queryGoodsSpecilgoodscredentialList(page);
		for(GoodsSpecilgoodscredentialVo goodsSpecilgoodscredentialVo :list){
			if(!ObjectUtils.isEmpty(goodsSpecilgoodscredentialVo.getGsgImgid())){
				goodsSpecilgoodscredentialVo.setImgUrl(AttachmentConstant.IMAGE_SHOW_URL+goodsSpecilgoodscredentialVo.getImgUrl());
			}
		}
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsSpecilgoodscredentialByGsgId(Long gsgId) {
		GoodsSpecilgoodscredential goodsSpecilgoodscredential = queryGoodsSpecilgoodscredentialByGsgId(gsgId);
		goodsSpecilgoodscredential.setIsDelete(1L);
		Long gsgImgid = goodsSpecilgoodscredential.getGsgImgid();
		if(!ObjectUtils.isEmpty(gsgImgid)){
			attachmentService.editorAttachment(gsgImgid, AttachmentConstant.ATTACHMENT_STATUS_NO);
		}
		int status = goodsSpecilgoodscredentialMapper.updateGoodsSpecilgoodscredential(goodsSpecilgoodscredential);
		return status == 1 ? true : false;
	}
}

