package com.retailers.hnc.common.dao;
import com.retailers.hnc.common.entity.ScanCode;
import com.retailers.hnc.common.vo.ScanCodeVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：扫码人员关系表DAO
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-31 12:47:06
 */
public interface ScanCodeMapper {

	/**
	 * 添加扫码人员关系表
	 * @param scanCodeList
	 * @return
	 * @author wangjue
	 * @date 2017-12-31 12:47:06
	 */
	public int saveScanCode(List<ScanCode> scanCodeList);
	/**
	 * 编辑扫码人员关系表
	 * @param scanCode
	 * @return
	 * @author wangjue
	 * @date 2017-12-31 12:47:06
	 */
	public int updateScanCode(ScanCode scanCode);
	/**
	 * 根据ScId删除扫码人员关系表
	 * @param scId
	 * @return
	 * @author wangjue
	 * @date 2017-12-31 12:47:06
	 */
	public int deleteScanCodeByScId(Long scId);
	/**
	 * 根据ScId查询扫码人员关系表
	 * @param scId
	 * @return
	 * @author wangjue
	 * @date 2017-12-31 12:47:06
	 */
	public ScanCode queryScanCodeByScId(Long scId);
	/**
	 * 查询扫码人员关系表列表
	 * @param pagination 分页对象
	 * @return  扫码人员关系表列表
	 * @author wangjue
	 * @date 2017-12-31 12:47:06
	 */
	public List<ScanCodeVo> queryScanCodeList(Pagination<ScanCodeVo> pagination);

	public List<ScanCode> queryOpeningEmployee(Long oid);

	public int deleteOpeningEmployee(Long oid);

}
