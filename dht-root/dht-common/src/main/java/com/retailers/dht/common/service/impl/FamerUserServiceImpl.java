
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.FamerUser;
import com.retailers.dht.common.dao.FamerUserMapper;
import com.retailers.dht.common.service.FamerUserService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：农夫用户关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-02-07 17:16:31
 */
@Service("fameruserService")
public class FamerUserServiceImpl implements FamerUserService {
	@Autowired
	private FamerUserMapper famerUserMapper;
	public boolean saveFamerUser(FamerUser famerUser) {
		int status = famerUserMapper.saveFamerUser(famerUser);
		return status == 1 ? true : false;
	}
	public boolean updateFamerUser(FamerUser famerUser) {
		int status = famerUserMapper.updateFamerUser(famerUser);
		return status == 1 ? true : false;
	}
	public FamerUser queryFamerUserByFuId(Long fuId) {
		return famerUserMapper.queryFamerUserByFuId(fuId);
	}

	public Pagination<FamerUser> queryFamerUserList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<FamerUser> page = new Pagination<FamerUser>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<FamerUser> list = famerUserMapper.queryFamerUserList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteFamerUserByFuId(Long fuId) {
		int status = famerUserMapper.deleteFamerUserByFuId(fuId);
		return status == 1 ? true : false;
	}
	public Pagination<String> queryFamerUserImgUrlList(Map<String, Object> params, int pageNo, int pageSize){
		Pagination<String> page = new Pagination<String>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<String> list = famerUserMapper.queryFamerUserImgUrlList(page);
		page.setData(list);
		return page;
	}
}

