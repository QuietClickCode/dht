
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.ReturnList;
import com.retailers.dht.common.dao.ReturnListMapper;
import com.retailers.dht.common.service.ReturnListService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：返现类型表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-01-13 10:29:40
 */
@Service("returnlistService")
public class ReturnListServiceImpl implements ReturnListService {
	@Autowired
	private ReturnListMapper returnListMapper;
	public boolean saveReturnList(ReturnList returnList) {
		int status = returnListMapper.saveReturnList(returnList);
		return status == 1 ? true : false;
	}
	public boolean updateReturnList(ReturnList returnList) {
		int status = returnListMapper.updateReturnList(returnList);
		return status == 1 ? true : false;
	}
	public ReturnList queryReturnListByRtId(Long rtId) {
		return returnListMapper.queryReturnListByRtId(rtId);
	}

	public Pagination<ReturnList> queryReturnListList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<ReturnList> page = new Pagination<ReturnList>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<ReturnList> list = returnListMapper.queryReturnListList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteReturnListByRtId(Long rtId) {
		ReturnList returnList = returnListMapper.queryReturnListByRtId(rtId);
		returnList.setIsDelete(1L);
		int status = returnListMapper.updateReturnList(returnList);
		return status == 1 ? true : false;
	}
}

