
package com.retailers.sbj.common.service.impl;

import com.retailers.sbj.common.dao.OpeningMapper;
import com.retailers.sbj.common.entity.Opening;
import com.retailers.sbj.common.entity.OpeningCopy;
import com.retailers.sbj.common.entity.OpeningFloor;
import com.retailers.sbj.common.service.OpeningCopyService;
import com.retailers.sbj.common.service.OpeningFloorService;
import com.retailers.sbj.common.service.OpeningService;
import com.retailers.sbj.common.vo.OpeningVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：开盘表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-22 16:36:52
 */
@Service("openingService")
public class OpeningServiceImpl implements OpeningService {
	@Autowired
	private OpeningMapper openingMapper;
	@Autowired
	private OpeningFloorService openingFloorService;
	@Autowired
	private OpeningCopyService openingCopyService;
	public boolean saveOpening(Opening opening,String floors,Long uploadperson) {
		int status = openingMapper.saveOpening(opening);
		addOFrel(status,opening,floors);
		if(status==1){
			copyOpening(opening,uploadperson,0L);
		}
		return status == 1 ? true : false;
	}
	public boolean updateOpening(Opening opening,String floors,Long uploadperson) {
		Map params = new HashMap();
		params.put("isDelete",0L);
		params.put("oid",opening.getOid());
		List<OpeningFloor> list = openingFloorService.queryOpeningFloorList(params,1,10000).getData();
		if(ObjectUtils.isNotEmpty(list)){
			for(OpeningFloor openingFloor:list){
				openingFloor.setIsDelete(1L);
				openingFloorService.updateOpeningFloor(openingFloor);
			}
		}
		int status = openingMapper.updateOpening(opening);
		addOFrel(status,opening,floors);
		if(status==1){
			copyOpening(opening,uploadperson,1L);
		}
		return status == 1 ? true : false;
	}
	public Opening queryOpeningByOid(Long oid) {
		return openingMapper.queryOpeningByOid(oid);
	}

	public Pagination<Opening> queryOpeningList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<Opening> page = new Pagination<Opening>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<Opening> list = openingMapper.queryOpeningList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteOpeningByOid(Long oid,Long uploadperson) {
		Opening opening = queryOpeningByOid(oid);
		opening.setIsDelete(1L);
		int status = openingMapper.updateOpening(opening);
		if(status==1){
			copyOpening(opening,uploadperson,2L);
		}
		return status == 1 ? true : false;
	}
	public Pagination<OpeningVo> queryOpeningVoList(Map<String, Object> params, int pageNo, int pageSize){
		Pagination<OpeningVo> page = new Pagination<OpeningVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<OpeningVo> list = openingMapper.queryOpeningVoList(page);
		page.setData(list);
		return page;
	}
	public List<OpeningVo> queryOFrelByOid(Long oid){
		List<OpeningVo> lsit = openingMapper.queryOFrelByOid(oid);
		return lsit;
	}

	public Opening queryEarlyOpening(){
		Opening opening = openingMapper.queryEarlyOpening();
		return opening;
	}
	public Opening queryLastOpening(){
		Opening opening = openingMapper.queryLastOpening();
		return opening;
	}
	public Opening queryRuningOpening(){
		Opening opening = openingMapper.queryRuningOpening();
		return opening;
	}

	public void addOFrel(int status,Opening opening,String floors){
		if(status==1){
			OpeningFloor openingFloor = new OpeningFloor();
			openingFloor.setOid(opening.getOid());
			openingFloor.setIsDelete(0L);
			String[] floorsStrArr = floors.split(",");
			if(ObjectUtils.isNotEmpty(floorsStrArr)){
				for(String floorsStr:floorsStrArr){
					Long floorId = Long.parseLong(floorsStr);
					openingFloor.setFid(floorId);
					openingFloorService.saveOpeningFloor(openingFloor);
				}
			}
		}
	}

	public void copyOpening(Opening opening,Long uplaodperson,Long type){
		OpeningCopy openingCopy = new OpeningCopy();
		BeanUtils.copyProperties(opening,openingCopy);
		openingCopy.setCreateTime(new Date());
		openingCopy.setUploadperson(uplaodperson);
		openingCopy.setUploadtype(type);
		openingCopyService.saveOpeningCopy(openingCopy);
	}
}

