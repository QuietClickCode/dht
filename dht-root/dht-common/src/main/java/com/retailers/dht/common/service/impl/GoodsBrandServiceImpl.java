
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;

import com.retailers.dht.common.constant.AttachmentConstant;
import com.retailers.dht.common.entity.GoodsBrand;
import com.retailers.dht.common.dao.GoodsBrandMapper;
import com.retailers.dht.common.service.AttachmentService;
import com.retailers.dht.common.service.GoodsBrandService;
import com.retailers.dht.common.vo.GoodsBrandVo;
import com.retailers.tools.utils.ObjectUtils;
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
	@Autowired
	private AttachmentService attachmentService;
	public boolean saveGoodsBrand(GoodsBrand goodsBrand) {
		int status = goodsBrandMapper.saveGoodsBrand(goodsBrand);

		String imgPath=goodsBrand.getGbImgpath();
		if(status==1 && imgPath!=null && !imgPath.equals("")){
			Long imgPathId = Long.parseLong(imgPath);
			attachmentService.editorAttachment(imgPathId);
		}
		return status == 1 ? true : false;
	}
	public boolean updateGoodsBrand(GoodsBrand goodsBrand) {
		int status = goodsBrandMapper.updateGoodsBrand(goodsBrand);
		GoodsBrand g = goodsBrandMapper.queryGoodsBrandByGbId(goodsBrand.getGbId());
		if(!g.getGbImgpath().equals(goodsBrand.getGbImgpath())){
			attachmentService.editorAttachment(Long.parseLong(g.getGbImgpath()),AttachmentConstant.ATTACHMENT_STATUS_NO);
		}
		String imgPath=goodsBrand.getGbImgpath();
		if(status==1 && imgPath!=null && !imgPath.equals("")){
			Long imgPathId = Long.parseLong(imgPath);
			attachmentService.editorAttachment(imgPathId);
		}
		return status == 1 ? true : false;
	}
	public GoodsBrand queryGoodsBrandByGbId(Long gbId) {
		return goodsBrandMapper.queryGoodsBrandByGbId(gbId);
	}

	public Pagination<GoodsBrandVo> queryGoodsBrandList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<GoodsBrandVo> page = new Pagination<GoodsBrandVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsBrandVo> list = goodsBrandMapper.queryGoodsBrandList(page);
		if(!ObjectUtils.isEmpty(list)){
			for (int i=0; i<list.size(); i++){
				GoodsBrandVo g = list.get(i);
				g.setImgUrl(AttachmentConstant.IMAGE_SHOW_URL+g.getImgUrl());
			}
		}

		page.setData(list);
		return page;
	}
	public boolean deleteGoodsBrandByGbId(Long gbId) {
		GoodsBrand goodsBrand = goodsBrandMapper.queryGoodsBrandByGbId(gbId);
		if(!ObjectUtils.isEmpty(goodsBrand.getGbImgpath())){
			attachmentService.editorAttachment(Long.parseLong(goodsBrand.getGbImgpath()),AttachmentConstant.ATTACHMENT_STATUS_NO);
		}
		goodsBrand.setIsDelete(1L);
		int status = goodsBrandMapper.updateGoodsBrand(goodsBrand);
		return status == 1 ? true : false;
	}
}

