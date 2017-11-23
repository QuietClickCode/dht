package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.SecspepAdvertising;
import com.retailers.dht.common.vo.SecspepAdvertisingVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：特价广告表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-23 10:19:14
 */
public interface SecspepAdvertisingMapper {

	/**
	 * 添加特价广告表
	 * @param secspepAdvertising
	 * @return
	 * @author fanghui
	 * @date 2017-11-23 10:19:14
	 */
	public int saveSecspepAdvertising(SecspepAdvertising secspepAdvertising);
	/**
	 * 编辑特价广告表
	 * @param secspepAdvertising
	 * @return
	 * @author fanghui
	 * @date 2017-11-23 10:19:14
	 */
	public int updateSecspepAdvertising(SecspepAdvertising secspepAdvertising);
	/**
	 * 根据SaId删除特价广告表
	 * @param saId
	 * @return
	 * @author fanghui
	 * @date 2017-11-23 10:19:14
	 */
	public int deleteSecspepAdvertisingBySaId(Long saId);
	/**
	 * 根据SaId查询特价广告表
	 * @param saId
	 * @return
	 * @author fanghui
	 * @date 2017-11-23 10:19:14
	 */
	public SecspepAdvertising querySecspepAdvertisingBySaId(Long saId);
	/**
	 * 查询特价广告表列表
	 * @param pagination 分页对象
	 * @return  特价广告表列表
	 * @author fanghui
	 * @date 2017-11-23 10:19:14
	 */
	public List<SecspepAdvertisingVo> querySecspepAdvertisingList(Pagination<SecspepAdvertisingVo> pagination);

}
