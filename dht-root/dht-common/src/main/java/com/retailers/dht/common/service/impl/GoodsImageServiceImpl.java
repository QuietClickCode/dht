
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;

import com.retailers.dht.common.dao.GoodsImageCopyMapper;
import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.entity.GoodsImage;
import com.retailers.dht.common.dao.GoodsImageMapper;
import com.retailers.dht.common.entity.GoodsImageCopy;
import com.retailers.dht.common.service.GoodsImageService;
import com.retailers.dht.common.service.GoodsService;
import com.retailers.dht.common.vo.GoodsImageVo;
import com.retailers.mybatis.common.constant.AttachmentConstant;
import com.retailers.mybatis.common.service.AttachmentService;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品图片表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-15 11:48:27
 */
@Service("goodsimageService")
public class GoodsImageServiceImpl implements GoodsImageService {
	@Autowired
	private GoodsImageMapper goodsImageMapper;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private GoodsImageCopyMapper goodsImageCopyMapper;
	@Autowired
	private GoodsService goodsService;

	public boolean saveGoodsImage(GoodsImage goodsImage,Long uploadpersonId) {
		int status = goodsImageMapper.saveGoodsImage(goodsImage);
		if(status==1){
			attachmentService.editorAttachment(goodsImage.getGiId());
			copyGoodsImage(goodsImage,uploadpersonId);
		}
		return status == 1 ? true : false;
	}
	public boolean updateGoodsImage(GoodsImage goodsImage,Long uploadpersonId) {
		int status = goodsImageMapper.updateGoodsImage(goodsImage);
		return status == 1 ? true : false;
	}
	public GoodsImage queryGoodsImageByGiId(Long giId) {
		return goodsImageMapper.queryGoodsImageByGiId(giId);
	}

	public Pagination<GoodsImageVo> queryGoodsImageList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsImageVo> page = new Pagination<GoodsImageVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsImageVo> list = goodsImageMapper.queryGoodsImageList(page);
		if(!ObjectUtils.isEmpty(list)){
			for(GoodsImageVo giv : list){
				giv.setImgUrl(AttachmentConstant.IMAGE_SHOW_URL+giv.getImgUrl());
			}
		}
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsImageByGiId(Long giId,Long uploadpersonId) {
		GoodsImage goodsImage = goodsImageMapper.queryGoodsImageByGiId(giId);
		goodsImage.setIsDelete(1L);
		int status = goodsImageMapper.updateGoodsImage(goodsImage);
		if(status==1){
			copyGoodsImage(goodsImage,uploadpersonId);
		}
		return status == 1 ? true : false;
	}

	public void copyGoodsImage(GoodsImage goodsImage,Long uploadpersonId){
		GoodsImageCopy gic = new GoodsImageCopy();
		BeanUtils.copyProperties(goodsImage,gic);
		gic.setGiUploadpersonid(uploadpersonId);
		goodsImageCopyMapper.saveGoodsImageCopy(gic);

		goodsImage = goodsImageMapper.queryGoodsImageByGiId(goodsImage.getGiId());
		goodsImage.setCopyid(gic.getGicId());
		goodsImageMapper.updateGoodsImage(goodsImage);
		//把商品改为未审核状态
		Goods goods = goodsService.queryGoodsByGid(goodsImage.getGid());
		goods.setIsChecked(0L);
		goodsService.updateGoods(goods,uploadpersonId);
	}
}

