
package com.retailers.hnc.common.service.impl;

import com.retailers.hnc.common.dao.ScanCodeMapper;
import com.retailers.hnc.common.entity.*;
import com.retailers.hnc.common.service.EmployeeManageService;
import com.retailers.hnc.common.service.ScanCodeService;
import com.retailers.hnc.common.service.TeamService;
import com.retailers.hnc.common.vo.ScanCodeVo;
import com.retailers.mybatis.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

	public boolean saveScanCode(List<ScanCode> scanCodeList) {
		int status = 1;
		if(scanCodeList.size() != 0){
			ScanCode code = scanCodeList.get(0);
			Long oid = code.getOid();
			HashMap<Long,ScanCode> map = new HashMap<Long, ScanCode>();
			for (ScanCode scanCode : scanCodeList) {
				map.put(scanCode.getEmId(),scanCode);
			}

			List<ScanCode> scanCodes = scanCodeMapper.queryOpeningEmployee(oid);
			for (ScanCode scanCode : scanCodes) {
				if(map.get(scanCode.getEmId()) == null){
					deleteScanCodeByScId(scanCode.getScId());
				}else if(map.get(scanCode.getEmId()) != null){
					map.remove(scanCode.getEmId());
				}
			}
			scanCodes.clear();
			for (Map.Entry<Long, ScanCode> entry : map.entrySet()) {
				scanCodes.add(entry.getValue());
			}

			for(ScanCode scanCode:scanCodes){
				System.out.println(scanCode.getEmId());
			}

			if(scanCodes.size() != 0)
				status = scanCodeMapper.saveScanCode(scanCodes);
		}
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

	public boolean deleteOpeningEmployee(Long oid) {
		int status = scanCodeMapper.deleteOpeningEmployee(oid);
		System.out.println(status);
		return status != 0 ? true : false;
	}

	public Pagination<ScanCodeVo> queryScanCodeList(Map<String, Object> params, int pageNo, int pageSize) {
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
