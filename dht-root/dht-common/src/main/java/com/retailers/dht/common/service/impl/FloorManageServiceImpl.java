
package com.retailers.dht.common.service.impl;

import com.retailers.dht.common.dao.FloorManageMapper;
import com.retailers.dht.common.entity.FloorManage;
import com.retailers.dht.common.service.FloorManageService;
import com.retailers.dht.common.vo.FloorManageVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 描述：主页楼层表Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-12 14:20:57
 */
@Service("floormanageService")
public class FloorManageServiceImpl implements FloorManageService {
	@Autowired
	private FloorManageMapper floorManageMapper;
	public boolean saveFloorManage(FloorManage floorManage) {
		int status = floorManageMapper.saveFloorManage(floorManage);
		return status == 1 ? true : false;
	}
	public boolean updateFloorManage(FloorManage floorManage) {
		FloorManage manage = floorManageMapper.queryFloorManageByFlId(floorManage.getFlId());
		floorManage.setVersion(manage.getVersion());
		int status = floorManageMapper.updateFloorManage(floorManage);
		return status == 1 ? true : false;
	}
	public FloorManage queryFloorManageByFlId(Long flId) {
		return floorManageMapper.queryFloorManageByFlId(flId);
	}

	public Pagination<FloorManage> queryFloorManageList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<FloorManage> page = new Pagination<FloorManage>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<FloorManage> list = floorManageMapper.queryFloorManageList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteFloorManageByFlId(Long flId) {
		FloorManage manage = floorManageMapper.queryFloorManageByFlId(flId);
		manage.setIsDelete(new Long(1));
		int status = floorManageMapper.updateFloorManage(manage);
		return status == 1 ? true : false;
	}

	public List<FloorManage> queryFloorsList() {
		List<FloorManage> manages = floorManageMapper.queryFloorsList();
		return manages;
	}

	public List<FloorManageVo> queryFloorTree() {
		List<FloorManageVo> list =floorManageMapper.queryFloorTree();
		List<FloorManageVo> rtnList=new ArrayList<FloorManageVo>();
		Map<Long,Map<Long,Long>> child=new HashMap<Long, Map<Long, Long>>();
		queryChildNode(list,child);
		Map<Long,Long> alloShow=new HashMap<Long, Long>();
		queryAllFloors(null,child,alloShow);
		for(FloorManageVo vo:list){
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

	public List<FloorManageVo> queryFloorNode(Long flId) {
		List<FloorManageVo> list =floorManageMapper.queryFloorNode(flId);
		List<FloorManageVo> rtnList=new ArrayList<FloorManageVo>();
		Map<Long,Map<Long,Long>> child=new HashMap<Long, Map<Long, Long>>();
		queryChildNode(list,child);
		Map<Long,Long> alloShow=new HashMap<Long, Long>();
		queryAllFloors(null,child,alloShow);
		for(FloorManageVo vo:list){
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


	private void queryChildNode(List<FloorManageVo> list, Map<Long,Map<Long,Long>> child){
		for(FloorManageVo vo:list){
			Long parentId=vo.getParentId();
			if(ObjectUtils.isEmpty(parentId)){
				parentId=-1l;
			}
			if(child.containsKey(parentId)){
				child.get(parentId).put(vo.getFlId(),vo.getIsDelete());
			}else{
				Map<Long,Long> maps=new HashMap<Long, Long>();
				maps.put(vo.getFlId(),vo.getIsDelete());
				child.put(parentId,maps);
			}
		}
	}

	public List<FloorManage> deleteFloorNode(Long flid) {
		List<FloorManage> lists = floorManageMapper.queryParent(flid);
		return null;
	}
}

