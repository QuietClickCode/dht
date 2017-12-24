
package com.retailers.hnc.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.hnc.common.entity.Opening;
import com.retailers.hnc.common.dao.OpeningMapper;
import com.retailers.hnc.common.service.OpeningService;
import com.retailers.hnc.common.vo.OpeningVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
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
	public boolean saveOpening(Opening opening) {
		int status = openingMapper.saveOpening(opening);
		return status == 1 ? true : false;
	}
	public boolean updateOpening(Opening opening) {
		int status = openingMapper.updateOpening(opening);
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
	public boolean deleteOpeningByOid(Long oid) {
		int status = openingMapper.deleteOpeningByOid(oid);
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
}

