
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsCgfrel;
import java.util.Map;
/**
 * 描述：地区与运费关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-31 10:49:29
 */
public interface GoodsCgfrelService {

	/**
	 * 添加地区与运费关系表
	 * @param cids,gfId
	 * @return
	 * @author fanghui
	 * @date 2017-10-31 10:49:29
	 */
	public boolean saveGoodsCgfrel(String cids,Long gfId);
	/**
	 * 编辑地区与运费关系表
	 * @param goodsCgfrel
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsCgfrel(GoodsCgfrel goodsCgfrel);
	/**
	 * 根据id查询地区与运费关系表
	 * @param cgfId
	 * @return goodsCgfrel
	 * @author fanghui
	 * @date 2017-10-31 10:49:29
	 */
	public GoodsCgfrel queryGoodsCgfrelByCgfId(Long cgfId);
	/**
	 * 查询地区与运费关系表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-31 10:49:29
	 */
	public Pagination<GoodsCgfrel> queryGoodsCgfrelList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据cgfId删除地区与运费关系表
	 * @param cgfId
	 * @return
	 * @author fanghui
	 * @date 2017-10-31 10:49:29
	 */
	public boolean deleteGoodsCgfrelByCgfId(Long cgfId);

}


