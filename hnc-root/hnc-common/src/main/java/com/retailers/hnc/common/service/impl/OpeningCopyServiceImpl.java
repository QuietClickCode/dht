
package com.retailers.hnc.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.hnc.common.entity.OpeningCopy;
import com.retailers.hnc.common.dao.OpeningCopyMapper;
import com.retailers.hnc.common.service.OpeningCopyService;
import com.retailers.hnc.common.vo.OpeningCopyVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：开盘副本表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-01-22 12:01:16
 */
@Service("openingcopyService")
public class OpeningCopyServiceImpl implements OpeningCopyService {
	@Autowired
	private OpeningCopyMapper openingCopyMapper;
	public boolean saveOpeningCopy(OpeningCopy openingCopy) {
		int status = openingCopyMapper.saveOpeningCopy(openingCopy);
		return status == 1 ? true : false;
	}
	public boolean updateOpeningCopy(OpeningCopy openingCopy) {
		int status = openingCopyMapper.updateOpeningCopy(openingCopy);
		return status == 1 ? true : false;
	}
	public OpeningCopy queryOpeningCopyByOcId(Long ocId) {
		return openingCopyMapper.queryOpeningCopyByOcId(ocId);
	}

	public Pagination<OpeningCopyVo> queryOpeningCopyList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<OpeningCopyVo> page = new Pagination<OpeningCopyVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<OpeningCopyVo> list = openingCopyMapper.queryOpeningCopyList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteOpeningCopyByOcId(Long ocId) {
		int status = openingCopyMapper.deleteOpeningCopyByOcId(ocId);
		return status == 1 ? true : false;
	}
}

