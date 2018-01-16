package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.ReturnList;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：返现类型表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-01-13 10:29:40
 */
public interface ReturnListMapper {

	/**
	 * 添加返现类型表
	 * @param returnList
	 * @return
	 * @author fanghui
	 * @date 2018-01-13 10:29:40
	 */
	public int saveReturnList(ReturnList returnList);
	/**
	 * 编辑返现类型表
	 * @param returnList
	 * @return
	 * @author fanghui
	 * @date 2018-01-13 10:29:40
	 */
	public int updateReturnList(ReturnList returnList);
	/**
	 * 根据RtId删除返现类型表
	 * @param rtId
	 * @return
	 * @author fanghui
	 * @date 2018-01-13 10:29:40
	 */
	public int deleteReturnListByRtId(Long rtId);
	/**
	 * 根据RtId查询返现类型表
	 * @param rtId
	 * @return
	 * @author fanghui
	 * @date 2018-01-13 10:29:40
	 */
	public ReturnList queryReturnListByRtId(Long rtId);
	/**
	 * 查询返现类型表列表
	 * @param pagination 分页对象
	 * @return  返现类型表列表
	 * @author fanghui
	 * @date 2018-01-13 10:29:40
	 */
	public List<ReturnList> queryReturnListList(Pagination<ReturnList> pagination);

	/**
	 * 取得所有的返现列表
	 * @return
	 */
	public List<ReturnList> queryAllReturnListList();

}
