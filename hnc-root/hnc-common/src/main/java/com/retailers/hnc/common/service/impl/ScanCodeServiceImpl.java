
package com.retailers.hnc.common.service.impl;
import java.util.List;
import java.util.Map;

import com.retailers.hnc.common.entity.EmployeeManage;
import com.retailers.hnc.common.entity.ScanCode;
import com.retailers.hnc.common.dao.ScanCodeMapper;
import com.retailers.hnc.common.entity.Team;
import com.retailers.hnc.common.service.EmployeeManageService;
import com.retailers.hnc.common.service.ScanCodeService;
import com.retailers.hnc.common.service.TeamService;
import com.retailers.hnc.common.vo.ScanCodeVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：扫码人员关系表Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-31 12:47:06
 */
@Service("scancodeService")
public class ScanCodeServiceImpl implements ScanCodeService {
	@Autowired
	private ScanCodeMapper scanCodeMapper;

	@Autowired
	private EmployeeManageService employeeManageService;

	@Autowired
	private TeamService teamService;

	public boolean saveScanCode(ScanCode scanCode) {
		int status = scanCodeMapper.saveScanCode(scanCode);
		return status == 1 ? true : false;
	}
	public boolean updateScanCode(ScanCode scanCode) {
		ScanCode code = queryScanCodeByScId(scanCode.getScId());
		scanCode.setVersion(code.getVersion());
		int status = scanCodeMapper.updateScanCode(scanCode);
		return status == 1 ? true : false;
	}
	public ScanCode queryScanCodeByScId(Long scId) {
		return scanCodeMapper.queryScanCodeByScId(scId);
	}

	public Pagination<ScanCodeVo> queryScanCodeList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<ScanCodeVo> page = new Pagination<ScanCodeVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<ScanCodeVo> list = scanCodeMapper.queryScanCodeList(page);
		for (ScanCodeVo scanCodeVo : list) {
			EmployeeManage employeeManage = employeeManageService.queryEmployeeManageByEmId(scanCodeVo.getEmId());
			scanCodeVo.setEmName(employeeManage.getEmName());
			scanCodeVo.setEmployeeManage(employeeManage);
			Team team = teamService.queryTeamByTid(employeeManage.getEmTeam());
			scanCodeVo.setTeam(team);
			scanCodeVo.setTeamName(team.getTname());
		}
		page.setData(list);
		return page;
	}
	public boolean deleteScanCodeByScId(Long scId) {
		ScanCode scanCode = queryScanCodeByScId(scId);
		scanCode.setIsDelete(1l);
		return updateScanCode(scanCode);
	}
}
