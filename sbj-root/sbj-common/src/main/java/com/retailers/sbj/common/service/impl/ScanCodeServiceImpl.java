
package com.retailers.sbj.common.service.impl;

import com.retailers.sbj.common.dao.ScanCodeMapper;
import com.retailers.sbj.common.entity.EmployeeManage;
import com.retailers.sbj.common.entity.ScanCode;
import com.retailers.sbj.common.entity.ScanCodeCopy;
import com.retailers.sbj.common.entity.Team;
import com.retailers.sbj.common.service.EmployeeManageService;
import com.retailers.sbj.common.service.ScanCodeCopyService;
import com.retailers.sbj.common.service.ScanCodeService;
import com.retailers.sbj.common.service.TeamService;
import com.retailers.sbj.common.vo.ScanCodeVo;
import com.retailers.mybatis.pagination.Pagination;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

	@Autowired
	private ScanCodeCopyService scanCodeCopyService;

	public boolean saveScanCode(List<ScanCode> scanCodeList, Long uploadperson) {
		int status = 0;
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
					deleteScanCodeByScId(scanCode.getScId(),uploadperson);
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

			if(status>0){
				for(ScanCode scanCode:scanCodes){
					copyScan(scanCode,uploadperson,0L);
				}
			}
		}

		return status > 0 ? true : false;

	}
	public boolean updateScanCode(ScanCode scanCode,Long uploadperson) {
		ScanCode code = queryScanCodeByScId(scanCode.getScId());
		scanCode.setVersion(code.getVersion());
		int status = scanCodeMapper.updateScanCode(scanCode);
		if(status==1){
			copyScan(scanCode,uploadperson,1L);
		}
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
	public boolean deleteScanCodeByScId(Long scId,Long uploadperson) {
		ScanCode scanCode = queryScanCodeByScId(scId);
		scanCode.setIsDelete(1l);
		int status = scanCodeMapper.updateScanCode(scanCode);
		if(status==1){
			copyScan(scanCode,uploadperson,2L);
		}
		return status==1?true:false;
	}
	public void copyScan(ScanCode scanCode,Long uplaodperson,Long type){
		ScanCodeCopy scanCodeCopy = new ScanCodeCopy();
		BeanUtils.copyProperties(scanCode,scanCodeCopy);
		scanCodeCopy.setCreateTime(new Date());
		scanCodeCopy.setUploadperson(uplaodperson);
		scanCodeCopy.setUploadtype(type);
		scanCodeCopyService.saveScanCodeCopy(scanCodeCopy);
	}
}
