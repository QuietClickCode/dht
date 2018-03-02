
package com.retailers.sbj.common.service.impl;
import java.util.List;
import java.util.Map;

import com.retailers.sbj.common.dao.SocialSecurityMapper;
import com.retailers.sbj.common.entity.SocialSecurity;
import com.retailers.sbj.common.service.SocialSecurityService;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：模拟社保卡信息绑定数据表Service
 * @author boylin
 * @version 1.0
 * @since 1.8
 * @date 2018-03-02 15:50:28
 */
@Service("socialsecurityService")
public class SocialSecurityServiceImpl implements SocialSecurityService {
	@Autowired
	private SocialSecurityMapper socialSecurityMapper;

	public Boolean isReserve(SocialSecurity socialSecurity) {
		if(socialSecurityMapper.querySocialSecurity(socialSecurity).size()>0)
			return socialSecurity.getTel().equals(socialSecurityMapper.querySocialSecurity(socialSecurity).get(0).getTel())	? true : false;

		return false;
	}

	public boolean saveSocialSecurity(SocialSecurity socialSecurity) {
		int status = socialSecurityMapper.saveSocialSecurity(socialSecurity);
		return status == 1 ? true : false;
	}
	public boolean updateSocialSecurity(SocialSecurity socialSecurity) {
		int status = socialSecurityMapper.updateSocialSecurity(socialSecurity);
		return status == 1 ? true : false;
	}
	public SocialSecurity querySocialSecurityById(Long id) {
		return socialSecurityMapper.querySocialSecurityById(id);
	}

	public Pagination<SocialSecurity> querySocialSecurityList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<SocialSecurity> page = new Pagination<SocialSecurity>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<SocialSecurity> list = socialSecurityMapper.querySocialSecurityList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteSocialSecurityById(Long id) {
		int status = socialSecurityMapper.deleteSocialSecurityById(id);
		return status == 1 ? true : false;
	}
}

