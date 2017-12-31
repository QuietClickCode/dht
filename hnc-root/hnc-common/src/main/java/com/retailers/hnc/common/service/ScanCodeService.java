
package com.retailers.hnc.common.service;

import com.retailers.hnc.common.entity.ScanCode;
import com.retailers.hnc.common.vo.ScanCodeVo;
import com.retailers.mybatis.pagination.Pagination;

import java.util.Map;
/**
 * 描述：扫码人员关系表Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-31 12:47:06
 */
public interface ScanCodeService {

	/**
	 * 添加扫码人员关系表
	 * @param scanCode
	 * @return
	 * @author wangjue
	 * @date 2017-12-31 12:47:06
	 */
	public boolean saveScanCode(ScanCode scanCode);
	/**
	 * 编辑扫码人员关系表
	 * @param scanCode
	 * @return
	 * @author wangjue
	 * @date
	 */
	public boolean updateScanCode(ScanCode scanCode);
	/**
	 * 根据id查询扫码人员关系表
	 * @param scId
	 * @return scanCode
	 * @author wangjue
	 * @date 2017-12-31 12:47:06
	 */
	public ScanCode queryScanCodeByScId(Long scId);
	/**
	 * 查询扫码人员关系表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author wangjue
	 * @date 2017-12-31 12:47:06
	 */
	public Pagination<ScanCodeVo> queryScanCodeList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据scId删除扫码人员关系表
	 * @param scId
	 * @return
	 * @author wangjue
	 * @date 2017-12-31 12:47:06
	 */
	public boolean deleteScanCodeByScId(Long scId);

}


