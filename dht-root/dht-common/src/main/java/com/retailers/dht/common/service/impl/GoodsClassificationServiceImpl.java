
package com.retailers.dht.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.vo.ZTreeVo;
import com.retailers.dht.common.constant.AttachmentConstant;
import com.retailers.dht.common.constant.CouponConstant;
import com.retailers.dht.common.dao.CouponUseRangeMapper;
import com.retailers.dht.common.dao.GoodsClassificationMapper;
import com.retailers.dht.common.entity.CouponUseRange;
import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.entity.GoodsClassification;
import com.retailers.dht.common.service.AttachmentService;
import com.retailers.dht.common.service.GoodsClassificationService;
import com.retailers.dht.common.service.GoodsService;
import com.retailers.dht.common.vo.GoodsClassificationVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.ConsoleHandler;

/**
 * 描述：商品子类表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-09-29 14:58:38
 */
@Service("goodsclassificationService")
public class GoodsClassificationServiceImpl implements GoodsClassificationService {
	Logger logger = LoggerFactory.getLogger(GoodsClassificationServiceImpl.class);

	@Autowired
	private GoodsClassificationMapper goodsClassificationMapper;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private CouponUseRangeMapper couponUseRangeMapper;
	@Autowired
	private GoodsService goodsService;

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
		List<Long> a = new ArrayList<Long>();
		a.add(1L);
		queryGoodsClassificationNode(a);

		if(!ObjectUtils.isEmpty(rntList)){
			for (int i=0; i<rntList.size(); i++){
				GoodsClassificationVo g = rntList.get(i);
				g.setImgUrl(AttachmentConstant.IMAGE_SHOW_URL+g.getImgUrl());
			}
		}
		return rntList;
	}

	public List<GoodsClassificationVo> queryGoodsClassificationNode(List<Long> ggIds) {
		List<GoodsClassificationVo> list =goodsClassificationMapper.queryGoodsClassificationNode(ggIds);
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
	 * 取得商品类型树
	 * @param cpId 优惠卷id
	 * @return
	 */
	public List<ZTreeVo> querGoodsClassificationTree(Long type,Long cpId) {
		//该优惠卷下选中的项
		Map<Long,Long> map=new HashMap<Long, Long>();
		//判断是否有优惠卷id
		if(ObjectUtils.isNotEmpty(cpId)){
			//取得优惠卷所有商品类型
			List<CouponUseRange> curs=couponUseRangeMapper.queryCouponUseRangeByCpId(type,cpId, CouponConstant.COUPON_USED_RANGE_GOODS_TYPE);
			for(CouponUseRange cur:curs){
				if(cur.getCpurIsAllow()==0){
					map.put(cur.getCpurRelevanceId(),cur.getCpurRelevanceId());
				}
			}
		}

		List<GoodsClassification> list =goodsClassificationMapper.queryAllGoodsClassificationByGtId();
		List<ZTreeVo> rtnList=new ArrayList<ZTreeVo>();
		Map<Long,Map<Long,Long>> child=new HashMap<Long, Map<Long, Long>>();
		queryChildNodes(list,child);
		Map<Long,Long> alloShow=new HashMap<Long, Long>();
		queryAllowGoodsClassification(null,child,alloShow);
		for(GoodsClassification gcf:list){
			if(ObjectUtils.isEmpty(gcf.getParentId())){
				ZTreeVo ztv=new ZTreeVo();
				ztv.setId(gcf.getGgId());
				ztv.setName(gcf.getGgName());
				ztv.setpId(-1l);
				if(map.containsKey(gcf.getGgId())){
					ztv.setChecked(true);
				}else{
					ztv.setChecked(false);
				}
				rtnList.add(ztv);
			}else{
				if(alloShow.containsKey(gcf.getParentId())){
					ZTreeVo ztv=new ZTreeVo();
					ztv.setId(gcf.getGgId());
					ztv.setName(gcf.getGgName());
					ztv.setpId(gcf.getParentId());
					rtnList.add(ztv);
					if(map.containsKey(gcf.getGgId())){
						ztv.setChecked(true);
					}else{
						ztv.setChecked(false);
					}
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
	 * 取得节点下的所有子节点
	 * @param list 树型菜单
	 * @param child
	 */
	private void queryChildNodes(List<GoodsClassification> list,Map<Long,Map<Long,Long>> child){
		for(GoodsClassification gcf:list){
			Long parentId=gcf.getParentId();
			if(ObjectUtils.isEmpty(parentId)){
				parentId=-1l;
			}
			if(child.containsKey(parentId)){
				child.get(parentId).put(gcf.getGgId(),gcf.getIsDelete());
			}else{
				Map<Long,Long> maps=new HashMap<Long, Long>();
				maps.put(gcf.getGgId(),gcf.getIsDelete());
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

	public List<GoodsClassificationVo> queryGoodsClassificationListByParentId(Long parentId){
		Map map = new HashMap();
		map.put("parentId",parentId);
		map.put("isShow",1L);
		map.put("isDelete",0L);
		List<GoodsClassificationVo> list = goodsClassificationMapper.queryGoodsClassificationListByParentId(map);
		for(GoodsClassificationVo goodsClassificationVo:list){
			goodsClassificationVo.setImgUrl(AttachmentConstant.IMAGE_SHOW_URL+goodsClassificationVo.getImgUrl());
		}
		return list;
	}
	public List<GoodsClassification> selectAllGClassList(){
		return goodsClassificationMapper.selectAllGClassList();
	}

	/**
	 *
	 * @param curNode 当前节点
	 * @return
	 */
	public List<Long> queryGoodsClassificationParents(Long curNode) {
		List<GoodsClassification> lists =goodsClassificationMapper.queryAllGoodsClassificationByGtId();
		Map<Long,Long> map = new HashMap<Long, Long>();
		for(GoodsClassification gc: lists){
			map.put(gc.getGgId(),gc.getParentId());
		}
		List<Long> rtnList = new ArrayList<Long>();
		rtnList.add(curNode);
		long loopNode=curNode;
		while (map.containsKey(loopNode)){
			if(ObjectUtils.isNotEmpty(map.get(loopNode))){
				loopNode=map.get(loopNode);
				rtnList.add(loopNode);
			}else{
				break;
			}

		}

		return rtnList;
	}

	/**
	 * 取得所有叶子节点
	 * @param curNode 当前节点
	 * @return
	 */
	public List<Long> queryGoodsClassificationChilds(Long curNode) {
		List<GoodsClassification> list =goodsClassificationMapper.queryAllGoodsClassificationByGtId();
		List<Long> rtnList=new ArrayList<Long>();
		Map<Long,Map<Long,Long>> child=new HashMap<Long, Map<Long, Long>>();
		queryChildNodes(list,child);
		Map<Long,Long> alloShow=new HashMap<Long, Long>();
		queryAllowGoodsClassification(null,child,alloShow);
		rtnList.add(curNode);
		queryAllChild(curNode,child,alloShow,rtnList);
		return rtnList;
	}

	public Map<Long, Map<String, Long>> queryGoodsClassificationByGids(List<Long> gids) {
		Date curDate=new Date();
		logger.info("根据商品id取得对应的商品种类开始");
		//取得所有的
		List<GoodsClassification> list =goodsClassificationMapper.queryAllGoodsClassificationByGtId();
		//取和疝例表
		List<Goods> goods=goodsService.queryGoodsByIds(gids);
		Map<Long,Long> map = new HashMap<Long, Long>();
		Set<Long> gcIds=new HashSet<Long>();
		for(Goods g:goods){
			map.put(g.getGid(),g.getGclassification());
			gcIds.add(g.getGclassification());
		}
		Map<Long,Map<String,Long>> rtn = new HashMap<Long, Map<String, Long>>();
		//取得商品类型层级
		Map<Long,Long> gcTree=new HashMap<Long, Long>();
		Map<Long,GoodsClassification> gcMap=new HashMap<Long, GoodsClassification>();
		for(GoodsClassification gc:list){
			gcTree.put(gc.getGgId(),gc.getParentId());
			gcMap.put(gc.getGgId(),gc);
		}
		Map<Long,Long> topNodes=new HashMap<Long, Long>();
		//取得商品顶层
		for(Long id:gcIds){
			topNodes.put(id,topNode(gcTree,id));
		}
		for(Long key:map.keySet()){
			Map<String,Long> infos=new HashMap<String, Long>();
			long curNode=map.get(key);
			//顶层节点类型
			infos.put("topNodes",topNodes.get(curNode));
			//当前 节点
			infos.put("curNodes",map.get(key));
			if(ObjectUtils.isNotEmpty(gcMap.get(topNodes.get(curNode)))){
				infos.put("topNodesCash",gcMap.get(topNodes.get(curNode)).getIsReturnnow());
			}
			if(ObjectUtils.isNotEmpty(gcMap.get(curNode))){
				infos.put("curNodesCash",gcMap.get(curNode).getIsReturnnow());
			}
			rtn.put(key,infos);
		}
		logger.info("根据商品id取得对应的商品种类结束，执行时间:[{}]",(System.currentTimeMillis()-curDate.getTime()));
		return rtn;
	}


	private Long topNode(Map<Long,Long> map,Long id){
		//取得该节点的直接上级
		Long parsentIds=map.get(id);
		while (ObjectUtils.isNotEmpty(map.get(parsentIds))){
			if(ObjectUtils.isEmpty(map.get(parsentIds))){
				break;
			}
			parsentIds=map.get(parsentIds);
		}
		return parsentIds;
	}



	private void queryAllChild(Long curNode, Map<Long,Map<Long,Long>> tree, Map<Long,Long> allow, List<Long> childList){
		if(tree.containsKey(curNode)){
			Map<Long,Long> childMap=tree.get(curNode);
			for(Long childs:childMap.keySet()){
				if(allow.containsKey(childs)){
					childList.add(childs);
					if(tree.containsKey(childs)){
						queryAllChild(childs,tree,allow,childList);
					}
				}
			}
		}
	}
}

