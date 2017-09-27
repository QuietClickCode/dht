package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsType;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：部门人员表DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-27 16:32:45
 */
public interface GoodsTypeMapper {

	/**
	 * 添加部门人员表
	 * @param goodsType
	 * @return
	 * @author zhongp
	 * @date 2017-09-27 16:32:45
	 */
	public int saveGoodsType(GoodsType goodsType);
	/**
	 * 编辑部门人员表
	 * @param goodsType
	 * @return
	 * @author zhongp
	 * @date 2017-09-27 16:32:45
	 */
	public int updateGoodsType(GoodsType goodsType);
	/**
	 * 根据GtId删除部门人员表
	 * @param gtId
	 * @return
	 * @author zhongp
	 * @date 2017-09-27 16:32:45
	 */
	public int deleteGoodsTypeByGtId(Long gtId);
	/**
	 * 根据GtId查询部门人员表
	 * @param gtId
	 * @return
	 * @author zhongp
	 * @date 2017-09-27 16:32:45
	 */
	public GoodsType queryGoodsTypeByGtId(Long gtId);
	/**
	 * 查询部门人员表列表
	 * @param pagination 分页对象
	 * @return  部门人员表列表
	 * @author zhongp
	 * @date 2017-09-27 16:32:45
	 */
	public List<GoodsType> queryGoodsTypeList(Pagination<GoodsType> pagination);

}
