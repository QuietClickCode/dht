package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsClassification;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：部门人员表DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-27 16:28:01
 */
public interface GoodsClassificationMapper {

	/**
	 * 添加部门人员表
	 * @param goodsClassification
	 * @return
	 * @author zhongp
	 * @date 2017-09-27 16:28:01
	 */
	public int saveGoodsClassification(GoodsClassification goodsClassification);
	/**
	 * 编辑部门人员表
	 * @param goodsClassification
	 * @return
	 * @author zhongp
	 * @date 2017-09-27 16:28:01
	 */
	public int updateGoodsClassification(GoodsClassification goodsClassification);
	/**
	 * 根据GgId删除部门人员表
	 * @param ggId
	 * @return
	 * @author zhongp
	 * @date 2017-09-27 16:28:01
	 */
	public int deleteGoodsClassificationByGgId(Long ggId);
	/**
	 * 根据GgId查询部门人员表
	 * @param ggId
	 * @return
	 * @author zhongp
	 * @date 2017-09-27 16:28:01
	 */
	public GoodsClassification queryGoodsClassificationByGgId(Long ggId);
	/**
	 * 查询部门人员表列表
	 * @param pagination 分页对象
	 * @return  部门人员表列表
	 * @author zhongp
	 * @date 2017-09-27 16:28:01
	 */
	public List<GoodsClassification> queryGoodsClassificationList(Pagination<GoodsClassification> pagination);

}
