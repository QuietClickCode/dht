
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.ReturnList;
import java.util.Map;
/**
 * 描述：返现类型表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-01-13 10:29:40
 */
public interface ReturnListService {

	/**
	 * 添加返现类型表
	 * @param returnList
	 * @return
	 * @author fanghui
	 * @date 2018-01-13 10:29:40
	 */
	public boolean saveReturnList(ReturnList returnList);
	/**
	 * 编辑返现类型表
	 * @param returnList
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateReturnList(ReturnList returnList);
	/**
	 * 根据id查询返现类型表
	 * @param rtId
	 * @return returnList
	 * @author fanghui
	 * @date 2018-01-13 10:29:40
	 */
	public ReturnList queryReturnListByRtId(Long rtId);
	/**
	 * 查询返现类型表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2018-01-13 10:29:40
	 */
	public Pagination<ReturnList> queryReturnListList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据rtId删除返现类型表
	 * @param rtId
	 * @return
	 * @author fanghui
	 * @date 2018-01-13 10:29:40
	 */
	public boolean deleteReturnListByRtId(Long rtId);

}


