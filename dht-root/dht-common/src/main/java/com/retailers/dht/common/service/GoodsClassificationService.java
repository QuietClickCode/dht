
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsClassification;
import java.util.Map;
/**
 * 描述：部门人员表Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-27 16:28:01
 */
public interface GoodsClassificationService {

	/**
	 * 添加部门人员表
	 * @param goodsClassification
	 * @return
	 * @author zhongp
	 * @date 2017-09-27 16:28:01
	 */
	public boolean saveGoodsClassification(GoodsClassification goodsClassification);
	/**
	 * 编辑部门人员表
	 * @param goodsClassification
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateGoodsClassification(GoodsClassification goodsClassification);
	/**
	 * 根据id查询部门人员表
	 * @param ggId
	 * @return goodsClassification
	 * @author zhongp
	 * @date 2017-09-27 16:28:01
	 */
	public GoodsClassification queryGoodsClassificationByGgId(Long ggId);
	/**
	 * 查询部门人员表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-09-27 16:28:01
	 */
	public Pagination<GoodsClassification> queryGoodsClassificationList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据ggId删除部门人员表
	 * @param ggId
	 * @return
	 * @author zhongp
	 * @date 2017-09-27 16:28:01
	 */
	public boolean deleteGoodsClassificationByGgId(Long ggId);

}


