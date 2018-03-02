
package com.retailers.sbj.common.service.impl;

import com.retailers.sbj.common.dao.ScanCodeCopyMapper;
import com.retailers.sbj.common.entity.ScanCodeCopy;
import com.retailers.sbj.common.service.ScanCodeCopyService;
import com.retailers.sbj.common.vo.ScanCodeCopyVo;
import com.retailers.mybatis.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 描述：扫码副本表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-01-22 12:00:59
 */
@Service("scancodecopyService")
public class ScanCodeCopyServiceImpl implements ScanCodeCopyService {
	@Autowired
	private ScanCodeCopyMapper scanCodeCopyMapper;
	public boolean saveScanCodeCopy(ScanCodeCopy scanCodeCopy) {
		int status = scanCodeCopyMapper.saveScanCodeCopy(scanCodeCopy);
		return status == 1 ? true : false;
	}
	public boolean updateScanCodeCopy(ScanCodeCopy scanCodeCopy) {
		int status = scanCodeCopyMapper.updateScanCodeCopy(scanCodeCopy);
		return status == 1 ? true : false;
	}
	public ScanCodeCopy queryScanCodeCopyBySccId(Long sccId) {
		return scanCodeCopyMapper.queryScanCodeCopyBySccId(sccId);
	}

	public Pagination<ScanCodeCopyVo> queryScanCodeCopyList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<ScanCodeCopyVo> page = new Pagination<ScanCodeCopyVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<ScanCodeCopyVo> list = scanCodeCopyMapper.queryScanCodeCopyList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteScanCodeCopyBySccId(Long sccId) {
		int status = scanCodeCopyMapper.deleteScanCodeCopyBySccId(sccId);
		return status == 1 ? true : false;
	}
}

