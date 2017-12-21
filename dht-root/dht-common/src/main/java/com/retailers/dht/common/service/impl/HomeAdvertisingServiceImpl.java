
package com.retailers.dht.common.service.impl;

import com.retailers.dht.common.dao.HomeAdvertisingMapper;
import com.retailers.dht.common.entity.HomeAdvertising;
import com.retailers.dht.common.service.HomeAdvertisingService;
import com.retailers.dht.common.vo.HomeAdvertisingVo;
import com.retailers.mybatis.common.constant.AttachmentConstant;
import com.retailers.mybatis.common.service.AttachmentService;
import com.retailers.mybatis.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 描述：首页广告设置表Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-20 17:07:09
 */
@Service("homeadvertisingService")
public class HomeAdvertisingServiceImpl implements HomeAdvertisingService {
	@Autowired
	private HomeAdvertisingMapper homeAdvertisingMapper;

	@Autowired
	private AttachmentService attachmentService;

	public boolean saveHomeAdvertising(HomeAdvertising homeAdvertising) {
		attachmentService.editorAttachment(homeAdvertising.getImagePath());
		int status = homeAdvertisingMapper.saveHomeAdvertising(homeAdvertising);
		return status == 1 ? true : false;
	}
	public boolean updateHomeAdvertising(HomeAdvertising advertising) {
		HomeAdvertising homeAdvertising = homeAdvertisingMapper.queryHomeAdvertisingByHaId(advertising.getHaId());
		if(homeAdvertising.getImagePath().compareTo(advertising.getImagePath()) != 0) {
			attachmentService.editorAttachment(homeAdvertising.getImagePath(), AttachmentConstant.ATTACHMENT_STATUS_NO);
			attachmentService.editorAttachment(advertising.getImagePath());
		}
		advertising.setVersion(homeAdvertising.getVersion());
		int status = homeAdvertisingMapper.updateHomeAdvertising(advertising);
		return status == 1 ? true : false;
	}
	public HomeAdvertising queryHomeAdvertisingByHaId(Long haId) {
		return homeAdvertisingMapper.queryHomeAdvertisingByHaId(haId);
	}

	public Pagination<HomeAdvertisingVo> queryHomeAdvertisingList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<HomeAdvertisingVo> page = new Pagination<HomeAdvertisingVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<HomeAdvertisingVo> list = homeAdvertisingMapper.queryHomeAdvertisingList(page);
		for(int i = 0;i<list.size();i++) {
			HomeAdvertisingVo vo = list.get(i);
			vo.setImageUrl(AttachmentConstant.IMAGE_SHOW_URL+vo.getImageUrl());
		}
		page.setData(list);
		return page;
	}
	public boolean deleteHomeAdvertisingByHaId(Long haId) {
		HomeAdvertising advertising = homeAdvertisingMapper.queryHomeAdvertisingByHaId(haId);
		attachmentService.editorAttachment(advertising.getImagePath(), AttachmentConstant.ATTACHMENT_STATUS_NO);
		advertising.setIsDelete(1L);
		int status = homeAdvertisingMapper.updateHomeAdvertising(advertising);
		return status == 1 ? true : false;
	}
}

