
package com.retailers.dht.common.service.impl;

import com.retailers.auth.vo.ZTreeVo;
import com.retailers.dht.common.constant.AttachmentConstant;
import com.retailers.dht.common.dao.GoodsCopyMapper;
import com.retailers.dht.common.dao.GoodsMapper;
import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.entity.GoodsClassification;
import com.retailers.dht.common.entity.GoodsCopy;
import com.retailers.dht.common.service.AttachmentService;
import com.retailers.dht.common.service.GoodsClassificationService;
import com.retailers.dht.common.service.GoodsService;
import com.retailers.dht.common.utils.AttachmentUploadImageUtils;
import com.retailers.dht.common.vo.GoodsVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 描述：商品表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-12 17:29:40
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodsCopyMapper goodsCopyMapper;
	@Autowired
	private GoodsClassificationService goodsClassificationService;
	@Autowired
	private AttachmentService attachmentService;
	public Goods saveGoods(Goods goods,Long uploadpersonId) {
		int status = goodsMapper.saveGoods(goods);
		if(status==1){
			copyGoods(goods,uploadpersonId);
			Map<Long,Long> atts = AttachmentUploadImageUtils.findUploadImages(goods.getGdescription());
			List<Long> list = new ArrayList<Long>();
			if(!atts.isEmpty()){
				for(Long id:atts.keySet()){
					list.add(id);
				}
			}
			attachmentService.editorAttachment(list);
		}
		goods.setVersion(1L);
		return status == 1 ? goods : null;
	}
	public boolean updateGoods(Goods goods,Long uploadpersonId) {
		goods.setIsChecked(0L);
		int status = goodsMapper.updateGoods(goods);
		if(status==1){
			goods.setVersion(goods.getVersion()+1);
			copyGoods(goods,uploadpersonId);
			Map<Long,Long> atts = AttachmentUploadImageUtils.findUploadImages(goods.getGdescription());
			List<Long> list = new ArrayList<Long>();
			if(!atts.isEmpty()){
				for(Long id:atts.keySet()){
					list.add(id);
				}
			}
			attachmentService.editorAttachment(list);
		}
		return status == 1 ? true : false;
	}
	public Goods queryGoodsByGid(Long gid) {
		return goodsMapper.queryGoodsByGid(gid);
	}

	public Pagination<GoodsVo> queryGoodsList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<GoodsVo> page = new Pagination<GoodsVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		if(!ObjectUtils.isEmpty(params.get("gclassification"))){
			Long gclassification = (Long)params.get("gclassification");
			List<Long> gclassIds = new ArrayList<Long>();
			gclassIds.add(gclassification);

			List<GoodsClassification> list = goodsClassificationService.selectAllGClassList();
			if(!ObjectUtils.isEmpty(list)){
				for(GoodsClassification goodsClassification1:list){
					if(gclassification.equals(goodsClassification1.getParentId())){
						gclassIds.add(goodsClassification1.getGgId());
						for(GoodsClassification goodsClassification2:list){
							if(goodsClassification1.getGgId().equals(goodsClassification2.getParentId())){
								gclassIds.add(goodsClassification2.getGgId());
							}
						}
					}
				}
			}
			params.put("gclassification",gclassIds);
		}
		page.setParams(params);
		List<GoodsVo> list = goodsMapper.queryGoodsList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsByGid(Long gid,Long uploadpersonId) {
		Goods goods = goodsMapper.queryGoodsByGid(gid);
		goods.setIsDelete(1L);
		return updateGoods(goods,uploadpersonId);
	}

	public void copyGoods(Goods goods,Long uploadpersonId){
		GoodsCopy goodsCopy = new GoodsCopy();
		BeanUtils.copyProperties(goods,goodsCopy);
		goodsCopy.setGuploadperson(uploadpersonId);
		goodsCopyMapper.saveGoodsCopy(goodsCopy);

		goods.setGcopyid(goodsCopy.getGcId());
		goodsMapper.updateGoods(goods);

	}

	public  boolean checkGoods(Goods goods ){
		int status = goodsMapper.updateGoods(goods);
		return status==1 ? true:false;
	}

	public boolean updateGoodsSetNotChecked(Long gid,Long uploadpersonId){
		Goods goods = goodsMapper.queryGoodsByGid(gid);
		goods.setIsChecked(0L);
		return updateGoods(goods,uploadpersonId);
	}

	public Pagination<GoodsVo> querySamegclassGoods(Map<String, Object> params, int pageNo, int pageSize){
		Pagination<GoodsVo> page = new Pagination<GoodsVo>();
		page.setParams(params);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		List<GoodsVo> list = goodsMapper.querySamegclassGoods(page);
		for(GoodsVo goodsVo:list){
			goodsVo.setImgUrl(AttachmentConstant.IMAGE_SHOW_URL+goodsVo.getImgUrl());
		}
		page.setData(list);
		return page;
	}

	public List<ZTreeVo> queryGoodsByGt(Long gt) {
		List<Goods> lists =goodsMapper.queryGoodsByGt(gt);
		List<ZTreeVo> rtn=new ArrayList<ZTreeVo>();
		for(Goods g:lists){
			ZTreeVo vo = new ZTreeVo();
			vo.setId(g.getGid());
			vo.setName(g.getGname());
			vo.setpId(-gt);
			rtn.add(vo);
		}
		return rtn;
	}
}

