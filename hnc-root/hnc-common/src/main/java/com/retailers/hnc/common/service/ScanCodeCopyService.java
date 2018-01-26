
package com.retailers.hnc.common.service;
import com.retailers.hnc.common.vo.ScanCodeCopyVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.hnc.common.entity.ScanCodeCopy;
import java.util.Map;
/**
 * 描述：扫码副本表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-01-22 12:00:59
 */
public interface ScanCodeCopyService {

	/**
	 * 添加扫码副本表
	 * @param scanCodeCopy
	 * @return
	 * @author fanghui
	 * @date 2018-01-22 12:00:59
	 */
	public boolean saveScanCodeCopy(ScanCodeCopy scanCodeCopy);
	/**
	 * 编辑扫码副本表
	 * @param scanCodeCopy
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateScanCodeCopy(ScanCodeCopy scanCodeCopy);
	/**
	 * 根据id查询扫码副本表
	 * @param sccId
	 * @return scanCodeCopy
	 * @author fanghui
	 * @date 2018-01-22 12:00:59
	 */
	public ScanCodeCopy queryScanCodeCopyBySccId(Long sccId);
	/**
	 * 查询扫码副本表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2018-01-22 12:00:59
	 */
	public Pagination<ScanCodeCopyVo> queryScanCodeCopyList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据sccId删除扫码副本表
	 * @param sccId
	 * @return
	 * @author fanghui
	 * @date 2018-01-22 12:00:59
	 */
	public boolean deleteScanCodeCopyBySccId(Long sccId);

}


