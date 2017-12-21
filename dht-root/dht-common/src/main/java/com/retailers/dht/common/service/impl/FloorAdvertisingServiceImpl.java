
package com.retailers.dht.common.service.impl;

import com.retailers.dht.common.dao.FloorAdvertisingMapper;
import com.retailers.dht.common.entity.FloorAdvertising;
import com.retailers.dht.common.service.FloorAdvertisingService;
import com.retailers.dht.common.vo.FloorAdvertisingVo;
import com.retailers.mybatis.common.constant.AttachmentConstant;
import com.retailers.mybatis.common.service.AttachmentService;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 描述：楼层广告设置Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-21 15:15:44
 */
@Service("flooradvertisingService")
public class FloorAdvertisingServiceImpl implements FloorAdvertisingService {
	@Autowired
	private FloorAdvertisingMapper floorAdvertisingMapper;

	@Autowired
	private AttachmentService attachmentService;


	public boolean saveFloorAdvertising(FloorAdvertising floorAdvertising) {
		attachmentService.editorAttachment(floorAdvertising.getImageId());
		int status = floorAdvertisingMapper.saveFloorAdvertising(floorAdvertising);
		return status == 1 ? true : false;
	}

	public boolean updateFloorAdvertising(FloorAdvertising advertising) {
		System.out.println(advertising.getFaName());
		FloorAdvertising floorAdvertising = floorAdvertisingMapper.queryFloorAdvertisingByFaId(advertising.getFaId());
		if(floorAdvertising.getImageId().compareTo(advertising.getImageId()) != 0) {
			attachmentService.editorAttachment(floorAdvertising.getImageId(), AttachmentConstant.ATTACHMENT_STATUS_NO);
			attachmentService.editorAttachment(advertising.getImageId());
		}
		advertising.setVersion(floorAdvertising.getVersion());
		int status = floorAdvertisingMapper.updateFloorAdvertising(advertising);
		return status == 1 ? true : false;
	}
	public FloorAdvertising queryFloorAdvertisingByFaId(Long faId) {
		return floorAdvertisingMapper.queryFloorAdvertisingByFaId(faId);
	}

	public Pagination<FloorAdvertising> queryFloorAdvertisingList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<FloorAdvertising> page = new Pagination<FloorAdvertising>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<FloorAdvertising> list = floorAdvertisingMapper.queryFloorAdvertisingList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteFloorAdvertisingByFaId(Long faId) {
		FloorAdvertising advertising = floorAdvertisingMapper.queryFloorAdvertisingByFaId(faId);
		advertising.setIsDelete(1L);
		attachmentService.editorAttachment(advertising.getImageId(), AttachmentConstant.ATTACHMENT_STATUS_NO);
		int status = floorAdvertisingMapper.updateFloorAdvertising(advertising);
		return status == 1 ? true : false;
	}

	public List<FloorAdvertisingVo> queryGoodsClassificationList() {
		return floorAdvertisingMapper.queryGoodsClassificationList();
	}

	public List<FloorAdvertisingVo> queryFloorAdvertisingVo() {
		return floorAdvertisingMapper.queryFloorAdvertisingVo();
	}

	public List<FloorAdvertisingVo> queryFloorAdvertisingTree() {
		List<FloorAdvertisingVo> advertisingVos = floorAdvertisingMapper.queryGoodsClassificationList();
		for(FloorAdvertisingVo vo:advertisingVos)
			vo.setFaId(vo.getFaId()*100000);
		List<FloorAdvertisingVo> advertisingVoList = floorAdvertisingMapper.queryFloorAdvertisingVo();
		for(FloorAdvertisingVo vo:advertisingVoList){
			vo.setParentId(vo.getParentId()*100000);
			vo.setImageUrl(AttachmentConstant.IMAGE_SHOW_URL+vo.getImageUrl());
		}
		advertisingVos.addAll(advertisingVoList);
		List<FloorAdvertisingVo> rtnList=new ArrayList<FloorAdvertisingVo>();
		Map<Long,Map<Long,Long>> child=new HashMap<Long, Map<Long, Long>>();
		queryChildNode(advertisingVos,child);
		Map<Long,Long> alloShow=new HashMap<Long, Long>();
		queryAllFloors(null,child,alloShow);
		for(FloorAdvertisingVo vo:advertisingVos){
			if(ObjectUtils.isEmpty(vo.getParentId())){
				vo.setLevel(1l);
				rtnList.add(vo);
			}else{
				if(alloShow.containsKey(vo.getParentId())){
					rtnList.add(vo);
				}
				vo.setLevel(2l);
			}
		}
		return rtnList;

	}

	private void queryAllFloors(Long parentId,Map<Long,Map<Long,Long>> map,Map<Long,Long>allowMap){
		if(ObjectUtils.isEmpty(parentId)){
			parentId=-1l;
		}
		Map<Long,Long> child=map.get(parentId);
		if(ObjectUtils.isNotEmpty(child)&&!child.isEmpty()){
			for(Long key:child.keySet()){
				allowMap.put(key,key);
				if(map.containsKey(key)){
					queryAllFloors(key,map,allowMap);
				}
			}
		}
	}


	private void queryChildNode(List<FloorAdvertisingVo> list, Map<Long,Map<Long,Long>> child){
		for(FloorAdvertisingVo vo:list){
			Long parentId=vo.getParentId();
			if(ObjectUtils.isEmpty(parentId)){
				parentId=-1l;
			}
			if(child.containsKey(parentId)){
				child.get(parentId).put(vo.getFaId(),vo.getIsDelete());
			}else{
				Map<Long,Long> maps=new HashMap<Long, Long>();
				maps.put(vo.getFaId(),vo.getIsDelete());
				child.put(parentId,maps);
			}
		}
	}

	public List<FloorAdvertisingVo> queryFloorAdvertisingListByGclassId(Map params){
		Pagination<FloorAdvertising> pagination = new Pagination<FloorAdvertising>();
		pagination.setParams(params);
		List<FloorAdvertisingVo> list = floorAdvertisingMapper.queryFloorAdvertisingListByGclassId(pagination);
		for(FloorAdvertisingVo floorAdvertisingVo:list){
			floorAdvertisingVo.setImageUrl(AttachmentConstant.IMAGE_SHOW_URL+floorAdvertisingVo.getImageUrl());
		}
		return list;
	}
}

