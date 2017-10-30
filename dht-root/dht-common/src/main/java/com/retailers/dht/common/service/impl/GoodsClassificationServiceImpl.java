
package com.retailers.dht.common.service.impl;

import com.retailers.dht.common.constant.AttachmentConstant;
import com.retailers.dht.common.dao.GoodsClassificationMapper;
import com.retailers.dht.common.entity.GoodsClassification;
import com.retailers.dht.common.service.AttachmentService;
import com.retailers.dht.common.service.GoodsClassificationService;
import com.retailers.dht.common.vo.GoodsClassificationVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：商品子类表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-09-29 14:58:38
 */
@Service("goodsclassificationService")
public class GoodsClassificationServiceImpl implements GoodsClassificationService {
	@Autowired
	private GoodsClassificationMapper goodsClassificationMapper;
	@Autowired
	private AttachmentService attachmentService;
	public boolean saveGoodsClassification(GoodsClassification goodsClassification) {
		Long parentId = goodsClassification.getParentId();
		Long isTop = goodsClassification.getIsTop();
		if(parentId==null && isTop.equals(0L)){
			return false;
		}

		int status = goodsClassificationMapper.saveGoodsClassification(goodsClassification);
		if(status==1 && goodsClassification.getGgImgpath()!=null && !goodsClassification.getGgImgpath().equals("")){
			attachmentService.editorAttachment(Long.parseLong(goodsClassification.getGgImgpath()));
		}
		return status == 1 ? true : false;
	}
	public boolean updateGoodsClassification(GoodsClassification goodsClassification) {
		Long isTop = goodsClassification.getIsTop();
		if(isTop.equals(1L)){
			goodsClassification.setParentId(null);
		}else{
			Long parentId = goodsClassification.getParentId();
			if(parentId==null){
				return false;
			}
		}

		GoodsClassification g = goodsClassificationMapper.queryGoodsClassificationByGgId(goodsClassification.getGgId());
		if(!g.getGgImgpath().equals(goodsClassification.getGgImgpath()) && !ObjectUtils.isEmpty(g.getGgImgpath())){
			attachmentService.editorAttachment(Long.parseLong(g.getGgImgpath()),AttachmentConstant.ATTACHMENT_STATUS_NO);
		}

		int status = goodsClassificationMapper.updateGoodsClassification(goodsClassification);
		if(status==1 && goodsClassification.getGgImgpath()!=null && !goodsClassification.getGgImgpath().equals("")){
			attachmentService.editorAttachment(Long.parseLong(goodsClassification.getGgImgpath()));
		}
		return status == 1 ? true : false;
	}
	public GoodsClassification queryGoodsClassificationByGgId(Long ggId) {
		return goodsClassificationMapper.queryGoodsClassificationByGgId(ggId);
	}

	public Pagination<GoodsClassification> queryGoodsClassificationList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<GoodsClassification> page = new Pagination<GoodsClassification>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		System.out.println(params.get("isDelete"));
		List<GoodsClassification> list = goodsClassificationMapper.queryGoodsClassificationList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsClassificationByGgId(Long ggId) {
		GoodsClassification goodsClassification = goodsClassificationMapper.queryGoodsClassificationByGgId(ggId);
		if(!ObjectUtils.isEmpty(goodsClassification.getGgImgpath())){
			attachmentService.editorAttachment(Long.parseLong(goodsClassification.getGgImgpath()),AttachmentConstant.ATTACHMENT_STATUS_NO);
		}
		goodsClassification.setIsDelete(1L);
		int status = goodsClassificationMapper.updateGoodsClassification(goodsClassification);
		return status == 1 ? true : false;
	}
	public List<GoodsClassificationVo> queryGoodsClassificationTree() {
		List<GoodsClassificationVo> goodsClassificationList = goodsClassificationMapper.queryGoodsClassificationTree();
		List<GoodsClassificationVo> rntList = new ArrayList<GoodsClassificationVo>();
		Map<Long, Map<Long, Long>> child = new HashMap<Long, Map<Long, Long>>();
		queryChildNode(goodsClassificationList, child);
		Map<Long, Long> alloShow = new HashMap<Long, Long>();
		queryAllowGoodsClassification(null, child, alloShow);
		for (GoodsClassificationVo vo : goodsClassificationList) {
			if (ObjectUtils.isEmpty(vo.getParentId())) {
				vo.setLevel(1l);
				rntList.add(vo);
			} else {
				if (alloShow.containsKey(vo.getParentId())) {
					rntList.add(vo);
				}
				vo.setLevel(2l);
			}
		}
		queryGoodsClassificationNode(1+"");

		if(!ObjectUtils.isEmpty(rntList)){
			for (int i=0; i<rntList.size(); i++){
				GoodsClassificationVo g = rntList.get(i);
				g.setImgUrl(AttachmentConstant.IMAGE_SHOW_URL+g.getImgUrl());
			}
		}
		return rntList;
	}

	public List<GoodsClassificationVo> queryGoodsClassificationNode(String ggId) {
		List<GoodsClassificationVo> list =goodsClassificationMapper.queryGoodsClassificationNode(ggId);
		List<GoodsClassificationVo> rtnList=new ArrayList<GoodsClassificationVo>();
		Map<Long,Map<Long,Long>> child=new HashMap<Long, Map<Long, Long>>();
		queryChildNode(list,child);
		Map<Long,Long> alloShow=new HashMap<Long, Long>();
		queryAllowGoodsClassification(null,child,alloShow);
		for(GoodsClassificationVo vo:list){
			if(ObjectUtils.isEmpty(vo.getParentId())){
				rtnList.add(vo);
			}else{
				if(alloShow.containsKey(vo.getParentId())){
					rtnList.add(vo);
				}
			}
		}
		return rtnList;
	}

	/**
	 * 取得节点下的所有子节点
	 * @param list 树型菜单
	 * @param child
	 */
	private void queryChildNode(List<GoodsClassificationVo> list,Map<Long,Map<Long,Long>> child){
		for(GoodsClassificationVo vo:list){
			Long parentId=vo.getParentId();
			if(ObjectUtils.isEmpty(parentId)){
				parentId=-1l;
			}
			if(child.containsKey(parentId)){
				child.get(parentId).put(vo.getGgId(),vo.getIsDelete());
			}else{
				Map<Long,Long> maps=new HashMap<Long, Long>();
				maps.put(vo.getGgId(),vo.getIsDelete());
				child.put(parentId,maps);
			}
		}
	}

	/**
	 * 取得允许展示节点，排除无节点的数据
	 * @param ggManager
	 * @param map
	 * @param allowMap
	 */
	private void queryAllowGoodsClassification(Long ggManager,Map<Long,Map<Long,Long>> map,Map<Long,Long>allowMap){
		if(ObjectUtils.isEmpty(ggManager)){
			ggManager=-1l;
		}
		Map<Long,Long> child=map.get(ggManager);
		if(ObjectUtils.isNotEmpty(child)&&!child.isEmpty()){
			for(Long key:child.keySet()){
					allowMap.put(key,key);
					if(map.containsKey(key)){
						queryAllowGoodsClassification(key,map,allowMap);
					}
			}
		}
	}

}

