
package com.retailers.dht.common.service;
import com.retailers.dht.common.vo.SecspepAdvertisingVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.SecspepAdvertising;
import java.util.Map;
/**
 * 描述：特价广告表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-23 10:19:14
 */
public interface SecspepAdvertisingService {

	/**
	 * 添加特价广告表
	 * @param secspepAdvertising
	 * @return
	 * @author fanghui
	 * @date 2017-11-23 10:19:14
	 */
	public boolean saveSecspepAdvertising(SecspepAdvertising secspepAdvertising);
	/**
	 * 编辑特价广告表
	 * @param secspepAdvertising
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateSecspepAdvertising(SecspepAdvertising secspepAdvertising);
	/**
	 * 根据id查询特价广告表
	 * @param saId
	 * @return secspepAdvertising
	 * @author fanghui
	 * @date 2017-11-23 10:19:14
	 */
	public SecspepAdvertising querySecspepAdvertisingBySaId(Long saId);
	/**
	 * 查询特价广告表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-11-23 10:19:14
	 */
	public Pagination<SecspepAdvertisingVo> querySecspepAdvertisingList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据saId删除特价广告表
	 * @param saId
	 * @return
	 * @author fanghui
	 * @date 2017-11-23 10:19:14
	 */
	public boolean deleteSecspepAdvertisingBySaId(Long saId);

}


