
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;

import com.retailers.dht.common.constant.AttachmentConstant;
import com.retailers.dht.common.entity.HomeNavigation;
import com.retailers.dht.common.dao.HomeNavigationMapper;
import com.retailers.dht.common.service.AttachmentService;
import com.retailers.dht.common.service.HomeNavigationService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：主页导航表Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-17 14:58:24
 */
@Service("homenavigationService")
public class HomeNavigationServiceImpl implements HomeNavigationService {
	@Autowired
	private HomeNavigationMapper homeNavigationMapper;

	@Autowired
	AttachmentService attachmentService;

	public boolean saveHomeNavigation(HomeNavigation homeNavigation) {
		attachmentService.editorAttachment(homeNavigation.getHnImgpath());
		int status = homeNavigationMapper.saveHomeNavigation(homeNavigation);
		return status == 1 ? true : false;
	}
	public boolean updateHomeNavigation(HomeNavigation navigation) {
		HomeNavigation homeNavigation = homeNavigationMapper.queryHomeNavigationByHnId(navigation.getHnId());
		if(homeNavigation.getHnImgpath().compareTo(navigation.getHnImgpath()) != 0) {
			attachmentService.editorAttachment(homeNavigation.getHnImgpath(), AttachmentConstant.ATTACHMENT_STATUS_NO);
			attachmentService.editorAttachment(navigation.getHnImgpath());
		}
		navigation.setVersion(homeNavigation.getVersion());
		int status = homeNavigationMapper.updateHomeNavigation(navigation);
		return status == 1 ? true : false;
	}
	public HomeNavigation queryHomeNavigationByHnId(Long hnId) {
		return homeNavigationMapper.queryHomeNavigationByHnId(hnId);
	}

	public Pagination<HomeNavigation> queryHomeNavigationList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<HomeNavigation> page = new Pagination<HomeNavigation>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<HomeNavigation> list = homeNavigationMapper.queryHomeNavigationList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteHomeNavigationByHnId(Long hnId) {
		HomeNavigation homeNavigation = homeNavigationMapper.queryHomeNavigationByHnId(hnId);
		attachmentService.editorAttachment(homeNavigation.getHnImgpath(), AttachmentConstant.ATTACHMENT_STATUS_NO);
		homeNavigation.setIsDelete(1L);
		int status = homeNavigationMapper.updateHomeNavigation(homeNavigation);
		return status == 1 ? true : false;
	}
}

