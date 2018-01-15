
package com.retailers.hnc.common.service.impl;

		import com.retailers.hnc.common.dao.FloorManageMapper;
		import com.retailers.hnc.common.dao.FloorRelationshipMapper;
		import com.retailers.hnc.common.dao.HouseTypeManageMapper;
		import com.retailers.hnc.common.entity.FloorManage;
		import com.retailers.hnc.common.entity.FloorRelationship;
		import com.retailers.hnc.common.entity.HouseTypeManage;
		import com.retailers.hnc.common.service.FloorRelationshipService;
		import com.retailers.hnc.common.service.HouseTypeManageService;
		import com.retailers.hnc.common.vo.HouseTypeManageVo;
		import com.retailers.mybatis.common.constant.AttachmentConstant;
		import com.retailers.mybatis.common.entity.Attachment;
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
 * 描述：户型表Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-18 11:36:10
 */
@Service("housetypemanageService")
public class HouseTypeManageServiceImpl implements HouseTypeManageService {
	@Autowired
	private HouseTypeManageMapper houseTypeManageMapper;

	@Autowired
	private FloorRelationshipService relationshipService;

	@Autowired
	private FloorManageMapper manageMapper;

	@Autowired
	private AttachmentService attachmentService;

	public boolean saveHouseTypeManage(HouseTypeManage houseTypeManage) {
		int status = houseTypeManageMapper.saveHouseTypeManage(houseTypeManage);
		return status == 1 ? true : false;
	}
	public boolean updateHouseTypeManage(HouseTypeManage houseTypeManage) {

		System.out.println(houseTypeManage.getHtShowImg() +" " + houseTypeManage.getHtImage());
		HouseTypeManage manage = queryHouseTypeManageByHtId(houseTypeManage.getHtId());
		if(houseTypeManage.getHtImage() != null){
			if (!ObjectUtils.compare(houseTypeManage.getHtImage(),manage.getHtImage())) {
				if(manage.getHtImage() == null)
					attachmentService.editorAttachment(houseTypeManage.getHtImage());
				else{
					attachmentService.editorAttachment(manage.getHtImage(),AttachmentConstant.ATTACHMENT_STATUS_NO);
					attachmentService.editorAttachment(houseTypeManage.getHtImage());
				}
			}
		}


		if(houseTypeManage.getHtShowImg() != null){
			if (!ObjectUtils.compare(houseTypeManage.getHtShowImg(),manage.getHtShowImg())) {
				if(manage.getHtShowImg() == null)
					attachmentService.editorAttachment(houseTypeManage.getHtShowImg());
				else{
					attachmentService.editorAttachment(manage.getHtShowImg(),AttachmentConstant.ATTACHMENT_STATUS_NO);
					attachmentService.editorAttachment(houseTypeManage.getHtShowImg());
				}
			}
		}

		houseTypeManage.setVersion(manage.getVersion());
		int status = houseTypeManageMapper.updateHouseTypeManage(houseTypeManage);
		return status == 1 ? true : false;
	}
	public HouseTypeManage queryHouseTypeManageByHtId(Long htId) {
		return houseTypeManageMapper.queryHouseTypeManageByHtId(htId);
	}

	public List<HouseTypeManage> queryAllHouseType(List<Integer> list) {
		return houseTypeManageMapper.queryAllHouseType(list);
	}

	public Pagination<HouseTypeManageVo> queryHouseTypeManageList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<HouseTypeManageVo> page = new Pagination<HouseTypeManageVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<HouseTypeManageVo> list = houseTypeManageMapper.queryHouseTypeManageList(page);
		for (HouseTypeManageVo typeManageVo : list) {
			if(typeManageVo.getHtImage() != null){
				Attachment attachment = attachmentService.queryAttachmentById(typeManageVo.getHtImage());
				if(attachment != null)
					typeManageVo.setImagePath(AttachmentConstant.IMAGE_SHOW_URL+attachment.getShowUrl());
			}
			typeManageVo.setFloorManages(relationshipService.queryHouseType(typeManageVo.getHtId()));
		}
		page.setData(list);
		return page;
	}

	public Pagination<HouseTypeManageVo> queryFirstHouseTypeManageList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<HouseTypeManageVo> page = new Pagination<HouseTypeManageVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<HouseTypeManageVo> list = houseTypeManageMapper.queryHouseTypeManageList(page);
		page.setData(list);
		return page;
	}

	public boolean deleteHouseTypeManageByHtId(Long htId) {
		HouseTypeManage houseTypeManage = queryHouseTypeManageByHtId(htId);
		houseTypeManage.setIsDelete(1);
		int status = houseTypeManageMapper.updateHouseTypeManage(houseTypeManage);
		return status == 1 ? true : false;
	}

	public boolean addFloorRelationship(List<FloorRelationship> relationships){
		boolean flag = false;
		if(relationships != null){
			Long htId = null;
			FloorRelationship relationship = relationships.get(0);
			htId = relationship.getHtId();
			HashMap<Long,FloorRelationship> map = new HashMap<Long, FloorRelationship>();
			for (FloorRelationship floorRelationship : relationships) {
				map.put(floorRelationship.getHrId(),floorRelationship);
			}
			List<FloorManage> floorManages = relationshipService.queryHouseType(htId);
			for (FloorManage floorManage : floorManages) {
				if(map.get(floorManage.getFmId()) == null){
					flag = relationshipService.queryFloorRelationship(htId,floorManage.getFmId());
				}else if(map.get(floorManage.getFmId()) != null){
					map.remove(floorManage.getFmId());
				}
			}

			for (Map.Entry<Long, FloorRelationship> entry : map.entrySet()) {
				entry.getValue().setIsDelete(0);
				flag = relationshipService.saveFloorRelationship(entry.getValue());
			}
		}
		return flag;
	}

	public List<HouseTypeManage> queryHouseTypeManageByHtIds(String htIds){
		if(ObjectUtils.isNotEmpty(htIds)){
			String[] htIdsArr = htIds.split(",");
			List<Long> htIdsList = new ArrayList<Long>();
			for(String hidStr:htIdsArr){
				Long hidLong = Long.parseLong(hidStr);
				htIdsList.add(hidLong);
			}
			List<HouseTypeManage> returnList = houseTypeManageMapper.queryHouseTypeManageByHtIds(htIdsList);
			return returnList;
		}
		return null;
	}

	public List<HouseTypeManageVo> queryHourseTypeManageVoWithUid(Map params,int pageNo,int pageSize){
		Pagination<HouseTypeManageVo> page = new Pagination<HouseTypeManageVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<HouseTypeManageVo> list = houseTypeManageMapper.queryHourseTypeManageVoWithUid(page);
		for(HouseTypeManageVo houseTypeManageVo:list){
			houseTypeManageVo.setImagePath(AttachmentConstant.IMAGE_SHOW_URL+houseTypeManageVo.getImagePath());
		}
		return list;
	}
}

