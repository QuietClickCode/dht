
package com.retailers.dht.common.service.impl;

import com.retailers.dht.common.dao.UserFootprintMapper;
import com.retailers.dht.common.entity.UserFootprint;
import com.retailers.dht.common.service.UserFootprintService;
import com.retailers.dht.common.vo.GoodsVo;
import com.retailers.mybatis.common.constant.AttachmentConstant;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 描述：足迹表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-30 09:50:48
 */
@Service("userfootprintService")
public class UserFootprintServiceImpl implements UserFootprintService {
	@Autowired
	private UserFootprintMapper userFootprintMapper;
	public boolean saveUserFootprint(UserFootprint userFootprint) {
		List<UserFootprint> list = queryTodayDate(userFootprint.getUid());
		if(!ObjectUtils.isEmpty(list)){
			for(UserFootprint userFootprint1:list){
				if(userFootprint1.getGid()==userFootprint.getGid()){
					return true;
				}
			}
		}
		int status = userFootprintMapper.saveUserFootprint(userFootprint);
		return status == 1 ? true : false;
	}
	public boolean updateUserFootprint(UserFootprint userFootprint) {
		int status = userFootprintMapper.updateUserFootprint(userFootprint);
		return status == 1 ? true : false;
	}
	public UserFootprint queryUserFootprintByUfId(Long ufId) {
		return userFootprintMapper.queryUserFootprintByUfId(ufId);
	}

	public Pagination<UserFootprint> queryUserFootprintList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<UserFootprint> page = new Pagination<UserFootprint>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<UserFootprint> list = userFootprintMapper.queryUserFootprintList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteUserFootprintByUfId(Long ufId) {
		UserFootprint userFootprint = userFootprintMapper.queryUserFootprintByUfId(ufId);
		userFootprint.setIsDelete(1L);
		int status = userFootprintMapper.updateUserFootprint(userFootprint);
		return status == 1 ? true : false;
	}

	public List<UserFootprint> queryTodayDate(Long uid) {
		List<UserFootprint> list = userFootprintMapper.queryTodayDate(uid);
		return list;
	}
	public List<GoodsVo> queryUserFootprintVoList(Map<String, Object> params, int pageNo, int pageSize){
		Pagination<GoodsVo> page = new Pagination<GoodsVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsVo> list = userFootprintMapper.queryUserFootprintVoList(page);
		for(GoodsVo goodsVo:list){
			goodsVo.setImgUrl(AttachmentConstant.IMAGE_SHOW_URL+goodsVo.getImgUrl());
		}
		return list;
	}

	public boolean delallfootprint(Long uid) {
		int status = userFootprintMapper.delallfootprint(uid);
		return status>0  ? true : false;
	}
}

