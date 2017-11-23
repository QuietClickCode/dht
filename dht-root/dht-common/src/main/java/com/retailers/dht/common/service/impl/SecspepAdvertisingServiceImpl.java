
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;

import com.retailers.dht.common.constant.AttachmentConstant;
import com.retailers.dht.common.entity.SecspepAdvertising;
import com.retailers.dht.common.dao.SecspepAdvertisingMapper;
import com.retailers.dht.common.service.AttachmentService;
import com.retailers.dht.common.service.SecspepAdvertisingService;
import com.retailers.dht.common.vo.SecspepAdvertisingVo;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：特价广告表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-23 10:19:14
 */
@Service("secspepadvertisingService")
public class SecspepAdvertisingServiceImpl implements SecspepAdvertisingService {
	@Autowired
	private SecspepAdvertisingMapper secspepAdvertisingMapper;
	@Autowired
	private AttachmentService attachmentService;
	public boolean saveSecspepAdvertising(SecspepAdvertising secspepAdvertising) {
		int status = secspepAdvertisingMapper.saveSecspepAdvertising(secspepAdvertising);
		if(status==1&&!ObjectUtils.isEmpty(secspepAdvertising.getImageId())){
			attachmentService.editorAttachment(secspepAdvertising.getImageId());
		}
		return status == 1 ? true : false;
	}
	public boolean updateSecspepAdvertising(SecspepAdvertising secspepAdvertising) {
		secspepAdvertising.setVersion(secspepAdvertisingMapper.querySecspepAdvertisingBySaId(secspepAdvertising.getSaId()).getVersion());
		int status = secspepAdvertisingMapper.updateSecspepAdvertising(secspepAdvertising);
		return status == 1 ? true : false;
	}
	public SecspepAdvertising querySecspepAdvertisingBySaId(Long saId) {
		return secspepAdvertisingMapper.querySecspepAdvertisingBySaId(saId);
	}

	public Pagination<SecspepAdvertisingVo> querySecspepAdvertisingList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<SecspepAdvertisingVo> page = new Pagination<SecspepAdvertisingVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<SecspepAdvertisingVo> list = secspepAdvertisingMapper.querySecspepAdvertisingList(page);
		for(SecspepAdvertisingVo secspepAdvertisingVo:list){
			secspepAdvertisingVo.setImageUrl(AttachmentConstant.IMAGE_SHOW_URL+secspepAdvertisingVo.getImageUrl());
		}
		page.setData(list);
		return page;
	}
	public boolean deleteSecspepAdvertisingBySaId(Long saId) {
		SecspepAdvertising secspepAdvertising = secspepAdvertisingMapper.querySecspepAdvertisingBySaId(saId);
		secspepAdvertising.setIsDelete(1L);
		int status = secspepAdvertisingMapper.updateSecspepAdvertising(secspepAdvertising);
		return status == 1 ? true : false;
	}
}

