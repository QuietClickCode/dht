
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsType;
import java.util.Map;
/**
 * 描述：部门人员表Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-27 16:32:45
 */
public interface GoodsTypeService {

	/**
	 * 添加部门人员表
	 * @param goodsType
	 * @return
	 * @author zhongp
	 * @date 2017-09-27 16:32:45
	 */
	public boolean saveGoodsType(GoodsType goodsType);
	/**
	 * 编辑部门人员表
	 * @param goodsType
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateGoodsType(GoodsType goodsType);
	/**
	 * 根据id查询部门人员表
	 * @param gtId
	 * @return goodsType
	 * @author zhongp
	 * @date 2017-09-27 16:32:45
	 */
	public GoodsType queryGoodsTypeByGtId(Long gtId);
	/**
	 * 查询部门人员表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-09-27 16:32:45
	 */
	public Pagination<GoodsType> queryGoodsTypeList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gtId删除部门人员表
	 * @param gtId
	 * @return
	 * @author zhongp
	 * @date 2017-09-27 16:32:45
	 */
	public boolean deleteGoodsTypeByGtId(Long gtId);

}


