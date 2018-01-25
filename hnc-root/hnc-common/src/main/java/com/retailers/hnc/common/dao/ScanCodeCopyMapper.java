package com.retailers.hnc.common.dao;
import com.retailers.hnc.common.entity.ScanCodeCopy;
import com.retailers.hnc.common.vo.ScanCodeCopyVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：扫码副本表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-01-22 12:00:59
 */
public interface ScanCodeCopyMapper {

	/**
	 * 添加扫码副本表
	 * @param scanCodeCopy
	 * @return
	 * @author fanghui
	 * @date 2018-01-22 12:00:59
	 */
	public int saveScanCodeCopy(ScanCodeCopy scanCodeCopy);
	/**
	 * 编辑扫码副本表
	 * @param scanCodeCopy
	 * @return
	 * @author fanghui
	 * @date 2018-01-22 12:00:59
	 */
	public int updateScanCodeCopy(ScanCodeCopy scanCodeCopy);
	/**
	 * 根据SccId删除扫码副本表
	 * @param sccId
	 * @return
	 * @author fanghui
	 * @date 2018-01-22 12:00:59
	 */
	public int deleteScanCodeCopyBySccId(Long sccId);
	/**
	 * 根据SccId查询扫码副本表
	 * @param sccId
	 * @return
	 * @author fanghui
	 * @date 2018-01-22 12:00:59
	 */
	public ScanCodeCopy queryScanCodeCopyBySccId(Long sccId);
	/**
	 * 查询扫码副本表列表
	 * @param pagination 分页对象
	 * @return  扫码副本表列表
	 * @author fanghui
	 * @date 2018-01-22 12:00:59
	 */
	public List<ScanCodeCopyVo> queryScanCodeCopyList(Pagination<ScanCodeCopyVo> pagination);

}
